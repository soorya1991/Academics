#!/usr/bin/python

def pattern_table(pattern):
    table = [None] * len(pattern)
    table[0] = 0
    i = 0
    j = 1
    while j < len(pattern):
        if pattern[j] == pattern[i]:
            i+=1
            table[j] = i
            j+=1
        else:
            if not i == 0:
                i-=1
            else:
                table[j] = 0
                j+=1
    return table
    """
    while j < len(pattern):
        if pattern[i] == pattern[j]:
            table[j+1] = table[j] + 1
            i+=1
            j+=1
        elif not pattern[i] == pattern[j]:
            if i == 0:
                table[j+1] = 0
            else:
                i = 0
                if pattern[i] == pattern[j]:
                    table[j] = 1
                else:
                    table[j+1] = 0
            j+=1
    """ 

def string_matching_kmp(str, pattern):
    table = pattern_table(pattern)
    w = len(pattern)
    i = 0
    j = 0 
    while i < len(str):
        while j < w:
            if str[i] == pattern[j]:
                i+=1
                j+=1
            else:
                break
        if j == w:
            return "Found"
        if not j == 0:
            j = table[j-1]
        else:
            i+=1
    return "Not Found"
    





if __name__ == "__main__":
    a = "ABCDABCDABDEFG"
    pattern = "ABCDABD"
    print string_matching_kmp(a, pattern)