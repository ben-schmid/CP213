package cp213;

/**
 * Sample string methods.
 *
 * @author your name, ID, and email here
 * @version 2022-05-06
 */
public class StringMethods {
    // Constants
    /**
     * String of vowels.
     */
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Counts the number of vowels in a string. Does not include 'y'.
     *
     * @param string A string.
     * @return Number of vowels in string.
     */
    public static int vowelCount(final String string) {
	int count = 0;

	for (char c : string.toCharArray()) {
	    if (VOWELS.contains(String.valueOf(c))) {
		count++;
	    }
	}

	return count;
    }

    /**
     * Counts the number of digits in a string.
     *
     * @param string A string.
     * @return Number of digits in string.
     */
    public static int digitCount(final String string) {
	int count = 0;

	for (char c : string.toCharArray()) {
	    if (Character.isDigit(c)) {
		count++;
	    }
	}

	return count;
    }

    /**
     * Totals the individual digits in a string.
     *
     * @param string A string.
     * @return The integer total of all individuals digits in string.
     */
    public static int totalDigits(final String string) {
	int total = 0;

	for (char c : string.toCharArray()) {
	    if (Character.isDigit(c)) {
		total += Character.getNumericValue(c);
	    }
	}

	return total;
    }

    /**
     * Compares string1 and string2 and returns a comma-delimited concatenated
     * string consisting of the string that is first lexically followed by the
     * string that is second lexically. If the strings are equal, then only string1
     * is returned.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return A concatenation of string1 and string2 in order.
     */
    public static String pairs(String string1, String string2) {
	String line = null;

	if (string1 == null || string1.isEmpty()) {
	    return string2;
	}
	if (string2 == null || string2.isEmpty()) {
	    return string1;
	}

	// perform lexical comparison
	// if string1 comes before string2 result will be -1, if string2 comes first,
	// result = 1 if string is equal, result will be 0
	int result = string1.compareTo(string2);

	// string1 comes before string 2 lexicographically
	if (result < 0) {
	    line = string1 + "," + string2;
	    // string 2 comes before stirng 1
	} else if (result > 0) {
	    line = string2 + "," + string1;
	    // string are equal
	} else {
	    line = string1;
	}

	return line;
    }

    /**
     * Finds the distance between the s1 and s2. The distance between two strings of
     * the same length is the number of positions in the strings at which their
     * characters are different. If two strings are not the same length, the
     * distance is unknown (-1). If the distance is zero, then two strings are
     * equal.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return The distance between string1 and string2.
     */
    public static int stringDistance(String string1, String string2) {

	if (string1.length() != string2.length()) {
	    return -1;
	}
	int distance = 0;

	for (int i = 0; i < string1.length(); i++) {
	    if (string1.charAt(i) != string2.charAt(i)) {
		distance++;
	    }
	}

	return distance;
    }
}
