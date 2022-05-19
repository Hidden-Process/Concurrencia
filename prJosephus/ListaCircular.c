#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ListaCircular.h"


void crear(TListaCircular *lc){
    *lc = NULL;
}

void insertar(TListaCircular *lc,char *nombre){
    TListaCircular aux = malloc(sizeof(struct TNodo));
  	strcpy((aux->nombre),nombre);
  	if(*lc!=NULL){
  		aux->sig=(*lc)->sig;
  		(*lc)->sig=aux;
  	} else{
  		aux->sig=aux;
  	}
  	*lc=aux;
}
void recorrer(TListaCircular lc){
    int tam = longitud(lc);
    int cont = 0;
    TListaCircular primero = lc->sig;
    if(primero != NULL){
        while(cont<tam){
            printf("%s \n",primero->nombre);
            primero = primero->sig;
            cont++;
        }
    } else {
        printf("Lista Vacia\n");
    }
}

int longitud(TListaCircular lc){
    int cont = 0;
    TListaCircular primero = lc->sig;
    if(primero != NULL){
        while(primero != lc){
            cont++;
            primero = primero->sig;
        }
    } else {
        return 0;
    }

    return cont+1;
}

void mover(TListaCircular *lc,int n){
    int mover = 0;
    if(lc != NULL){
        while(mover<n){
            *lc = (*lc)->sig;
            mover++;
        }
    } else {
        printf("Lista Vacia\n");
    }
}

void extraer(TListaCircular *lc,char *nombre){
    TListaCircular primero = (*lc)->sig;
    TListaCircular ultimo = *lc;
    strcpy(nombre, primero->nombre);
    primero = primero->sig;
    ultimo->sig = primero;
    free(primero);
}
