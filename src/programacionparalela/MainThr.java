package programacionparalela;

import java.util.Iterator;

import runnablethr.HiloThr1;
import runnablethr.HiloThr2;

public class MainThr {
	
	public static void main(String[] args) {
//		new EatingPan("Juancho").start();
//		new EatingPan("Better Call Saul").start();
//		new EatingPan("Lolito").start();
//		new EatingPan("Dalas").start();
//		new EatingPan("Mini").start();
//		new EatingPan("Juan English").start();
//		new EatingPan("Lucas").start();
//		new EatingPan("Jair con h").start();
//		new EatingPan("Jafe con d").start();
//		new EatingPan("Jezer con y").start();
		
//		new HiloThr1().start();
//		new HiloThr2().start();
		
		new Guepardo("jaicho").start();
		new Guepardo("beto").start();
		new Guepardo("pepe").start();
		new Guepardo("bob").start();
	}
	
	
	
	static class EatingPan extends Thread{
		private String name;
		

		public EatingPan(String name) {
			this.name=name;
		}

		public void run() {
			
			for (int i = 1; i <= 3; i++) {
				System.out.println(name+" comiendo pan "+i);
			}
			
		}
	}
	
	static class Guepardo extends Thread{
		String name;
		public static boolean llego =false;
		
		public Guepardo(String name) {
			this.name=name;
		}
		
		public void carrera() {
			System.out.println("Guepardo "+name+" listo para la carrera.");
			for (int i = 0; i < 200; i++) {
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(llego==false) {
				System.out.println("Guepardo "+name+" termino primero");
				llego=true;

			}
			
		}
		
		@Override
		public void run() {
			carrera();
		}
		
	}

}
