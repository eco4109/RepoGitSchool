#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include<omp.h>
void oddEvenSplit(int L[],int odd[],int even[],int INI,int n){
   int cont1=1,cont2=1;
        for(int i=INI;i<=n;i++){
            if((i%2)==0){
                even[cont1]=L[i];
                cont1++;
            }else{
                odd[cont2]=L[i];
                cont2++;
            }
        }
}

void oddEvenMerge(int L[],int ini,int n){
    int odd[16];
    int even[16];
    int fin=n-ini+1;
    int au=(int)((fin/2)+1);
    int aux;
    if(fin==2){
        if(L[ini]>L[n]){
                aux=L[ini];
                L[ini]=L[n];
                L[n]=aux;
        }
    }else{
        #pragma omp parallel
        #pragma omp task
        oddEvenSplit(L,odd,even,ini,n);
        #pragma omp parallel
            {
                #pragma omp task
                oddEvenMerge(odd,1,(fin/2));
                oddEvenMerge(even,1,(fin/2));

            }

        #pragma omp parallel
			{
			    #pragma omp for
                for(int i=1;i<=(fin/2);i++){
                    L[(2*i)-1+(n-fin)]=odd[i];
                    L[(2*i)+(n-fin)]=even[i];
                }
			}
        #pragma omp parallel
			{
			    #pragma omp for
                for(int i=ini;i<=((n/2)-1);i++){
                    if(L[2*i]>L[(2*i)+1]){
                        int aux;
                        aux=L[2*i];
                        L[2*i]=L[(2*i)+1];
                        L[(2*i)+1]=aux;
                    }
                }
			}
    }
}
void mergeS(int L[],int ini,int n){
    int m=(n+ini)/2;
    if(ini<n){
         #pragma omp parallel
            {
                #pragma omp task
                mergeS( L,ini, m);
                mergeS( L, m+1, n);
                oddEvenMerge(L,ini,n);
            }
    }
}




int main(){
    printf("\n					Ordenamiento EREW\n");
	int i, j,x,n;
	 int L[]={0,16,22,25,40,53,66,70,85,5,28,23,55,60,69,72,78};
	 n=16;
	printf("      \n  Vector Principal\n");
	printf ( "[");
	for(i=0 ; i<n ; i++){
		printf("    %d    ",L[i]);
	}
    printf ( "]");
    mergeS(L,1, n);
    printf ("\nEL VECTOR ORDENADO ES: ");
    printf ( "[");
	for(i=0 ; i<n ; i++){
		printf("    %d    ",L[i]);
	}
    printf ( "]");
}