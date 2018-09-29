import java.io.*;
import java.nio.ByteBuffer;
public class Clase{
	static  byte datos[] = new byte[128];
	public static void main(String[] arg){
		/*datos[0] = (byte)0xEF;
		datos[1] = (byte)0xA6;
		datos[2] = (byte)0xA7;
		datos[3] = (byte)0xCD;
		datos[4] = (byte)0x40;
		datos[127] = (byte)0xEF;
		escribe_bin(arg[0]);
		for (int i=0; i<=127 ;i++ ) {
			datos[i]= (byte) 0x00;			
		}*/
		
			lee_pos(arg[0],2);			
		
		
		/*for (int i=0; i<=127 ;i++ ) {
			System.out.print("Datos de ["+i+"]="+(datos[i] & 0xFF )+"   ");			
		}*/
		
	}
	public static File xArchivo(String xName){
		File xFile = new File(xName);
		return xFile;
	}

	public static void escribe_bin(String archivo){
		try{
			FileOutputStream fos = new FileOutputStream(xArchivo(archivo));
			fos.write(datos);
			fos.write(datos);
			fos.close();
		}catch(IOException e){
			System.out.println("Error al abrir el archivo");
		}
	}
	public static void lee_bin(String archivo){
		try{
			FileInputStream fos = new FileInputStream(xArchivo(archivo));
			fos.read(datos);
			
			fos.close();

		}catch(IOException e){
			System.out.println("Error al abrir el archivo");
		}
	}

	public static void lee_pos(String archivo, int pos){
		try{
			RandomAccessFile disco = new RandomAccessFile(archivo, "rw");
			disco.seek(pos);
			int bytes_leidos = disco.read(datos);
			byte numeroBinario = (byte)(Integer.toBinaryString((datos[0] & 0xFF)));
			byte mascara = (byte)(32);
			int  convertido= numeroBinario | mascara;
			System.out.println(convertido);
			System.out.println("Datos de ["+pos+"]="+(datos[0] & 0xFF )+"   ");
			disco.close();
		}catch(IOException e){
			System.out.print("No se pudo abrir el archivo");

		}
	}


}