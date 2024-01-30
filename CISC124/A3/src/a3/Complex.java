package a3;

import java.lang.Math;

/**
 * A class that represents complex numbers. In the complex number
 * (a + bi) the real value a is called the real part of the complex
 * number and the real value b is called the imaginary part of the
 * complex number. This class provides methods to perform addition
 * and multiplication of complex numbers.
 */
public final class Complex {
	private double re;
	private double im;
	
	/**
	 * Initializes this complex number to (0 + 0i).
	 */
	public Complex() {
		this(0.0, 0.0);
	}
	
	/**
	 * Initializes this complex number so that it has the given
	 * real and imaginary components.
	 * 
	 * @param re the real component of this complex number
	 * @param im the imaginary component of this complex number
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * Initializes this complex number so that it has the same
	 * real and imaginary parts as another complex number.
	 * 
	 * @param other the complex number to copy
	 */
	public Complex(Complex other) {
		this(other.re, other.im);
	}
	
	public static Complex real(double re) {
		return new Complex(re, 0.0);
	}
	
	public static Complex imag(double im) {
		return new Complex(0.0, im);
	}
	
	public double re() {
		return this.re;
	}
	
	public double im() {
		return this.im;
	}
	
	public void re(double val) {
		this.re = val;
	}
	
	public void im(double val) {
		this.im = val;
	}
	
	public Complex add(Complex c) {
		double re = this.re + c.re;
		double im = this.im + c.im;
		return new Complex(re, im);
	}
	
	/**
	 * Multiplies this complex number with another complex number
	 * to obtain a new complex number. Neither this complex number
	 * nor c is changed by this method.
	 * 
	 * <p>
	 * This is not an industrial strength implementation of complex
	 * number multiplication. In particular, issues related to the
	 * differences between -0.0 and 0.0 and infinite real or imaginary
	 * parts are not taken into account.
	 * 
	 * @param c the complex number to multiply by
	 * @return a new complex number equal to this complex number
	 * multiplied by c
	 */
	public Complex multiply(Complex c) {
		double re = this.re * c.re - this.im * c.im;
		double im = this.im * c.re + this.re * c.im;
		return new Complex(re, im);
	}
	
	public double mag() {
		return Math.hypot(this.re, this.im);
	}
	
	public String toString() {
		String sign = "";
		if (this.im < 0) {
			sign += "-";
		}
		else {
			sign += "+";
		}
		return this.re + " " + sign + " " + Math.abs(this.im) + "i";
	}
}
