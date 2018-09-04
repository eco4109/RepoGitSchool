/*
	Simulación de una computadora: CPU
	20-Agosto-2018
	Programa para simular el cpu de una computadora virtual
*/
import java.io.*;

public class cpu{
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

	static int cod_inst = 0;
	static int orig; 
	static int dest;
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

	public static void capta(){ //Lee byte´s guardados en la memoria RAM

	}

	public static void traduce(){ //Traduce los bytés obtenidos
		
	}

	public static void ejecuta(){
		switch(cod_inst){
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
		}
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

	public static void main(String[] argumento) {
		for(int i=0; i<=15; i++){
			PSW[i] = false;
		}
		PSW[15] = true;

		for(int i=0; i<=13; i++){
			R[i] = 0;
		}
		R[RA] = 0x4219147B;
		R[RB] = 0x42357AE1;

		cod_inst = MUE_REG_BUS;
		orig = RA;
		dest = ALU_B1;
		ejecuta();

		cod_inst = MUE_REG_BUS;
		orig = RB;
		dest = ALU_B2;
		ejecuta();

		dump(1133903872);
		System.out.println("REG A: "+R[RA]+", REG IX: "+R[IX]);
	}
}