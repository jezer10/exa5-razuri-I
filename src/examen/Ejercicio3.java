package examen;

import java.util.Random;

public class Ejercicio3 {

	public static void main(String[] args) {
		int NUM_PROCESOS = 5;
		Filosofo filosofos[] = new Filosofo[NUM_PROCESOS];
		GestorPalillos gestorPalillos;
		gestorPalillos = new GestorPalillos(NUM_PROCESOS);
		Thread hilos[] = new Thread[NUM_PROCESOS];
		for (int i = 1; i < NUM_PROCESOS; i++) {
			filosofos[i] = new Filosofo(gestorPalillos, i, i - 1);
			hilos[i] = new Thread(filosofos[i]);
			hilos[i].start();
		}
		filosofos[0] = new Filosofo(gestorPalillos, 0, 4);
		hilos[0] = new Thread(filosofos[0]);
		hilos[0].start();

	}

	
	
	static class GestorPalillos {
		boolean palilloLibre[];

		public GestorPalillos(int numPalillos) {
			palilloLibre = new boolean[numPalillos];
			for (int i = 0; i < numPalillos; i++) {
				palilloLibre[i] = true;
			} // Fin del for
		} // Fin del constructor

		public synchronized boolean intentarCogerPalillos(int pos1, int pos2) {
			boolean seConsigue = false;
			if ((palilloLibre[pos1]) && (palilloLibre[pos2])) {
				palilloLibre[pos1] = false;
				palilloLibre[pos2] = false;
				seConsigue = true;
			} // Fin del if
			return seConsigue;
		}

		public void liberarPalillos(int pos1, int pos2) {
			palilloLibre[pos1] = true;
			palilloLibre[pos2] = true;
		}
	}

	
	
	static class Filosofo implements Runnable {
		GestorPalillos gestorPalillos;
		int posPalilloIzq, posPalilloDer;

		public Filosofo(GestorPalillos g, int pIzq, int pDer) {
			this.gestorPalillos = g;
			this.posPalilloDer = pDer;
			this.posPalilloIzq = pIzq;
		}

		public void run() {
			while (true) {
				boolean palillosCogidos;
				palillosCogidos = this.gestorPalillos.intentarCogerPalillos(posPalilloIzq, posPalilloDer);
				if (palillosCogidos) {
					comer();
					this.gestorPalillos.liberarPalillos(posPalilloIzq, posPalilloDer);
					dormir();
				} // Fin del if
			} // Fin del while true
		} // Fin del run()

		private void comer() {
			System.out.println("Filosofo " + Thread.currentThread().getName() + " comiendo");
			esperarTiempoAzar();
		}

		private void esperarTiempoAzar() {
			Random generador = new Random();
			int msAzar = generador.nextInt(3);
			try {
				Thread.sleep(msAzar);
			} catch (InterruptedException ex) {
				System.out.println("Fallo la espera");
			}
		}

		private void dormir() {
			System.out.println("Filosofo " + Thread.currentThread().getName() + " durmiendo (zzzzzz)");
			esperarTiempoAzar();
		}
	}

}
