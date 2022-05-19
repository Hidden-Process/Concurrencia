package prodcons;

import java.util.Random;

public class Consumidor extends Thread{


	private Buffer b;
	private Random r = new Random();
	
	public Consumidor(Buffer b) {
		this.b = b;
	}
	
	public void run() {
		for(int i=0;i<100;i++) {
			try {
				System.out.println(b.extraerDato());
				Thread.sleep(r.nextInt(200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

