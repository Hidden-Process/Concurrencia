package practicaTema6;

import java.util.Random;

public class Productor extends Thread{
	
	private Buffer buffer;
	private Random r = new Random();
	
	public Productor(Buffer b) {
		// TODO Auto-generated constructor stub
		buffer = b;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i < 10) {
			try {
				Thread.sleep(r.nextInt(200));
				buffer.almacena(i);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
