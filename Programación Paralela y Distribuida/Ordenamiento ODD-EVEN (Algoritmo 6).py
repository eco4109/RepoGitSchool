
from threading import Thread
import os
import math


#Definicion de Funciones
def hilo3(L2,INI,FIN):
    oddEvenMerge(L2,INI,FIN)
    print ("procesos: ",L2[1:FIN+1])
 

def OddEvenMerge(L2,INI,FIN):
    t3=Thread(target=oddEvenMerge,args=(L2,INI,FIN))
    t3.start()
    t3.join()

def oddEvenMerge(L2,INI,FIN):
    m=(FIN-INI)+1
    odd=[0 for _ in range(int((m // 2))+1)]
    even=[0 for _ in range(int((m // 2))+1)]
    if(m==2):
        if(int(L2[INI]) > int(L2[FIN])):
            intercambio(L2,INI,FIN)
    else:
        oddEvenSplit(L2,odd,even,INI,m)
        t=Thread(target=ordena,args=(odd,(m//2)))
        t.start()
        t.join()
        t2=Thread(target=ordena,args=(even,(m//2)))
        t2.start()
        t2.join()
     
        i=1
        while(i<=(m//2)):
            t=Thread(target=mezcla,args=(L2,odd,even,i,1))
            t.start()
            t.join()
            i=i+1
         
        i=1
        while(i<int(m//2)):
            t=Thread(target=HiloOddEven,args=(L2,i))
            t.start()
            t.join()
            i=i+1
        i=1
        while(i<=int(m//2)):
            t=Thread(target=HiloOddEvenC,args=(L2,i))
            t.start()
            t.join()
            i=i+1
     

def intercambio(L2,INI,FIN):
    aux=L2[INI]
    L2[INI]=L2[FIN]
    L2[FIN]=aux


def oddEvenSplit(L2,odd,even,INI,FIN):
    od=1
    ev=1
    x=INI
    while(x<=FIN):
        if((x%2)==0):
            even[ev]=L2[x]
            ev=ev+1
        else:
            print("Segun es aqui: ")
            print("L2")
            print (L2, x)
            print("odddddd")
            print(odd,od)
            
            odd[od]=L2[x]
            od=od+1
        x=x+1
    print ("\n\tOdd = ",odd[1:FIN+1])
    print ("\n\tEven = ",even[1:FIN+1])

 
def ordena(L2,FIN):
    L3=L2
    numero=FIN
    OddEvenMerge(L3,1,numero)


def mezcla(L2,odd,even,j,aux):
    m=L2
    impar=odd
    par=even
    m[(2*j)-1]=impar[j]
    m[2*j]=par[j]


def HiloOddEven(num,i):
    numero=num
    j=i
    a=(2*j)
    b=(2*j)+1
    if(numero[a]>numero[b]):
        intercambio(numero,a,b)

def HiloOddEvenC(num,i):
    numero=num
    j=i
    a=(2*j)-1
    b=(2*j)
    if(numero[a]>numero[b]):
        intercambio(numero,a,b)



#Programa Principal
print ("\n\t -------------------------  ORDENAMIENTO ODD-EVEN  -------------------------")
n=int(input("\n\tCuantos valores desea ordenar? (numero par): "))
while((n%2)!=0):
    n=int(input("\n\tERROR, el numero no es par, Ingrese un numero de valores PAR: "))

L=[0 for _ in range(n+1)]
 
i=0
while(i<n):
    print("\n\tIngrese el valor ",i+1,": ")
    x=input()
    L[i+1]=x
    i=i+1
 
print ("\tValores Iniciales: ",L[1:n+1])
OddEvenMerge(L,1,n)

print ("\n\tValores Ordenados: ",L[1:n+1])
