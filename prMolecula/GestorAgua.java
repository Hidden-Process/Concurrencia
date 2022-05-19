package aguaEsqueleto;

import java.util.concurrent.locks.*;


public class GestorAgua {
	
	private Lock l = new ReentrantLock(true);
	private int nOxigeno = 0;
	private int nHidrogeno = 0;
	private boolean pAbiertaH = true;
	private boolean pAbiertaO = true;
	private boolean pSalida = false;
	private Condition cpAbiertaH = l.newCondition();
	private Condition cpAbiertaO = l.newCondition();
	private Condition cMolecula = l.newCondition();

	public void hListo(int id) throws InterruptedException{ 
		l.lock();
		try {
			while(!pAbiertaH) cpAbiertaH.await();

			nHidrogeno++; 
			System.out.println("Entra un Hidrogeno " + id + " H: " + nHidrogeno + " O: " + nOxigeno );
			
			if(nHidrogeno == 2) {
				pAbiertaH = false;
			}
			
			if(nHidrogeno == 2 && nOxigeno == 1) {
				pSalida = true;
				cMolecula.signalAll();
			} else {
				while(!pSalida) cMolecula.await();
			}
			
			nHidrogeno --;
			System.out.println("Sale un Hidrogeno " + id + " H: " + nHidrogeno + " O: " + nOxigeno );
			
			 // La ultima hebra en abandonar deja todo preparado para el siguiente que entre.
			if(nHidrogeno == 0 && nOxigeno == 0) {
				System.out.println("***********************************************************************");
				pAbiertaH = true;
				pAbiertaO = true;
				pSalida = false;
				cpAbiertaH.signalAll();
				cpAbiertaO.signalAll();
				
			}
		} finally {
			l.unlock();
		}
		
	}
	
	public void oListo(int id) throws InterruptedException{ 
		l.lock();
		try {
			while(!pAbiertaO) cpAbiertaO.await();
			
			nOxigeno++;
			System.out.println("Entra un Oxigeno " + id + " H: " + nHidrogeno + " O: " + nOxigeno );
			pAbiertaO = false;
			
			if(nHidrogeno == 2 && nOxigeno == 1) {
				pSalida = true;
				cMolecula.signalAll();
			} else {
				cMolecula.await();
			}
			
			nOxigeno--;
			System.out.println("Sale un Oxigeno " + id + " H: " + nHidrogeno + " O: " + nOxigeno );
			
			if(nHidrogeno == 0 && nOxigeno == 0) {
				System.out.println("***********************************************************************");
				pAbiertaH = true;
				pAbiertaO = true;
				pSalida = false;
				cpAbiertaH.signalAll();
				cpAbiertaO.signalAll();
			}
			
		} finally {
			l.unlock();
		}
		
	}
}
