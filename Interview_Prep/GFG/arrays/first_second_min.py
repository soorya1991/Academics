#!/usr/bin/python
import sys
a=[12, 13, 1, 10, 34, 1, 0]

first_min = sys.maxint
second_min = sys.maxint

for i in a:
    if i < first_min:
        second_min = first_min
        first_min = i
    if i < second_min and not i == first_min:
        second_min = i

print first_min
print second_min