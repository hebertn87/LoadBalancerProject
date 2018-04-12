package waldo;

import java.util.concurrent.ThreadLocalRandom;

public class WheresWaldo implements Runnable {
	
	@Override
	public void run() {
		int rand = 0;
		while(rand != 5555) {
			rand = ThreadLocalRandom.current().nextInt(0, 9999);
		}
	}
}
