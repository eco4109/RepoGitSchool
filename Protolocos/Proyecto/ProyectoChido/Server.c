#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <netinet/in.h>
#include <net/if.h>

//Global variables
char * ip;

void getIpAddress(){ //Function for get the IP address of this machine
	int fd;
	struct ifreq ifr;
	fd = socket(AF_INET, SOCK_DGRAM, 0);
	//I want to get an IPv4 IP address
	ifr.ifr_addr.sa_family = AF_INET;
	//I want IP address attached to "NAME OF THE NIC INTERFACE" in this case is: wlp2s0
	strncpy(ifr.ifr_name, "wlp2s0", IFNAMSIZ-1);
	ioctl(fd, SIOCGIFADDR, &ifr);
	close(fd);
	//obtain ip and save it in ip variable ip
 	ip = (inet_ntoa(((struct sockaddr_in *)&ifr.ifr_addr)->sin_addr));
}

int main(){

	printf("\n\n\n\t=============================  WELCOME :D (Server)  =============================\n\n");
	//Initializing some things ..
  	char * idPort = "5555";
  	int conexion_servidor, conexion_cliente, port; //declaramos las variables
  	//printf("\tSet the ID of my connection port:  ");
  	//scanf("%s", idPort);
  	printf("\tI'm starting ... ... ... \n\n");
  	getIpAddress(); //get The IP of this machine

	
	socklen_t longc; //Debemos declarar una variable que contendrá la longitud de la estructura
	struct sockaddr_in servidor, cliente;
	char buffer[100]; //Declaramos una variable que contendrá los mensajes que recibamos
	port = atoi(idPort);
	conexion_servidor = socket(AF_INET, SOCK_STREAM, 0); //creamos el socket
	bzero((char *)&servidor, sizeof(servidor)); //llenamos la estructura de 0's
	servidor.sin_family = AF_INET; //asignamos a la estructura
	servidor.sin_port = htons(port);
	servidor.sin_addr.s_addr = INADDR_ANY; //esta macro especifica nuestra dirección

	if(bind(conexion_servidor, (struct sockaddr *)&servidor, sizeof(servidor)) < 0){ //asignamos un puerto al socket
		printf("Error al asociar el puerto a la conexion\n");
		close(conexion_servidor);
		return 1;
	}
	printf("\tThat's it. So, I'm:         --->  %s\n",ip);
	listen(conexion_servidor, 3); //Estamos a la escucha
	printf("\tI'm listening in the port:  --->  %d\n\n", ntohs(servidor.sin_port));
	printf("\tNow, I'll wait for somebody ...\n" );
	longc = sizeof(cliente); //Asignamos el tamaño de la estructura a esta variable
	int noConexiones = 0; //VAriable auxiliar para contar cuantas conexiones hay
	struct sockaddr_in ArraydeConexiones[4]; //Array de conexiones cada posicion es una conexion :D con determinada estructura
	int i = 0; //VAriable para recorrer e vector de conexiones

	printf("\n\n\n\t=============================  FASE DE CONEXIONES :D (Server)  =============================\n\n");
	while(noConexiones < 1){ //Empieza e cclo para establecer las conexiones
		printf(" \n\t... \n");
		//Se acepta alguna conexion y se guarda en "cliente" OJO, NO se guarda en "conexion_clente"
		//eso es por que a funcion "accept" regresa un 0 si NO se ha hecho la conexion bien
		conexion_cliente = accept(conexion_servidor, (struct sockaddr *)&cliente, &longc); //Esperamos una conexion
		ArraydeConexiones[i] = cliente; //Se guarda la conexion en este vector en la poscicion i
		if(conexion_cliente<0){
			printf("Error al aceptar trafico\n");
			close(conexion_servidor);
	    	return 1;
		}else{
			noConexiones = noConexiones + 1; //contador del numero de conexiones
			//Se muestran los detalles de la conexion en pantalla
			printf("\tI'm conected with:  %s, through the port: %d\n", inet_ntoa(ArraydeConexiones[i].sin_addr),htons(ArraydeConexiones[i].sin_port));
			printf("\tHasta el momento hay : %d vatos(s) conectado(s)", noConexiones);
			i = i +1; //Siguente posicion del vector de conexiones
		}
	}
	
	printf("\n\n\n\t=============================  CHAT :D (Server)  =============================\n\n");
	if(recv(conexion_cliente, buffer, 100, 0) < 0){ //Comenzamos a recibir datos del cliente
	    //Si recv() recibe 0 el cliente ha cerrado la conexion. Si es menor que 0 ha habido algún error.
	    printf("Error al recibir los datos\n");
	    close(conexion_servidor);
	    return 1;
	}else{
		while(buffer != "chao"){
			printf("\t- %s says: %s\n",inet_ntoa(cliente.sin_addr), buffer);
	    	bzero((char *)&buffer, sizeof(buffer));	
	    	printf("\t- You: ");
	    	scanf("%s",buffer);
	    	send(conexion_cliente, buffer,100,0);
	    	bzero(buffer,100);
		}
	}
	close(conexion_servidor);
}
