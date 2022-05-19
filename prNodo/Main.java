package nodo;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Random r = new Random();
		int tam = r.nextInt(20);
		
		List<Integer> l1 = new ArrayList<Integer>();
		
		for(int i=0;i<tam;i++){
			l1.add(r.nextInt(100));
		}
		
		System.out.println("Desordenado: \n" + l1);
		Nodo n = new Nodo(l1);
		n.start();
		try{
			n.join();
			System.out.println("Ordenado: \n" + l1);
		} catch(InterruptedException ie){}
		
	}

}
