package edu.umb.cs681.singleton;


import java.util.Objects;

public class Singleton {
	
	private static Singleton instance = null;
	
	//default constructor
	private Singleton() {
	}
	//Singleton 
	public static Singleton getFileSystem() {
		try {
			return Objects.requireNonNull(instance);
		} catch(NullPointerException ex) {
			instance = new Singleton();
			return instance;
		}
	}

}
