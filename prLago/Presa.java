package lago;

public class Presa extends Thread{

	private Lago lago;
	private int id; // Para identificar que presa es.
	
	public Presa(Lago lago,int id){
		this.lago = lago;
		this.id = id;
	}
	
	public void run(){
		for(int i=0;i<1000;i++){
			if(id == 0) lago.dec0();
			if(id == 1) lago.dec1();
		}
	}
}
