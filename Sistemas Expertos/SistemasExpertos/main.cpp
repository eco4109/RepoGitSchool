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

#include <stdlib.h>

#include <stdio.h>

int estX=0, estY=0;
int enteroX=0,enteroY=0;
int coorX = 0, coorY=0;

//Declaracion de funciones
void creaTablero();
void pegaImagenAlpha();
void cargaObstaculo();
void creaObstaculos();
void usarTexturaAlpha();
void insertaObstaculos();
//unsigned char * cargaObstaculo();



int mObstaculos[10][10];
int yaSeGenero = 0;
unsigned char * datos1[10];
unsigned char * datos;



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

void creaObstaculos(){
    int coordenadaX = 0;
    int coordenadaY = 0;
    if (yaSeGenero==0){
    for(int i=0; i<10;i++){
        coordenadaX = rand() % 11;
        coordenadaY = rand() % 11;
        printf("Coordenadas que van a tener los 1s%d\t%d\n",coordenadaX,coordenadaY);
        mObstaculos[coordenadaX][coordenadaY] = 1;


    }

    for(int i = 0; i<10;i++){
        for(int j = 0; j < 10; j++){
            if (mObstaculos[i][j] != 1){
                mObstaculos[i][j] == 0;
                printf("Asginamos los 0 para los no obstaculos\n");

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
    }

}
void mouse(int boton, int estado,int x,int y){

    if(boton == GLUT_LEFT_BUTTON && estado == GLUT_DOWN){
        printf("He hecho click");
        printf("Coordenadas(%d,%d)\n", enteroX,enteroY);
        coorX = enteroX;
        coorY = enteroY;
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

static void resize(int width, int height)
{
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
    //glutPostRedisplay();
    glFlush();
}


static void key(unsigned char key, int x, int y)
{
    switch (key)
    {
        case 27 :
        break;
    }

    glutPostRedisplay();
}

static void idle(void)
{
    glutPostRedisplay();
}

const GLfloat light_ambient[]  = { 0.0f, 0.0f, 0.0f, 1.0f };
const GLfloat light_diffuse[]  = { 1.0f, 1.0f, 1.0f, 1.0f };
const GLfloat light_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
const GLfloat light_position[] = { 2.0f, 5.0f, 5.0f, 0.0f };

const GLfloat mat_ambient[]    = { 0.7f, 0.7f, 0.7f, 1.0f };
const GLfloat mat_diffuse[]    = { 0.8f, 0.8f, 0.8f, 1.0f };
const GLfloat mat_specular[]   = { 1.0f, 1.0f, 1.0f, 1.0f };
const GLfloat high_shininess[] = { 100.0f };

/* Program entry point */
void insertaObstaculos(){
    for(int i = 1; i<11;i++){
        for(int j = 1; j<11; j++){
            if (mObstaculos[i-1][j-1] == 1){
                //printf("Las coordenadas son: %d,%d\n", i,j);
                glPushMatrix();
                    glScalef(2,1,0);
                    glTranslatef(j-.7 ,-i,0);
                    glScalef(.45,1.7,0);
                    pegaImagenAlpha("C:\\Users\\Xavier\\Desktop\\RepoGitSchool\\Sistemas Expertos\\SistemasExpertos\\obstaculos.data",datos1[i],128,128);
                //glPopMatrix();
                glPopMatrix();

            }
        }
    }

}
int main(int argc, char *argv[])
{
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
