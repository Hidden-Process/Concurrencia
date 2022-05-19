package sensores;

import java.util.Random;

public class Worker extends Thread{
	
	private Mediciones m;
	private Random r = new Random();
	
	public Worker(Mediciones m) {
		this.m = m;
	}
	
	public void run() {
		while(true) {
			try {
				m.leerMediciones();
				Thread.sleep(r.nextInt(300));
				m.finTarea();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
