#!/usr/bin/python

a=[1,2,5,6,7,13,56,100]
def binary_search(a,p,r,value):
    if p > r:
        return None
    else:
        mid = p + ((r-p)/2)
        if (p == r):
            return a[p]
        if a[mid] == value:
            return True
        elif value < a[mid]:
            return binary_search(a,p,mid-1,value)
        else:
            return binary_search(a,mid+1,r,value)
    


print binary_search(a,0,len(a)-1,11)