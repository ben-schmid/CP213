package cp213;

/**
 * @author Benjamin Schmid 169042790
 * @version 2023-09-19
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	boolean valid = true;

	// gets rid of spaces, and symbols of the string for easy comparison
	String cleaned = string.replaceAll("[^A-Za-z]", "").toLowerCase();

	int left = 0;
	int right = cleaned.length() - 1;

	while (left < right && valid) {
	    if (cleaned.charAt(left) != cleaned.charAt(right)) {
		valid = false;
	    }
	    left++;
	    right--;

	}
	return valid;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	// your code here
	String pattern = "^[a-zA-Z_][0-9A-Za-z_]*$";

	return name.matches(pattern);
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	String result = "";

	// check if string is empty or null
	if (word == null || word.isEmpty()) {
	    result = word;

	} else {
	    char firstChar = word.charAt(0);
	    boolean UCaseFirstChar = Character.isUpperCase(firstChar);

	    if (VOWELS.indexOf(firstChar) >= 0) {
		// word starts with a vowel
		result = word + "way";

	    } else {
		// word starts with
		int index = 1;
		int len = word.length();

		while (index < len && VOWELS.indexOf(Character.toLowerCase(word.charAt(index))) < 0) {
		    index++;

		}
		result = word.substring(index) + word.substring(0, index) + "ay";

	    }
	    if (UCaseFirstChar) {
		result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
	    }
	}

	return result;
    }

}
