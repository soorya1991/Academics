#!/usr/bin/python


def list_powerset(lst):
    # the power set of the empty set has one element, the empty set
    result = [[]]
    count = 0
    for x in lst:
        result.extend([subset + [x] for subset in result])
        if count == 1:
            print result
        count+=1
    return result

list_powerset(['a','b','c'  ])