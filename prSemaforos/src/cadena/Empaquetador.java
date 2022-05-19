package cadena;

import java.util.Random;

public class Empaquetador extends Thread{

	private Random r = new Random();
	private Cadena c;
	private int id;
	
	public Empaquetador(Cadena c, int id) {
		this.id = id;
		this.c = c;
	}
	
	public void run() {
		while(true) {
			try{
				c.extraeProducto(id);
				Thread.sleep(r.nextInt(1000));
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
