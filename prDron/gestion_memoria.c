#include <stdio.h>
#include <stdlib.h>
#include "gestion_memoria.h"

#define MAX 1000

//  Reservamos memoria para la estructura y la inicializamos.

void crear(T_Manejador *manejador){
	*manejador = (T_Manejador)malloc(sizeof(struct T_Nodo));
	(*manejador)->inicio=0;
	(*manejador)->fin=MAX-1;
	(*manejador)->sig=NULL;
}

/* Creamos un puntero auxiliar que nos ayudará a eliminar el primer nodo
 *  en cada iteración sin perder la referencia a los nodos siguientes */

void destruir(T_Manejador* manejador){
	T_Manejador ptr;
	while(*manejador != NULL){
		ptr=(*manejador)->sig;
		free((void *)*manejador);
		*manejador = ptr;
	}
}

/*
 * 1) Busco algún nodo libre de tamaño tam.
 * 2) Si hay memoria disponible, devolvemos su dirección.
 * 3) Finalmente liberamos recursos y actualizamos punteros.
 */

void obtener(T_Manejador* manejador,unsigned tam,unsigned* dir,unsigned*ok){
	int tam_actual,restante;
	T_Manejador ptr,ant,aux;

	*ok=0;
	ant=NULL;
	ptr=*manejador;

	while((!(*ok)) && (ptr != NULL)){
		tam_actual=(ptr->fin - ptr->inicio+1);
		if(tam_actual>=tam) *ok=1;
		else {
			ant=ptr;
			ptr=ptr->sig;
		}
	}

	if(*ok){
		*dir=ptr->inicio;
		restante = tam_actual - tam;
		if(restante) ptr->inicio=ptr->inicio + tam;
		else {
			if(ant==NULL) {
				aux=ptr->sig;
				free((void *)*manejador);
				*manejador = aux;
			} else {
				ant->sig = ptr->sig;
				free((void *)ptr);
			}
		}
	}
}

/* Iteramos sobre la lista actualizando el puntero en cada iteración mostrando
 * las posiciones de inicio y fin de cada nodo */

void mostrar (T_Manejador manejador){
	while(manejador != NULL){
		printf("[%d,%d]",manejador->inicio,manejador->fin);
		manejador = manejador->sig;
	}
		printf("\n");
}


/*  Nos aseguramos que haya mas de un nodo para que tenga sentido la compactación de nodos.
 *  Si fin+1 del ant == inicio de ptr se puede compactar.
 *  Si no, simplemete actualizamos los punteros */

void compactar(T_Manejador *manejador){
	T_Manejador ant = *manejador;
	T_Manejador ptr = (*manejador)->sig;

	if(ptr !=NULL){
		while(ant->sig != NULL){
			if(ptr->inicio == ant->fin + 1){
				ant->fin = ptr->fin;
				ant->sig = ptr->sig;
				free((void *)ptr);
				ant = ant->sig;
			} else {
				ant = ant->sig;
				ptr = ptr->sig;
			}
		}
	}
}

/*
 * 1) Creamos el nuevo nodo con la información de la cantidad de memoria continua devuelta.
 * 2) Buscamos donde insertar ese nodo
 * 3) Es el primer nodo
 * 4) Es un nodo intermedio
 * 5) Ya tenemos el nodo posicionado e insertado y ahora compactamos memoria.
 */

void devolver(T_Manejador *manejador,unsigned tam,unsigned dir){
	T_Manejador ptr,ant;
	ant = NULL;
	ptr = *manejador;

	T_Manejador nodo = (T_Manejador)malloc(sizeof(struct T_Nodo));
	nodo->inicio=dir;
	nodo->fin=dir + tam-1;

	while(ptr != NULL && ptr->inicio < dir){
		ant = ptr;
		ptr = ptr->sig;
	}

	if(ant == NULL){
		nodo->sig = ptr;
		*manejador = nodo;
	} else { //
		ant->sig = nodo;
		nodo->sig = ptr;
	}

	compactar(manejador);
}



