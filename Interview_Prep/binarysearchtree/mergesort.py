#!/usr/bin/python

def mergesort(a,p,r):
    if p <= r:
        q = (p+r)/2
        mergesort(a,p,q-1)
        mergesort(a,q+1,r)
        merge(a,p,q,r)
temp = [None] * 9


def merge(a,p,q,r):
    #temp = []
    i = p
    j = q+1
    k = p
    while i <= q  and j <= r:
        if a[i] < a[j]:
            temp[k] = a[i]
            i+=1
            k+=1
        else:
            temp[k] = a[j]
            j+=1
            k+=1
    while i <= q:
        #temp.append(a[i])
        temp[k] = a[i]
        k+=1
        i+=1
    while j <= r:
        #temp.append(a[j])
        temp[k] = a[j]
        k+=1
        j+=1
    for l in range(p,r+1):
        a[l] = temp[l]


a=[3,12,19,42,8,17,18,36]
mergesort(a,0,len(a)-1)
print a