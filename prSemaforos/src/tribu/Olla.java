package tribu;

import java.util.concurrent.*;
public class Olla {
	private int TamRac = 10;
	private int olla = 0;
	private Semaphore ollaVacia = new Semaphore(1);
	private Semaphore ollaLlena = new Semaphore(0);
	
	public void nuevoExplorador() throws InterruptedException{
		ollaVacia.acquire();
		olla = TamRac;
		System.out.println("Cocinero llena la olla " + olla);
		ollaLlena.release();
	}
	
	
	public void comeRacion(int id) throws InterruptedException{
		ollaLlena.acquire();
		olla--;
		System.out.println("Canibal " + id + " come racion " + olla);
		if(olla == 0) ollaVacia.release();
		else ollaLlena.release();
	}

}
