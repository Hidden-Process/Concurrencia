#include "arbolbb.h"
#include <stdio.h>
#include <stdlib.h>

void Crear(T_Arbol* arbol){
	*arbol = NULL;
}

void Destruir(T_Arbol* arbol){
	if(*arbol != NULL){
		Destruir(&((*arbol)->izq));
		Destruir(&((*arbol)->der));
		free(*arbol);
		*arbol = NULL;
	}
}

void Mostrar(T_Arbol arbol){
	if(arbol != NULL){
		Mostrar(arbol->izq);
		printf("%d ", arbol->dato);
		Mostrar(arbol->der);
	}
}

void Insertar(T_Arbol* arbol,unsigned num){
	T_Arbol ant = NULL, act = *arbol;
	int esta = 0;
	while(act != NULL && !esta){
		ant = act;
		if(act->dato > num){
			act = act->izq;
		} else if(act->dato < num){
			act = act->der;
		} else esta = 1;
	}

	if(!esta){
		T_Arbol aux = malloc(sizeof(struct T_Nodo));
		aux->dato = num;
		aux->izq = NULL;
		aux->der = NULL;

		if(*arbol == NULL){
			*arbol = aux;
		} else {
			if(ant->dato > num){
				ant->izq = aux;
			} else
				 ant->der = aux;
		}

	}
}

void Salvar(T_Arbol arbol, FILE* fichero){
	unsigned n;
	if(arbol != NULL){
		Salvar(arbol->izq,fichero);
		n = arbol->dato;
		fwrite(&n,sizeof(unsigned),1,fichero);
		Salvar(arbol->der,fichero);
	}
}

