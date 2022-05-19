package prodcons;

public class Buffer {
	
	private int [] b;
	private int i=0,j=0; 	   // Index para consumidor y productor
	private volatile int numDatos = 0; // Numero de datos en el buffer
	
	private volatile boolean fp = false, fc= false;
	private volatile int turno = 0; // Prod = 0 y Cons = 1
	
	public Buffer(int n) {
		b = new int[n];
	}
	
	public void nuevoDato(int dato) {
		while(numDatos == b.length) Thread.yield(); // Condicion Sincronizacion del Productor 
		
		// Exclusion Mutua con Peterson sobre la Seccion Critica del Productor.
		
		fp = true;
		turno = 1;
		while(fc && turno ==1)Thread.yield(); 
			b[i] = dato;
			i = (i+1)%b.length; 
			numDatos++;
			fp = false;
	}
	
	public int extraerDato() {
		while(numDatos == 0) Thread.yield(); // Condicion Sincronizacion del Consumidor
		
		// Exclusion Mutua con Peterson sobre la Seccion Critica del Consumidor
		fc = true;
		turno = 0;
	
		while(fp && turno == 0) Thread.yield(); 
		
			int aux = b[j];
			j = (j+1)%b.length;
			numDatos--;
		
		fc = false;
		
		return aux;
	}

}

// Exclusion Mutua = Peterson (Envolver las secciones criticas con Peterson)