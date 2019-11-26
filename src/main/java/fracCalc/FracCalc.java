/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

import org.checkerframework.checker.units.qual.s;

public class FracCalc {
	public static void main(String[] args) {
		System.out.println("Welcome to Fraction Calculator.");
		System.out.println("I can calculate +, -, *, or / .");
		System.out.println("Type \"quit\" to quit the program.");
		System.out.print("Enter here: ");
		Scanner userInput = new Scanner(System.in);
		String input = userInput.nextLine();
		while (input.indexOf("quit") == -1) {
			System.out.println(produceAnswer(input));
			System.out.print("Enter here: ");
			input = userInput.nextLine();
		}
		System.out.println("Program quit.");
		userInput.close();
	}

	public static String produceAnswer(String input) {
		int totalNumer = 0;
		int totalDenom = 1;
		String total = "";

		if (input.charAt(0) == ' ') {
			input = input.substring(1);
		}
		int firstSpace = input.indexOf(" ");
		String firstOperand = input.substring(0, firstSpace);
		int wholeF = whole(firstOperand);
		int denomF = denominator(firstOperand);
		int numerF = wholeF * denomF + numerator(firstOperand);
		String operator = input.substring(firstSpace + 1, firstSpace + 2);
		String secondOperand = input.substring(firstSpace + 3);
		int wholeS = whole(secondOperand);
		int denomS = denominator(secondOperand);
		int numerS = wholeS * denomS + numerator(secondOperand);
		// the following if and else if statements perform the calculations.
		if (operator.equals("+")) {
			totalNumer = numerF * denomS + numerS * denomF;
			totalDenom = denomF * denomS;
		} else if (operator.equals("-")) {
			totalNumer = numerF * denomS - numerS * denomF;
			totalDenom = denomF * denomS;
		} else if (operator.equals("*")) {
			totalNumer = numerF * numerS;
			totalDenom = denomF * denomS;
		} else {
			totalDenom = denomF * numerS;
			if (totalDenom < 0) {
				// This if statement always makes the denominator positive for easier
				// calculations
				totalNumer = -1 * numerF * denomS;
				totalDenom = Math.abs(totalDenom);
			} else {
				totalNumer = numerF * denomS;
			}
		}
		total = simplify(totalNumer, totalDenom);
		return total;
	}

	public static String simplify(int totalNumer, int totalDenom) {
		String simplified = "";
		int totalWhole = 0;
		boolean isPositive = totalNumer >= 0;
		totalNumer = Math.abs(totalNumer);
		int gcd = gcdTest(totalNumer, totalDenom);
		totalNumer /= gcd;
		totalDenom /= gcd;
		if (totalNumer >= totalDenom) {
			totalWhole += totalNumer / totalDenom;
			totalNumer = totalNumer % totalDenom;
		}
		if (!isPositive) {
			simplified = "-";
		}

		if (totalDenom == 1 || totalNumer == 0) {
			totalWhole += totalNumer;
			simplified += totalWhole;
		} else {
			if (totalWhole != 0) {
				simplified = simplified + totalWhole + "_";
			}
			simplified = simplified + totalNumer + "/" + totalDenom;
		}
		return simplified;
	}

	// The following method returns the GCD of the numerator and denominator
	public static int gcdTest(int totalNumer, int totalDenom) {
		int gcd = 1;
		int smaller = totalNumer;
		if (totalNumer > totalDenom) {
			smaller = totalDenom;
		}
		if (totalNumer == totalDenom) {
			gcd = totalNumer;
		} else {
			for (int g = smaller; g >= 2; g--) {
				if (totalNumer % g == 0 && totalDenom % g == 0) {
					return g;
				}
			}
		}
		return gcd;
	}

	// The following method parses the whole from the user input
	public static int whole(String operand) {
		int whole = 0;
		if (operand.indexOf("_") != -1) {
			whole = Integer.parseInt(operand.substring(0, operand.indexOf("_")));
		} else if (operand.indexOf("/") == -1) {
			whole = Integer.parseInt(operand);
		}
		return whole;
	}

	// The following method parses the numerator from the user input
	public static int numerator(String operand) {
		int numer = 0;
		if (operand.indexOf("/") != -1) {
			numer = Integer.parseInt(operand.substring(operand.indexOf("_") + 1, operand.indexOf("/")));
		}
		if (operand.indexOf("-") == 0 && numer > 0) {
			numer = -1 * numer;
		}
		return numer;
	}

	// The following method parses the denominator from the user input
	public static int denominator(String operand) {
		int denom = 1;
		if (operand.indexOf("/") != -1) {
			denom = Integer.parseInt(operand.substring(operand.indexOf("/") + 1));
		}
		return denom;
	}

}
