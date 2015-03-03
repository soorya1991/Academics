#!/usr/bin/python

import Queue

class binarytree_node:
    def __init__(self):
        self.data = None
        self.left = None
        self.right = None

class binary_tree:
    def __init__(self):
        self.root = None

    def insert(self,data):
        new_node = binarytree_node()
        new_node.data = data
        q = Queue.Queue()
        if not self.root:
            self.root = new_node
        else:
            root = self.root
            q.put(root)
            while not q.empty():
                root = q.get()
                if not root.left:
                    root.left=new_node
                    break
                elif not root.right:
                    root.right=new_node
                    break
                if root.left:
                    q.put(root.left)
                if root.right:
                    q.put(root.right)

    def print_inorder(self,root):
        if root:
            self.print_inorder(root.left)
            print root.data,
            self.print_inorder(root.right)

    def print_preorder(self,root):
        if root:
            print root.data,
            self.print_preorder(root.left)
            self.print_preorder(root.right)

    def size(self,root):
        if not root:
            return 0
        else:
            return self.size(root.left)+1+self.size(root.right)

    def height(self,root):
        if not root:
            return 0
        else:
            left = self.height(root.left)
            right = self.height(root.right)
            return max(left+1,right+1)

    def preorder_iterative(self,root):
        if not root:
            print "None"
        else:
            stack = []
            result = []
            node = None
            stack.append(root)
            while stack:
                node = stack.pop()
                result.append(node.data)
                if node.right:
                    stack.append(node.right)
                if node.left:
                    stack.append(node.left)
        print result

    def inorder_iterative(self,root):
        if not root:
            print "None"
        else:
            stack = []
            result = []
            node = root
            while stack or node:
                if node:
                    stack.append(node)
                    node = node.left
                else:
                    node = stack.pop()
                    result.append(node.data)
                    node = node.right
            print result


binary_obj = binary_tree()
for i in range(5):
    binary_obj.insert(i)
#binary_obj.print_preorder(binary_obj.root)
binary_obj.print_inorder(binary_obj.root)
print "\n"
binary_obj.inorder_iterative(binary_obj.root)
#binary_obj.preorder_iterative(binary_obj.root)
#print binary_obj.size(binary_obj.root)
#print binary_obj.height(binary_obj.root)
