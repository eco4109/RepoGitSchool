#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

int main(){
  printf("\n\n\n\t=============================  WELCOME :D (Client)  =============================\n\n" );
  char ipAddress[20];
 
  char * idPortC = "5555";
  printf("\tEnter the IP address of the Server, plox: ");
  scanf("%s", ipAddress);
  //printf("\tEnter the ID PORT of the Server, plox: ");
  //scanf("%s", idPortC);
  printf("\tThe IP is: : %s\n",ipAddress);
  printf("\tThe port is: %s\n",idPortC);
  /*if(argc<2){
    printf("<host> <puerto>\n");
    return 1;
  }*/
  struct sockaddr_in cliente; //Declaración de la estructura con información para la conexión
  struct hostent *servidor; //Declaración de la estructura con información del host
  servidor = gethostbyname(ipAddress); //Asignacion
  if(servidor == NULL){ //Comprobación
    printf("Host erróneo\n");
    return 1;
  }
  int puerto, conexion;
  char buffer[100];
  conexion = socket(AF_INET, SOCK_STREAM, 0); //Asignación del socket
  puerto=(atoi(idPortC)); //conversion del argumento
  bzero((char *)&cliente, sizeof((char *)&cliente)); //Rellena toda la estructura de 0's
        //La función bzero() es como memset() pero inicializando a 0 todas la variables
  cliente.sin_family = AF_INET; //asignacion del protocolo
  cliente.sin_port = htons(puerto); //asignacion del puerto
  bcopy((char *)servidor->h_addr, (char *)&cliente.sin_addr.s_addr, sizeof(servidor->h_length));
  //bcopy(); copia los datos del primer elemendo en el segundo con el tamaño máximo 
  //del tercer argumento.


  //cliente.sin_addr = *((struct in_addr *)servidor->h_addr); //<--para empezar prefiero que se usen
  //inet_aton(argv[1],&cliente.sin_addr); //<--alguna de estas dos funciones
  printf("\n\tTrying to reach the server %s, through the port %d\n",inet_ntoa(cliente.sin_addr),htons(cliente.sin_port));
  if(connect(conexion,(struct sockaddr *)&cliente, sizeof(cliente)) < 0){ //conectando con el host
    printf("Error conectando con el host\n");
    close(conexion);
    return 1;
  }
  //printf("\tI'm conected with %s, trought the port: %d\n",inet_ntoa(cliente.sin_addr),htons(cliente.sin_port));
  printf("\tConnection Succesfully :)\n");


  printf("\n\n\n\t=============================  STATUS INFORMATION :D (Client)  =======================\n\n");
  
  //Starts to receive information about the POTROBUS
  if (recv(conexion, buffer, 100, 0)< 0){
  	printf("ERROR AL RECIBIR LOS DATOS");
  	close(conexion);
  	return 1;
  }else{
  	//Ciclo para recibir continuamene los datos del POTROBUS
  	while(buffer != "chao"){
  		//printf("\tPotroBu State: %s\n", buffer);
  		//recv(conexion, buffer, 100, 0);
 		
 		printf("EStado de recepcion de datos%s\n",buffer);
  		bzero((char *)&buffer, sizeof(buffer));
 		recv(conexion, buffer, 100, 0);
  		
  		//send(conexion, buffer, 100, 0);
  	}
  }

return 0;
}
