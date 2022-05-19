package Semaforos.guarderia;


import java.util.concurrent.Semaphore;

public class Guarderia {

	private int numBebes;
	private int numProf;

	private Semaphore mutex = new Semaphore(1);
	private Semaphore bebes = new Semaphore(1);
	private Semaphore adulto = new Semaphore(1);

	
	/**
	 * Un bebe que quiere entrar en la Semaforos.guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		if(numBebes > numProf *3 ) bebes.acquire();
		mutex.acquire();
		numBebes++;
		System.out.println("El bebe con el ID: " + id + " Entra en la guardería");
		mutex.release();
		
	}
	/**
	 * Un bebe que quiere irse de la Semaforos.guarderia llama a este metodo *
	 */
	public void saleBebe(int id) throws InterruptedException{
		mutex.acquire();
		numBebes--;
		System.out.println("El bebe con el ID: " + id + " Abandona la guardería");
		if(numBebes <= numProf *3 ) bebes.release();
		mutex.release();
			
	}
	/**
	 * Un adulto que quiere entrar en la Semaforos.guarderia llama a este metodo *
	 */
	public void entraAdulto(int id) throws InterruptedException{
		mutex.acquire();
		numProf++;
		System.out.println("El adulto con el ID: " + id + " Entra en la guardería");
		if(numBebes <= numProf *3 ) adulto.release();
		mutex.release();
		
	}
	
	/**
	 * Un adulto que quiere irse  de la Semaforos.guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		if(numBebes > numProf *3 ) adulto.acquire();
		mutex.acquire();
		numProf--;
		System.out.println("El adulto con el id: " + id + " Abandona la guardería");
		mutex.release();
		
	}

}
