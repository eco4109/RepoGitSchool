import java.io.*;
import java.nio.*;
import java.util.*;

public class cola{
	static int max = 10; //Tamaño máximo de la cola
	static int[] cola = new int[max]; //Definición de la cola circular de tamaño "n"
	static int inicioC, finC; //Indicadores del inicio y final de la cola

	public static void inicializarCola(){ //Fución para iniciar la cola y los apuntadores
		inicioC = -1;
		finC = -1;
		System.out.println("\n\nCola inicializada, presiona para continuar ... ...\n");
		new java.util.Scanner(System.in).nextLine();
	}

	public static void insertarCola(int dato){ //Inserta en la cola un entero
		if((finC == max-1 && inicioC == 0) || (finC+1==inicioC)){
			System.out.println("\n¡COLA LLENA, TERMINA PROGRAMA!\n");
			System.exit(0);
		}
		if(finC==max-1 && inicioC!=0){
			finC = 0;
		}else{
			finC++;
		}

		cola[finC] = dato;
		System.out.println("Dato insertado correctamente: "+dato);

		
		if(inicioC==-1){
			inicioC = 0;
		}
	}

	public static void removerCola(){ //Remover un elemento de a cola
		if(inicioC==-1) {
			System.out.println("\n¡COLA VACIA!\n");
			return;
		}
		System.out.println("Dato eliminado correctamente: "+cola[inicioC]);
		if(inicioC==finC){
			inicioC=-1;
			finC=-1;
			return;
		}
		if(inicioC==max){
			inicioC=0; 
		}else {
			inicioC++;
		}
	}


	public static void mostrarCola(){ //Muestra la cola
		if(inicioC == -1 ){
			System.out.println("\n¡COLA VACIA!\n");
		}else{
			int i=inicioC;
			System.out.print("\n\nVolcado de la Cola: [");
			do {
				System.out.print(" "+cola[i]+" ");
				i++;
				if(i==max && inicioC>finC){
					i=0; // Reiniciar en cero (dar la vuelta)	
				} 
			}while(i!=finC+1);
			System.out.println("]");
			//System.out.println("Inicio: "+inicioC);
			//System.out.println("Fin: "+fin);
			//System.out.println("Max: "+max);
			System.out.println("\nCola volcada, presiona para continuar ... ...\n");
			new java.util.Scanner(System.in).nextLine();
		}
	}



	public static void main(String[] args) {
		inicializarCola();
		insertarCola(1);
		insertarCola(2);
		insertarCola(3);
		insertarCola(4);
		insertarCola(5);
		insertarCola(6);
		insertarCola(7);
		insertarCola(8);
		insertarCola(9);
		insertarCola(10);
		mostrarCola();
		removerCola();
		insertarCola(11);
		mostrarCola();
		System.out.println("Programa terminado, presionar una tecla ... ...");
		new java.util.Scanner(System.in).nextLine();
	}
}