#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

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
    printf("\n\t--------------------  ORDENAMIENTO EREW  --------------------\n");
    int i,j,x,n,l,eleva;
    //Determinar el tamaño del vector
    do{
        printf("\n\tIngresa el tama%co del vector (Potencias de 2): ",164);
        scanf("%d",&l); //Leyendo el número solicitado
        if((l<2) && ((pow(2,log2(l))))!=l){
            printf("\7");
        }
        int log = log2(l);
        eleva = pow(2,log);
    }while((l<2) || (eleva!=l)); //En la condicion delñ while se asegura de solo reci
	
	int L[l];
	L[0] = 0;

	for(int t =1; t<=l;t++){
        printf("\n\tIngresa el valor numero %d: ",t);
        scanf("%d",&L[t]); //Leyendo el número solicitado
    }
	n=log2(l);
	printf("\n\n\tVector Incial: ");
	printf ( "[");
	for(i=1 ; i<=l ; i++){
		printf("  %d  ",L[i]);
	}
    printf ( "]\n");
    mergeS(L,1, l);
    printf ("\n\tVector Ordenado: ");
    printf ( "[");
	for(i=1 ; i<=l ; i++){
		printf("    %d    ",L[i]);
	}
    printf ( "]\n\n");
}