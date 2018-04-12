package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import waldo.WheresWaldo;

class WheresWaldoTest {
	
	@Test
	void test() {
		WheresWaldo waldo = new WheresWaldo();
		Thread myThread = new Thread(waldo);
		myThread.start();
	}

}
