#include <stdio.h>
#include<math.h>
#include <omp.h>

using namespace std;
void minCRCW(int L[], int n,int Win[]){
    int i,j;
    #pragma omp parallel
	{
	    #pragma omp parallel for
	   for( i=0 ; i<n ; i++){
            Win[i]=0;
        }
	}
    #pragma omp parallel for shared(i)
    for (i=0;i<n;i++){
        #pragma omp parallel for  private(j)
        for (j=i+1;j<n;j++){
            if(i<j){
                if(L[i]>L[j]){
                    Win[i]=1;
                }else{
                    Win[j]=1;
                }
            }
        }
    }

}
int main()
{
    int i, j, IndexMin,n,num,k;
    printf("\n\t--------------------  BUSQUEDA PRAM CRCW --------------------\n\n");
    printf("\tDe que tamanio sera el vector?: ");
    scanf("%d",&n);
    int L[n],Win[n];
    for(i=0;i<n;i++){
		printf("\n\tIngrese el dato %d: ",i+1);
		scanf("%d",&num);
		L[i]=num;
	}
	printf("\n\n\tVector Inicial: ");
	printf ( "[");
	for(k=0;k<n;k++){
        printf ("  %d  ", L[k] );
	}
	printf ( "]\n");
	minCRCW(L,n,Win);
	printf("\n\tVector WIN: ");
	printf ( "[");
	for(k=0;k<n;k++){
        printf ("  %d  ", Win[k] );
	}
	printf ( "]");
	#pragma omp parallel
	{
	    #pragma omp parallel for
	    for( i=0 ; i<n ; i++ ){
            if( Win[i]==0 ){
                IndexMin=i;
            }
        }
	}

	printf("\n\n\tEl valor mimino es:  %d\n",L[IndexMin]);
}
