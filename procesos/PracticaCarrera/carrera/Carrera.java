package carrera;

import java.util.concurrent.CountDownLatch;

import corredor.Corredor;

public class Carrera {
	private CountDownLatch espera;
	public Carrera(int nCorredores) {
		espera = new  CountDownLatch(1);
		for (int i = 0; i < nCorredores; i++) {
			new Thread(new Corredor(i,espera)).start();
		}
		try {
			Thread.sleep(1500);
			System.out.println("Preparados...");
			Thread.sleep(1500);
			System.out.println("Listos...");
			Thread.sleep(1500);
			System.out.println("YA!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		espera.countDown();
	}
}
