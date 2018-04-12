package loadBalancer;

import java.util.ArrayDeque;
import java.util.Queue;

import requestMaker.RequestMaker;

//This class listens for requests that come in, and then sends the request to a worker
public class LBListener {

	RequestMaker request;
	int count = 1;
	LBWorker worker = new LBWorker();
	public static Queue<Integer> q = new ArrayDeque<Integer>();
	
	public LBListener(RequestMaker newRequest) {
		request = newRequest;
	}

	public void Listen() {
		//repeat once another request has been make
		//Do this for as many requests are available
		
		//While request is null spin
			//while(request.getRequest().equals(false)) {}
			System.out.println("We have a Request.");	
			//take the request and pop on queue
			q.add(count);
			worker.doWork();
			count++;
	}
	
	
	
}
