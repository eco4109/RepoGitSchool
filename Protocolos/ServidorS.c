#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/wait.h>
#include <signal.h>
#include <ctype.h>
#include <sys/time.h>
#define MYPORT 8086// Puerto al que conectarán los usuarios
#define MAXBUFLEN 1000 // Maximo de caracteres para el buffer
#define BACKLOG 10    // Conexiones pendientes en cola
#define STDIN  0
#define MAX 100
char buf[MAXBUFLEN];// buffer para recivir o enviar datos
//---------------------------------------------------------------------
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <fcntl.h>
#include <errno.h>
#include <termios.h>
#include <signal.h>
#include <unistd.h>
#include <signal.h>
char codigo_hacia_arduino[99];
char codigo_desde_arduino[99];
char dato[2];
int fd;   /* File descriptor for the port */
int verbose;
char ttyport[80];
int open_port(void)
{
   // intentar abrir ttyACM0,ttyACM1...
     int fd;
     for(int i = 0; i<10;i++)
     {
       sprintf(ttyport,"/dev/ttyACM%i",i);
       fd = open(ttyport,O_RDWR | O_NOCTTY);
       if(fd != -1)
       {
         printf("Conexion establecida con arduino\n");
         break;
       }
     }
   if(fd == -1)
   {

       printf("\nError: fallo la conexion del puerto ttyACM!\n\n");
       printf("%s",ttyport);
         }else
   {
           struct termios options;

           // Get the current options for the port...
           tcgetattr(fd, &options);

     // Set the baud rates to B9600...
           cfsetispeed(&options, B9600);

     // Enable the receiver and set local mode...
           options.c_cflag |= (CLOCAL | CREAD);

     // Set the new options for the port...
           tcsetattr(fd, TCSANOW, &options);

           options.c_cflag &= ~CSIZE;   /* Mask the character size bits */
           options.c_cflag |= CS8;      /* Select 8 data bits */
           options.c_cflag &= ~PARENB;
           options.c_cflag &= ~CSTOPB;
           options.c_cflag &= ~CSIZE;
           options.c_cflag |= CS8;

           fcntl(fd, F_SETFL, 0);
           return (fd);
         }

}


int enviar_hacia_arduino()
{
  FILE *file;
  file = fopen(ttyport,"w");
  fprintf(file,"%s",buf);
  fclose(file);
  return 0;
}



int recibir_desde_arduino()
{

   int n;

   n = 0;
        strcpy(codigo_desde_arduino, "");
        for(;;)
   {
          strcpy(dato, "");
          read(fd,dato,1);
          if(dato[0] ==  10)
       {
            codigo_desde_arduino[n] = '\0';
            break;
          }
       else if(dato[0] == 13);
                 else if(dato[0] > 0 && n < 98)
                 codigo_desde_arduino[n++] = dato[0];
   }
                 return strlen(codigo_desde_arduino);

}
//------------------------------------------------------------------------------
struct timeval tiempo;//estructura para tiempo
fd_set selector;//variable para Select
int sockfd,leidos; // El servidor escuchara por sockfd
int newfd; // las transferencias de datos se realizar mediante newfd
struct sockaddr_in my_addr; // contendra la direccion IP y el numero de puerto local
struct sockaddr_in their_addr;//Contendra la direccion IP y numero de puerto del cliente
socklen_t sin_size;//Contendra el tamaño de la escructura sockaddr_in


void crear_socket();
void atributos();
void socket_nombre();
void escuchar();
void valores_tiempo();


void crear_socket(){
//creando el socket

sockfd = socket(AF_INET, SOCK_STREAM, 0);
 if (sockfd<0) {
 perror("creando socket:");
 exit(1);
 }

}

void atributos(){
// Asignamos valores a la estructura my_addr para luego poder llamar a la funcion bind()
my_addr.sin_family = AF_INET; //Se sa un servidor Stream (Protocolo TCP)
my_addr.sin_port = htons(MYPORT); //se asigna el puerto por el que se va a escuchar (3490)
my_addr.sin_addr.s_addr = INADDR_ANY; // se usa la IP local
bzero(&(my_addr.sin_zero), 8); // rellena con ceros el resto de la estructura
}

void socket_nombre(){
if (bind(sockfd, (struct sockaddr *)&my_addr, sizeof(struct sockaddr)) <0)
  {
    perror("asignando direccion");
    exit(1);
  }
}

void escuchar (){
/*Empezamos a acpetar conexiones*/
// Habilitamos el socket para recibir conexiones, con una cola de 10 conexiones en espera como maximo (BACKLOG)

if (listen(sockfd, BACKLOG) == -1)
  {
    perror("listen");
    exit(1);
  }
}

void valores_tiempo(){
FD_ZERO(&selector);
	FD_SET(sockfd,&selector);//pone en ceros mi estructura selector
	tiempo.tv_sec = 15;
	tiempo.tv_usec =0;
if (select(MAXBUFLEN,&selector,(fd_set *)0,(fd_set *)0,&tiempo)<0){
 	printf("Error en Select:");//llegamos a select y hacemos que se bloquee hasta que se conecte el socket
 	exit(1);
	}
/* Si ha sido así, conectamos con el cliente */
}

int main(){

crear_socket();
atributos();
socket_nombre();
escuchar();
valores_tiempo();
//-------------
verbose = 1;
fd = 0;
fd = open_port();
//--------------------------------------


if(FD_ISSET(sockfd,&selector)){
 while(1){
  //printf("Esperando nueva conexion...\n");
  sin_size = sizeof(struct sockaddr_in);
  newfd= accept(sockfd, (struct sockaddr *)&their_addr,&sin_size);
 	if (newfd<0){
    perror("accept");
    exit(1); // Si se produce un error se finaliza el programa
       }

	else{
            printf("servidor: Se esta recibiendo conexion desde: %s\n", inet_ntoa(their_addr.sin_addr));
	    memset(buf, 0, sizeof buf);
            while((leidos=recv(newfd, buf, MAXBUFLEN, 0)) !=0){
                 if(leidos<0){
                         perror("leyendo:");
                       }
		  else {
			int long_buf,i;
			long_buf=strlen(buf);
			printf("%s\n", buf);
//----------------------------------------------------------------------------------------------------
			codigo_hacia_arduino[0]=buf[0];
			if(fd == -1) exit(0);
		         //for(;;)
	    		//{
		           printf("\n\nMensaje para arduino: ");
		           //fgets(codigo_hacia_arduino, 98, stdin);
               printf("se recibio %s\n", buf);


			if(buf[0] == '\n')
		            break;
		            if(codigo_hacia_arduino[strlen(codigo_hacia_arduino) - 1] != '\n')
		            strcat(codigo_hacia_arduino, "\n");

		            enviar_hacia_arduino();

		            if(recibir_desde_arduino())
		            printf("       Desde Arduino: %s\n", codigo_desde_arduino);
		            printf("Te recibi y te contesto");
	    		//}
//------------------------------------------------------------------------------------------------------------------
			for(int i=0;i<long_buf;i++) buf[i]=toupper(buf[i]);
			if(send(newfd,buf,long_buf,0)==-1){
				perror("llamada para enviar");
				exit(1);
			}
 			memset(buf, 0, sizeof buf);
                       }
                }printf("Conexion terminada. \n");
                  int close(int fd);
			return 0;
            }
}

}else{printf("\n(15 seg) El tiempo de espera ha terminado \n");
	int close(int fd);}
int close(int fd);
return 0;
}
