package loadBalancer;

import java.util.ArrayDeque;
import java.util.Queue;
import requestMaker.RequestMaker;
import waldo.WheresWaldo;

public class LoadBalancer {
	
	private Integer numThreads, requestNum;
	Listener listener = new Listener();
	WheresWaldo[] waldo = new WheresWaldo[4];
	Worker worker = new Worker();
	RequestMaker[] request = new RequestMaker[1000];
	Thread[] myThread = new Thread[12];
	
	
	Queue<Integer> qRequest = new ArrayDeque<Integer>();
	Queue<Integer> qWaldo = new ArrayDeque<Integer>();
	
	public LoadBalancer(Listener _listener, Worker _worker) {
		
		listener = _listener;
		worker = _worker;
		numThreads = 0;
		requestNum = 0;
		
		for(int i = 0; i < request.length; i++) {
			request[i] = new RequestMaker(false, i);
		}
		
		for(int i = 0; i < waldo.length; i++) {
			waldo[i] = new WheresWaldo();
		}
		
		for(int i = 0; i < myThread.length; i++) {
			myThread[i] = new Thread(worker);
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
			
			//Call work
			Work();
		}
	}
	
	public void Work() {
		//synchronized(numThreads) {
			synchronized(qRequest) {
				for(int i = 0; i < waldo.length; i++) {
					if(numThreads <= 12 && numThreads >= 0 && !qRequest.isEmpty()) {
						numThreads++;
						
						myThread[numThreads - 1] = new Thread(worker);
						myThread[numThreads - 1].start();
					
						System.out.println("We have now put our thread to work.");
						requestNum = qRequest.remove();
						
						//Put the thread we chose into one of 4 waldo's depending on capacity
						myThread[i] = new Thread(waldo[i]);
						
						myThread[i].start();
						
						System.out.println("Server has finished request number " + requestNum + ".");
						numThreads--;
					}
				}
			}
		//}	
	}
	
}
