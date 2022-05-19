package locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuerda {
	
	private Lock l = new ReentrantLock(true);

	private Condition NorteCond = l.newCondition();
	private Condition SurCond = l.newCondition();

	private boolean norte = false;
	private boolean sur = false;

	private int num = 0;


	public Cuerda(){

	}

	public  void entraDireccionNS(int id) throws InterruptedException{
		l.lock();

		try{
			while(num == 3 || sur) NorteCond.await();
			norte = true;
			num++;
			System.out.println("El babuino con ID: " + id + " Se ha subido a la cuerda por el norte");
		}finally {
			l.unlock();
		}
		
	}

	public  void entraDireccionSN(int id) throws InterruptedException{
		l.lock();

		try{
			while(num == 3 || norte) SurCond.await();
			sur = true;
			num++;
			System.out.println("El babuino con ID: " + id + " Se ha subido a la cuerda por el sur");
		} finally {
			l.unlock();
		}
		
	}

	public  void saleDireccionNS(int id) throws InterruptedException{
		l.lock();

		try{
			num--;
			if(num==0) norte = false;
			System.out.println("El babuino con ID: " + id + " Se baja de la cuerda por el lado sur.");
			SurCond.signalAll();
		} finally {
			l.unlock();
		}
			
	}

	public  void saleDireccionSN(int id) throws InterruptedException{
		l.lock();

		try {
			num--;
			if(num == 0) sur = false;
			System.out.println("El babuino con ID: " + id + " Se baja de la cuerda por el lado norte.");
			NorteCond.signalAll();
		} finally {
			l.unlock();
		}
	}	
			
		
}
