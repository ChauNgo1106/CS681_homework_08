package edu.umb.cs681.singleton;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable {

	private static ConcurrentSingleton instance = null;
	private static ReentrantLock locks = new ReentrantLock();;

	// default constructor
	private ConcurrentSingleton() {
	}

	// Singleton
	public static ConcurrentSingleton getInstance() {
		locks.lock();
		try {
			return Objects.requireNonNull(instance);
		} catch (NullPointerException ex) {
			instance = new ConcurrentSingleton();
			return instance;
		} finally {
			locks.unlock();
		}
	}

	@Override
	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());

	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new ConcurrentSingleton().getInstance());
		Thread thread2 = new Thread(new ConcurrentSingleton().getInstance());

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException ex) {
			ex.getMessage();
		}

	}

}
