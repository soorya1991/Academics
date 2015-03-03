#!/usr/bin/python

from double_linkedlist import double_linked_list,double_list_node
from copy import deepcopy
class binarytreenode:
    def __init__(self):
        self.data = None
        self.left = None
        self.right =None
head = None
prev = None
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

    def bsttodll(self,root):
        """
        global head
        if not root:
            return double_list_node()
        if not root.left and not root.right:
            new_node = double_list_node()
            new_node.data = root.data
            return new_node
        node = self.bsttodll(root.left)
        new_node = double_list_node()
        new_node.data = root.data
        if not head:
            head=node
        else:
            node.next = new_node
            new_node.prev = node
        node = self.bsttodll(root.right)
        if not head:
            head=node
        else:
            new_node.next = node
            node.prev = new_node
        return node
        """
        global head,prev
        if root:
            self.bsttodll(root.left)
            new_node = double_list_node()
            new_node.data = root.data
            if not head:
                head  = new_node
                prev = head
            else:
                prev.next = new_node
                new_node.prev = prev
                prev = new_node
            self.bsttodll(root.right)

root = binarysearchtree(5)
root.insert(root.root,8)
root.insert(root.root,2)
root.insert(root.root,100)
root.insert(root.root,1)
root.insert(root.root,7)
root.bsttodll(root.root)
print "Inorder Traversal"
root.inorder_traversal(root.root)
list_obj = double_linked_list()
list_obj.print_list(head)
"""
print "Inorder Traversal"
root.inorder_traversal(root.root)
print "Find Result"
print root.find(root.root,12)
print "Preorder"
root.preorder_traversal(root.root)
print "Find Minimum"
print root.find_min(root.root)
print "Find Maximum"
print root.find_max(root.root).data
#root.delete_node(root.root, 8)
root.inorder_traversal(root.root)
print root.LCA(root.root,2,7).data
print root.LCA_binarytree(root.root,2,7).data
"""