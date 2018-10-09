#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

int A[10][10];	


float calculaDistancia(int pos_depX,int pos_depY, int pos_preX, int pos_preY){
	float distancia;
	//printf("Entro a la funcion: %d, %d, %d, %d ....\n",pos_depX, pos_depY, pos_preX, pos_preY );
	distancia = sqrt((pow(pos_preX-pos_depX,2))+(pow(pos_preY-pos_depY,2))); //Formula para calcular la distancia
	return distancia;
}

void despliegaMAtriz(){
	printf("\n");
	for (int i = 0; i < 10; i++){
		printf("\n\t");
		for (int j = 0; j < 10; j++){
			printf("%d  ", A[i][j]);
		}
	}
	printf("\n");
}

char checarSentido(int pos_depX,int pos_depY){
	printf("Vienes de X: %d\n",pos_depX );
	printf("Vienes de Y: %d\n",pos_depY );
	//Se checara el sentido en el que se puede mover el depredador, en este orden especifico:
	const char *orden[4] = {"Abajo", "Derecha", "Arriba", "Izquierda"};
	char sentido;
	for (int i = 0; i < 3; i++){
		printf("Intento hacia: %s\n",orden[i] );
		if(orden[i] == "Arriba"){ //Checar si s puede mover hacia arriba
			printf("Entro arriba\n");
			if(pos_depX == 0){ //Ya no se puede subir, FIN DEL TABLERO
				continue;
			}else{
				if(A[pos_depX-1][pos_depY] == 1){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia ARRIBA, regresar el sentido
					sentido = 'U';
					return sentido;
				}
			}			
		}else if(orden[i] == "Derecha"){
			printf("Entro a la DERECHA\n");
			if(pos_depY == 9){ //Ya no se puede ir a la DERECHA, FIN DEL TABLERO
					continue;
			}else{
				if(A[pos_depX][pos_depY+1] == 1){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia DERECHA, regresar el sentido
					sentido = 'R';
					return sentido;
				}
			}
		}else if(orden[i] == "Izquierda"){
			printf("Entro a la izquierda\n");
			if(pos_depY == 0){ //Ya no se puede ir a la IZQUIERDA, FIN DEL TABLERO
					continue;
				}else{
					if(A[pos_depX][pos_depY-1] == 1){ //Se lee la nueva casilla para saber si hay obstaculo
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
				if(A[pos_depX +1][pos_depY] == 1){ //Se lee la nueva casilla para saber si hay obstaculo
					printf("\7");
					continue;
				}else{
					//Si se puede mover hacia ABAJO, regresar el sentido
					sentido = 'D';
					return sentido;
				}
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
	printf("Vector X: ");
	for (int j = 0; j < i; j++){
		printf("  %d  ", VX[j]);
	}
	printf("\n");
	printf("Vector Y: ");
	for (int j = 0; j < i; j++){
		printf("  %d  ", VY[j]);
	}
}


int main(int argc, char const *argv[]){
	//Definir la matriz que será el tablero
	//Definir la matriz:

	for (int i = 0; i < 10; i++){
		for (int j = 0; j < 10; j++){
			A[i][j] = 0;

		}
	}
	A[0][0] = 2;
	A[9][9] = 2;
	//Escribiendo los obstaculos
	//A[3][0] = 1;
	//A[6][1] = 1;
	//A[6][2] = 1;
	A[6][0] = 1;
	A[5][1] = 1;

	despliegaMAtriz();
	int pos_depX = 0;
	int pos_depY = 0;
	int pos_preX = 9;
	int pos_preY = 9;
	float distancia2, distancia;
	//Vectores que guardan los movimientos que se van haciendo
	int VX[20];
	int VY[20];

	int iteracion;
	
	distancia = calculaDistancia(pos_depX,pos_depY, pos_preX, pos_preY); //Funcion para calcular la distancia ntre presa-depredado
	printf("La distancia inicial entre el depredador y la presa es: %f\n", distancia);
	if (distancia <=1){
		printf("PRESA ALCANZADA CON EXITO\n");
	}else{
		int i = 0; //Variabler para TUS VECTORES XAVIIII
		while(distancia>0){
			getchar();
			char sentido = checarSentido(pos_depX,pos_depY);
			printf("Muevete hacia: %c\n",sentido );
			
			if(sentido == 'U'){
				pos_depX = pos_depX -1 ;
				A[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				A[pos_depX+1][pos_depY] = 1;//Esro borra tu rastro para que no te sigan los malditos
				//Osea para no ir dejando un camino de 1´s y simular más movimiento JIJIJI aplica en todos
			}else if(sentido == 'R'){
			//Mover hacia la derecha
				pos_depY = pos_depY + 1;
				A[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				A[pos_depX][pos_depY-1] = 1;
			}else if(sentido == 'L'){
				pos_depY = pos_depY - 1 ;
				A[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				A[pos_depX][pos_depY+1] = 1;
			}else if(sentido == 'D'){
				pos_depX = pos_depX + 1;
				A[pos_depX][pos_depY] = 2;
				VX[i] = pos_depX; //Lleno tu vector Xavi
				VY[i] = pos_depY;
				A[pos_depX-1][pos_depY] = 1;
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
		//imprimeVectoresMov(VX,VY,i);
	}
	return 0;
}