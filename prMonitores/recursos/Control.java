package recursos;

import java.util.LinkedList;
import java.util.List;

public class Control {
	
	private int NUM; //numero total de recursos
	private int rec; //Numero de recursos disponibles en un momento determinado.
	private int esperando = 0;
	private int sig = -1; // Siguiente que le toca
	private List<Integer> listaEspera = new LinkedList<Integer>();
	
	public Control(int num){
		this.NUM = num;
		this.rec = NUM;
	}
	
	public void qRecursos(int id,int num) throws InterruptedException{
		System.out.println("El Proceso " + id + " quiere " + num + " recursos");
		esperando++;
		
		if(esperando > 1) {
			listaEspera.add(id);
			System.out.println(listaEspera);
			while(sig != id) wait();
		}
		
		while(rec < num) wait();
		
		esperando--;
		rec = rec - num;
		System.out.println("El Proceso " + id + " obtiene " + num + " recursos. Quedan " + rec + " libres");
		
		if(!listaEspera.isEmpty()) {
			sig = listaEspera.remove(0);
			System.out.println(listaEspera);
			notifyAll();
		} else {
			sig = -1;
		}
		
	}

	public void libRecursos(int id,int num){
		rec = rec + num;
		System.out.println("El Proceso " + id + " devuelve " + num + " recursos. Quedan " + rec + " libres");
		notifyAll();
	}
}
