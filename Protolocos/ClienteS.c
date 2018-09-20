#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <netdb.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <signal.h>
#define PORT 8086 // puerto al que vamos a conectar
#define MAXBUFLEN 100  // Maximo de caracteres para el buffer
#define MAX 100

char num[10],opcn[1];
int sockfd, numbytes;
char buf[MAXBUFLEN];
struct hostent *host;
struct sockaddr_in their_addr; // información de la dirección de destino
size_t len;
void crear_socket();
void menu();
void conectar();
void atributos();
void host_name(char *argv[]);
void enviar(char cadena[]);
void valor();
void menu(){
int n;

        n=1;
	while(n!=0){
	char ejemplo[100];
        fflush(stdin);
	puts("Escriba la instruccion que desea ejecutar:\n1. Encender carro \n0. Apagar carro\n");
	fgets(buf,MAXBUFLEN,stdin);
	enviar(buf);
	fflush(stdin);
        }
}

void sig_handler(int signo)//Validacion de las combinaciones de teclas ctrl+c y ctrl+z
{
    char opc;
    if (signo == SIGINT){
        printf("Deseas salir del programa?\n");
        scanf("%c", &opc);
        if(opc=='y'){
            printf("Presiona nuevamente CRTL+C\n");
            signal(SIGINT, SIG_DFL);
        }else if(opc=='n'){

            signal(SIGINT, SIG_IGN);
        }
    }
    else if (signo == SIGTSTP){
        printf("Deseas salir del programa? y/n\n");
        scanf("%c", &opc);
        if(opc=='y'){
            printf("Preisona nuevamente CRTL+Z\n");
            signal(SIGTSTP, SIG_DFL);
        }else if(opc=='n'){

            signal(SIGTSTP, SIG_IGN);
        }
    }
}

void valor()
{

    if (signal(SIGINT, sig_handler) == SIG_ERR)
        printf("\nNo se reconoce comando\n");
    if (signal(SIGTSTP, sig_handler) == SIG_ERR)
        printf("\nNo se reconoce comando\n");

}

void host_name(char *argv[])//Se utiliza para convertir un nombre de un host a su dirección IP
{
  if ((host=gethostbyname(argv[1])) == NULL)
  {
    perror("Error al convertir el nombre de la direccion a su direccion IP\n");
    exit(1);
  }
}

void crear_socket()//se crea el socket para comunicarse con el servidor
{
  if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
  {
    perror("Error al crear el socket\n");
    exit(1);
  }
}

void conectar()// nos conectamos con el servidor
{
  if (connect(sockfd, (struct sockaddr *)&their_addr,sizeof(struct sockaddr)) == -1)
  {
    perror("Error al conectar\n");
    exit(1);
  }
}

void atributos()//definimos el tipo de transferencia,puerto y la ip con la que nos vamos a comunicar
{
  len = sizeof(struct sockaddr_in);
  their_addr.sin_family = AF_INET;    // Ordenación de bytes de la máquina
  their_addr.sin_port = htons(PORT);  // short, Ordenación de bytes de la red
  their_addr.sin_addr = *((struct in_addr *)host->h_addr);// se pasa la direccion ip al socket
  memset(&(their_addr.sin_zero), 8, len);  // poner a cero el resto de la estructura
}

void enviar(char cadena[])//envio de datos
{

  if ((send(sockfd, cadena, strlen(cadena), 0)) == -1)
  {
    perror("Error al enviar al mensaje");
    exit(1);
  }
  printf("%s",buf);
  if(recv(sockfd, buf, MAXBUFLEN, 0)==-1){
	perror("Error a la respuesta del servidor\n");
	exit(1);
  }
  printf("\nRespuesta del servidor:\n\n%s\n",buf);
}


int main(int argc, char *argv[])//metodo principal del programa
{
  if (argc != 2)
  {
    fprintf(stderr,"usaste solo: %d argumento, escribe también el nombre del servidor\n",argc);
    exit(1);
  }

  host_name(argv);

  crear_socket();

  atributos();

  conectar();

  valor();

  menu();

  close(sockfd);
  return 0;
}
