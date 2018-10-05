#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

void imprimeMatriz(int Mtrx[][2]){ //Funcion para pintar una matriz de 2x2 así bien bonito
	for (int a = 0; a < 2; ++a){
		printf("\t");
		for (int b = 0; b < 2; ++b){
			printf("[ %d ] ",Mtrx[a][b]);
		}
		printf("\n");
	}
}


int main(){
   printf("\n\t----------  MULTIPLICACION DE MATRCES CREW  ----------\n");
	int i, l , j, k, x, A[2][2], B[2][2],C[2][2][2],C2[2][2][2];
	printf("\n\tLlenado de la Matriz A:\n");
	i=0;
	while(i<2){
		j=0;
		while(j<2){
			printf("\n\tIngrese el valor de la celda (%d,%d): ",i+1,j+1);
			scanf("%d",&x);
			A[i][j]=x;
			j++;
		}
		i++;
	}
	printf("\n\tLa Matriz A queda así: \n\n");
	imprimeMatriz(A);

	printf("\n\n\n\tLlenado de la Matriz B:\n");
	i=0;
	while(i<2){
		j=0;
		while(j<2){
			printf("\n\tIngrese el valor de la celda (%d,%d): ",i+1,j+1);
			scanf("%d",&x);
			B[i][j]=x;
			j++;
		}
		i++;
	}
	printf("\n\tLa Matriz B queda así: \n\n");
	imprimeMatriz(B);



	#pragma omp parallel
	{
	    #pragma omp for private (k)
	    for (k=0;k<2;k++){
            #pragma omp parallel for private(i)
            for(i=0;i<2;i++){
                #pragma omp parallel for private(j)
                for(j=0;j<2;j++){
                    C[k][i][j]=A[i][k]*B[k][j];
                }
            }
	    }
	}

    for(l=0;l<log2(4.0);l++){
        #pragma omp parallel
        {
        #pragma omp parallel for shared(C) private(i)
        for(i=0;i<2;i++){
            #pragma omp parallel for private(j)
            for(j=0;j<2;j++){
                #pragma omp parallel for private(k)
                for(k=0;k<1;k++){
                    if( ( 2*k % 2^l )==0 ){
                        C2[2*k][i][j] = C[2*k][i][j] + C[ 2*k - 2^(l-1) ][i][j] ;
                    }
                }
            }
        }
        }

    }

	printf("\n\nResultado: \n\n");
	printf("[ %d  %d ]       [ %d  %d ]        [ %d  %d ]\n", A[0][0], A[0][1], B[0][0], B[0][1], C2[0][0][0] , C2[0][0][1] );
	printf("           X 	           =\n");
	printf("[ %d  %d ]       [ %d  %d ]        [ %d  %d ]", A[1][0], A[1][1], B[1][0], B[1][1], C2[0][1][0] , C2[0][1][1] );
}