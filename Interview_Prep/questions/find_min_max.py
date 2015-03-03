#!/usr/bin/python

"""
Implementation of find min and max using less number of comparisons
find_min_max --> compare in pairs
find_min_max_tournament --> divide and conquer approach(tournament method)
"""
import sys
def find_min_max(a):
    if not len(a) > 1:
        return "Invalid input",None
    if len(a) == 2:
        min,max = None
        if a[0] > a[1]:
            max = a[0]
            min = a[1]
        else:
            max = a[1]
            min = a[0]
        return min,max
    if len(a)%2 == 0:
        if a[0] < a[1]:
            min = a[0]
            max = a[1]
        else:
            min = a[1]
            max = a[0]
        i = 2
    else:
        min,max = a[0],a[0]
        i=1
    while i < len(a)-1:
        if a[i] > a[i+1]:
            if a[i] > max:
                max = a[i]
            if a[i+1] < min:
                min = a[i+1]
        else:
            if a[i+1] > max:
                max = a[i+1]
            if a[i] < min:
                min = a[i]
        i+=2
    return min,max

min,max = sys.maxint, -sys.maxint
def find_min_max_tournament(a,low,high):
    global min,max
    if low == high:
        min,max = a[low],a[low]
        return min,max
    if (high-low) == 1:
        if a[low] < a[high]:
            if a[low] < min:
                min = a[low]
            if a[high] > max:
                max = a[high]
        else:
            if a[high] < min:
                min = a[high]
            if a[low] > max:
                max = a[low]
        return min,max
    else:
        mid = (low+high)/2
        left = find_min_max_tournament(a,low,mid)
        right = find_min_max_tournament(a,mid+1,high)
        if left[0] < right[0]:
            min = left[0]
        else:
            min = right[0]
        if left[1] > right[1]:
            max = left[1]
            
        else:
            max = right[1]
        return min,max



a = [1000, 11, 445, 1, 330, 3000, 0, 10000]
print find_min_max(a)
"""
find_min_max_tournament(a,0,5)
print min
print max
"""