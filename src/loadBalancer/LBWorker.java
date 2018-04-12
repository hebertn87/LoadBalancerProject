package loadBalancer;

import waldo.WheresWaldo;
import java.util.Queue;

//Worker has threads that handle the request
//requests are handled here and set back
public class LBWorker {
	
	private Queue<Integer> q;
	private Integer workerCount = 0;
	private static Integer requestNum = 0;
	
	Thread worker;
	WheresWaldo waldo = new WheresWaldo();
	
	public LBWorker() {
		q = LBListener.getQueue();
	}
	
	//Do work sets the queue, then checks how many worker we have. If we have
	//More than 12, we wait until we don't have 
	public void doWork() {
		synchronized(this) {
			q = LBListener.getQueue();		//Set the queue to the Listeners queue
			
			//If there are 12 or more workers, we wait until a worker is free
			while(Thread.activeCount() > 12) {
				System.out.println("The threads are busy or the queue is empty, try again.");	
			}	
			
			//If the queue is empty, start a new thread, and remove the request from the queue
			if(Thread.activeCount() <= 12 && !q.isEmpty()) {
				
				System.out.println("We have now put thread to work.");
				
				//Spawn off new thread
				worker = new Thread(waldo);		
				workerCount++;
				
				requestNum = q.remove();
				
				//Start the worker
				worker.start();			
			}
		}
	}
	
	public static Integer sendRequestNum() {
		return requestNum;
	}
}
