package sensores;

public class Trabajador implements Runnable {

	private int[] valores;
	private Synchro synchro;
	
	
	
	public Trabajador(int[] valores, Synchro synchro) {
		this.valores = valores;
		this.synchro = synchro;
		for (int v : valores) {
			v = -1; //inicializacion
		}
	}



	@Override
	public void run() {
		while(true){
			try {
				synchro.arrancaTrabajador();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//procesar todos los valores cuando estén disponibles
			procesa(valores);
			synchro.finalizaTrabajador();
		}
	}



	private void procesa(int[] valores2) {
		for (int i = 0; i < valores.length; i++) {
			System.out.println(Thread.currentThread().getName()+" procesando " + valores[i]);
			valores[i] = -1;
		}
		
	}

}
