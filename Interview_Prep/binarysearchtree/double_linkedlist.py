#!/usr/bin/python

class double_list_node:
    def __init__(self):
        self.data = None
        self.prev = None
        self.next = None

class double_linked_list:
    def __init__(self):
        self.head = None

    def insert(self, data):
        new_node = double_list_node()
        new_node.data = data
        if not self.head:
            self.head = new_node
        else:
            curr = self.head
            while curr.next:
                curr = curr.next
            temp = curr
            curr.next = new_node
            curr.next.prev = temp

    def print_list(self, head=None):
        prev = None
        if not head:
            curr = self.head
        else:
            curr = head
        while curr:
            print curr.data
            prev = curr
            curr = curr.next


        
        while prev:
            print prev.data,
            prev = prev.prev
        

"""
list_obj = double_linked_list()
for i in xrange(1,10):
    list_obj.insert(i)
list_obj.print_list()
"""