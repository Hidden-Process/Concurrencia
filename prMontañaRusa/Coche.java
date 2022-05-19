package mrusaEsqueleto;

import java.util.concurrent.locks.*;

public class Coche extends Thread{
	
	private Lock l = new ReentrantLock(true);
	private int asientos;
	private int numPas = 0;
	private boolean entradaPasajero = true;
	private boolean salidaPasajero = true;
	private boolean cocheLleno = false;
	private Condition entradaCocheCond = l.newCondition();
	private Condition salidaCocheCond = l.newCondition();
	private Condition cocheLlenoCond = l.newCondition();
	
	
	public Coche(int tam){
		asientos = tam;
	}
	
	public Coche(){
		asientos = 5;
	}
	
	
	public void subir(int id) throws InterruptedException{
		// id del pasajero que se sube al coche
		
		l.lock();
		
		try {
			while(!entradaPasajero) entradaCocheCond.await();
			
			numPas++;
			System.out.println("Entra un Pasajero " + id + " se ha subido al coche. Hay : " +numPas);
			
			if(numPas == asientos) {
				entradaPasajero = false;
				cocheLleno = true;
				cocheLlenoCond.signal();
			}
		} finally {
			l.unlock();
		}
		
	}
	
	public void bajar(int id) throws InterruptedException{
		// id del pasajero que se baja del coche
		
		l.lock();
		
		try {
			
			while(!salidaPasajero) salidaCocheCond.await();
			numPas--;
			System.out.println("Sale un Pasajero " + id + " se ha bajado del coche. Hay : " +numPas);
			
			if(numPas == 0) {
				salidaPasajero = false;
				entradaPasajero = true;
				entradaCocheCond.signalAll();
			}
		} finally {
			l.unlock();
		}
		
	}

	
	public void esperaLleno() throws InterruptedException{
		//el coche espera a que este lleno para dar una vuelta
		
		l.lock();
		
		try {
			while(!cocheLleno) cocheLlenoCond.await();
			
			cocheLleno = false; // Para la siguiente Iteración
			System.out.println("Coche lleno y damos una vuelta");
			
			Thread.sleep(500);
			
			salidaPasajero = true;
			salidaCocheCond.signalAll();
			
		} finally {
			l.unlock();
		}
		
	}
	
	public void run(){
		boolean fin = false;
		while (!this.isInterrupted() && !fin)
			try{
				this.esperaLleno();
				
			}catch (InterruptedException ie){}
		
		fin = true;
	}
}