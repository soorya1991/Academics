#!/usr/bin/python

a = [20,None,25,26,None,30,None]
b = [5,8,12,14]
j=None
count = 0
for i in range(len(a)):
    if not a[i]:
        count+=1
    else:
        a[i-count]=a[i]
print count
for i in range(count,len(a)-1):
    a[i] = None
"""
for i in range(len(a)-1):
    if a[i] == None:
        j = i+1
        while not a[j] and j<len(a)-1:
            j+=1
        a[i] = a[j]
        a[j] = None
"""
k = len(a)-1
i = (len(a)-len(b)) - 1
j = len(b)-1

while i >= 0 and j >=0:
    if a[i] > b[j]:
        a[k] = a[i]
        k-=1
        i-=1
    elif b[j] > a[i]:
        a[k] = b[j]
        k-=1
        j-=1
if not j < 0:
    while j >=0:
        a[k] = b[j]
        k-=1
        j-=1
print a