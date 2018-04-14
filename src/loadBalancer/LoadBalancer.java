package loadBalancer;

import java.util.ArrayDeque;
import java.util.Queue;
import requestMaker.RequestMaker;
import waldo.WheresWaldo;

public class LoadBalancer {
	
	//Data Members
	private Integer numThreads, requestNum;
	Listener listener = new Listener();
	WheresWaldo[] waldo = new WheresWaldo[4];
	Worker worker = new Worker();
	RequestMaker[] request;
	Thread[] myThread = new Thread[12];
	
	//Queue
	Queue<Integer> qRequest = new ArrayDeque<Integer>();

	//Load balancer constructor
	public LoadBalancer(Listener _listener, Worker _worker, Integer numOfReq) {
		
		listener = _listener;
		worker = _worker;
		numThreads = 0;
		requestNum = 0;
		
		request = new RequestMaker[numOfReq];
		
		for(int i = 0; i < numOfReq; i++) {
			request[i] = new RequestMaker(false, i);
		}
		
		for(int i = 0; i < waldo.length; i++) {
			waldo[i] = new WheresWaldo();
		}
	}
	
	//Listen puts the request on the queue sends it off
	public void Listen() {
		for(int i = 0; i < request.length; i++) {
			//Outputs the request number for debug
			System.out.println("Load balancer got request # " + request[i].GetId() + ".");
			
			//Queue needs to be synchronized
			synchronized(qRequest) {
				qRequest.add(request[i].GetId());
			}
		
			//Spawns off new worker threads
			myThread[numThreads] = new Thread(worker);
			myThread[numThreads].start();
			
			System.out.println("We have now put our thread to work.");
			
			//Call work
			Work();
		}
	}
	
	//Does the work of spawning off new waldo threads for our worker threads to do
	public void Work() {
		synchronized(numThreads) {
			for(int i = 0; i < waldo.length; i++) {
				if(numThreads <= 12 && numThreads >= 0 && !qRequest.isEmpty()) {
					numThreads++;	
					//Synchronized so that only 1 can take from the queue at a time
					synchronized(qRequest) {
						requestNum = qRequest.remove();
					}
					
					//Put the thread we chose into one of 4 waldo's depending on capacity
					myThread[i] = new Thread(waldo[i]);
					myThread[i].start();
					
					//Outputs the request number and decrements our thread number
					System.out.println("Server has finished request number " + requestNum + ".");
					numThreads--;
				}
			}
		}
	}
	
}
