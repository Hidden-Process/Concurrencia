package mrusaEsqueleto;

public class Principal {

	
	public static void main(String[] args){
		Coche c = new Coche();
		Pasajero[] pas = new Pasajero[10];
		for (int i = 0; i<pas.length; i++){
			pas[i] = new Pasajero(i,c);
		}
		c.start();
		for (int i = 0; i<pas.length; i++){
			pas[i].start();
		}
		
		try {
			Thread.sleep(3000);
			for (int i = 0; i<pas.length; i++){
				pas[i].interrupt();
			}
			
			c.interrupt();
			
			for (int i = 0; i<pas.length; i++){
				pas[i].join();
			}
			
			c.join();
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin del programa");
	}
}
