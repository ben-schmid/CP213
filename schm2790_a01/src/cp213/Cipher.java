package cp213;

/**
 * @author Benjamin Schmid 169042790
 * @version 2023-09-19
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {

	// your code here

	String chiph = s.toUpperCase();
	String result = "";

	for (char c : chiph.toCharArray()) {
	    if (Character.isLetter(c)) {
		char shifted = (char) (c + n % ALPHA_LENGTH);

		result += shifted;

	    } else {
		result += c;
	    }
	}

	return result;
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

	// your code here

	String input = s.toUpperCase();
	String result = "";

	for (char c : input.toCharArray()) {
	    if (Character.isLetter(c)) {
		// find index of character in alphabet
		int index = ALPHA.indexOf(c);

		if (index != -1) {
		    // Replace character with coresponding character
		    char encipherChar = ciphertext.charAt(index);
		    result += encipherChar;

		}

	    } else {
		result += c;
	    }
	}

	return result;
    }

}
