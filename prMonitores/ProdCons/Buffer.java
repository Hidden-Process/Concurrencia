package practicaTema6;

import java.util.Arrays;

public class Buffer {
	private int N = 5;
	private int espacio = N;
	private int[] buffer = new int[N];
	private int[] numCons = new int[N];
	private int idProductor;
	private int[] idCons = new int[3];
	private int[] numElem = new int[3];
	
	
	public Buffer() {
		// TODO Auto-generated constructor stub
		for(int i = 0; i < numCons.length; i++) {
			numCons[i] = 0;
		}
		
		for(int i = 0; i < idCons.length; i++) {
			idCons[i] = 0;
			numElem[i] = 0;
		}
		
	}
	
	public synchronized int extrae(int id) throws InterruptedException {
		int v; // Valor a devolver
		while(numElem[id] == 0) {
			wait();		
		}
		v = buffer[idCons[id]];
		numCons[idCons[id]]--;
		numElem[id]--;
		if(numCons[idCons[id]] == 0) {
			espacio++;
			notifyAll();
		}
		idCons[id] = (idCons[id] + 1) % buffer.length;
		System.out.println("Consumidor "+ id + " consume " + v);
		System.out.println(Arrays.toString(buffer));
		System.out.println(Arrays.toString(numCons));
		System.out.println("numElem: " + Arrays.toString(numElem));
		System.out.println("Espacio "+ espacio);
		System.out.println("***********************");
		return v;
	}
	
	
	public synchronized void almacena(int elem) throws InterruptedException {
		
		while(espacio == 0) {
			wait();			
		}
		buffer[idProductor] = elem;
		numCons[idProductor] = 3;
		for(int i = 0; i < numElem.length; i++) {
			numElem[i]++;			
		}
		idProductor = (idProductor + 1) % N;
		espacio--;
		System.out.println("Productor produce " + elem);
		System.out.println(Arrays.toString(buffer));
		System.out.println(Arrays.toString(numCons));
		System.out.println("numElem: " + Arrays.toString(numElem));
		System.out.println("Espacio "+ espacio);
		System.out.println("***********************");
		
		notifyAll();
		
	}
	
	
	
}
