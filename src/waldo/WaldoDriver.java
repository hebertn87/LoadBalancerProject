package waldo;

public class WaldoDriver {
	
	public static void main(String[] args) { 
		WheresWaldo waldo = new WheresWaldo();
		Thread myThread = new Thread(waldo);
		myThread.start();
	}
}
