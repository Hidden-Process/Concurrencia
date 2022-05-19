#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "arbolbb.h"

/**
 * Pide un numero "tam" al usuario, y
 * crea un fichero binario para escritura con el nombre "nfichero"
 * en que escribe "tam" numeros (unsigned int) aleatorios
 * Se utiliza srand(time(NULL)) para establecer la semilla (de la libreria time.h)
 * y rand()%100 para crear un numero aleatorio entre 0 y 99.
 */

void creafichero(char* nfichero){
	unsigned tam,i,n;
	FILE *pf = fopen(nfichero,"wb");
	if(pf == NULL)perror("Error al abrir el fichero\n");
	else {
		printf("Introduce el numero de elementos de la lista: ");
		scanf("%d", &tam);

		srand(time(NULL));

		for(i=0;i<tam;i++){
			n = rand()%100;
			fwrite(&n,sizeof(unsigned),1,pf);
		}
		fclose(pf);
	}
}

/**
 * Muestra por pantalla la lista de numeros (unsigned int) almacenada
 * en el fichero binario "nfichero"
 */

void muestrafichero(char* nfichero){
	unsigned n;
	FILE *pf = fopen(nfichero,"rb");
	if(pf == NULL) perror("Error al abrir el fichero\n");
	else {
		printf("[ ");
		while(fread(&n,sizeof(unsigned),1,pf)==1){
			printf("%d ",n);
		}
		printf("]\n");
	}
	fclose(pf);
}

/**
 * Guarda en el arbol "*miarbol" los numeros almacenados en el fichero binario "nfichero"
 */

void cargaFichero(char* nfichero, T_Arbol* miarbol){
	unsigned n;
	FILE* pf = fopen(nfichero,"rb");
	if(pf == NULL) perror("Error al abrir el fichero \n");
	else {
		while(fread(&n,sizeof(unsigned),1,pf)==1){
			Insertar(miarbol,n);
		}
	}

	fclose(pf);
}

int main(void) {

	setvbuf(stdout,NULL, _IONBF, 0);
	setvbuf(stderr,NULL, _IONBF,0);

	char nfichero[50];
	printf ("Introduce el nombre del fichero binario: ");
	fflush(stdout);
	scanf ("%s",nfichero);
	fflush(stdin);
	printf("\n");
	creafichero(nfichero);
	printf("\nAhora lo leemos y mostramos \n");
	printf("\n");
	muestrafichero(nfichero);
	fflush(stdout);

	printf ("\nAhora lo cargamos en el arbol \n");
	T_Arbol miarbol;
	Crear (&miarbol);
	cargaFichero(nfichero,&miarbol);
	printf ("\nY lo mostramos ordenado \n");
	printf("\n");
	Mostrar(miarbol);
	fflush(stdout);
	printf("\n");
	printf("\nAhora lo guardamos ordenado \n");
	FILE * fich;
	fich = fopen (nfichero, "wb");
	Salvar (miarbol, fich);
	fclose (fich);
	printf("\nY lo mostramos ordenado \n");
	printf("\n");
	muestrafichero(nfichero);
	Destruir (&miarbol);

	return EXIT_SUCCESS;
}
