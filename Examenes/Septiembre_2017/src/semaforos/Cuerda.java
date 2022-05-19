package semaforos;

import java.util.concurrent.Semaphore;

public class Cuerda {
	
	
	private int num = 0;
	private boolean norte = false;
	private boolean sur = false;
	
	private Semaphore mutex = new Semaphore(1);
	
	private Semaphore North = new Semaphore(0);
	private Semaphore South = new Semaphore(0);
	
	public Cuerda() {
		
	}
	

	public  void entraDireccionNS(int id) throws InterruptedException{
		
		while(num == 3 || sur) North.acquire(); 
		
		mutex.acquire();
		norte = true;
		num++;
		System.out.println("El babuino con ID: " + id + " Se ha subido a la cuerda por el norte");
		mutex.release();
		
	}

	public  void entraDireccionSN(int id) throws InterruptedException{
		
		while(num == 3 || norte) South.acquire();
		
		mutex.acquire();
		sur = true;
		num++;
		System.out.println("El babuino con ID: " + id + " Se ha subido a la cuerda por el sur");
		mutex.release();
		
	}

	public  void saleDireccionNS(int id) throws InterruptedException{
		
		mutex.acquire();
		num--;
		System.out.println("El babuino con ID: " + id + " Se baja de la cuerda por el lado sur.");
		if(num == 0) norte = false;
		mutex.release();
		
		South.release();
		North.release();

	}
	

	public  void saleDireccionSN(int id) throws InterruptedException{
		
		mutex.acquire();
		num--;
		System.out.println("El babuino con ID: " + id + " Se baja de la cuerda por el lado norte.");
		if(num == 0) sur = false;
		mutex.release();
		
		North.release();
		South.release();
	}	
		
}
