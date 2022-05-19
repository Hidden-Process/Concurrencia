package lago;

public class Main {

	public static void main(String[] args) {
		Lago lago = new Lago();
		Rio rio = new Rio(lago);
		Presa p1 = new Presa(lago,0);
		Presa p2 = new Presa(lago,1);
		rio.start();
		p1.start();
		p2.start();
	}

}
