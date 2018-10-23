import java.io.*;
import java.nio.*;
import java.util.*;

public class cola{
	static final LinkedList cola = new LinkedList(); //Definición de la cola

	public static void encolar(int a){ //Inserta en la cola
		cola.addFirst(a);
	}

	public static int desencolar(){ //Remover un elemento de a cola
		return (int)cola.removeLast();
	}

	public static void ordenar(){ //Ordena la cola de menor a mayor (enteros)
		Collections.sort(cola);
	}

	public static void mostrar(){ //Muestra la cola
		System.out.println(cola);
	}

	public static void main(String[] args) {
		//1 = 1er elemento
		//10 = ultimo elemento
		encolar(1);
		encolar(2);
		encolar(3);
		encolar(4);
		encolar(5);
		encolar(6);
		encolar(7);
		encolar(8);
		encolar(9);
		encolar(0);
		ordenar();
		mostrar();

		System.out.println("Programa terminado, presionar una tecla ...");
		new java.util.Scanner(System.in).nextLine();
	}
}