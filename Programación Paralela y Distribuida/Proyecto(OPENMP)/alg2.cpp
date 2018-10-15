#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

int main(){ //Programa principal

	int i,j,k,u, n,q, eleva;

	printf("\t--------------------  SUMA CREW  --------------------\n");
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

    int b[n]; //Vector auxiliar
    int A[n]; //Vector principal

    //Llenar el vector A
    for(int t =0; t<n;t++){
        printf("\n\tIngresa el valor numero %d: ",t+1);
        scanf("%d",&A[t]); //Leyendo el número solicitado
    }
    //Im´rimimos el vector inicial
	printf("\n\tVector Inicial: ");
	printf ( "[");
	for ( k = 0; k <n; k ++)	{
		printf ("  %d  ", A [k] );
    }
    printf ( "]\n\n");

    for(u=0;u<n;u++){
        b[u]=A[u];
    }

    for(i=1;i<=int(log2(n));i++){
    	printf("\tPaso %d: ",i );
        #pragma omp parallel shared(j)// #pragma omp single
        {
             #pragma omp  for//#pragma omp parallel  for
            for(j=(int)(pow(2,i-1));j<=n;j++){
                b[j]=A[j]+ A[ (j) - (int)(pow(2,(i-1))) ];
                //printf("j:%d\n",j);
            }
        }
        printf ( "\n\t[ ");
        for(k=0;k<n;k++){
            printf("  %d  ",b[k]);
        }
        printf ( " ]\n");
        for (u=0;u<n;u++){
            A[u]=b[u];
        }
     }

    printf("\n\n\n\tEl Resultado: %d\n",b[n-1]);
}
