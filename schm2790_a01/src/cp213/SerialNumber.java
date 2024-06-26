package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Benjamin Schmid 169042790
 * @version 2023-09-19
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	// your code here
	boolean result = true;

	if (str == null || str.isEmpty()) {
	    result = false; // return false if there is no string

	} else {
	    for (char c : str.toCharArray())
		if (!Character.isDigit(c)) {
		    result = false;
		}
	}

	return result;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	// your code here
	String pattern = "^SN/[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]$";

	return sn.matches(pattern);

    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here

	while (fileIn.hasNextLine()) {
	    // get string from file
	    String serial = fileIn.nextLine();

	    // call validSn to determine if line is a valid serial number
	    boolean valid = validSn(serial);

	    if (valid) {
		goodSns.println(serial);
	    } else {
		badSns.println(serial);
	    }

	}

	return;
    }

}
