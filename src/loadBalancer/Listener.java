package loadBalancer;

import java.util.ArrayDeque;
import java.util.Queue;

public class Listener {
	
	private Queue<Integer> q;
	
	//Constructor, creates a new queue
	public Listener() {
		q = new ArrayDeque<Integer>();
	}
	
	//Sets the queue
	public void SetQueue(Queue<Integer> q){
		this.q = q;
	}
	
	//Returns the queue
	public Queue<Integer> GetQueue(){
		return q;
	}
}
