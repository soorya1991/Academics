#!/usr/bin/python

import Queue

class binarytreenode:
    def __init__(self):
        self.data = None
        self.left = None
        self.right =None


class binarysearchtree:
    """
    Implemantation of binary search operations
    """
    def __init__(self,data):
        self.root = binarytreenode()
        self.root.data = data

    def insert(self,root,data):
        if not root:
            root = binarytreenode()
            root.data = data
            return root
        elif data < root.data:
            root.left = self.insert(root.left,data)
        elif data > root.data:
            root.right = self.insert(root.right,data)
        return root

    def inorder_traversal(self,root):
        if root:
            self.inorder_traversal(root.left)
            print root.data
            self.inorder_traversal(root.right)

    def preorder_traversal(self,root):
        if root:
            print root.data
            self.inorder_traversal(root.left)
            self.inorder_traversal(root.right)

    def find(self,root,x):
        if not root:
            return False
        if x == root.data:
            return True
        elif x < root.data:
            return self.find(root.left,x)
        elif x > root.data:
            return self.find(root.right,x)

    def find_min(self,root):
        if not root:
            return False
        if root.left is not None:
            return self.find_min(root.left)
        else:
            return root

    def find_max(self,root):
        if not root:
            return False
        if root.right is not None:
            return self.find_max(root.right)
        else:
            return root

    def delete_node(self,root,delete_data):
        if not root:
            return
        if delete_data == root.data:
            if not root.left and not root.right:
                return None
            elif root.left and not root.right:
                return root.left
            elif root.right and not root.left:
                return root.right
            elif root.left and root.right:
                min_node = self.find_min(root.right)
                root.data = min_node.data
                root.right = self.delete_node(root.right, root.data)
                """
                max_node = self.find_max(root.left)
                root.data = deepcopy(max_node.data)
                if root.left.left:    
                    root.left.right = deepcopy(max_node.left)
                max_node.left = None
                max_node.right = None
                #root.left.right = max_node.left
                """
                return root
        elif delete_data < root.data:
            root.left = self.delete_node(root.left, delete_data)
        elif delete_data > root.data:
            root.right = self.delete_node(root.right, delete_data)
        return root
    
    def LCA(self,root,a,b):
        """
        LCA based on preorder traversal approach.
        Basically used for finding LCA of binary trees
        """
        if root:
            if root.data == a or root.data == b:
                return 1
            left = self.LCA(root.left,a,b)
            right = self.LCA(root.right,a,b)
            if left == 1 and right == 1:
                return root
            else:
                if left == 1:
                    return left
                else:
                    return right

    def LCA_binarytree(self, root, a, b):
        """
        Finding LCA based on the binary tree search proprety
        """
        if root:
            if (a < root.data and b < root.data):
                self.LCA(root.left,a, b)
            elif (a > root.data and b > root.data):
                self.LCA(root.right,a,b)
            return root

    def is_bst(self,root):
        if not root:
            return None
        if not root.left and not root.right:
            return root
        left = self.is_bst(root.left)
        if left and left.data > root.data:
            return 0
        right = self.is_bst(root.right)
        if right and right.data < root.data:
            return 0
        return right if right else root

    def height_iterative(self, root):
        height = 0
        q = Queue.Queue()
        q.put(root)
        while True:
            n = q.qsize()
            while n > 0:
                root = q.get()
                if root.left:
                    q.put(root.left)
                if root.right:
                    q.put(root.right)
                n-=1
            if q.empty():
                break
            height+=1
        print height

def constructbstfromarray(arr, start, end):
    if start > end:
        return None
    else:
        mid = (start+end)/2
        #print mid
        root = binarytreenode()
        root.data = arr[mid]
        root.left = constructbstfromarray(arr,start,mid-1)
        root.right = constructbstfromarray(arr,mid+1,end)
    return root


root = binarysearchtree(5)
root.insert(root.root,8)
root.insert(root.root,2)
root.insert(root.root,100)
root.insert(root.root,1)
root.insert(root.root,7)
root.height_iterative(root.root)
#print root.is_bst(root.root).data
"""
obj = binarysearchtree(None)
arr = [1,2,3,4,5,6,7,8,9,10]
result = constructbstfromarray(arr,0,len(arr)-1)
obj.inorder_traversal(result)
"""