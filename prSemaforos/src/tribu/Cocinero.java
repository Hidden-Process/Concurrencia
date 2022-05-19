package tribu;

import java.util.*;
public class Cocinero extends Thread{

	private Olla n;
	private static Random r = new Random();
	public Cocinero(Olla n){
		this.n = n;
	}
	
	
	public void run(){
		while (true){
			try {
				Thread.sleep(r.nextInt(20));
				n.nuevoExplorador();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
