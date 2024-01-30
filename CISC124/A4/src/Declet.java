
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.lang.Math;

/**
 * A class that represents a signed 10-bit binary number represented using a
 * twos complement representation.
 *
 */
public class Declet implements Comparable<Declet> {
	
	static final int MAX_VALUE = 511;
	static final int MIN_VALUE = -512;
	static final int NUM_BITS = 10;
	
	private List<Bit> bitList = new ArrayList<>();
	
	public Declet() {
		for (int i = 0; i < Declet.NUM_BITS; i++) {
			this.bitList.add(Bit.ZERO);
		}
	}
	
	public Declet(Bit... theBits) {
		if (theBits.length != Declet.NUM_BITS) {
			throw new IllegalArgumentException();
		}
		
		for (Bit bit : theBits) {
			this.bitList.add(bit);
		}
	}
	
	public Declet(int decimal) {
		this();
		if (decimal < Declet.MIN_VALUE || decimal > Declet.MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		
		int decimalCopy = decimal;
		if (decimalCopy < 0) {
			decimalCopy = -decimalCopy;
		}
		
		int index = Declet.NUM_BITS - 1;
		while (decimalCopy != 0) {
			int bit = decimalCopy % 2;
			decimalCopy = decimalCopy / 2;
			if (bit == 0) {
				this.bitList.set(index, Bit.ZERO);
			}
			else {
				this.bitList.set(index, Bit.ONE);
			}
			index--;
		}
		
		if (decimal < 0) {
			this.not();
			this.addOne();
		}
	}
	
	public boolean isNegative() {
		return (this.toDecimal() < 0);
	}
	
	public void not() {
		for (int i = 0; i < Declet.NUM_BITS; i++) {
			Bit bit = this.bitList.get(i);
			this.bitList.set(i, bit.not());
		}
	}
	
	public void addOne() {
		for (int i = Declet.NUM_BITS - 1; i >= 0; i--) {
			Bit bit = this.bitList.get(i);
			this.bitList.set(i, bit.not());
			if (bit == Bit.ZERO) {
				break;
			}
		}
	}
	
	public void add(Declet other) {
		Bit carry = Bit.ZERO;
		for (int i = Declet.NUM_BITS - 1; i >= 0; i--) {
			if (other.bitList.get(i) == Bit.ONE && carry == Bit.ONE) {
				if (this.bitList.get(i) == Bit.ONE) {
					this.bitList.set(i, Bit.ONE);
				}
				else {
					this.bitList.set(i, Bit.ZERO);
				}
				carry = Bit.ONE;
			}
			else if (other.bitList.get(i) == Bit.ONE || carry == Bit.ONE) {
				if (this.bitList.get(i) == Bit.ONE) {
					this.bitList.set(i, Bit.ZERO);
					carry = Bit.ONE;
				}
				else {
					this.bitList.set(i, Bit.ONE);
					carry = Bit.ZERO;
				}
			}
			else {
				if (this.bitList.get(i) == Bit.ONE) {
					this.bitList.set(i, Bit.ONE);
				}
				else {
					this.bitList.set(i, Bit.ZERO);
				}
				carry = Bit.ZERO;
			}
		}
	}
	
	public List<Bit> getBits() {
		List<Bit> bitListCopy = new ArrayList<>(this.bitList);
		return bitListCopy;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Declet other = (Declet) obj;
		if (!this.bitList.equals(other.bitList)) {
			return false;
		}
		return true;
	}
	
	public int hashCode() {
		return Objects.hash(this.bitList);
	}
	
	public int compareTo(Declet other) {
		int thisDecimal = this.toDecimal();
		int otherDecimal = other.toDecimal();
		if (thisDecimal < otherDecimal) {
			return -1;
		}
		else if (thisDecimal > otherDecimal) {
			return 1;
		}
		return 0;
	}
	
	public String toString() {
		String bitString = "";
		for (Bit bit : this.bitList) {
			bitString += bit.toString();
		}
		return bitString;
	}
	
	public int toDecimal() {
		int bitInt = 0;
		for (int i = 0; i < Declet.NUM_BITS; i++) {
			if (this.bitList.get(i) == Bit.ONE) {
				if (i == 0) {
					bitInt -= Math.pow(2, Declet.NUM_BITS - 1);
				}
				else {
					bitInt += Math.pow(2, Declet.NUM_BITS - 1 - i);
				}
			}			
		}
		return bitInt;
	}
	
	/**
	 * Prints some sums illustrating overflow at {@code Decle.MAX_VALUE} and
	 * {@code Decle.MIN_VALUE}.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Declet d = new Declet(Declet.MAX_VALUE - 2);
		Declet one = new Declet(1);
		
		System.out.println("Overflow at Declet.MAX_VALUE");
		for (int i = 0; i <= 4; i++) {
			System.out.println("d       " + d + " = " + d.toDecimal());
			System.out.println("      + " + one);
			d.addOne();
			System.out.println("d + 1 = " + d + " = " + d.toDecimal());
			System.out.println();
		}
		
		Declet negOne = new Declet(-1);
		
		System.out.println("Overflow at Declet.MIN_VALUE");
		for (int i = 0; i <= 4; i++) {
			System.out.println("d     = " + d + " = " + d.toDecimal());
			System.out.println("      + " + negOne);
			d.add(negOne);
			System.out.println("d - 1 = " + d + " = " + d.toDecimal());
			System.out.println();
		}
	}

}
