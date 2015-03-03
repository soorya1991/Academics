#!/usr/bin/python

a="geeksforgeeks"
new_str = []
map = {}
for c in a:
    if not c in map:
        map[c] = None
        new_str.append(c)
a="".join(new_str)
print a