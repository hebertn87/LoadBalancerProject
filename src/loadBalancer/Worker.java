package loadBalancer;


public class Worker implements Runnable {
	
	//Data members
	private Integer requestNum;

	//Constructor
	public Worker() {
		requestNum = 0;
	}
	
	public Integer sendRequestNum() {
		return requestNum;
	}

	@Override
	public void run() {
		
		//If at max threads	
		if(Thread.activeCount() > 12) {
			System.out.println("The threads are busy, waiting until one is ready.");
			while(Thread.activeCount() > 12) {}
		}

	}
}
