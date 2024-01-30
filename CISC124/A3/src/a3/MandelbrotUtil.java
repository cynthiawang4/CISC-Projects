package a3;

/**
 * This class provides a method for computing the number of iterations for which
 * z(n + 1) = z(n) * z(n) + c remains bounded where z(0) = 0 + 0i.
 *
 */
public class MandelbrotUtil {

	private MandelbrotUtil() {
		// A private constructor prevents users from creating a
		// MandelbrotUtil object.
	}

	/**
	 * Simple test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Complex c = new Complex(0.0, 5.0);
		System.out.println(mandelbrotIterations(c, 100));
	}
	
	/**
	 * Return the number of iterations for which z(n + 1) = z(n) * z(n) + c remains
	 * bounded where z(0) = 0 + 0i. z(n + 1) is bounded if its magnitude is less than
	 * or equal to 2. Returns 1 if the magnitude of c is greater than 2. Returns max
	 * if the magnitude of z(n + 1) is less than or equal to 2 after max iterations.
	 * 
	 * @param c a complex number
	 * @param max the maximum number of iterations to attempt
	 * @return the number of iterations for which z(n + 1) = z(n) * z(n) + c remains
	 * bounded where z(0) = 0.0 + 0.0i.
	 * @throws IllegalArgumentException is max is less than 1
	 */
	public static int mandelbrotIterations(Complex c, int max) {
		if (max < 1) {
			throw new IllegalArgumentException();
		}
		
		Complex z = new Complex();
		
		if (c.mag() > 2) {
			return 1;
		}
		
		for (int i = 1; i <= max; i++) {
			z = z.multiply(z);
			z = z.add(c);
			if (z.mag() > 2) {
				return i;
			}
		}
		return max;
	}
}
