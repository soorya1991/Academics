#!/usr/bin/python


a = [1, 2, 4, 6, 3, 7, 8]
#using formula n*(n+1)/2
actual_sum = 8*9/2
present_sum = 0

for i in a:
    present_sum+=i

missing_num = actual_sum  - present_sum
print missing_num


#using xor

xor_sum = 0

for i in range(1,9):
    xor_sum = xor_sum ^ i
for i in a:
    xor_sum = xor_sum ^ i
print xor_sum

