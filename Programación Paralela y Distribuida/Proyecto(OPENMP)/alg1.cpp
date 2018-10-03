/*
    Algoritmo No. 1
    SUMA EREW
*/
#include<stdio.h>
#include<math.h>
#include<omp.h>

int main(){ //Programa Principal
    //int A[9]={0,1,1,1,1,1,1,1,1};
	int i,j,k,n,eleva;

	printf("\n\n\t--------------------  SUMA EREW  --------------------\n");
    //Determinar el tamaño del vector
    do{
        printf("\n\tIngresa el tama%co del vector (Potencias de 2): ",164);
        scanf("%d",&n); //Leyendo el número solicitado
        if((n<2) && ((pow(2,log2(n))))!=n){
            printf("\7");
        }
        int log = log2(n);
        eleva = pow(2,log);
    }while((n<2) || (eleva!=n)); //En la condicion delñ while se asegura de solo recibir 
    //numeros mayores a 1 y potencias de 2, si no, no jala con otyros tamaños de vectores jeje 
    //Declarar el vector A , de tamaño n
    int A[n];
    A[0] = 0;
    //Llenar el vector A
    for(int t =1; t<=n;t++){
        printf("\n\tIngresa el valor numero %d: ",t);
        scanf("%d",&A[t]); //Leyendo el número solicitado
    }
    //Im´rimimos el vector inicial
	printf("\n\tVector Inicial: ");
	printf ( "[");
	for ( k = 1; k <=n; k ++)	{
		printf ("  %d  ", A [k] );
    }
    printf ( "]");
    printf ( "\n" );

    //Empezamos con los calculos
	for( i = 1; i <= (log2 (n)); i++){
        printf("\n\n\tPASO %d: \n", i);
        #pragma omp parallel  shared (j)
        {
            #pragma omp for
            for ( j  = 1; j <= (n/2); j++){
                if( ( (2*j) % ( int ) ( pow ( 2, i ) ) ) == 0) {
                    A [2*j] = A [2*j] + A [ ( 2 * j ) - ( ( int ) ( pow ( 2, (i - 1 ) ) ) ) ];
                }

            }
        }
            printf ( "\n" );
            printf ( "[");
            for(k=1;k<=n;k++)        {
                printf(" %d ",A[k]);
            }
            printf ( " ]");
      }
      printf("\n\n\n\n\tEl resultado es: %d", A[n]);
      printf ( "\n" );
}
