#!/usr/bin/python

"""
Implementation of maximum sub array using
Kadane's Algortihm
"""
a=[-2, -3, 4, -1, -2, 1, 5, -3]

max_pos = a[0]
max_ele = a[0]

for i in a[1:]:
    max_pos = max(i,max_pos + i)
    max_ele = max(max_pos,max_ele)

print max_ele