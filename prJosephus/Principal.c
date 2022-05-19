#include "ListaCircular.h"
#include <stdio.h>


// Lee el fichero y lo introduce en la lista
void cargarFichero (const char *nombreFich, TListaCircular *lc){
	FILE *pf = fopen(nombreFich,"r");
	    if(pf == NULL){
	        perror("Error de apertura");
	    } else {
	        char s[30];
	        while(fscanf(pf,"%s",s)==1){
	           insertar(lc,s);
	       }
	    }
  fclose(pf);
}


int main(){
	setvbuf(stdout, NULL, _IONBF,0);
  setvbuf(stderr, NULL, _IONBF,0);

	TListaCircular lc;
	crear(&lc);

	char nombre[30];

	int n;

	cargarFichero ("listaNombres.txt",&lc);
	recorrer(lc);
	printf("Introduce un numero entre 0 y 60: ");
	//fflush(stdout);
	scanf("%d",&n);
	while (longitud(lc)>1){
	mover(&lc,n);
	extraer(&lc,nombre);
	printf("%s ha salido del circulo \n",nombre);
	}

	extraer(&lc,nombre);
	printf("--------------------------------------\n");
	printf("%s ha sido seleccionado !!!!! \n",nombre);
	//fflush(stdout);

	return 0;
}
