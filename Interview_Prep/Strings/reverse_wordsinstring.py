#!/usr/bin/python

a="I am Soorya"
new_str = ""
temp = ""
for i in range(len(a)-1):
    temp+=a[i]
    if a[i] == ' ':
        #print temp
        k=len(temp)-1
        while k >= 0:
            new_str+=temp[k]
            k-=1
            #print new_str
        new_str+=" "
        temp=""

print new_str
    