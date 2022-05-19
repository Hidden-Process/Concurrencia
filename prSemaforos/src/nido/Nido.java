package nido;

import java.util.concurrent.Semaphore;

public class Nido { 
	
	private int TAM = 5;
	private int plato = 0; 
	private Semaphore mutex = new Semaphore(1);
	private Semaphore siguientePapa = new Semaphore(1);
	private Semaphore siguienteBebe = new Semaphore(1);
	private Semaphore esperaPapa = new Semaphore(0);
	private Semaphore esperaBebe = new Semaphore(0);
	private boolean esperaEspacio = false;
	private boolean esperaBichitos = false;
	
	
	public void nuevoBichito(int id) throws InterruptedException {
		siguientePapa.acquire();
		mutex.acquire();
		
		if(plato == TAM) {
			esperaEspacio = true;
			mutex.release();
			esperaPapa.acquire();
			mutex.acquire();
		}
		
		plato++;
		System.out.println("Padre " + id + " Deja un bichito. Hay " + plato);
		
		if(esperaBichitos) {
			esperaBichitos = false;
			esperaBebe.release();
		}
		mutex.release();
		siguientePapa.release();
	}
	
	
	public void comeBichito(int id) throws InterruptedException{
		siguienteBebe.acquire();
		mutex.acquire();
		
		if(plato == 0) {
			esperaBichitos = true;
			mutex.release();
			esperaBebe.acquire();
			mutex.acquire();
		}
		
		plato--;
		System.out.println("El bebe " + id + " come un bichito. Hay " + plato);
		
		if(esperaEspacio) {
			esperaEspacio = false;
			esperaPapa.release();
		}
		mutex.release();
		siguienteBebe.release();
	}

}
