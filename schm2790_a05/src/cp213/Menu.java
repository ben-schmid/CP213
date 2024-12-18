package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-09-06
 */
public class Menu {

    // Attributes.

    public static void main(String[] args) {
	String filename = "menu.txt";

	try {
	    Scanner fileScanner = new Scanner(new File(filename));
	    Menu menu = new Menu(fileScanner);
	    fileScanner.close();
	    Cashier cashier = new Cashier(menu);
	    cashier.takeOrder();
	} catch (FileNotFoundException e) {
	    System.out.println("Cannot open menu file");
	}
	System.exit(0);

    }

    private List<MenuItem> items = new ArrayList<MenuItem>();

    /**
     * Creates a new Menu from an existing Collection of MenuItems. MenuItems are
     * copied into the Menu List.
     *
     * @param items an existing Collection of MenuItems.
     */
    public Menu(Collection<MenuItem> items) {
	this.items.addAll(items);

    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	this.items = new ArrayList<>();

	while (fileScanner.hasNextLine()) {
	    String line = fileScanner.nextLine();
	    int spaceIndex = line.indexOf(' ');
	    double price = Double.parseDouble(line.substring(0, spaceIndex));
	    String name = line.substring(spaceIndex + 1);
	    MenuItem item = new MenuItem(name, price);
	    this.items.add(item);
	}
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	MenuItem item = this.items.get(i);

	return item;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	return this.items.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
	String menuString = "";
	for (int i = 0; i < this.items.size(); i++) {
	    menuString = menuString + " " + (i + 1) + ") " + getItem(i).toString() + "\n";
	}
	return menuString;
    }
}