package Locks.guarderia;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Guarderia {

	private int numBebes;
	private int numAdultos;

	private Lock l = new ReentrantLock(true);

	private Condition bebes = l.newCondition();
	private Condition adultos = l.newCondition();

	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		l.lock();

		try{

			if(numBebes > numAdultos *3 ) bebes.await();
			numBebes++;
			System.out.println("El bebe con el ID: " + id + " Entra en la guardería");

		} finally {
			l.unlock();
		}
		
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		l.lock();

		try{
			numBebes--;
			System.out.println("El bebe con el ID: " + id + " Abandona la guardería");
			if(numBebes <= numAdultos * 3) bebes.signalAll();
		} finally {
			l.unlock();
		}
			
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{

		l.lock();

		try{
			numAdultos++;
			System.out.println("El adulto con el ID: " + id + " Entra en la guardería");
			if(numBebes <= numAdultos * 3) bebes.signalAll();
		} finally {
			l.unlock();
		}
		
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		l.lock();

		try{

			if(numBebes > numAdultos *3 ) adultos.await();
			numAdultos--;
			System.out.println("El adulto con el ID: " + id + " Abandona la guardería");

		} finally {
			l.unlock();
		}
		
	}

}
