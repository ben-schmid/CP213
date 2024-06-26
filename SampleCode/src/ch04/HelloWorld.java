package ch04;

public class HelloWorld {
    public static void main(String[] args) {
	System.out.println("Hello, World!");

	// To display command-line arguments
	for (String arg : args) {
	    System.out.println(arg);
	}
    }
}
