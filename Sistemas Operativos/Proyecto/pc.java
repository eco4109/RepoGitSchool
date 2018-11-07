
/*
	Simulación de una computadora: CPU
	20-Agosto-2018
	Programa para simular el cpu de una computadora virtual
*/
import java.io.*;
import java.nio.*;
import java.util.*;


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
	static final int NOP = 5;
	static final int COMP = 6;
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
	static final int SBAND = 49;
	static final int DUMP=33;
	static final int MMU_OPER=70;
    static final int MMU_BY_PASS=71;
    static final int MEM_ESC_B=2;
    static final int MEM_ESC_P=3;
    static final int MEM_ESC_DP=4;
    static final int MEM_LECT_B=72;
    static final int MEM_LECT_P=73;
    static final int MEM_LECT_DP=74;
	static final int BANDON=192;
	static final int BANDOFF=193;


	public static boolean CPUInt = false;


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
	

	static  byte buffer[] = new byte[128];
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
	static byte buff_trad[]= new byte[2];
	static byte buff_dato[]= new byte[4];

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
	public static int verificaValor(int valor, int num){
		int masc1=128;
		int masc2=64;
		int masc3=32;
		int R;
		
		if(num==1){
			R = valor & masc1;
			
			if(R==128){
				return 1;
			}else{
				return 0;
			}
		}else if(num==2){
			R = valor & masc2;
			if(R==64){
				return 1;
			}else{
				return 0;
			}
		}else if(num==3){
			R = valor & masc3;
			if(R==32){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		
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

	public static void capta(){
		int x=forceint(IEEE_a_flotante(R[BP]) + IEEE_a_flotante(R[IP]));
		buff_trad[0]=RAM[x];
		x++;
		buff_trad[1]=RAM[x];
		x++;
		buff_dato[0]=RAM[x];
		x++;
		buff_dato[1]=RAM[x];
		x++;
		buff_dato[2]=RAM[x];
		x++;
		buff_dato[3]=RAM[x];
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
	
		int k = verificaValor(forceint(buff_trad[0]),1);
		int s = verificaValor(forceint(buff_trad[0]),2);
		int L = verificaValor(forceint(buff_trad[0]),3);
		
		 
	
		if(k==1 && (!PSW[15])){
			System.out.println("\7 Violacion de Acceso al Modo Kernel");
			System.exit(4);
		}
		cod_inst=buff_trad[0];
		int largo = 2+4*L;
		int aux;
		if(largo==6){
			dato = 0;
			aux = buff_dato[0];
			aux = aux << 24;
			dato = dato + aux;
			aux = buff_dato[1];
			aux =  (aux <<16)&0x00FF0000;
			dato = dato + aux;
			aux = buff_dato[2];
			aux =  aux << 8;
			dato = dato + aux;
			aux = (buff_dato[3])&0x000000FF;
			dato = dato + aux;
		}
		float j = IEEE_a_flotante(R[IP])+largo;
		R[IP] = flotante_a_IEEE(j);
		float f = IEEE_a_flotante(R[OI]);
		float w = s*(f+s*largo);
		R[OI]=flotante_a_IEEE(w);
		orig = (buff_trad[1]&0xF0)>>>4;
		dest = (buff_trad[1]&0xF);
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
			case MUE_REG_REG:
				R[dest]=R[orig];
				break;
			case MUE_REG_BUS:
				if(dest==ALU_B3||dest==MMU_B3||dest==MEM_B2){
					System.out.println("\7 Corto circuito");
					System.exit(4);
				}
				B[dest]=R[orig];
				break;
			case MUE_BUS_REG:
				if(orig==MMU_B3){
					System.out.println("\7 Corto Circuito");
					System.exit(4);
				}
				R[dest]=B[orig];
				break;
			case MUE_BUS_BUS:
				if(dest==ALU_B3||dest==MMU_B3||dest==MEM_B2||orig==MMU_B3||orig==MEM_B1){
					System.out.println("\7 Corto Circuito");
					System.exit(4);
				}
				B[dest]=B[orig];
				break;
			case ALU_SUM:
				res_alu=IEEE_a_flotante(B[ALU_B1])+IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3]=flotante_a_IEEE(res_alu);
				break;
			case ALU_RES:
				res_alu=IEEE_a_flotante(B[ALU_B1])-IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3]=flotante_a_IEEE(res_alu);
				break;
			case ALU_MUL:
				res_alu=IEEE_a_flotante(B[ALU_B1])*IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3]=flotante_a_IEEE(res_alu);
				break;
			case ALU_DIV:
				res_alu=IEEE_a_flotante(B[ALU_B1])/IEEE_a_flotante(B[ALU_B2]);
				B[ALU_B3]=flotante_a_IEEE(res_alu);
				break;
			case MUE_DATO_REG:
				R[dest]=dato;
				break;
			case DUMP:
				dump(dato);
				break;
			case MUE_DATO_BUS:
				if(dest==ALU_B3||dest==MMU_B3||dest==MEM_B2){
					System.out.println("\7Corto Circuito");
					System.exit(4);
				}
				B[dest]=dato;
				break;
			case COMP:
				for(int i=0;i<=14;i++){
					PSW[i]=false;
					float r1 = IEEE_a_flotante(R[orig]);
					float r2 = IEEE_a_flotante(R[dest]);
					if(r1==r2)
						PSW[0]=true;
					if(r1>r2)
						PSW[1]=true;
					if(r1<r2)
						PSW[2]=true;
					if(r1>=r2)
						PSW[3]=true;
					if(r1<=r2)
						PSW[4]=true;
					if(r1!=r2)
						PSW[5]=true;
				}
				break;         
            case SBAND:
                   if(PSW[orig]){
                       R[dest]=dato;
                   }
            case MMU_OPER:
                   res_alu=IEEE_a_flotante(B[MMU_B1])+IEEE_a_flotante(B[MMU_B2]);
                   if(forceint(res_alu)<=999&&!PSW[15]){
                       System.out.println("\7Violacion de memoria");
                       System.exit(4);
                   }else
                       B[MMU_B3]=flotante_a_IEEE(res_alu);
                   break;
            case MMU_BY_PASS:
                   temp1=forceint(IEEE_a_flotante(B[MMU_B2]));
                   if(temp1<=999&&!PSW[15]){
                       System.out.println("\7Violacion de memoria");
                       System.exit(4);
                   }else
                       B[MMU_B3]=B[MMU_B2];
                   break;
            case MEM_LECT_B:
            	B[MEM_B2] = RAM[forceint(IEEE_a_flotante(MMU_B3))] & 0xFF;
            	break;
            case MEM_LECT_P:
            	temp1 = forceint(IEEE_a_flotante(B[MMU_B3]));
            	buff_dato[0] = (byte)0x00;
            	buff_dato[1] = (byte)0x00;
            	buff_dato[2] = RAM[temp1++];
            	buff_dato[3] = RAM[temp1];
            	B[MEM_B2] = ByteBuffer.wrap(buff_dato).getInt();
            	break;
            case MEM_LECT_DP:
            	temp1 = forceint(IEEE_a_flotante(B[MMU_B3]));
            	buff_dato[0] = RAM[temp1++];
            	buff_dato[1] = RAM[temp1++];
            	buff_dato[2] = RAM[temp1++];
            	buff_dato[3] = RAM[temp1];
            	break;
            case MEM_ESC_B:
            	RAM[forceint(IEEE_a_flotante(B[MMU_B3]))] = (byte)(B[MEM_B1]>>>0);
            	break;
            case MEM_ESC_P:
		        temp2 = forceint(IEEE_a_flotante(B[MMU_B3]));
		        RAM[temp2++]= (byte)(B[MEM_B1]>>>0);
            	break;
            case MEM_ESC_DP:
            	temp2 = forceint(IEEE_a_flotante(B[MMU_B3]));
            	RAM[temp2++] = (byte)(B[MEM_B1]>>>24);
            	RAM[temp2++] = (byte)(B[MEM_B1]>>>16);
            	RAM[temp2++] = (byte)(B[MEM_B1]>>>8);
            	RAM[temp2] = (byte)(B[MEM_B1]>>>0);
            	break;
			case BANDON:
				PSW[orig] = true;
				break;
			case BANDOFF:
				PSW[orig] = false;
				break;
			default:
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
			if(argumento[0].equals("escribe")){
				escribeDisco();
				System.out.println("Escritura correctaa");
				pausa();
			}
			System.exit(0);
		}
		Computadora COMPAQ = new Computadora();
		Interrupcion interrumpe = new Interrupcion();
		
		
		
		BIOS.VerifDispo();
		BIOS.StartUP();
		for (int i=0; i<=45 ; i++) {
			 System.out.printf("RAM[%d] = %02X\t",i,RAM[i]);
		}
		PSW[15] = true;
		for (int i = 0; i<=13 ; i++) {
		 	R[i] = 0;
		 } 
		//Inicializar vector de interrupciones
		cola.inicializarCola();
		COMPAQ.start();
		interrumpe.start();
	}

	public static void escribeDisco(){
		RAM[0]=(byte)0x00;
		RAM[1]=(byte)0x01;
		RAM[2]=(byte)0x00;
		RAM[3]=(byte)0x01;
		RAM[4]=(byte)0x00;
		RAM[5]=(byte)0x01;
		RAM[6]=(byte)0x00;
		RAM[7]=(byte)0x01;
		RAM[8]=(byte)0x00;
		RAM[9]=(byte)0x01;
		RAM[10]=(byte)0x00;
		RAM[11]=(byte)0x01;
		RAM[12]=(byte)0x00;
		RAM[13]=(byte)0x01;
		RAM[14]=(byte)0x00;
		RAM[15]=(byte)0x01;
		RAM[16]=(byte)0x00;
		RAM[17]=(byte)0x01;
		RAM[18]=(byte)0x00;
		RAM[19]=(byte)0x01;
		RAM[20]=(byte)0x00;
		RAM[21]=(byte)0x01;
		RAM[22]=(byte)0x00;
		RAM[23]=(byte)0x01;
		RAM[24]=(byte)0x00;
		RAM[25]=(byte)0x01;
		RAM[26]=(byte)0x00;
		RAM[27]=(byte)0x01;
		RAM[28]=(byte)0x00;
		RAM[29]=(byte)0x01;
		RAM[30]=(byte)0x00;
		RAM[31]=(byte)0x01;
		RAM[32]=(byte)0x00;
		RAM[33]=(byte)0x01;
		RAM[34]=(byte)0x00;
		RAM[35]=(byte)0x01;
		RAM[36]=(byte)0x00;
		RAM[37]=(byte)0x01;
		RAM[38]=(byte)0x00;
		RAM[39]=(byte)0x01;
		RAM[40]=(byte)0x00;
		RAM[41]=(byte)0x01;
		RAM[42]=(byte)0x00;
		RAM[43]=(byte)0x01;
		RAM[44]=(byte)0x00;
		RAM[45]=(byte)0x01;
		RAM[46]=(byte)0x00;
		RAM[47]=(byte)0x01;
		RAM[48]=(byte)0x00;
		RAM[49]=(byte)0x01;
		RAM[50]=(byte)0x00;
		RAM[51]=(byte)0x01;
		RAM[52]=(byte)0x00;
		RAM[53]=(byte)0x01;
		RAM[54]=(byte)0x00;
		RAM[55]=(byte)0x01;
		RAM[56]=(byte)0x00;
		RAM[57]=(byte)0x01;
		RAM[58]=(byte)0x00;
		RAM[59]=(byte)0x01;
		RAM[60]=(byte)0x00;
		RAM[61]=(byte)0x01;
		RAM[62]=(byte)0x00;
		RAM[63]=(byte)0x01;
		RAM[64]=(byte)0x00;
		RAM[65]=(byte)0x01;
		RAM[66]=(byte)0x00;
		RAM[67]=(byte)0x01;
		RAM[68]=(byte)0x00;
		RAM[69]=(byte)0x01;
		RAM[70]=(byte)0x20;
		RAM[71]=(byte)0x03;
		RAM[72]=(byte)0x00;
		RAM[73]=(byte)0x00;
		RAM[74]=(byte)0x00;
		RAM[75]=(byte)0x00;


		escribe("DSK1.dsk",13);
	}
	public static void lee(String archivo, int pos){
		try{
			RandomAccessFile binfile = new RandomAccessFile(archivo, "rw");
			binfile.seek(pos);
			int bytes_leidos = binfile.read(buffer);
			binfile.close();
		}catch(IOException e){
			System.out.print("No se pudo abrir el archivo");

		}
	}
	public static void escribe(String Archivo, int pos){
		try{
			RandomAccessFile binfile = new RandomAccessFile(Archivo, "rw");
			binfile.seek(pos);
			binfile.write(RAM);
			binfile.close();

		} catch(IOException ex){
			ex.printStackTrace();
			System.out.println("\n\7 Fatal error");
			System.exit(0);
			}
		}
}

class Computadora extends Thread{
	public void run(){
		while(!pc.PSW[14]){
			pc.capta();
		 	pc.traduce();
		 	pc.ejecuta();
		 	System.out.println("Un Ciclo de Fetch Terminado ... ");
		 	if(pc.CPUInt){
		 		//pc.dump(0);
		 		System.out.println("\7Fetch Interrumpido ... ");
		 	}	
			pc.CPUInt = false;
		 	
		}
	}

}

class Interrupcion extends Thread{
	public void run(){ //Genera la interrupcion de justicia, alterna entre procesos
		long quantum = 500; //Interrupción de justicia
		
			long horaSistema, horaInicial, diferencia;
			horaInicial = System.currentTimeMillis();
			while(true){
				horaSistema = System.currentTimeMillis();
				diferencia = horaSistema - horaInicial;	
				//System.out.println(diferencia);
				if((diferencia >= quantum - 10)&&(diferencia <= quantum +10)){
					//pc.CPUInt = true;
					System.out.println("INTERRUPCION !\7");
					cola.insertarCola(1);
					horaInicial = horaSistema;					
					//System.out.println("Press Any Key To Continue...");
					//new java.util.Scanner(System.in).nextLine();
			}
		}
	






		/*Random generadorAleatorios = new Random();
		while(true){
		//System.out.println(generadorAleatorios.nextInt(100));
			if (generadorAleatorios.nextInt(10000000)==5469){
				pc.CPUInt = true;
			}
		}*/
	}

}



class cola{
	static int max = 10; //Tamaño máximo de la cola
	static int[] cola = new int[max]; //Definición de la cola circular de tamaño "n"
	static int inicioC, finC; //Indicadores del inicio y final de la cola
	static int noElementos; //Contador de numero de elementos en la cola

	public static void inicializarCola(){ //Fución para iniciar la cola y los apuntadores
		inicioC = -1;
		finC = -1;
		System.out.println("\n\nCola inicializada resiona para continuar ... ...\n");
		new java.util.Scanner(System.in).nextLine();
	}

	public static void insertarCola(int dato){ //Inserta en la cola un entero
		if((finC == max-1 && inicioC == 0) || (finC+1==inicioC)){
			System.out.println("\nCOLA LLENA:\n");
			mostrarCola();
			System.out.println("\nTERMINA PROGRAMA");
			System.exit(0);
		}
		noElementos ++;
		if(finC==max-1 && inicioC!=0){
			finC = 0;
		}else{
			finC++;
		}

		cola[finC] = dato;
		System.out.println("Dato insertado correctamente: "+dato+" Datos en la cola: "+noElementos);

		
		if(inicioC==-1){
			inicioC = 0;
		}
	}

	public static void removerCola(){ //Remover un elemento de a cola
		if(inicioC==-1) {
			System.out.println("\nCOLA VACIA\n");
			return;
		}
		System.out.println("Dato eliminado correctamente: "+cola[inicioC]);
		noElementos = noElementos -1;
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
			System.out.println("\nCOLA VACIA\n");
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
}