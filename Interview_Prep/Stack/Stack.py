#!/usr/bin/python

"""
Implementation of Stack using Singly Linked List
Functions:
push
pop
is_empty
"""
class stack_node:
    def __init__(self):
        self.data = None
        self.next = None

class Stack:
    def __init__(self):
        self.head = None
        self.tail = None

    def push(self, data):
        new_node = stack_node()
        new_node.data = data
        if not self.head:
            self.head = new_node
        else:
            new_node.next = self.head
            self.head = new_node

    def is_empty(self):
        if not self.head:
            return True
        else:
            return False

    def pop(self):
        if not self.is_empty():
            temp = self.head
            self.head = self.head.next
            return temp.data
        else:
            return "Stack Empty"

stack_obj = Stack()
for i in range(5):
    stack_obj.push(i)
print stack_obj.head.data
print stack_obj.pop()
print stack_obj.pop()
print stack_obj.is_empty()
print stack_obj.head.data