import java.io.*;
import java.nio.*;
import java.util.*;

public class quantum{
	static final long quantum = 1000;
	


	public static void main(String[] args){
		long horaSistema, horaInicial, diferencia;
		
		horaInicial = System.currentTimeMillis();
		//char ch;
		while(true){
			horaSistema = System.currentTimeMillis();
			diferencia = horaSistema - horaInicial;	
			//System.out.println(diferencia);
			if((diferencia >= quantum - 10)&&(diferencia <= quantum +10)){
				System.out.println("¡INTERRUPCIÓN  :D  !\7");
				horaInicial = horaSistema;
				//System.out.println("Press Any Key To Continue...");
          		//new java.util.Scanner(System.in).nextLine();
			}
		}
	}	
}