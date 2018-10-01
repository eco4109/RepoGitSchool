import java.io.*;
import java.util.Scanner;

public class eha{
	public static void print(String string){
		System.out.println(string);
	}

	public static void main(String[] arg){

		System.out.println("\n\t--------------- Deducci\u00f3n de mascota ---------------");
		System.out.println("\n\tEmpezemos ... ... \n\n\n");
		//Fase 1 CUERPO
		Scanner lectura = new Scanner(System.in);
		String opcion;

		do{
			System.out.println("\tDe que est\u00e1 recubierto su cuerpo?: \n");
			System.out.println("\t1.- Pelaje.");
			print("\t2.- Escamas.");
			print("\t3.- Plumas.");
			print("\t4.- Piel humeda.");
			print("\t5.- Escamas");
			print("\n\t6.- Salir.");
			print("\tElige una opción: ");

			opcion = lectura.nextLine(); //Se lee la opcion del men� principal
			if ((opcion.equals("1")==false) && (opcion.equals("2")==false) && (opcion.equals("3")==false) && (opcion.equals("4")==false) &&(opcion.equals("5")==false)) {
				System.out.println('\7');
				print("¡ERROR! opción incorrecta");	
			} //Tocar campanita si se introuce una opci�n incorrecta
		}while((opcion!="6"));

			

	}
}