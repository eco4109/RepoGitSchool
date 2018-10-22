#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <time.h>
int main(){
  printf("\n\n\n\t=============================  WELCOME :D (POTRO)  =============================\n\n" );
  char ipAddress[20];
  char * idPortC = "5555";
  printf("\tEnter the IP address of the Server, plox: ");
  scanf("%s", ipAddress);
  //printf("\tEnter the ID PORT of the Server, plox: ");
  //scanf("%s", idPortC);
  //printf("\tThe IP is: : %s\n",ipAddress);
  //printf("\tThe port is: %s\n",idPortC);
  /*if(argc<2){
    printf("<host> <puerto>\n");
    return 1;
  }*/
  struct sockaddr_in server; //Declaración de la estructura con información para la conexión
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
  bzero((char *)&server, sizeof((char *)&server)); //Rellena toda la estructura de 0's
        //La función bzero() es como memset() pero inicializando a 0 todas la variables
  server.sin_family = AF_INET; //asignacion del protocolo
  server.sin_port = htons(puerto); //asignacion del puerto
  bcopy((char *)servidor->h_addr, (char *)&server.sin_addr.s_addr, sizeof(servidor->h_length));
  //bcopy(); copia los datos del primer elemendo en el segundo con el tamaño máximo 
  //del tercer argumento.


  //cliente.sin_addr = *((struct in_addr *)servidor->h_addr); //<--para empezar prefiero que se usen
  //inet_aton(argv[1],&cliente.sin_addr); //<--alguna de estas dos funciones
  printf("\n\tTrying to reach the server %s, through the port %d\n",inet_ntoa(server.sin_addr),htons(server.sin_port));
  if(connect(conexion,(struct sockaddr *)&server, sizeof(server)) < 0){ //conectando con el host
    printf("Error conectando con el host\n");
    close(conexion);
    return 1;
  }
  printf("\n\n\n\t=============================  SERVER INFORMATION :D   =============================\n\n");
  printf("\tI'm conected with the Server Sucessfully (%s), through the port: %d :)\n\n",inet_ntoa(server.sin_addr),htons(server.sin_port));
  //Get the time in which the POTROBUS left the first station
  time_t t;
  struct tm *tm;
  char horaPartida[50];
  t = time(NULL);
  tm=localtime(&t);
  strftime(horaPartida, 100, "%H:%M:%S",tm);
  printf("\tStart time: %s\n", horaPartida );
  send(conexion, horaPartida, 100, 0); //envio
  printf("\n\n\n\t=============================   STATUS INFORMATION     ============================\n\n");
  char * buffer2 = "Moving";
  while(buffer != "chao"){
    //printf("\t- You: ");
    //scanf("%s", buffer);
    printf("\tPotroBus state: %s\n", buffer2);
  	//send(conexion, buffer, 100, 0); //envio
    send(conexion, buffer2, 100, 0); //envio
    usleep(1500000);
    //printf("Se envio: %s\n",buffer );
  	//bzero((char * )&buffer, sizeof(buffer));
    //recv(conexion, buffer, 100, 0); //recepción
  	//printf("%s", buffer);
  }  

return 0;
}
