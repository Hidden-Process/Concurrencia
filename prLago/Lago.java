package lago;

public class Lago {

	// Recurso Compartido.
	private volatile int nivel = 0;
	
	// Recurso Primera Aplicación de Peterson.
	private volatile boolean fr = false, fp = false;
	private volatile int turnopr = 0; // rio = 0 y presa = 1.
	
	// Recursos Segunda Aplicación Peterson.
	private volatile boolean fp0 = false, fp1 = false;
	private volatile int turnop = 0;
	
	public void inc(){
		// Llamado por el rio
		
		fr = true;
		turnopr = 1;
		while(fp && turnopr == 1) Thread.yield();
			
		nivel ++; // Exclusión Mutua, SC para el rio.
				
		System.out.println("Incrementa: " + nivel);
				
		fr = false;
	}
	
	public void dec0(){
		// Llamado por la presa 0
		
		// CS: No puedo decrementar si es 0.
				
		fp0 = true;
		turnop = 1;
		while(fp1 && turnop == 1) Thread.yield();
				
		while(nivel == 0) Thread.yield();
				
		fp = true;
		turnopr = 0;
		while(fr && turnopr == 0) Thread.yield();
				
		nivel --;
				
		System.out.println("Decrementa 0: " + nivel);
				
		fp = false;
				
		fp0 = false;
				
		// SC Presa 0 : Exclusión Mutua.	
	}
	
	public void dec1(){
		// Llamado por la presa 1
		
		// CS: No puedo decrementar si es 0.
		
		
		fp1 = true;
		turnop = 0;
		while(fp0 && turnop == 1) Thread.yield();
		
		while(nivel == 0) Thread.yield();
		
		fp = true;
		turnopr = 0;
		while(fr && turnopr == 0) Thread.yield();
		
		nivel --; // SC Presa 1 : Exclusión Mutua.
		
		System.out.println("Decrementa 1: " + nivel);
		
		fp = false;
		
		fp1 = false;
	}
	
}
