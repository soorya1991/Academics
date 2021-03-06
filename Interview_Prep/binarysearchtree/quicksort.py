#!/usr/bin/python

def quicksort(a,p,r):
    if p <= r:
        x = a[r]
        q = partition(a,p,r,x)
        quicksort(a,p,q-1)
        quicksort(a,q+1,r)
    else:
        return

def partition(a,p,r,x):
    i = p
    j = r
    while True:
        while (i<=r and a[i]<= x):
            i+=1
        while (j>=p and a[j]>= x):
            j-=1
        if i<j:
            a[i],a[j] = a[j],a[i]
            i+=1
            j+=1
        else:
            break
    q = j+1
    a[q],a[r] = a[r],a[q]
    return q

a=[3,12,19,42,8,17,18,36]
quicksort(a,0,len(a)-1)
print a