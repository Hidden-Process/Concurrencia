package nido;

import java.util.*;
public class Padres extends Thread{

	private Nido n;
	private int id;
	private static Random r = new Random();
	public Padres(Nido n,int id){
		this.n = n;
		this.id = id;
	}
	
	
	public void run(){
		while (true){
			try {
				Thread.sleep(r.nextInt(200));
				n.nuevoBichito(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
