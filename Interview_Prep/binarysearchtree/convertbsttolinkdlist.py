#!/usr/bin/python

from copy import deepcopy
class binarytreenode:
    def __init__(self):
        self.data = None
        self.left = None
        self.right =None

class linkedlistnode:
    def __init__(self):
        self.data = None
        self.next = None

class linked_list:
    def __init__(self):
        self.head = None

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

    def convertbsttolinklst(self,root,head):
        if not root:
            return None
        if root.left == None or root.right == None:
            return root
        left = self.convertbsttolinklst(root.left,head)
        if not head:
            head = linkedlistnode()
            head.data = left.data
            prev = head
        new_node = linkedlistnode()
        new_node.data = root.data
        prev.next = new_node
        prev = prev.next
        right = self.convertbsttolinklst(root.right,head)
        new_node = linkedlistnode()
        new_node.data = right.data
        prev.next = new_node
        prev = prev.next
        return right



root = binarysearchtree(5)
root.insert(root.root,8)
root.insert(root.root,2)
root.insert(root.root,100)
root.insert(root.root,1)
root.insert(root.root,7)
print "Inorder Traversal"
root.inorder_traversal(root.root)
root.convertbsttolinklst(root.root,None)

