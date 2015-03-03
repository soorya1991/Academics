#!/usr/bin/python

new_string = ""

def rreverse(s):
    if s == "":
        return s
    else:
        return rreverse(s[1:]) + s[0]

a = raw_input()
print rreverse(a)

