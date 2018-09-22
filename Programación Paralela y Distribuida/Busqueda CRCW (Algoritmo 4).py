# Busqueda CRCW (Algoritmo 4)

import math
import _thread

def MinCRCW(array, win, n):

        #Inicializar a WIN
        print("\n\n\t--------- Paso 1 ---------")
        for i in range(n):
                win[i] = 0
        print("\n\tInicializando a win: ",win)

        #Comparaciones de valores de WIN
        print("\n\n\t--------- Paso 2. ---------")
        for i in range(n):
                for j in range(n):
                        if array[i]>array[j]:
                                win[i] = 1
                print("\twin: ",win)
			
        #Busqueda del elemento que contiene un 0 y obtener su indice
        print("\n\n\t--------- Paso 3. ---------")
        for i in range(n):
                if(win[i] == 0):
                        indexMin = i
        print ("\n\tIndice del elemento minimo: ",indexMin)
        print ("\n\tElemento minimo: ",array[indexMin])

def main():
        #Variables
        array = []
        win = []
        i = 1

        print ("\n\n\t------------------  Busqueda CRCW  ------------------\n\n")
        x=int(input("\tCuantos datos tendra el vector?: "))
        while (i<=x):
                n1=int(input("\n\tIngrese el dato : "))
                array.append(n1)
                win.append(0) #El vector auxiliar se inicializa en 0´s
                print("\tVector actualizado: ",array)
                i+=1

        print ("\n\tVector : ",array)
        _thread.start_new_thread(MinCRCW, (array,win,len(win)))

main()
