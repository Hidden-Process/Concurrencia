package sensores;

public class Principal {

	public static void main(String[] args) {
		Mediciones m = new Mediciones();
		Sensor [] s = new Sensor[3];
		
		for(int i=0;i<s.length;i++) s[i] = new Sensor(m,i);
		
		Worker w = new Worker(m);
		
		for(int i=0;i<s.length;i++) {
			s[i].start();
		}
		
		w.start();

	}

}
