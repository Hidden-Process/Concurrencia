package sensores;

import java.util.Random;

public class Sensor implements Runnable{

	private int id;
	private int[] valores;
	private Synchro synchro;
	
	public Sensor(int id, int[] valores, Synchro synchro) {
		this.id = id;
		this.valores = valores;
		this.synchro = synchro;
	}


	@Override
	public void run() {
		Random r = new Random();
		int muestra;
		while(true){
			try {
				synchro.arrancaSensor(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//tomará muestra
			muestra = r.nextInt(100);
			System.out.println(Thread.currentThread().getName()+" tomando muestra "+ muestra);
			
			//escribe el valor -- SC
			
			valores[id] = muestra;
			synchro.finalizaSensor(id);
			
		}
	}

}
