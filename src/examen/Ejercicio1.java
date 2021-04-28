package examen;

import java.util.Random;

public class Ejercicio1 {

	public static void main(String[] args) throws InterruptedException{
		final int NUMCLIENTES = 300;
		final int NUMPRODUCTOS = 100;
		
		Cliente[] cliente = new Cliente[NUMCLIENTES];
		Almacen almacen = new Almacen(NUMPRODUCTOS);
		
		Puerta puerta = new Puerta();
		
		Thread[] hilos = new Thread[NUMCLIENTES];
		
		for (int i = 0; i < NUMCLIENTES;i++) {
			String nhilo= "Cliente "+(i+1);
			cliente[i]=new Cliente(puerta,almacen,nhilo);
			hilos[i]= new Thread(cliente[i]);
			hilos[i].start();	
		}
		
		for (int i = 0; i < NUMCLIENTES; i++) {
			hilos[i].join();
			
		}
	}

	static class Cliente implements Runnable {

		Puerta puerta;
		Almacen almacen;
		String nombre;
		Random gen;

		final int MAXTRIES = 100;

		Cliente(Puerta p, Almacen a, String n) {
			puerta = p;
			almacen = a;
			nombre = n;
			gen = new Random();
		}

		public void esperar() {
			try {
				int msazar = gen.nextInt(100);
				Thread.sleep(msazar);
			} catch (InterruptedException e) {
				System.out.println("Fallo Espera");

			}
		}

		@Override
		public void run() {
			for (int i = 0; i < MAXTRIES; i++) {
				if (!puerta.ocupada()) {
					if (puerta.entrar()) {
						esperar();
						puerta.desocupar();
						if (almacen.cogerProducto()) {
							System.out.println(nombre + ": cogí un producto!");
							return;
						} else {
							System.out.println(nombre + ": ops, crucé pero no cogí nada");
							return;
						} 
					} 
				} else {
					esperar();
				}
			}
			System.out.println(this.nombre + ": lo intenté muchas veces y no pude");

		}

	}

	static class Almacen {
		private int numpr;

		Almacen(int numpr) {
			this.numpr = numpr;
		}

		public boolean cogerProducto() {
			if (this.numpr > 0) {
				this.numpr--;
				return true;
			}
			return false;
		}

	}

	static class Puerta {
		boolean ocupada;

		Puerta() {
			this.ocupada = false;
		}

		public boolean ocupada() {
			return this.ocupada;
		}

		public synchronized void desocupar() {
			this.ocupada = false;
		}

		public synchronized boolean entrar() {
			if (ocupada())
				return false;
			this.ocupada = true;
			return true;
		}
	}


}
