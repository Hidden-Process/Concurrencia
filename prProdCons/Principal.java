package prodcons;

public class Principal {

	public static void main(String[] args) {
		Buffer b = new Buffer(5);
		Productor p = new Productor(b);
		Consumidor c = new Consumidor(b);
		
		p.start();
		c.start();
		
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin del programa");
	}

}
