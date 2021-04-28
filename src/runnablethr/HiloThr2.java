package runnablethr;

public class HiloThr2 extends Thread{
	int cubes[]= {2, 7, 5, 4, 9, 3, 6, 8, 1, 10};
	int number;
	public HiloThr2() {
		super();
	}
	
	public HiloThr2(int number) {
		super();
		this.number= number;
	}
	
	private void asc() {
		for (int i = 0; i < 10; i++) {
			System.out.println("HILO 2 ASC( NUMERO : " + (i+1) +" )");
		}
	}
	
	private  void par() {
		int c = 0;
		for (int i = 1; i <= 10; i++) {

			if (i % 2 == 1) {

			} else {
				c += i;
				System.out.println("HILO 2( NUMERO PAR: " + i + " SUMA: " + c + " )");
			}

		}

	}
	public void cr() {
		int count=0;
		for (int i = 0; i < cubes.length; i++) {
			new HiloThr1(cubes[i]).start();
			count+=HiloThr1.cub(cubes[i]);
		}
		System.out.println("SUMA TOTAL: "+count);
	}
	
	
	public int  fact(int n) {
		int count=1;
		for (int i = 2; i <= n; i++) {
			count=count*i;
		}
		return count;
	}

	
	public void run() {
		System.out.println(fact(number));
	}
}
