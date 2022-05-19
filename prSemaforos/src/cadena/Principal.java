package cadena;

public class Principal {

	public static void main(String[] args) {
		Cadena c = new Cadena();
		Empaquetador [] emp = new Empaquetador[3];

		for(int i=0;i<emp.length;i++) emp[i] = new Empaquetador(c,i);

		Colocador col = new Colocador(c);

		for(int j=0;j<emp.length;j++) emp[j].start();

		col.start();
	}

}
