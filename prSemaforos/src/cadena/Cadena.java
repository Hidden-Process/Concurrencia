package cadena;

import java.util.Arrays;
import java.util.concurrent.*;

public class Cadena {

	private int N = 10;
	private int[] cadena = new int[N];
	private int numProd = 0;
	
	private Semaphore mutex = new Semaphore(1);
	private Semaphore [] hayProd = new Semaphore[3];
	private Semaphore HayHuecos = new Semaphore(N);
	
	public Cadena() {
		
		for(int i = 0;i<cadena.length;i++) {
			cadena[i] = -1; // Quiere decir que esta vacia.
		}
		
		for(int j=0;j<hayProd.length;j++){
			hayProd[j] = new Semaphore(0);
		}
	}
	
	public void nuevoProducto(int id) throws InterruptedException {
		HayHuecos.acquire();
		mutex.acquire(); // Importante el orden del aquire.
		numProd++;
		int i = 0;
		while(cadena[i] != -1) { // Busco hueco vacio.
			i++;
		}
		cadena[i] = id;
		System.out.println("Colocador pone " + id +  " " + Arrays.toString(cadena));
		System.out.println("Hay " + numProd + " productos");
		hayProd[id].release();
		mutex.release();
	}
	
	public void extraeProducto(int id) throws InterruptedException{
		hayProd[id].acquire();
		mutex.acquire();
		numProd--;
		int i = 0;
		while(cadena[i] != id) { // Dejamos un hueco en la cinta
			i++;
		}
		
		cadena[i] = -1;
		System.out.println("Empaquetador " + id + " extrae " + Arrays.toString(cadena));
		System.out.println("Hay " + numProd + " productos");
		HayHuecos.release();
		mutex.release();
	}
}


