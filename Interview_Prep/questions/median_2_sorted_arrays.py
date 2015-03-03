#!/usr/bin/python


def median(a1,a2):
    if not len(a1) == len(a2):
        return "Invalid Input"
    elif len(a1) == 2 and len(a2) == 2:
        med = (max(a1[0],a2[0])+min(a1[1],a2[1]))/2
        return med
    else:
        if len(a1) % 2 == 0:
            mid = len(a1)/2
            mid-=1
            m1 = (a1[mid] + a1[mid+1])/2
            m2 = (a2[mid] + a2[mid+1])/2
        else:
            mid = len(a1)/2
            m1 = a1[mid]
            m2 = a2[mid]
        if m1 == m2:
            return a1[mid]
        elif m1 > m2:
            if len(a1)%2 == 0:
                return median(a1[0:mid+2],a2[mid:len(a2)])
            else:
                return median(a1[0:mid+1],a2[mid:len(a2)])
        else:
            if len(a1)%2 == 0:
                print a1[mid:len(a1)]
                print a2[0:mid+2]
                return median(a1[mid:len(a1)],a2[0:mid+2])
            else:
                return median(a1[mid:len(a1)],a2[0:mid+1])

if __name__=="__main__":
    #a1 = [1, 2, 3, 6]
    #a2 = [4, 6, 8, 10]
    a1 = [1, 12, 15, 26, 38]
    a2 = [2, 13, 17, 30, 45]
    print median(a1,a2)
    