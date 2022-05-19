package practicaTema6;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Buffer b = new Buffer();
		Productor prd = new Productor(b);
		Consumidor[] cons = new Consumidor[3];
		
		for(int i = 0; i < cons.length; i++) {
			cons[i] = new Consumidor(b, i);
		}
		
		prd.start();
		
		for(int i = 0; i < cons.length; i++) {
			
			cons[i].start();
			
		}
	}

}
