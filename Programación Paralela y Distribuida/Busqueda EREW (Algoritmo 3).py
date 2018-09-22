import os
import sys
import math
from threading import Thread

#Definicion de funciones
def hilo1(Temp2,i,j):
    Temp2.insert(j,Temp2[j-(2**i-1)])
 
def hilo2(K,Temp2,x,n):
    if(x<(n-1)):
        if(K[x]==Temp2[x]):
            Temp2[x]=x+1
        else:
            Temp2[x]=99999

def hilo3(Temp2,n,k):
    if(Temp2[(2**(k))-1]>Temp2[2**k]):
        Temp2[k]=Temp2[2*k]
    else:
        Temp2[k]=Temp2[(2**(k))-1]

def Broadcast(Temp,x,n):
    Temp2=[]
    Temp2=Temp
    Temp2.insert(0,numero)
    Temp2.insert(1,numero)
    i=1
    while(i<=lg):
        j=(2**(i-1))+1
        while(j<=2**i):
            t=Thread(target=hilo1,args=(Temp2,i,j))
            t.start()
            t.join()
            j=j+1
        i=i+1
     
def SearchPram(Temp,lista,n):
    i=0
    while(i<=n):
        t=Thread(target=hilo2,args=(lista,Temp,i,n))
        t.start()
        t.join()
        i=i+1

def MinPram(Temp,n):
    i=1
    while(i<=lg):
        j=1
        while(j<=int(n/(2**j))):
            t=Thread(target=hilo3,args=(Temp,n,i))
            t.start()
            t.join()
            j=j+1
        i=i+1




#Programa Principal
print ("\n\n\t------------------  Busqueda EREW  ------------------\n\n")
a=[]
Temp=[]
lista=[]
x=int(input("\tCuantos datos tendra el vector?:"))
i=1
while (i<=x):
    n1=int(input("\n\tIngrese el dato : "))
    lista.append(n1)
    print("\tVector actualizado: ",lista)
    i+=1
n=len(lista)
lg=int(math.log(n,2))

numero=int(input("\n\n\tValor a buscar:"))
if((numero in lista) == False):
    print("\n\tEl numero ",numero ," NO está en el vector ")
    sys.exit()

print ("\n\t------------------------------------------------")
print ("\tVector:")
print ("\t",lista)
Broadcast(Temp,numero,n)
print ("-\n\t------------------------------------------------")
print ("\tVECTOR TEMPORAL: ")
print ("\t",Temp)
SearchPram(Temp,lista,n)
print ("\n\t------------------------------------------------")
print ("\tIndices:")
print ("\t",Temp)
MinPram(Temp,n)
h=1
while(h<len(Temp)):
    if(Temp[h]!=99999):
        pos=Temp[h]
        break
    h=h+1
print ("\n\t------------------------------------------------")
print ("\tEl numero: ",numero," se encontró en la posición: ",pos)
os.system("PAUSE")
