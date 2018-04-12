package requestMaker;

import loadBalancer.*;

public class RequestDriver {
	public static void main(String[] args) { 
		
		RequestMaker[] request = new RequestMaker[20];
		//Send a request by calling listener
		//listener hands it off
		for(int i = 0; i < request.length; i++){
			request[i] = new RequestMaker(true, i);
			System.out.println("You are request " + i + ".");
			LBListener listener = new LBListener(request[i]);
			listener.Listen();
			System.out.println("Your request, Request " +  LBWorker.sendRequestNum() + " has been completed!.");
		}
		
		
	}
}
