package nido;

public class Principal {

	public static void main(String[] args) {
		int B = 10;
		Nido n = new Nido();
		Padres p = new Padres(n,0);
		Padres m = new Padres(n,1);
		Bebes[] bebe = new Bebes[B];
		for (int i = 0; i<bebe.length; i++)
			bebe[i] = new Bebes(n,i);
		p.start();
		m.start();
		for (int i = 0; i<bebe.length; i++)
			bebe[i].start();
	}

}
