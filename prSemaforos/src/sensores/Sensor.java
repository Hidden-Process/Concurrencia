package sensores;

import java.util.Random;

public class Sensor extends Thread {
	private Mediciones m;
	private int id;
	private Random r = new Random();
	
	public Sensor(Mediciones m,int id) {
		this.m = m;
		this.id = id;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(r.nextInt(200));
				m.nuevaMedicion(id);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
