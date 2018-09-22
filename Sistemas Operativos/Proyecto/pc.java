
/*
	Simulación de una computadora: CPU
	20-Agosto-2018
	Programa para simular el cpu de una computadora virtual
*/
import java.io.*;


public class pc{
	static final int MUE_REG_REG = 0;
	static final int MUE_REG_BUS = 64;
	static final int MUE_BUS_REG = 1;
	static final int MUE_BUS_BUS = 65;
	static final int RA = 0;
	static final int RB = 1;
	static final int RC = 2;
	static final int IP = 3;
	static final int SP = 4;
	static final int HP = 5;
	static final int BP = 6;
	static final int IX = 7;
	static final int CS = 8;
	static final int SS = 9;
	static final int HS = 10;
	static final int DS = 11;
	static final int IPR = 12;
	static final int OI = 13;
	static final int ALU_B1 = 0;
	static final int ALU_B2 = 1;
	static final int ALU_B3 = 2;
	static final int MMU_B1 = 3;
	static final int MMU_B2 = 4;
	static final int MMU_B3 = 5;
	static final int MEM_B1 = 6;
	static final int MEM_B2 = 7;
	static final int ALU_SUM = 66;
	static final int ALU_RES = 67;
	static final int ALU_MUL = 68;
	static final int ALU_DIV = 69;
	static final int MUE_DATO_REG = 32;
	static final int MUE_DATO_BUS = 96;

	//Dispositivos y arranque del BIOS
	public static String BOOT="0000";
	public static int[] d_TIPO=new int[256];
	public static String[] d_IDENT=new String[256];
	public static int[] d_SECTOR=new int[256];
	public static int[] d_PISTAS=new int[256];
	public static int[] d_SECxPISTA=new int[256];
	public static boolean[] d_FMTBN=new boolean[256];
	public static boolean[] d_FMTSO=new boolean[256];
	public static boolean[] d_BOOT=new boolean[256];
	public static int[] d_IDSO=new int[256];
	public static int[] d_POSB=new int[256];	//Pos Brazo
	public static int[] d_POSS=new int[256];	//Pos Sector
	public static int idx_BOOT=0;
	public static int idx_MAX=0;
	public static int disp_ES=0;
	

	
	//Registros del CPU
	public static int[] R = new int[14];
	//Buses de todo el sistema (compuertas)
	public static int[] B = new int[16];
	//PSW de la computadora (banderas)
	public static boolean[] PSW = new boolean[16];
	//Memoria principal RAM
	// int = 4 byte´s
	// 1Mb de RAM
	public static byte[] RAM = new byte[1048576]; 
	//Buffer Arrays contains BYTES
	public static byte[] r2b = new byte[2];
	public static byte[] r4b = new byte[4];
	//Buffer Arrays, contains bits in each position
	public static String[] r2binary = new String[2];
	public static String[] r4binary = new String[4];
	

	public static int[] data = new int[4];
	static int dato;

	//public static int dato[];

	static int cod_inst = 0;
	static int orig; 
	static int dest;
	static int temp1, temp2, temp3, temp4, temp5;
	static float res_alu;
	static String NoSirve;

	public static String pausa(){
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)) ;
		String nada = null;
		try{
			nada = entrada.readLine();
		}catch(Exception e){
			System.err.println(e);
		}
		return nada;
	}

	public static void printArrayinDec(byte[] array){ //Function for print an Array in decimal, a BYTE comes in
		for (int i=0;i<array.length;i++) {
			System.out.println(array[i]&0xFF);
		}
	}

	public static void printArray(String[] array){ //Function for print an Array, a STRING comes in
		for (int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}
	}

	public static String bytes_to_bits(byte stringBytes){ //Method for convert BYTES into bits String
		String bb = Integer.toBinaryString(stringBytes); //First the BYTE it´s convert into a String
		//we need to complete the Byte to 8-bits if it's less than 8
		if(bb.length()<8){
			while(bb.length()<8){
				bb = "0"+bb;
			}
		}
		String inBinary = bb.substring(bb.length()-8, bb.length());//Split the String , we need only the lasts 8 bits, so cut it
		return inBinary;
	}

	public static void capta(){ //Lee byte´s guardados en la memoria RAM
		//Creacion de los buffers de lectura

		System.out.println("El registro IP, contiene (en flotante): "+IEEE_a_flotante(R[3]));
		System.out.println("El registro BP, contiene (en flotante): "+IEEE_a_flotante(R[6]));
		//Suma de los Valores contenidos en el registro IP e IB
		System.out.println("Se va a leer en la RAM desde :"+IEEE_a_flotante(R[6]));

		float direcMem2 = IEEE_a_flotante(R[6]) + IEEE_a_flotante(R[0]); //R[6], es IP R[3] es IB, o al revés NO RECUERDO BIEN JEJE
		int direcMem = (int) direcMem2;
		//Leer 6 bytes de la "RAM" a partir de la posicion calculada+
		//Guardar los primeros 2 bytes leidos en un buffer y los otros 4 en el otro 
		for (int i=0;i<=5; i++) { //Ciclo para recorrer 6 bytes en la RAM a partir de la posicion IP + BP
			if(i<2){
				r2b[i] = RAM[direcMem + i]; 
			}else{
				r4b[i-2] = RAM[direcMem + i];
			}
		}
	}

	public static int char_to_int(char caracter){ //Funcition for transform CHARACTERS INTO INTEGERS
		int aux = Integer.parseInt(Character.toString(caracter));
		return aux;
	}

	public static String[] float_to_IEEE(float numberF){ //Function for transform a FLOAT number into IEE
		//Like the "ieee.java teacher´s code" but in one function
		//System.out.println("Lo que entró (en flotante): "+ numberF);
		int numberI = Float.floatToIntBits(numberF);
		//System.out.println("Ya convertido entero: "+numberI);
		byte datos[] = new byte[4];
		String datos2[] = new String[4];
		String datos3[] = new String[4];
		datos[0] = (byte) (numberI>>> 24);
		datos[1] = (byte) (numberI>>> 16);
		datos[2] = (byte) (numberI>>> 8);
		datos[3] = (byte) (numberI>>> 0);
		//Transform into decimal

		datos2[0] =String.format("%02x", datos[0]&0xFF);
		datos2[1] = String.format("%02x", datos[1]&0xFF);
		datos2[2] = String.format("%02x", datos[2]&0xFF);
		datos2[3] = String.format("%02x", datos[3]&0xFF);


		return datos2;
	}

	public static String arrayUnion (String[] array){ //Function for join the elements of an array
		String result ="";
		for (int i=0;i<array.length;i++) {
			result = result+array[i];
		}
		return result.toUpperCase();
	}

	public static void traduce(){ 
		System.out.println("Buffer de 2 bytes (En decimal)");
		printArrayinDec(r2b);
		System.out.println("Buffer de 4 bytes (En decimal)");
		printArrayinDec(r4b);
		//The buffers(Arrays) are full, now, we´ve to analizate them
		//A method was created to convert the BYTE of the buffer to an 8-bit string
		//A new buffer is created that contains in each position the 8-bit string 
		for (int i=0;i<=5; i++) { //Loop for read each position of the arrays an convert the BYTES into BITS using the method that was created before called "bytes_to_bits"
			if(i<2){
				r2binary[i] = bytes_to_bits(r2b[i]); 
			}else{
				r4binary[i-2] = bytes_to_bits(r4b[i-2]);
			}
		}
		System.out.println("2 bytes buffer (In binary)");
		printArray(r2binary);
		System.out.println("4 bytes buffer (In binary)");
		printArray(r4binary);
		//Now we've other 2 Arrays with the instructions microcode in each position.
		//Analizate them, starting for the first bit of all. Validation starts
		//Transform the first bit of the firs BYTE in INTEGER
		int erbit = char_to_int(r2binary[0].charAt(0));
		//Obtain the value of the PSW
		if((erbit==1)&&(PSW[15]==false)){
			System.out.println("Kernel mode access violation");
			System.exit(4);
		}else{
			//There isn't violation, so, transform the first BYTE of the 2-BYTES buffer into INT
			cod_inst = r2b[0]&0xFF;
			System.out.println("Instruction code: "+cod_inst);
			//Obtain the secod bit of the first BYTE, thats bit indicate the instruction safety
			int S = char_to_int(r2binary[0].charAt(1)); 
			System.out.println("S: "+S);
			int L = char_to_int(r2binary[0].charAt(2)); 
			System.out.println("L: "+L);
			//Calculating large
			int large = 2 + L * 4;
			System.out.println("Large: "+large);
			if(large == 6){ //If the large it's 6 fill the variable "dato" with the 4 BYTES of the second buffer
				data[0] = r4b[0]&0xFF; 
				data[1] = r4b[1]&0xFF;
				data[2] = r4b[2]&0xFF;
				data[3] = r4b[3]&0xFF;
			}

				System.out.println(R[IP]);


			//Then .... we´ve to convert to FLOAT the IP ... add the LARGE and transform AGAIN into BYTE
			//System.out.println("IP en bruto: "+R[3]);
			float aux = (IEEE_a_flotante(R[IP]) + large); //Once we´have the float, add the large
			//System.out.println("En flotante: "+aux);
			//We call a function who´ll convert the FLOAT number INTO IEEE representation (HEXA)
			String ieeeArray[] = float_to_IEEE(aux);
			System.out.print("The new IP: ");
			//Join the elements of an array in one single STRING
			String sieeeArray = arrayUnion(ieeeArray); //IP in HEXA (String)
			//Save the new value of IP
			R[IP] = Integer.parseInt(sieeeArray,16); //Convert the HEXa (String) into decimal, IP has the vaue on decimal "12345678"
			System.out.println(IEEE_a_flotante(R[IP]));


			//Then..  obtain the vaue of Register OI and transform into FLOAT
			float F = IEEE_a_flotante(R[OI]);
			System.out.println("Initial OI: "+F);
			//CAlculate  the vaue of OI:
			float newROI = S * (F+S*L); // 1 * (0+1*1) = 1
			System.out.println("New OI in float: "+newROI);
			R[OI] = flotante_a_IEEE(newROI);
			
			//Then ... TAke the second byte of the 2-byte's buffer and split it into 2 nibles
			String niblee1 = r2binary[0].substring(0,4);
			String niblee2 = r2binary[0].substring(4,8);
			System.out.println("Nible 1(binary): "+niblee1);
			System.out.println("Nible 2(binary): "+niblee2);
			//Convert this niblee's into DEC
			int nible1 = binary_to_int(niblee1);
			int nible2 = binary_to_int(niblee2);			
			R[IP] = Integer.parseInt(sieeeArray,16);
			
			//Then ... put the first nible into variabe "orig"
			orig = nible1;
			dest = nible2;
			System.out.println("Orig(nible1 in dec): "+orig);
			System.out.println("Dest(nible2 in dec): "+dest);

			pausa();
			
		}
	}

	public static float IEEE_a_flotante( int f){
		float yy = Float.intBitsToFloat((f));
		return yy;
	}
	public static int flotante_a_IEEE(float f){
		int num = Float.floatToIntBits(f);
		return num;

	}
	public static int binary_to_int(String numero){
		int num;
		return num=Integer.parseInt(numero,2);
	}

	public static void ejecuta(){
		switch(cod_inst){
			case MUE_DATO_BUS: //conjunto de instrucciones de la arquitectura ISA
				if (dest == ALU_B3 || dest==MMU_B3|| dest == MEM_B2){
					System.out.println("\7Corto circuito");
					System.exit(4);
				}
				R[dest]=dato;
				break;
			case MUE_REG_REG:
				R[dest] = R[orig];
				break;
			case MUE_REG_BUS:
				if(dest == ALU_B3 || dest == MMU_B3 || dest == MEM_B2){
					System.out.println("\7Corto Circuito");
					System.exit(4);
				}
				B[dest] = R[orig];
				break;
			case MUE_BUS_REG:
				if(orig == MMU_B3){
					System.out.println("\7Corto Circuito");
					System.exit(4);
				}
				R[dest] = B[orig];
				break;
			case MUE_BUS_BUS:
				if(dest == ALU_B3 ||dest == MMU_B3 || dest== MEM_B2 ||orig == MMU_B2 || orig == MEM_B1){
					System.out.println("\7Corto Circuito");
					System.exit(4);
				}
				B[dest] = B[orig];
				break;
			case ALU_SUM:
				res_alu = IEEE_a_flotante(B[ALU_B1])+IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3] = flotante_a_IEEE(res_alu);
				break;
			case ALU_RES:
				res_alu = IEEE_a_flotante(B[ALU_B1])-IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3] = flotante_a_IEEE(res_alu);
				break;
			case ALU_DIV:
				res_alu = IEEE_a_flotante(B[ALU_B1])/IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3] = flotante_a_IEEE(res_alu);
				break;
			case ALU_MUL:
				res_alu = IEEE_a_flotante(B[ALU_B1])*IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3] = flotante_a_IEEE(res_alu);
				break;
			case MUE_DATO_REG:
				R[dest] = dato;
				break;			

		}
	}
	public static int forceint( float f){
		int valorEntero = (int) f;
		return valorEntero;
	}

	public static void dump(int mem){
		String var1, var2;
		String[] NomReg=new String[14];
		String[] NomBus=new String[8];
		NomReg[0]="RA";
		NomReg[1]="RB";
		NomReg[2]="RC";
		NomReg[3]="IP";
		NomReg[4]="SP";
		NomReg[5]="HP";
		NomReg[6]="BP";
		NomReg[7]="IX";
		NomReg[8]="CS";
		NomReg[9]="SS";
		NomReg[10]="HS";
		NomReg[11]="DS";
		NomReg[12]="IPR";
		NomReg[13]="OI";
		NomBus[0]="ALU_B1";
		NomBus[1]="ALU_B2";
		NomBus[2]="ALU_B3";
		NomBus[3]="MMU_B1";
		NomBus[4]="MMU_B2";
		NomBus[5]="MMU_B3";
		NomBus[6]="MEM_B1";
		NomBus[7]="MEM_B2";
		System.out.println("\nPresione x para terminar el volcado\n\n");
		System.out.println("\n\tBANDERAS\t      REGISTROS\t\t    BUSES\n");
		for(int j=0;j<=15;j++){
			var1="";	var2="";
			if(j<=13)
				var1=NomReg[j]+"=["+String.format("%08X",R[j])+"]";
			if(j<=7)
				var2=NomBus[j]+"=["+String.format("%08X",B[j])+"]";
			System.out.printf("\t%-22s%-22s%-22s\n","PSW"+j+"=["+PSW[j]+"]",var1,var2);
		}
		System.out.print("\n - ");
		NoSirve=pausa();
		System.out.print("\n");
		if(!NoSirve.equals("x")){
			NoSirve="";
			temp1=forceint(IEEE_a_flotante(mem)); 
			temp1=temp1-(temp1%16);
			temp2=0;
			temp3=0;
			while(temp1<1048575&&(!NoSirve.equals("x"))){
				System.out.printf(" %07d : ",temp1+temp3*16);
				for(int i=0;i<=7;i++)
					System.out.printf("%02X ",RAM[temp1+i+temp3*16]);
				System.out.print("| ");
				for(int i=8;i<=15;i++)
					System.out.printf("%02X ",RAM[temp1+i+temp3*16]);
				for(int i=0;i<=15;i++){
					temp4=(RAM[temp1+i+temp3*16]&0xFF);
					if(temp4>=32&&temp4<=254)
						System.out.printf("%c",temp4);
					else
						System.out.print(".");
				}
				temp3++;
				System.out.print("\n");
				if(temp2++>=7){
					System.out.print("\n - ");
					NoSirve=pausa();
					System.out.print("\n");
					temp2=0;
				}
			}
		}
	}
// dump instrution, instruction in user mode 
	public static void main(String[] argumento) {

		if(argumento.length!=0){
			if(argumento[0].equals("BIOS"))
				BIOS.IniciaBIOS();
			if(argumento.length==2 && argumento[0].equals("CreaDD")){
				CrearDisco.creaHD(argumento[1]);
			}
			if(argumento.length==3 && argumento[0].equals("CreaUSB")){
				CrearUSB.creaUSB(argumento[1],argumento[2]);
			}
			System.exit(0);

		}
		BIOS.VerifDispo();

	}
}