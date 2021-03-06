#!/usr/bin/python


"""
Implementation of Dutch National Flag Problem
Functions
dutch_flag_problem - Segregate 0s,1s,2s
segregate_1_0 - Segregate 0s,1s
"""
def dutch_flag_problem(a):
    low = 0
    mid = 0
    high = len(a) - 1
    while mid <= high:
        if a[mid] == 0:
            a[low],a[mid] = a[mid],a[low]
            mid+=1
            low+=1
        elif a[mid] == 1:
            mid+=1
        else:
            a[mid],a[high] = a[high],a[mid]
            high-=1
    return a

def segregate_1_0(a):
    low = 0
    high = len(a)-1
    while True:
        while low <= len(a)-1 and a[low] == 0:
            low+=1
        while high >= 0 and a[high] == 1:
            high-=1
        if low<high:
            a[low],a[high] = a[high],a[low]
        else:
            break
    return a
    





if __name__ == "__main__":
    #a = [0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1]
    a = [0,2,2,0,1,2,0,2,1]
    b = [0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1]
    print dutch_flag_problem(a)
    print segregate_1_0(b)