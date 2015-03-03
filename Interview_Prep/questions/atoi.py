#!/usr/bin/python


"""
Implementation of atoi function
"""

def atoi(string):
    res = 0
    for c in string:
        res = res*10 + ord(c) - ord('0')
        print res
    print res

string = "165"
atoi(string)