#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<netdb.h>
int main(int argc, char **argv){
  if(argc<2)
  { //Especifica los argumentos
    printf("%s <puerto>\n",argv[0]);
    return 1;
  }
  int conexion_servidor, conexion_cliente, puerto; //declaramos las variables
  socklen_t longc; //Debemos declarar una variable que contendrá la longitud de la estructura
  struct sockaddr_in servidor, cliente;
  char buffer[100]; //Declaramos una variable que contendrá los mensajes que recibamos
  puerto = atoi(argv[1]);
  conexion_servidor = socket(AF_INET, SOCK_STREAM, 0); //creamos el socket
  bzero((char *)&servidor, sizeof(servidor)); //llenamos la estructura de 0's
  servidor.sin_family = AF_INET; //asignamos a la estructura
  servidor.sin_port = htons(puerto);
  servidor.sin_addr.s_addr = INADDR_ANY; //esta macro especifica nuestra dirección
  if(bind(conexion_servidor, (struct sockaddr *)&servidor, sizeof(servidor)) < 0)
  { //asignamos un puerto al socket
    printf("Error al asociar el puerto a la conexion\n");
    close(conexion_servidor);
    return 1;
  }
  listen(conexion_servidor, 3); //Estamos a la escucha
  printf("A la escucha en el puerto %d\n", ntohs(servidor.sin_port));
  longc = sizeof(cliente); //Asignamos el tamaño de la estructura a esta variable
  conexion_cliente = accept(conexion_servidor, (struct sockaddr *)&cliente, &longc); //Esperamos una conexion
  if(conexion_cliente<0)
  {
    printf("Error al aceptar trafico\n");
    close(conexion_servidor);
    return 1;
  }
  printf("Conectando con %s:%d\n", inet_ntoa(cliente.sin_addr),htons(cliente.sin_port));
  if(recv(conexion_cliente, buffer, 100, 0) < 0)
  { //Comenzamos a recibir datos del cliente
    //Si recv() recibe 0 el cliente ha cerrado la conexion. Si es menor que 0 ha habido algún error.
    printf("Error al recibir los datos\n");
    close(conexion_servidor);
    return 1;
  }
  else
  {
    printf("%s\n", buffer);
    bzero((char *)&buffer, sizeof(buffer));
    send(conexion_cliente, "Recibido\n", 13, 0);
  }
  close(conexion_servidor);
  return 0;
}
