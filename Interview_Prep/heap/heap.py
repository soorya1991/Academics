#!/usr/bin/python

"""
Implemented Heap Operations
Funnctions:
build_heap
insert//(percolate_up)
find_min
delete_min
percolate_down
Heap_sort
"""

import sys

class heap:
    def __init__(self,array = None):
        if array:
            self.array = []
            self.array.append(None)
            for i in array:
                self.array.append(i)
            self.current_size = len(self.array)-1
        else:
            self.current_size = 0
            self.array = [None] * 2

    def insert(self, value):
        array_size = len(self.array)
        if self.current_size == array_size - 1:
            for j in xrange(2*array_size):
                self.array.append(None)
        hole = self.current_size + 1
        self.array[0] = value
        while hole/2 > 0:
            if value < self.array[hole/2]:
                self.array[hole] = self.array[hole/2]
            else:
                break
            hole/=2 
        self.array[hole] = value
        self.current_size += 1

    def find_min(self):
        if self.current_size == 0:
            print "Heap Empty"
            sys.exit(1)
        else:
            return self.array[1]

    def delete_min(self):
        if self.current_size == 0:
            print "Heap Empty"
            sys.exit(1)
        else:
            temp = self.array[1]
            self.array[1] = None
            self.percolate_down(1)
            self.current_size -= 1
            self.array.pop()
            return temp

    def build_heap(self):
        i = self.current_size/2
        print self.array
        while i>0:
            self.percolate_down(i)
            i-=1

    def percolate_down(self,index):
        hole = index
        temp = self.array[hole]
        array_size = len(self.array) -1
        while hole <= self.current_size:
            if 2*hole <= array_size and 2*hole+1 <= array_size:
                left_child = self.array[2*hole]
                right_child = self.array[2*hole+1]
                if left_child < right_child:
                    self.array[hole] = left_child
                    hole = 2*hole
                else:
                    self.array[hole] = right_child
                    hole = 2*hole+1
            else:
                break
        if not temp:
            self.array[hole],self.array[self.current_size] = self.array[self.current_size],temp
        else:
            self.array[hole] = temp
        #self.array[hole],self.array[self.current_size] = self.array[self.current_size],temp
        """
        if hole < self.current_size:
            self.array[hole] = self.array[self.current_size]
            self.array[self.current_size] = None
        else:
            self.array[self.current_size] = None
        """
    def heap_sort(self):
        final_array = []
        self.build_heap()
        for i in range(1,len(self.array)):
            tmp = self.delete_min()
            final_array.append(tmp)
        return final_array

heap_obj = heap()
heap_obj.insert(13)
heap_obj.insert(21)
heap_obj.insert(16)
heap_obj.insert(24)
heap_obj.insert(31)
heap_obj.insert(19)
heap_obj.insert(68)
heap_obj.insert(65)
heap_obj.insert(26)
heap_obj.insert(32)
heap_obj.insert(1)
heap_obj.insert(14)
for i in range(1,len(heap_obj.array)):
    if i:
        print heap_obj.array[i],
print heap_obj.find_min()
heap_obj.delete_min()
for i in range(1,len(heap_obj.array)):
    if i:
        print heap_obj.array[i],
a=[60,80,45,20,90,30,35,10,40,15,25]
heap_sort = heap(a)
print "\n\n"
print heap_sort.heap_sort()