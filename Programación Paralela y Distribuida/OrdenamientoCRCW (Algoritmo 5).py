#Ordenamiento CRCW

import math
import _thread

def SurtCRCW(L,win,n,L2):
    for i in range(n):
        win[i]  = 0
    print ("\n\tpaso 1: ", win)

    for i in range(n):
        for j in range(n):
            if L[i] > L[j]:
                win[i] = win[i]+1
    print ("\n\tPaso 2: ", win)

    for i in range(n):
        L2[win[i]] = L[i]
    print ("\n\tVector Ordenado: ", L2)


def main():
    print ("\n\t---------------  Ordenamiento CRCW  ---------------")
    L= [95,10,6,15]
    win = [9,9,9,9]
    L2 = [0,0,0,0]
    n=4

    print ("\n\tVector Original: ", L)

    _thread.start_new_thread(SurtCRCW,(L,win,n,L2))

main()
