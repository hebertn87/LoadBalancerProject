package requestMaker;

import loadBalancer.*;


public class RequestDriver {
	public static void main(String[] args) { 
		
		RequestMaker request = new RequestMaker();
		LBListener listener = new LBListener(request);
		
		//Send a request by calling listener
		//listener hands it off
		listener.Listen();
		
	}
}
