package runnablethr;


public class HiloThr1 extends Thread{
	private int number;
	public int result;

	public  HiloThr1() {
		super();
	}
	public HiloThr1(int number) {
		super();
		this.number=number;
	}
	private void dec() {
		for (int i = 10; i > 0; i--) {
			System.out.println("HILO 1 DEC( NUMERO : " + (i+1) +" )");
		}
	}
	
	private  void impar() {
		int c = 0;
		for (int i = 1; i <= 10; i++) {

			if (i % 2 == 1) {
				c += i;
				System.out.println("HILO 1( NUMERO PAR: " + i + " SUMA: " + c + " )");
			} else {

			}

		}
	}
	
	public static int  cub(int n) {
		int c=1;
		for (int i = 0; i < 2; i++) {
			c*=n;
		}
		return c;
	}
	
	public void run() {
		impar();
	}
	
	

}
