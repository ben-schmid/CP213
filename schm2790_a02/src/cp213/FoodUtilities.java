package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author your name, id, email here
 * @version 2023-05-07
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {

	// check if food list is null or empty

	if (foods == null || foods.isEmpty()) {
	    return 0;
	}

	int sumCalories = 0;

	for (int i = 0; i < foods.size(); i++) {
	    sumCalories += foods.get(i).getCalories();
	}

	return sumCalories / foods.size();
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

	if (foods == null || foods.isEmpty()) {
	    return 0;
	}

	int sumCalories = 0;
	int count = 0;

	for (Food food : foods) {
	    if (food.getOrigin() == origin) {
		sumCalories += food.getCalories();
		count++;

	    }
	    if (count == 0) {
		return 0;
	    }

	}

	return sumCalories / count;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

	ArrayList<Food> foodOrigin = new ArrayList<>();

	if (foods == null || foods.isEmpty()) {
	    return foodOrigin; // return empty list list
	}

	for (Food food : foods) {
	    if (food.getOrigin() == origin) {
		foodOrigin.add(food);

	    }
	}

	return foodOrigin;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {

	Boolean validVeg = false; // loop condition
	Boolean validOrigin = false; // loop condition
	Boolean isVegetarian = false;
	int origin = -1;

	// input user name
	System.out.println("Name: ");
	String name = keyboard.nextLine();
	System.out.println(Food.originsMenu());

	// get origin input
	while (!validOrigin) {
	    System.out.println("Origin: ");
	    origin = Integer.parseInt(keyboard.nextLine());
	    if (origin >= 0 && origin <= 11) {
		validOrigin = true;
		// exit loop, condition met

	    } else {
		System.out.println("Invalid origin number! Please choose a number between 0 and 11!");
		// invalid input, loop
	    }
	}
	// get vegetarian input
	while (!validVeg) {
	    System.out.println("Vegetarian (Y/N)");
	    String veg = keyboard.nextLine();
	    if (veg.toUpperCase().equals("Y")) {
		isVegetarian = true;
		validVeg = true; // exit loop
	    } else if (veg.toUpperCase().equals("N")) {
		validVeg = true; // exit loop

	    } else {
		System.out.println("Invalid Vegetarian option! Please type Y or N!");
		// invalid input, loop
	    }
	}

	// get calorie input
	int calories = -1;
	while (calories < 0) {
	    try {
		System.out.println("Calories: ");
		calories = Integer.parseInt(keyboard.nextLine());
		if (calories < 0) {
		    System.out.println("Invalid! Calories cannot be negative!");
		    // continue loop if calorie is negative number
		}
	    } catch (NumberFormatException e) {
		System.out.println("Please enter a valid number for calories!");
		// if a non integer value in inputed, error will be caught and program will loop
	    }
	}

	Food food = new Food(name, origin, isVegetarian, calories);

	return food;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

	ArrayList<Food> veg = new ArrayList<>();

	for (Food food : foods) {
	    if (food.isVegetarian()) {
		veg.add(food);
	    }

	}

	return veg;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {

	String[] result = line.split("\\|");

	String name = result[0];
	int origin = Integer.parseInt(result[1]);
	Boolean isVegetarian = Boolean.parseBoolean(result[2]);
	int calories = Integer.parseInt(result[3]);
	Food food = new Food(name, origin, isVegetarian, calories);

	return food;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {

	ArrayList<Food> foods = new ArrayList<>();
	String line;
	while (fileIn.hasNext()) {
	    line = fileIn.nextLine();
	    foods.add(readFood(line));
	}

	return foods;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {

	ArrayList<Food> list = new ArrayList<Food>();
	for (Food food : foods) {
	    if ((origin == -1 || food.getOrigin() == origin) && (maxCalories == 0 || food.getCalories() <= maxCalories)
		    && (!isVegetarian || food.isVegetarian())) {
		list.add(food);
	    }

	}
	return list;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	for (Food food : foods) {
	    ps.println(food.toString());
	}
	return;

    }
}
