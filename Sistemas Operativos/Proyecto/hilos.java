import java.io.*;
public class hilos{
	public static void main(String[] args) {
		hiloT1 HijoA = new hiloT1();
		hiloT2 HijoB = new hiloT2();
		HijoA.start();
		HijoB.start();
		while(true){
			System.out.println("Principal");
		}

	}


}
class hiloT1 extends Thread{
	public void run(){
		while(true){
			System.out.println("hola perro hilo 1");
		}
	}
}


class hiloT2 extends Thread{
	public void run(){
		while(true){
			System.out.println("hola perro hilo 2");
		}
	}
}

