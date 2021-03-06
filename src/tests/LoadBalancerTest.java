package tests;

import org.junit.jupiter.api.Test;

import loadBalancer.Listener;
import loadBalancer.LoadBalancer;
import loadBalancer.Worker;

public class LoadBalancerTest {
	
	@Test
	public void Test() {
		Listener listener = new Listener();
		Worker worker = new Worker();
		LoadBalancer lb = new LoadBalancer(listener, worker, 2000);
		lb.Listen();
		System.out.println("Your request for this action has been completed.");
	}
}
