package loadBalancer;

import java.util.ArrayDeque;
import java.util.Queue;
import requestMaker.RequestMaker;

//This class listens for requests that come in, and then sends the request to a worker
public class LBListener {
	
	//Instantiation of variables
	private static RequestMaker request;
	private static Queue<Integer> q = new ArrayDeque<Integer>();
	int count = 1;
	
	//Constructor
	public LBListener(RequestMaker _request) {
		request = _request;	
	}

	//This method listens for the request and sends it to worker thread
	public void Listen() {
		synchronized(this) {
			//While request is null spin
			while(request.GetRequest().equals(false)) {}
			
			System.out.println("Load Balancer got request # " + request.GetId() + ".");	
			
			//Take the request and pop on queue
			q.add(request.GetId());
			LBWorker worker = new LBWorker();
			worker.doWork();
			count++;
		}
	}
	
	public static Queue<Integer> getQueue(){
		return q;
	}
	
	public static RequestMaker getRequset() {
		return request;
	}
	
}
