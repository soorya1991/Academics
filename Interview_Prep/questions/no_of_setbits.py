#!/usr/bin/python

n = 6
count = 0
while not n == 0:
    count+=n & 1
    n = n >> 1

print count
    
