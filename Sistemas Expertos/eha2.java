import java.io.*;
import java.util.Scanner;

public class eha2{
	public static void main(String[] args){
		String opcion; //Opcion del menú principal
		String opcionS;//Opci�n de los menus 1er nivel
		String opcionS2; //Opcion de los menus 2 nivel
		String opcionS3;
        do{        //Ciclo para cargar el men� principal, hasta que se teclee 5
        	Scanner teclado = new Scanner(System.in);
			System.out.println("\t");
			System.out.println("             ----------------- Deducci\u00f3n de mascota ---------------");
			System.out.println("\n           De que esta recubierto su cuerpo?\n");
			System.out.println("                                 1.- Pelaje");
			System.out.println("                                 2.- Escamas");
			System.out.println("                                 3.- Plumas");
			System.out.println("                                 4.- Piel Humeda");
			System.out.println("                                 5.- Escamas");
			System.out.println("\n");
			System.out.println("                                 6.- Salir del menu.");
			System.out.print("               Teclee una opcion: ");

			opcion = teclado.nextLine(); //Se lee la opcion del men� principal
			if ((opcion.equals("1")==false) && (opcion.equals("2")==false) && (opcion.equals("3")==false) && (opcion.equals("4")==false) &&(opcion.equals("5")==false)) {
				System.out.println('\7');
			} //Tocar campanita si se introuce una opci�n incorrecta

			switch(opcion){ //Switch para decidir lo que se har� dependiendo de la opcion elegida
				case "1":     //En caso de que sea la opcion 1, se cargar� el SUBMENU 1, con sus opciones
					do{
						System.out.println("\t");
						System.out.println("\t");
						System.out.println("             -----------------   SUB-MEN\u00da 1   --------------- ");
						System.out.println("\n             Aproximadamente cual es su peso maximo en etapa adulta?\n");
						System.out.println("                                 1.- De 0 a 500g.");
						System.out.println("                                 2.- De 500 a 5Kg.");
						System.out.println("                                 3.- De 5Kg a 70 Kg.");
						System.out.println("                                 4.- Mas de 70Kg.");
						System.out.println("\n");
						System.out.println("                                 5.- Regresar Menu anterior.");
						System.out.print("              Teclee una opcion: ");
						opcionS = teclado.nextLine();
						if ((opcionS.equals("1")==false) && (opcionS.equals("2")==false) && (opcionS.equals("3")==false) && (opcionS.equals("4")==false)) {
							System.out.println('\7');
						}
						switch(opcionS){
							case "1":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             ----------------   SUB-MEN\u00da  1.A  ------------- ");
									System.out.println("\n           Tiene cola?\n");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No");
									System.out.println("\n");
									System.out.println("                                 3.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}
									if(opcionS2.equals("1")==true){
										System.out.println("                         TU MASCOTA ES UN RATON.");
										System.exit(4);
									}else if(opcionS2.equals("2")==true){
										System.out.println("                         TU MASCOTA ES UN HAMSTER O UN CUYO.");
										System.exit(4);
									}
									
								}while(opcionS2.equals("3")!=true);
								break;
							case "2":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             ----------------   SUB-MEN\u00da  1.B  ------------- ");
									System.out.println("\n           Es usual que las personas lo saquen a pasear con correa?\n");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No");
									System.out.println("\n");
									System.out.println("                                 3.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}
									if(opcionS2.equals("1")==true){
										System.out.println("                         TU MASCOTA ES UN PERRO RAZA PEQUEÑA.");
										System.exit(4);
									}else if(opcionS2.equals("2")==true){
										System.out.println("                         TU MASCOTA ES UN GATO O UN HURON O un CONEJO.");
										System.exit(4);
									}
								}while((opcionS2.equals("3")) != true);
							break;
							
							case "3":
								System.out.println("TU MASCOTA ESS UN PERRO RAZA MEDIANA-GRANDE");
								break;
							
							case "4":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             -----------------   SUB-MEN\u00da 1.D   ----------------- ");
									System.out.println("\n           La gente suele comerce a tu mascota?");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
									System.out.println("                                 3.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}else if(opcionS2.equals("1")==true){
										System.out.println("\tTu mascota es un CERDO, OVEJA o una VACA, en algunos lugares un caballo");
										System.exit(4);
									}else if(opcionS2.equals("2")==true){
										System.out.println("\tTu mascota es un BURRO, un CAMELLO, un CABALLO o un PERRO muy grande.");
										System.exit(4);									}
								}while((opcionS2.equals("3")) != true);
								break;
							
						}
					}while((opcionS.equals("5")) != true);
					break;
					
				case "2":
					do{
						System.out.println("\t");
						System.out.println("\t");
						System.out.println("             =================   SUB-MEN\u00da 2   ============== ");
						System.out.println("\t  Tiene Patas?");
						System.out.println("                                 1.- Si.");
						System.out.println("                                 2.- No.");
						System.out.println("\n");
						System.out.println("                                 3.- Regresar al Menu principal.");
						System.out.print("              Teclee una opcion: ");
						opcionS = teclado.nextLine();
						if ((opcionS.equals("1")==false) && (opcionS.equals("2")==false)&& (opcionS.equals("3")==false)) {
							System.out.println('\7');
						}
						switch(opcionS){
							case "1":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             -----------------   SUB-MEN\u00da  2.A  ------------------ ");
									System.out.println("\tSe mueve leeeeeeeeeento?");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
									System.out.println("                                 3.- Regresar Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}else if(opcionS2.equals("1")==true){
										System.out.println("\tTu mascota es UNA TORTUGA");
									}else if(opcionS2.equals("2")==true){
										System.out.println("Tu mascota es UNA IGUANA, LAGARTIJA o un COCODRILO");
									}
								}while((opcionS2.equals("3")) != true);
								break;
							case "2":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             ------------------   SUB-MEN\u00da 2.B   --------------- ");
									System.out.println("\t  Si se mantiene fuera del agua muere?");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
									System.out.println("                                 3.- Regresar  Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}else if(opcionS2.equals("1")==true){
										System.out.println("Tu mascota es UN PEZ");
										System.exit(4);
									}else if(opcionS2.equals("2")==true){
										System.out.println("Tu mascota es UNA SERPIENTE");
										System.exit(4);
									}
								}while((opcionS2.equals("3")) != true);
								break;
						}
					}while((opcionS.equals("3")) != true);
					break;
				case "3":
					do{
						System.out.println("\t");
						System.out.println("\t");
						System.out.println("             ------------------   SUB-MEN\u00da 3   ---------------- ");
						System.out.println("\t");
						System.out.println("                                 1.- Opc. A");
						System.out.println("                                 2.- Opc. B");
						System.out.println("                                 3.- Opc. C");
						System.out.println("                                 4.- Opc. D");
						System.out.println("                                 5.- Regresar  Menu principal.");
						System.out.print("              Teclee una opcion: ");
						opcionS = teclado.nextLine();
						if ((opcionS.equals("1")==false) && (opcionS.equals("2")==false) && (opcionS.equals("3")==false) && (opcionS.equals("4")==false) &&(opcionS.equals("5")==false)) {
							System.out.println('\7');
						}
						switch(opcionS){
							case "1":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             -----------------   SUB-MEN\u00da  3.A  -------------- ");
									System.out.println("\t");
									System.out.println("                                 1.- Opcion 1");
									System.out.println("                                 2.- Opcion 2");
									System.out.println("                                 3.- Opcion 3");
									System.out.println("                                 4.- Opcion 4");
									System.out.println("                                 5.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
										System.out.println('\7');
									}
								}while((opcionS2.equals("5")) != true);
								break;
							case "2":
							do{
								System.out.println("\t");
								System.out.println("\t");
								System.out.println("             -----------------   SUB-MEN\u00da 3.B   -------------- ");
								System.out.println("\t");
								System.out.println("                                 1.- Opcion 1");
								System.out.println("                                 2.- Opcion 2");
								System.out.println("                                 3.- Opcion 3");
								System.out.println("                                 4.- Opcion 4");
								System.out.println("                                 5.- Regresar al Menu anterior.");
								System.out.print("              Teclee una opcion: ");
								opcionS2 = teclado.nextLine();
								if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
									System.out.println('\7');
								}
							}while((opcionS2.equals("5")) != true);
							break;
						case "3":
						do{
							System.out.println("\t");
							System.out.println("\t");
							System.out.println("             -----------------   SUB-MEN\u00da 3.C   -------------- ");
							System.out.println("\t");
							System.out.println("                                 1.- Opcion 1");
							System.out.println("                                 2.- Opcion 2");
							System.out.println("                                 3.- Opcion 3");
							System.out.println("                                 4.- Opcion 4");
							System.out.println("                                 5.- Regresar al Menu anterior.");
							System.out.print("              Teclee una opcion: ");
							opcionS2 = teclado.nextLine();
							if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
								System.out.println('\7');
							}
						}while((opcionS2.equals("5")) != true);
						break;
						case "4":
						do{
							System.out.println("\t");
							System.out.println("\t");
							System.out.println("             -----------------   SUB-MEN\u00da 3.D   -------------- ");
							System.out.println("\t");
							System.out.println("                                 1.- Opcion 1");
							System.out.println("                                 2.- Opcion 2");
							System.out.println("                                 3.- Opcion 3");
							System.out.println("                                 4.- Opcion 4");
							System.out.println("                                 5.- Regresar al Menu anterior.");
							System.out.print("              Teclee una opcion: ");
							opcionS2 = teclado.nextLine();
							if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
								System.out.println('\7');
							}
						}while((opcionS2.equals("5")) != true);
						break;
				}
					//Aqui va el SWITCH!!!!
				}while((opcionS.equals("5")) != true);
				break;
				case "4":
					do{
						System.out.println("\t");
						System.out.println("\t");
						System.out.println("             -----------------   SUB-MEN\u00da 4   -------------- ");
						System.out.println("\t");
						System.out.println("                                 1.- Opc. A");
						System.out.println("                                 2.- Opc. B");
						System.out.println("                                 3.- Opc. C");
						System.out.println("                                 4.- Opc. D");
						System.out.println("                                 5.- Regresar Menu principal.");
						System.out.print("              Teclee una opcion: ");
						opcionS = teclado.nextLine();
						if ((opcionS.equals("1")==false) && (opcionS.equals("2")==false) && (opcionS.equals("3")==false) && (opcionS.equals("4")==false) &&(opcionS.equals("5")==false)) {
							System.out.println('\7');
						}
						switch(opcionS){
							case "1":
							do{
								System.out.println("\t");
								System.out.println("\t");
								System.out.println("             -----------------   SUB-MEN\u00da  4.A  -------------- ");
								System.out.println("\t");
								System.out.println("                                 1.- Opcion 1");
								System.out.println("                                 2.- Opcion 2");
								System.out.println("                                 3.- Opcion 3");
								System.out.println("                                 4.- Opcion 4");
								System.out.println("                                 5.- Regresar al Menu anterior.");
								System.out.print("              Teclee una opcion: ");
								opcionS2 = teclado.nextLine();
								if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
									System.out.println('\7');
								}
							}while((opcionS2.equals("5")) != true);
							break;
							case "2":
							do{
								System.out.println("\t");
								System.out.println("\t");
								System.out.println("             -----------------   SUB-MEN\u00da 4.B   -------------- ");
								System.out.println("\t");
								System.out.println("                                 1.- Opcion 1");
								System.out.println("                                 2.- Opcion 2");
								System.out.println("                                 3.- Opcion 3");
								System.out.println("                                 4.- Opcion 4");
								System.out.println("                                 5.- Regresar al Menu anterior.");
								System.out.print("              Teclee una opcion: ");
								opcionS2 = teclado.nextLine();
								if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
									System.out.println('\7');
								}
							}while((opcionS2.equals("5")) != true);
							break;
							case "3":
							do{
								System.out.println("\t");
								System.out.println("\t");
								System.out.println("             -----------------   SUB-MEN\u00da 4.C   -------------- ");
								System.out.println("\t");
								System.out.println("                                 1.- Opcion 1");
								System.out.println("                                 2.- Opcion 2");
								System.out.println("                                 3.- Opcion 3");
								System.out.println("                                 4.- Opcion 4");
								System.out.println("                                 5.- Regresar al Menu anterior.");
								System.out.print("              Teclee una opcion: ");
								opcionS2 = teclado.nextLine();
								if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
									System.out.println('\7');
								}
							}while((opcionS2.equals("5")) != true);
							break;
							case "4":
							do{
								System.out.println("\t");
								System.out.println("\t");
								System.out.println("             ------------------   SUB-MEN\u00da 4.D   --------------- ");
								System.out.println("\t");
								System.out.println("                                 1.- Opc. 1");
								System.out.println("                                 2.- Opc. 2");
								System.out.println("                                 3.- Opc. 3");
								System.out.println("                                 4.- Opc. 4");
								System.out.println("                                 5.- Regresar  Menu anterior.");
								System.out.print("              Teclee una opcion: ");
								opcionS2 = teclado.nextLine();
								if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false) &&(opcionS2.equals("5")==false)) {
									System.out.println('\7');
								}
							}while((opcionS2.equals("5")) != true);
							break;
					}
					}while((opcionS.equals("5")) != true);
				break;
			}
        }while((opcion.equals("6")) != true);
        System.out.println("\n");
        System.out.println("                                      Adios!");
      }
}
