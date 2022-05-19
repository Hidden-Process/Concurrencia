package sensores;

import java.util.concurrent.Semaphore;

public class Synchro {

	Semaphore sem_trb;
	Semaphore[] sem_sensor;
	int num_sensores;
	
	
	public Synchro(int num_sensores) {
		this.num_sensores = num_sensores;
		sem_sensor = new Semaphore[num_sensores];
		for (int i = 0; i < sem_sensor.length; i++) {
			sem_sensor[i] = new Semaphore(1,true);
		}
		sem_trb = new Semaphore(0,true); //no debe pasar hasta alcanzar num_sensores permisos
	}

	public void arrancaSensor(int id) throws InterruptedException {
		sem_sensor[id].acquire();
	}

	public void finalizaSensor(int id) {
		sem_trb.release();
	}

	public void arrancaTrabajador() throws InterruptedException {
		sem_trb.acquire(num_sensores); //quiero que todos los sensores hayan escrito
	}

	public void finalizaTrabajador() {
		for (int i = 0; i < sem_sensor.length; i++) {
			sem_sensor[i].release();
		}
	}

}
