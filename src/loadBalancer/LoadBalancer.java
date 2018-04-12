package loadBalancer;

import waldo.WheresWaldo;

public class LoadBalancer {
	
	private Listener listener = new Listener();
	private WheresWaldo waldo = new WheresWaldo();
	
	Worker[] worker = new Worker[];
	
}
