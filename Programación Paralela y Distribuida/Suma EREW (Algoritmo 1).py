import math
import _thread

def proceso(i,j,A):
    if(((2*j)%(math.pow(2,i))) == 0):
        A[2*j] = A[2*j] + A[((2*j) - ((int)(math.pow(2,i-1))))]

def main():
    A = [0,5,2,10,1,8,12,7,3]
    n = 8

    log = (int) (math.log(8,2))

    print (A)
    
    for i in range(1,log+1):
        print ("Paso ", i)

        for j in range(1, (int) (n/2) + 1):
            _thread.start_new_thread(proceso, (i,j,A,))
            print (A[1:9])
    print (A[1:9])
    print ("\nResultado: ", A[8])

main()
