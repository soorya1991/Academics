#!/usr/bin/python

"""
Implementation of Queue using Singly Linked List
Functions:
enqueue
dequeue
is_empty
"""
class Queue_node:
    def __init__(self):
        self.data = None
        self.next = None


class Queue:
    def __init__(self):
        self.head = None
        self.tail = None

    def enqueue(self, data):
        new_node = Queue_node()
        new_node.data = data
        if not self.head:
            self.head = new_node
        else:
            if not self.tail:
                self.tail = self.head
            else:
                self.tail.next = new_node
                self.tail = self.tail.next

    def is_empty(self):
        if not self.head:
            return True
        else:
            return False

    def dequeue(self):
        if not self.is_empty():
            temp = self.head
            self.head = self.head.next
            return temp.data
        else:
            return "Queue Empty"

queue_obj = Queue()
for i in range(5):
    queue_obj.enqueue(i)
print queue_obj.head.data
print queue_obj.tail.data
print queue_obj.dequeue()
