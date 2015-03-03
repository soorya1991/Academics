#!/usr/bin/python
import sys


def find_max(a):
    mx = -sys.maxint
    mx2 = -sys.maxint
    for i in range(len(a)):
        if a[i] > mx:
            mx2 = mx
            mx = a[i]
        elif a[i] > mx2:
            mx2 = a[i]
    
    print mx,mx2




a=[4,6,3,10,90,1,500,2]
find_max(a)