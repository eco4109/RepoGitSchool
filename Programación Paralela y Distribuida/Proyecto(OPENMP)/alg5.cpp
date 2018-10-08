#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include<omp.h>


main(){
	printf("\n\t--------------------  ORDENAMIENTO CRCW  --------------------\n");
	int i, j,n,x,w;
	 printf("\n\tCuantos datos desea introducir: ");
	scanf("%d",&n);
	printf("\n");
	int L[n],Win[n];
	for(i=0;i<n;i++){
		printf("\n\tIngrese el valor %d: ",i+1);
		scanf("%d",&x);
		L[i]=x;
	}
	printf("\n\n\n\tVector Inicial: ");
	printf ( "[");
	for(i=0 ; i<n ; i++){
		printf("  %d  ",L[i]);
	}
    printf ( "]");
	#pragma omp parallel
	{
	    #pragma omp for
	    for( i=0 ; i<n ; i++){
            Win[i]=0;
        }
	}
	#pragma omp parallel for shared(i)
    for (i=0;i<n;i++){
        #pragma omp parallel shared(j)
        {
            #pragma omp  for
            for(j=1;j<n;j++){
                if(i<j){
                    if(L[i]>L[j]){
                        Win[i]=Win[i]+1;
                    }else{
                        Win[j]=Win[j]+1;
                    }
                }
            }

        }
    }

    int a;
    int ll[n];
	#pragma omp parallel shared (i)
	{
	    #pragma omp for
	    for( i=0 ; i<n ; i++ ){
            a= Win[i]+1;
            ll[a-1]= L[i];
        }

	}

	printf("\n\n\tVector WIN: ");
	printf ( "[");
	for(i=0 ; i<n ; i++){
		printf("  %d  ",Win[i]);
	}
	printf ( "]");
	printf("\n\n");
	printf("\n\tVector Ordenado: ");
	printf ( "[");
	for(i=0 ; i<n ; i++){
		printf("  %d  ",ll[i]);
	}
	printf ( "]\n");
}
