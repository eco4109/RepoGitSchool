import java.io.*;
import java.util.Scanner;
/*
	\u00e1 -> á
	\u00e9 -> é
	\u00ed -> í
	\u00f3 -> ó
	\u00fa -> ú
	\u00c1 -> Á
	\u00c9 -> É
	\u00cd -> Í
	\u00d3 -> Ó
	\u00da -> Ú
	\u00f1 -> ñ
	\u00d1 -> Ñ
*/

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
			System.out.println("\n              De que esta recubierto su cuerpo?\n");
			System.out.println("                                 1.- Pelaje.");
			System.out.println("                                 2.- Escamas.");
			System.out.println("                                 3.- Plumas.");
			System.out.println("                                 4.- Piel Humeda.");
			System.out.println("                                 5.- Exoesqueleto/Diminutos bellos/Otro.");
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
						System.out.println("\n\n             -----------------   PESO   --------------- ");
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
									System.out.println("\n\n             ----------------   COLA  ------------- ");
									System.out.println("\n             Tiene cola?\n");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
									System.out.println("                                 3.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}
									if(opcionS2.equals("1")==true){
										System.out.println("\n                         TU MASCOTA ES UN RATON.\n");
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
									System.out.println("             ----------------   PASEO  ------------- ");
									System.out.println("\n            Es usual que las personas lo saquen a pasear con correa?\n");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
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
								System.out.println("TU MASCOTA ES UN PERRO DE RAZA MEDIANA-GRANDE");
								System.exit(4);
								break;
							
							case "4":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             -----------------   COMIDA   ----------------- ");
									System.out.println("\n             La gente suele comerce a tu mascota?");
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
										System.out.println("\tTu mascota es un BURRO, un CAMELLO, o un PERRO muy grande.");
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
						System.out.println("               -----------------   PATAS   ----------------- ");
						System.out.println("\t     Tiene Patas?");
						System.out.println("                                 1.- Si.");
						System.out.println("                                 2.- No.\n");
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
									System.out.println("             -----------------   VELOCIDAD  ------------------ ");
									System.out.println("\t   Se mueve leeeeeeeeeento?");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
									System.out.println("                                 3.- Regresar Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false)) {
										System.out.println('\7');
									}else if(opcionS2.equals("1")==true){
										System.out.println("\tTu mascota es UNA TORTUGA");
										System.exit(4);
									}else if(opcionS2.equals("2")==true){
										System.out.println("Tu mascota es UNA IGUANA, LAGARTIJA");
										System.exit(4);
									}
								}while((opcionS2.equals("3")) != true);
								break;
							case "2":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             ------------------   HABITAT   --------------- ");
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
						System.out.println("             ------------------   PESO   ---------------- ");
						System.out.println("\t     Cual es su peso maximo en etapa adulta?\n");
						System.out.println("                                 1.- 0 a 1Kg.");
						System.out.println("                                 2.- 1Kg a 3Kg.");
						System.out.println("                                 3.- Mas de 3Kg.\n");
						System.out.println("                                 4.- Regresar  Menu principal.");
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
									System.out.println("             -----------------   COLOR  -------------- ");
									System.out.println("\t     De que color es? ");
									System.out.println("                                 1.- Blanco.");
									System.out.println("                                 2.- Negro");
									System.out.println("                                 3.- Gris.\n");
									System.out.println("                                 4.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false)) {
										System.out.println('\7');
									}else if((opcionS2.equals("1")==true)){
										System.out.println("\t     Tu mascota es UN PATO");
										System.exit(4);
									}else if((opcionS2.equals("2")==true)){
										System.out.println("\t     Tu mascota es UN TUCAN");
										System.exit(4);
									}else if((opcionS2.equals("3")==true)){
										System.out.println("\t     Tu mascota es UNA PALOMA");
										System.exit(4);
									}
								}while((opcionS2.equals("4")) != true);
								break;
							case "2":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             -----------------   COLOR  -------------- ");
									System.out.println("\t     De que color es? ");
									System.out.println("                                 1.- Verde/Amarillo.");
									System.out.println("                                 2.- Azul");
									System.out.println("                                 3.- Cafe.\n");
									System.out.println("                                 4.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false) && (opcionS2.equals("4")==false)) {
										System.out.println('\7');
									}else if((opcionS2.equals("1")==true)){
										System.out.println("\t      Tu mascota es UN CANARIO");
										System.exit(4);
									}else if((opcionS2.equals("2")==true)){
										System.out.println("\t     Tu mascota es UN CANARIO");
										System.exit(4);
									}else if((opcionS2.equals("3")==true)){
										System.out.println("\t     Tu mascota es UN AVE DE LAS QUE ANDAN EN LA CALLE");
										System.exit(4);
									}
								}while((opcionS2.equals("4")) != true);
								break;
							case "3":
								do{
									System.out.println("\t");
									System.out.println("\t");
									System.out.println("             -----------------   CRESTA   -------------- ");
									System.out.println("\t    Tiene cresta en la cabeza?\n");
									System.out.println("                                 1.- Si.");
									System.out.println("                                 2.- No.\n");
									System.out.println("                                 3.- Regresar al Menu anterior.");
									System.out.print("              Teclee una opcion: ");
									opcionS2 = teclado.nextLine();
									if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false)) {
										System.out.println('\7');
									}else if((opcionS2.equals("1")==true)){
										System.out.println("\t     Tu mascota es UNA GALLINA/GALLO o GUAJOLOTE");
										System.exit(4);
									}else if((opcionS2.equals("2")==true)){
										System.out.println("\t     Tu mascota es UN AVESTRUZ");
										System.exit(4);
									}
								}while((opcionS2.equals("3")) != true);
								break;
					}
					}while((opcionS.equals("4")) != true);
					break;
				case "4":
					do{
						System.out.println("\t");
						System.out.println("\t");
						System.out.println("             -----------------   Habitad   -------------- ");
						System.out.println("\tCual es su habitat natural?");
						System.out.println("                                 1.- Debajo de la tierra.");
						System.out.println("                                 2.- Tierra/Agua.\n");
						System.out.println("                                 3.- Regresar al Menu anterior.");
						System.out.print("              Teclee una opcion: ");
						opcionS2 = teclado.nextLine();
						if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false)) {
							System.out.println('\7');
						}else if((opcionS2.equals("1")==true)){
							System.out.println("\tTu mascota es UNA LOMBRIZ");
							System.exit(4);
						}else if((opcionS2.equals("2")==true)){
							System.out.println("\tTu mascota es UNA RANA, un SAPO o UNA SALAMANDRA");
							System.exit(4);
						}
					}while((opcionS2.equals("3")) != true);
					break;
				case "5":
					do{
						System.out.println("\t");
						System.out.println("\t");
						System.out.println("             -----------------   Patas  -------------- ");
						System.out.println("\tCuantas patas tiene?");
						System.out.println("                                 1.- 6.");
						System.out.println("                                 2.- 8.\n");
						System.out.println("                                 3.- Regresar al Menu anterior.");
						System.out.print("              Teclee una opcion: ");
						opcionS2 = teclado.nextLine();
						if ((opcionS2.equals("1")==false) && (opcionS2.equals("2")==false) && (opcionS2.equals("3")==false)) {
							System.out.println('\7');
						}else if((opcionS2.equals("1")==true)){
							System.out.println("Tu mascota es UN ESCORPION");
							System.exit(4);
						}else if((opcionS2.equals("2")==true)){
							System.out.println("Tu mascota puede ser una araña");
							System.exit(4);
						}
					}while((opcionS2.equals("3")) != true);
					break;
			}
        }while((opcion.equals("6")) != true);
        System.out.println("\n");
        System.out.println("\t\t\t\t\tAdios!");
      }
}
