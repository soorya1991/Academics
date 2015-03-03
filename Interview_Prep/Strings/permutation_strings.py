#!/usr/bin/python

count = 0
def permute(a,k, n):
    if k==n:
        global count
        count+=1
        print a
    else:
        i = k
        while i < n:
            a[k],a[i] = a[i],a[k]
            permute(a, k+1, n)
            a[k],a[i] = a[i],a[k]
            i+=1

a=[1,2,3,4]
permute(a,0,4)
print count

