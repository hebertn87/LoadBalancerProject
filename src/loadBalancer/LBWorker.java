package loadBalancer;
import waldo.WheresWaldo;

import java.util.ArrayDeque;
import java.util.Queue;

//Worker has threads that handle the request
//requests are handled here and set back
public class LBWorker {
	
	private Integer threadPerWaldo = 3;
	private Integer waldoCount = 0;
	Thread[] worker = new Thread[12];
	WheresWaldo waldo = new WheresWaldo();
	
	public LBWorker() {
		//queue = q.getQueue();
	}
	
	public void doWork() {
		//while(true){
			//If there are less than 12 workers
			//Start a new thread, and run where's Waldo
			synchronized(this) {
				for(int i = 0; i < worker.length; i++) {
					while(true) {
						if(Thread.activeCount() < 12 && !LBListener.q.isEmpty()) {
							System.out.println("We have now put thread " + i + " to work.");
							
							waldo = new WheresWaldo();
							worker[i] = new Thread(waldo);		
							waldoCount++;
							LBListener.q.remove();
							
							//Start the worker
							worker[i].start();
							break;
						//If there are 12 or more workers, we wait until a worker is free
						}else {
							System.out.println("The threads are busy or the queue is empty, try again.");
							while(i >= 12) {}
						}
					}
				}
			}
		//}
	}
}
