from threading import Thread
import math
import os

#Definicion de Funciones



def hilo1(i,j,k):
    C[k][i][j]=int(A[i][k])* int(B[k][j])

def hilo2(i,j,k,l):
    if(((2*k) % (2 **l))==0):
        C2[2*k][i][j]=int(C[2*k][i][j]+C[2*k-(2**(l))][i][j])


#Programa Principal

print "================MULTIPLICACION DE MATRICES CREW==========================="


A=[[0 for _ in range(2)] for _ in range(2)]
B=[[0 for _ in range(2)] for _ in range(2)]
C=[[[0 for _ in range(2)] for _ in range(2)] for _ in range(2)]
C2=[[[0 for _ in range(2)] for _ in range(2)] for _ in range(2)]
   
lg=int(math.log(2,2))
print "\n\n             LLENADO DE LA MATRIZ A:"
i=0
while(i<2):
    j=0
    while(j<2):
       
        print "INGRESE EL VALOR DE LA COORDENADA [",i+1 ,", ",j+1," ]: "
        x=int(raw_input())
        A[i][j]=x
        j=j+1
    i=i+1

print "\n\n             LLENADO DE LA MATRIZ B:"
i=0
while(i<2):
    j=0
    while(j<2):
       
        print "INGRESE EL VALOR DE LA COORDENADA [",i+1 ,", ",j+1," ]: "
        x=int(raw_input())
        B[i][j]=x
        j=j+1
    i=i+1


print "\nPROCEDIMIENTO DE LAS MULTIPLICACIONES : \n"
print "[ ",A[0][0],"  ",A[0][1]," ]      X      [ ",B[0][0],"  ",B[0][1]," ]"
print "[ ",A[1][0],"  ",A[1][1]," ]      X      [ ",B[1][0],"  ",B[1][1]," ]"

k=0
while(k<2):
    i=0
    while(i<2):
        j=0
        while(j<2):
            t=Thread(target=hilo1,args=(i,j,k))
            t.start()
            t.join()
            j=j+1
        i=i+1
    k=k+1


print "\n\nPRIMER PROCESO: ",C

l=0
while(l<lg):
    i=0
    while(i<2):
        j=0
        while(j<2):
            k=0
            while(k<1):
                t=Thread(target=hilo2,args=(i,j,k,l))
                t.start()
                t.join()
                k=k+1
            j=j+1
        i=i+1
    l=l+1


print "\nRESULTADO de LAS MULTIPLICACIONES DE LAS MATRICES A Y B: \n"

print "          [ ",C2[0][0][0],"  ",C2[0][0][1]," ]"
print "          [ ",C2[0][1][0],"  ",C2[0][1][1]," ]"

os.system('pause')