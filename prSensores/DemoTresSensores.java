package sensores;

public class DemoTresSensores {

	private static final int NUM_SENSORES = 3;

	public static void main(String[] args) {
		int[] valores = new int[NUM_SENSORES];
		Synchro synchro = new Synchro(NUM_SENSORES);
		
		Trabajador trabajador = new Trabajador(valores, synchro);
		Sensor[] sensor = new Sensor[NUM_SENSORES];
		for(int i = 0; i < sensor.length; i++){
			sensor[i] = new Sensor(i, valores, synchro);
		}
		Thread th_trb = new Thread(trabajador,"Dispositivo_Trabajador");
		th_trb.start();
		
		Thread[] th_sen = new Thread[NUM_SENSORES];
		for (int i = 0; i < th_sen.length; i++) {
			th_sen[i] = new Thread(sensor[i],"Sensor_"+i);
			th_sen[i].start();
		}
	}

}
