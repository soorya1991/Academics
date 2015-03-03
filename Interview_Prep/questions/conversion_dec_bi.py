#!/usr/bin/python



def decimal_to_binary(input):
    result = []
    while input > 0:
        result.append(str(input%2))
        input/=2
    return ''.join(reversed(result))

def binary_to_decimal(input):
    sum = 0
    i = 0
    while input:
        if input%10 == 1:
            sum = sum + pow(2,i)
        input = input/10
        i+=1
    return sum

def decimal_to_hex(input):
    result = []
    hex = {10:'A',11:'B',12:'C',13:'D',14:'E',15:'F'}
    while input > 0:
        res = input%16
        if res >=10:
            result.append(str(hex[res]))
        else:
            result.append(str(res))
        input/=16
    return ''.join(reversed(result))

print "1.decimal to binary"
print "2.binary to decimal"
print "3.decimal to hex"
print "Enter the value for the conversion"
input = int(raw_input())
print "Enter the Option"
option = int(raw_input())
options = {1:decimal_to_binary,2:binary_to_decimal,3:decimal_to_hex}
result = options.get(option)(input)
print result
