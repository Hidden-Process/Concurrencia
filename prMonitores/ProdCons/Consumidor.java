package practicaTema6;

import java.util.Random;

public class Consumidor extends Thread{
	
	private Buffer buffer;
	private int id;
	private Random r = new Random();
	
	public Consumidor(Buffer b, int id) {
		// TODO Auto-generated constructor stub
		buffer = b;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i < 10) {
			try {
				buffer.extrae(id);
				Thread.sleep(r.nextInt(200));
				i++;
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
