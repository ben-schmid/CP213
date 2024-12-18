package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");
	this.printCommands();
	Order order = new Order();
	Scanner reader = new Scanner(System.in);

	boolean not_done = true;

	do {
	    int input = -1, input2 = 0;

	    System.out.print("Command: ");
	    if (reader.hasNextInt()) {
		input = reader.nextInt();
	    } else {
		System.out.println("Not a valid number\n");
		reader.next();
	    }

	    if (input < 0 || input > this.menu.size()) {
		System.out.println("Menu:");
		System.out.print(this.menu.toString());
		System.out.println("Press 0 when done.");
		System.out.println("Press any other key to see the menu again.\n");
	    } else if (input > 0 && input <= this.menu.size()) { // if its on menu
		System.out.print("How many do you want? ");
		if (reader.hasNextInt()) {
		    input2 = reader.nextInt();
		    if (input2 > 0) {
			MenuItem item = this.menu.getItem(input - 1);
			order.add(item, input2);
		    }
		}
	    } else {
		not_done = false;
	    }
	} while (not_done);

	System.out.println("----------------------------------------");
	System.out.println("Receipt");
	System.out.println(order.toString());

	reader.close();
	return order;

    }
}