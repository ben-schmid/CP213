package cp213;

/**
 * @author Benjamin Schmid 169042790
 * @version 2023-09-19
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {

	double v1result = Math.abs(target - v1);
	double v2result = Math.abs(target - v2);

	if (v2result < v1result) {
	    return v2;
	}

	return v1;
    }

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {

	// your code here
	boolean prime = true;

	if (n <= 1) {
	    prime = false; // 1 and all negative numbers are not prime

	} else if (n <= 3) {
	    prime = true; // 2 and 3 are prime

	} else if (n % 2 == 0 || n % 3 == 0) {
	    prime = false; // numbers divisible by 2 and 3 are not prime

	} else {
	    int divisor = 5;
	    while (divisor * divisor <= n && prime) {
		if (n % divisor == 0 || n % (divisor + 2) == 0) {
		    prime = false; // If n is divisible by any number in this range, it's not prime
		}
		divisor += 6; // Increment divisor by 6 to check the next potential divisors
	    }

	}

	return prime;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {

	double sum = 0.0;
	// your code here
	for (int i = 1; i <= n; i++) {
	    sum += 1.0 / i;
	}

	return sum;
    }

}
