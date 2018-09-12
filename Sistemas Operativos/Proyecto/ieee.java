import java.io.*;
import java.nio.ByteBuffer;

public class ieee{
	public static void main(String[] numero){
		byte datos[] = new byte[4];

		if(numero[0].equals("f")){
			System.out.println("En bruto: "+numero[1]);
			float f = Float.parseFloat(numero[1]);
			System.out.println("En flotante: "+f);
			int num = Float.floatToIntBits(f);
			System.out.println("En entero: "+num);

			datos[0] = (byte) (num>>> 24);
			datos[1] = (byte) (num>>> 16);
			datos[2] = (byte) (num>>> 8);
			datos[3] = (byte) (num>>> 0);

			System.out.println(num);
			System.out.printf("%02X %02X %02X %02X\n",datos[0]&0xFF, datos[1]&0xFF, datos[2]&0xFF, datos[3]&0xFF);
		}

		if(numero[0].equals("e")){
			float yy = Float.intBitsToFloat(Integer.parseInt(numero[1]));
			System.out.println(yy);
		}
	}
}

