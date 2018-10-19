#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

void Broadcast(int Temp[],int num,int n){
    int u,i,j;
    Temp[0]=num;
    Temp[1]=num;

    for(i=1;i<=(int)(log2(n));i++){
        #pragma omp parallel  shared(j,i)
        {
            for(j=((int)(pow(2,i-1))+1);j<=(int)(pow(2,i));j++){
                Temp[j]=Temp[(j) - (int)(pow(2,(i-1)))];
            }
        }
    }
}
void SearchPram(int Temp[],int L[],int n){
    int i;
    #pragma omp parallel
    {
        #pragma omp for
        for(i=0;i<=n;i++){
        if(i<n){
            if(L[i]==Temp[i]){
                Temp[i]=i+1;
            }else{
                Temp[i]=88888;
            }
        }
    }
    }

}
void MinPram(int Temp[],int n){
    int i,j;
    for(i=1;i<=(int)(log2(n));i++){
            #pragma omp parallel
            {
                 for(j=1;j<=(n/((int)(pow(2,j))));j++){
                    if(Temp[(int)(pow(2,j))-1]>Temp[(int)(pow(2,j))]){
                        Temp[j]=Temp[(int)(pow(2,j))];
                    }else{
                    Temp[j]=Temp[(int)(pow(2,j))-1];
                    }
                }
            }

    }
}
int main(){ //Menú principal

    printf("\n\n\t--------------------  BUSQUEDA PRAM EREW  --------------------\n");
    int n,i,k,num,h,pos,t,aux,m,aux2, salida;
    do{
        printf("\n\tIngresa el tama%co del vector: ",164);
        scanf("%d",&n); //Leyendo el número solicitado
    }while(n<2); //En la condicion delñ while se asegura de solo recibir 
    printf("\n");
    int L[n];
    int Temp[n];
    //int L[16]={2,-1,23,-4,2,5,-2,0,5,1,5,-5,8,5,3,-2};
    //int Temp[16];
    for(int t =0; t<n;t++){
        do{
        	salida = 0;
        	printf("\n\tIngresa el valor numero %d: ",t+1);
	        //Hay que verificar que ese valor NO esté en el vector ya, para que lo haga bien.
	        scanf("%d",&aux); //Buscar aux en los valores del vector ingresados hasta ahora
	        if(t == 0){
	        	L[t] = aux;
	        	break;
	        }
	        for (int aux2 = 0; aux2 < t ; aux2++){
	        	if(L[aux2] == aux){
	        		printf("\n\tEse valor ya está en el vector, insertar otro\n");
	        		salida = 0;
	        		break;
	        	}else{
	        		salida = 1;
	        	}
	        }
        }while(salida == 0);
        L[t] = aux;
        //scanf("%d",&L[t]); //Leyendo el número solicitado
    }

    //n=16;
	printf("\n\n\tVector Inicial: ");
	printf ( "[");
	for ( k = 0; k < n; k ++)
	{
		printf ("  %d  ", L [k] );
    }
    printf ( "]");
    printf ( "\n" );
    printf("\n\tIngresa el numero a buscar: ");
	scanf("%d",&num);
	Broadcast(Temp,num,n);
	printf("\n\tVector temporal: ");
	printf ( "[");
	for ( k = 0; k < n; k ++)
	{
		printf ("  %d  ", Temp [k] );
    }
    printf ( "]\n");
    SearchPram(Temp,L,n);
    printf("\n\tIndices:");
    printf ( "[");
	for ( k = 0; k < n; k ++)
	{
		printf ("  %d  ", Temp [k] );
    }
    printf ( "]");
    MinPram(Temp,n);
    h=1;
    while(h<16){
        if(Temp[h]!=88888){
            pos=Temp[h];
            break;
        }
        h=h+1;
    }
    if (pos == 0){
    	printf("\n\n\n\tEl valor: %d NO se encuentra en el vector.\n",num);
    }else{
    	printf("\n\n\n\tEl valor: %d se encuentra en la posicion: %d \n", num, pos);
    }
}
