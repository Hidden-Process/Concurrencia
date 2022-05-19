package sensores;

import java.util.concurrent.*;

public class Mediciones {
	private Semaphore tresMed = new Semaphore(0);
	private int numMed = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore[] nuevaIter = new Semaphore [3];
	
	public Mediciones() {
		for(int i=0;i<nuevaIter.length;i++) nuevaIter[i] = new Semaphore(0);
	}
	
	public void leerMediciones() throws InterruptedException {
		tresMed.acquire();
		System.out.println("El worker lee las 3 mediciones");
	}
	

	public void nuevaMedicion(int id) throws InterruptedException {
		mutex.acquire();
		numMed++;
		System.out.println("El sensor " + id + " deja su medicion");
		if(numMed == 3) {
			tresMed.release();
			numMed = 0;
		}
		mutex.release();
		nuevaIter[id].acquire();
	}
	
	public void finTarea() {
		System.out.println("El worker ya ha terminado de procesar las mediciones");
		nuevaIter[0].release();
		nuevaIter[1].release();
		nuevaIter[2].release();
	}
}
