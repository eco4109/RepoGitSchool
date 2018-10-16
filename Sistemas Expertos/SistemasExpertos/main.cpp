/*
 * GLUT Shapes Demo
 *
 * Written by Nigel Stewart November 2003
 *
 * This program is test harness for the sphere, cone
 * and torus shapes in GLUT.
 *
 * Spinning wireframe and smooth shaded shapes are
 * displayed until the ESC or q key is pressed.  The
 * number of geometry stacks and slices can be adjusted
 * using the + and - keys.
 */
#include<windows.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

int estX=0, estY=0, o=0;;
int enteroX=0,enteroY=0;
int coorX = 0, coorY=0;
int posicionMonoX=0,posicionMonoY=0;
int pos_depX = 0;
int pos_depY = 0;
int pos_preX = 9;
int pos_preY = 9;
float distancia2, distancia;
//Vectores que guardan los movimientos que se van haciendo
int VX[20];
int VY[20];
int iteracion;

//Declaracion de funciones
void creaTablero();
void pegaImagenAlpha();
void cargaObstaculo();
void creaObstaculos();
void usarTexturaAlpha();
void insertaObstaculos();
void imprimeVectoresMov(int VX[],int VY[], int i);
void insertaMono(int posMonoX,int posMonoY);
void hazMovimiento();
float calculaDistancia(int pos_depX,int pos_depY, int pos_preX, int pos_preY);
//char checarSentido(int pos_depX,int pos_depY);
int regresaMov(char sentido, int pos_depX,int pos_depY);
//unsigned char * cargaObstaculo();
int calculaLoBueno();
char checarSentido(int pos_depX,int pos_depY, char movAnterior);


int mObstaculos[9][9];
int yaSeGenero = 0;
int movX=0,movY=0;
int coordenadasX[] = {1};
int coordenadasY[] = {1};
int vectorCalculado = 0;

unsigned char * datos1[10];
unsigned char * mono;
const GLfloat light_ambient[]  = { 0.0f, 0.0f, 0.0f, 1.0f };
const GLfloat light_diffuse[]  = { 1.0f, 1.0f, 1.0f, 1.0f };
const GLfloat light_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
const GLfloat light_position[] = { 2.0f, 5.0f, 5.0f, 0.0f };

const GLfloat mat_ambient[]    = { 0.7f, 0.7f, 0.7f, 1.0f };
const GLfloat mat_diffuse[]    = { 0.8f, 0.8f, 0.8f, 1.0f };
const GLfloat mat_specular[]   = { 1.0f, 1.0f, 1.0f, 1.0f };
const GLfloat high_shininess[] = { 100.0f };


/*puebas

*/
//char *str[5] = {"holi","perro","infeliz","comes","i,i"};

unsigned char * leerImagen( const char * ruta,unsigned char * dato,int alto,int ancho){
    FILE *imagen;
    imagen=fopen(ruta,"r");
    if(imagen==NULL){printf("Error: No imagen");}
    else{
           // printf("Imagen cargada correctamente");
    }

    dato=(unsigned char*)malloc(ancho*alto*3);
    fread(dato,alto*ancho*3,1,imagen);
    fclose(imagen);
    return dato;

}
void usarTextura(unsigned char * dato,int alto,int ancho){

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, ancho, alto, 0, GL_RGB, GL_UNSIGNED_BYTE, dato);

}
void pegaImagen(char * ruta,unsigned char * dato,int alto,int ancho){
    //glPushMatrix();
    usarTextura(leerImagen(ruta,dato,alto,ancho),alto,ancho);
    glEnable(GL_TEXTURE_2D);

    glColor3f(1, 1, 1);

    //glTranslatef((posx*0.01)-2.5,(posy*0.01)-2.5,0);
    //glRotatef(ang, 0, 0, 1);
    glTranslatef(1.5, -1.5, 0);
    glBegin(GL_QUADS);

    glTexCoord2f(1.0, 1.0);glVertex2f(2.0, -3.5);
    glTexCoord2f(0.0, 1.0);glVertex2f(0.0, -3.5);
    glTexCoord2f(0.0, 0.0);glVertex2f(0.0, 1.5);
    glTexCoord2f(1.0, 0.0);glVertex2f(2.0, 1.5);
    glEnd();
    //glPopMatrix();

}
void usarTexturaAlpha(unsigned char * dato,int alto,int ancho){

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, ancho, alto, 0, GL_RGBA, GL_UNSIGNED_BYTE, dato);

}
unsigned char * cargaObstaculo(const char * ruta,unsigned char * dato,int alto,int ancho){
    FILE *imagen;
    imagen=fopen(ruta,"r");
    if(imagen==NULL){printf("Error: No imagen");}
    else{
           // printf("Imagen cargada correctamente");
    }
    dato=(unsigned char*)malloc(ancho*alto*4);
    fread(dato,ancho*alto*4,1,imagen);
    fclose(imagen);
    return dato;

}
unsigned char * cargaMono(const char * ruta,unsigned char * dato,int alto,int ancho){
    FILE *imagen;
    imagen=fopen(ruta,"r");
    if(imagen==NULL){printf("Error: No imagen");}
    else{
           // printf("Imagen cargada correctamente");
    }
    dato=(unsigned char*)malloc(ancho*alto*4);
    fread(dato,ancho*alto*4,1,imagen);
    fclose(imagen);
    return dato;

}
void pegaImagenAlpha(char * ruta,unsigned char * dato,int alto,int ancho){
    // glPushMatrix();
    usarTexturaAlpha(cargaObstaculo(ruta,dato,alto,ancho),alto,ancho);
    glEnable(GL_TEXTURE_2D);
    glEnable(GL_ALPHA_TEST);

    glColor3f(1, 1, 1);

    glTranslatef(-0.5, -0.5, 0);
    glBegin(GL_QUADS);

    glTexCoord2f(1.0, 1.0);glVertex2f(2.0, 0.0);

    glTexCoord2f(0.0, 1.0);glVertex2f(0.0, 0.0);
    glTexCoord2f(0.0, 0.0);glVertex2f(0.0, 1.0);
    glTexCoord2f(1.0, 0.0);glVertex2f(2.0,1.0);
    glEnd();
    //glPopMatrix();
    glDisable(GL_ALPHA_TEST);

}
void pegaImagenAlphaMono(char * ruta,unsigned char * dato,int alto,int ancho){
    // glPushMatrix();
    usarTexturaAlpha(cargaMono(ruta,dato,alto,ancho),alto,ancho);
    glEnable(GL_TEXTURE_2D);
    glEnable(GL_ALPHA_TEST);

    glColor3f(1, 1, 1);

    glTranslatef(-0.5, -0.5, 0);
    glBegin(GL_QUADS);

    glTexCoord2f(1.0, 1.0);glVertex2f(2.0, 0.0);

    glTexCoord2f(0.0, 1.0);glVertex2f(0.0, 0.0);
    glTexCoord2f(0.0, 0.0);glVertex2f(0.0, 1.0);
    glTexCoord2f(1.0, 0.0);glVertex2f(2.0,1.0);
    glEnd();
    //glPopMatrix();
    glDisable(GL_ALPHA_TEST);

}
void moveMouse(int x,int y){
    //printf("x:%d\ty:%d\n",x,y);
    estX = (10*x)/400;
    estY = (20*y)/450;
    if((estX/1) +1<=10 && estY/1<=10){
        enteroX = (estX/1) +1;
        enteroY = estY/1;
    }else{
        enteroX =10;
        enteroY =10;
    }

    //printf("EnteroX: %d, EnteroY: %d\n",enteroX,enteroY);
    glutPostRedisplay();

}
void despliegaMAtriz(){
	printf("\n");
	for (int i = 0; i < 10; i++){
		printf("\n\t");
		for (int j = 0; j < 10; j++){
			if (mObstaculos[i][j] == -1){
				printf("0  ");
			}else{
				printf("%d  ", mObstaculos[i][j]);
			}
		}
	}
	printf("\n");
}
void creaObstaculos(){
    int coordenadaX = 0;
    int coordenadaY = 0;
    if (yaSeGenero==0){
    srand(time(NULL));
    /*Esto es para los obstaculos*/
    for(int i=0; i<10;i++){

        coordenadaX = rand() % 11;

        coordenadaY = rand() % 11;
        if (coordenadaX==1 && coordenadaY==1){coordenadaX = rand() % 11;coordenadaY = rand() % 11;}
        if (coordenadaX==10 && coordenadaY==10){ coordenadaX = rand() % 11;coordenadaY = rand() % 11;}
        printf("Coordenadas: %d,%d\n",coordenadaX,coordenadaY);
        printf("Coordenadas que van a tener los 1s%d\t%d\n",coordenadaX,coordenadaY);
        mObstaculos[coordenadaX][coordenadaY] = 1;
        mObstaculos[9][9] = 2;
        mObstaculos[0][0] = 2;




    }

    for(int i = 0; i<10;i++){
        for(int j = 0; j < 10; j++){
            if (mObstaculos[i][j] != 1){
                mObstaculos[i][j] == 0;
                //printf("Asginamos los 0 para los no obstaculos\n");

            }
        }

    }

    //imprimiendo lo que tiene la matriz
    for(int i = 0; i<10;i++){
        for(int j = 0; j < 10; j++){
            printf("%d\t",mObstaculos[i][j]);
        }
        printf("\n");
    }
    yaSeGenero = 1;
    despliegaMAtriz();
	int pos_depX = 0;
	int pos_depY = 0;
	int pos_preX = 9;
	int pos_preY = 9;
	float distancia2, distancia;
	//Vectores que guardan los movimientos que se van haciendo

	char movAnterior = 'N';

	distancia = calculaDistancia(pos_depX,pos_depY, pos_preX, pos_preY); //Funcion para calcular la distancia ntre presa-depredado
	printf("La distancia inicial entre el depredador y la presa es: %f\n", distancia);
	if (distancia <=1){
		printf("PRESA ALCANZADA CON EXITO\n");
	}else{
		int i = 0; //Variabler para TUS VECTORES XAVIIII
		while(distancia>0){
			getchar();
			char sentido = checarSentido(pos_depX,pos_depY, movAnterior);
			movAnterior = sentido;
			printf("Muevete hacia: %c\n",sentido );

			if(sentido == 'U'){
				pos_depX = pos_depX -1 ;
				mObstaculos[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				mObstaculos[pos_depX+1][pos_depY] = -1;//Esro borra tu rastro para que no te sigan los malditos

				//Osea para no ir dejando un camino de 1´s y simular más movimiento JIJIJI aplica en todos
			}else if(sentido == 'R'){
			//Mover hacia la derecha
				pos_depY = pos_depY + 1;
				mObstaculos[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				mObstaculos[pos_depX][pos_depY-1] = -1;
			}else if(sentido == 'L'){
				pos_depY = pos_depY - 1 ;
				mObstaculos[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				mObstaculos[pos_depX][pos_depY+1] = -1;
			}else if(sentido == 'D'){
				pos_depX = pos_depX + 1;
				mObstaculos[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				mObstaculos[pos_depX-1][pos_depY] = -1;
			}
			i++;
			printf("Quedaste en X: %d\n", pos_depX);
			printf("Quedaste en Y: %d\n", pos_depY);
			despliegaMAtriz();
			distancia = calculaDistancia(pos_depX,pos_depY, pos_preX, pos_preY);
			printf("LA distancia es: %f\n",distancia );
		}
		printf("PRESA ALCANZADA CON EXITO\n");
		//printf("Vectores de movimiento XV y XY\n");
		imprimeVectoresMov(VX,VY,i);
	}
    }

}
void mouse(int boton, int estado,int x,int y){

    if(boton == GLUT_LEFT_BUTTON && estado == GLUT_DOWN){
        printf("He hecho click");
        printf("Coordenadas(%d,%d)\n", enteroX,enteroY);
        posicionMonoX = enteroX;
        posicionMonoY = enteroY;
        //glPushMatrix();
        //glTranslatef(enteroX,enteroY, 0);


    }else if(boton == GLUT_LEFT_BUTTON){

       // printf("Clic para enviar");
       // printf("Coordenada de ataque: %d %d\n",enteroX,enteroY)

    }
    glutPostRedisplay();
    }
void creaTablero(){

    for(int i=1; i<12;i++){
        glPushMatrix();
        glBegin(GL_LINES);
        glColor3f(0,0,0);
        glVertex2f(0,-i);
        glVertex2f(20,-i);
        glEnd();
        glPopMatrix();
    }
    creaObstaculos();
    //vectorCalculado = calculaLoBueno();


    for(int i=2; i<24;i=i+2){
            glPushMatrix();
        glBegin(GL_LINES);
        if (i == 20){
            glColor3f(0,0,0);

        }else{
            glColor3f(0,0,0);
        }
        glVertex2f(i,-1);
        glVertex2f(i,-11);

        glEnd();
        glPopMatrix();
    }
   // printf("Vamo a ver");
    //creaObstaculos();

}
/* GLUT callback Handlers */
static void resize(int width, int height){
    const float ar = (float) width / (float) height;

    glViewport(0, 0, width, height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glFrustum(-ar, ar, -1.0, 1.0, 2.0, 100.0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity() ;
}
static void display(void){
    //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); esto es para 3D

    glClear(GL_COLOR_BUFFER_BIT);
    creaTablero();
    insertaObstaculos();
    //insertaMono(posicionMonoX,posicionMonoY);

    //glutPostRedisplay();
    glFlush();
}
static void key(unsigned char key, int x, int y){
    int i=0;

    switch (key)

    {
        case 'a':
           // int salida = calculaLoBueno();
            printf("El vector en la posicion x,y: (%d,%d)",VX[o],VY[o]);
            if(o == sizeof(VX)/4)o=0;
            o = o+1;
            printf("presione a: %d",o);
            //return salida;
            break;

    }

    glutPostRedisplay();
}
static void idle(void){
    glutPostRedisplay();
}
/* Program entry point */
void insertaObstaculos(){
    for(int i = 1; i<11;i++){
        for(int j = 1; j<11; j++){
            if (mObstaculos[i-1][j-1] == 1){
                //printf("Las coordenadas son: %d,%d\n", i,j);
                glPushMatrix();
                    glScalef(2,1,0);
                    glTranslatef(j-.8 ,-i-.6,0);
                    glScalef(.45,1.7,0);
                    pegaImagenAlpha("C:\\Users\\Xavier\\Desktop\\RepoGitSchool\\Sistemas Expertos\\SistemasExpertos\\obs.data",datos1[i],128,128);
                //glPopMatrix();
                glPopMatrix();
            }
        }
    }
    glPushMatrix();
        glScalef(2,1,0);
        glTranslatef(VY[o]+.3,-VX[o]-1.4,0);/*el ajuste en x: es el -1 si es que la coordenada empieza en 0, pero si no, entonces empieza en 1 el ajuste es 0
                                       el del y es el -.5 y ese es igual que en el X si empieza en 0 o 1*/
        glScalef(.45,1.7,0);
        pegaImagenAlphaMono("C:\\Users\\Xavier\\Desktop\\RepoGitSchool\\Sistemas Expertos\\SistemasExpertos\\mon.data",mono,128,128);
    glPopMatrix();
    glPushMatrix();
        glScalef(2,1,0);
        glTranslatef(9.3,-10.5,0);
        glScalef(.5,.5,0);
        pegaImagenAlphaMono("C:\\Users\\Xavier\\Desktop\\RepoGitSchool\\Sistemas Expertos\\SistemasExpertos\\ob.data",mono,128,128);
    glPopMatrix();
    //hazMovimiento();


}



void hazMovimiento(){
    int longitud = sizeof(coordenadasX)/4;

}
float calculaDistancia(int pos_depX,int pos_depY, int pos_preX, int pos_preY){
	float distancia;
	//printf("Entro a la funcion: %d, %d, %d, %d ....\n",pos_depX, pos_depY, pos_preX, pos_preY );
	distancia = sqrt((pow(pos_preX-pos_depX,2))+(pow(pos_preY-pos_depY,2))); //Formula para calcular la distancia
	return distancia;
}
char checarSentido(int pos_depX,int pos_depY, char movAnterior){
	printf("Vienes de X: %d\n",pos_depX );
	printf("Vienes de Y: %d\n",pos_depY );
	//Se checara el sentido en el que se puede mover el depredador, en este orden especifico:
	const char *orden[5] = {"Abajo", "Derecha", "Arriba", "Izquierda", "Encerrado"};
	char sentido;
	for (int i = 0; i <= 4; i++){
		//printf("Intento hacia: %s\n",orden[i] );
		if(orden[i] == "Arriba"){ //Checar si s puede mover hacia arriba
			//printf("Entro arriba\n");
			if(pos_depX == 0){ //Ya no se puede subir, FIN DEL TABLERO
				continue;
			}else{
				if((mObstaculos[pos_depX-1][pos_depY] == 1)||(mObstaculos[pos_depX-1][pos_depY] == -1)){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia ARRIBA, regresar el sentido
					sentido = 'U';
					return sentido;
				}
			}
		}else if(orden[i] == "Derecha"){
			//printf("Entro a la DERECHA\n");
			if(pos_depY == 9){ //Ya no se puede ir a la DERECHA, FIN DEL TABLERO
					continue;
			}else{
				if((mObstaculos[pos_depX][pos_depY+1] == 1)||(mObstaculos[pos_depX][pos_depY+1] == -1)){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia DERECHA, regresar el sentido
					sentido = 'R';
					return sentido;
				}
			}
		}else if(orden[i] == "Izquierda"){
			//printf("Entro a la izquierda\n");
			if(pos_depY == 0){ //Ya no se puede ir a la IZQUIERDA, FIN DEL TABLERO
				continue;
			}else{
				if((mObstaculos[pos_depX][pos_depY-1] == 1)||(mObstaculos[pos_depX][pos_depY-1] == -1)){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia ARRIBA, regresar el sentido
					sentido = 'L';
					return sentido;
				}
			}
		}else if(orden[i] == "Abajo"){
			printf("Entro a  Abajo\n");
			if(pos_depX == 9){ //Ya no se puede ir hacia abajo, FIN DEL TABLERO
				continue;
			}else{
				if((mObstaculos[pos_depX +1][pos_depY] == 1)||(mObstaculos[pos_depX +1][pos_depY] == -1)){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia ABAJO, regresar el sentido
					sentido = 'D';
					return sentido;
				}
			}
		}else if(orden[i] == "Encerrado"){
			printf("Entro a Encerrado\n");
			//Cuando estoy totalomente encerrado, YO ME ENCERRÉ JAJAJA regresarme xD
			switch(movAnterior){
				case 'D':
					sentido = 'U';
					return sentido;
					break;
				case 'R':
					sentido = 'L';
					break;
			}
		}
	}
}
int regresaMov(char sentido, int pos_depX,int pos_depY){
	if(sentido == 'U'){ //Rgrsar hacia abajo
		pos_depX = pos_depX - 1;
	}else if(sentido == 'R'){ //Regresar a a izquerda
		pos_depY = pos_depY - 1;
	}else if(sentido == 'L'){ //Regresar a la derecha
		pos_depY= pos_depY + 1;
	}else if(sentido == 'D'){ //Regresar hacia arriba
		pos_depX = pos_depX +1;
	}
}
void imprimeVectoresMov(int VX[],int VY[], int i){
	printf("Vector Original X: ");
	for (int j = 0; j < i; j++){
        coordenadasX[j] = VX[j];
		printf("  %d  ", VX[j]);
	}
	printf("\n");
	printf("Vector Original Y: ");
	for (int j = 0; j < i; j++){
	    coordenadasY[j] =VY[j];
		printf("  %d  ", VY[j]);
	}
}


int main(int argc, char *argv[]){
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB |GLUT_SINGLE);
    glutInitWindowSize(400,450);
  //   glutInitWindowSize(600,600);
    glutCreateWindow("Encontrando la ruta mas corta");
    gluOrtho2D(0, 20, -20, 0);
    glClearColor(0.5,0.6,1,0);
   // gluOrtho2D(0.8,2.2,0.8,2.2);
    glPointSize(12);
    //teclado
    glutKeyboardFunc(key);
    //menu de clic drecho
    //crearMenu();
    glutDisplayFunc(display);
    //glutTimerFunc(1000,temporizador,1);
     //glEnable(GL_ALPHA_TEST);
    glAlphaFunc(GL_GREATER,0.1);
    //agregamos el mouse
    glutPassiveMotionFunc(moveMouse);
    glutMouseFunc(mouse);
    glutMainLoop();
    return EXIT_SUCCESS;
}
