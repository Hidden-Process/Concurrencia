package prodcons;

import java.util.*;

public class Productor extends Thread{

	private Buffer b;
	private Random r = new Random();
	
	public Productor(Buffer b) {
		this.b = b;
	}
	
	public void run() {
		for(int i=0;i<100;i++) {
			try {
				Thread.sleep(r.nextInt(200));
				b.nuevoDato(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
