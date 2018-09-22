import threading
import math

def hilo(a,i,j):
    a[j]=a[j]+a[j-pow(2,i-1)]

def main():
    print("\t------------------  Suma CREW  -----------------\n")
    a=[0,5,2,10,1,8,12,7,3]
    print("\n\tVector Original: ",a,"\n")
    n=len(a)
    lg=int(math.log(n,2))

    for i in range(1,lg-1):
        for j in range((pow(2,i-1)+1),n):
            h = threading.Thread(target=hilo,args=(a,i,j))
            h.start()
            h.join()
            print("\t",a)

    print ("\n\tResultado: ", a[len(a)-1]) 

main()
