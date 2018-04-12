package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import waldo.WheresWaldo;

class WheresWaldoTest {

	@BeforeEach
	void beforeEach() {
		WheresWaldo waldo = new WheresWaldo();
		Thread myThread = new Thread(waldo);
		myThread.start();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
