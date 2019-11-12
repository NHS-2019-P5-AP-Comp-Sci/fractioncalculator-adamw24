/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

import org.checkerframework.checker.units.qual.s;

public class FracCalc {
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		// TODO: Read the input from the user and call produceAnswer with an equation
		String input = userInput.nextLine();
		while (input.indexOf("quit") == -1) {
			System.out.println(produceAnswer(input));
			input = userInput.nextLine();
		}
		userInput.close();
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"

	/*
	 * public static String produceAnswer(String input) { int totalNumer = 0; int
	 * totalDenom = 1; int totalWhole = 0; if (input.charAt(0) == ' ') { input =
	 * input.substring(1); } int firstSpace = input.indexOf(" "); String
	 * firstOperand = input.substring(0, firstSpace); int wholeF =
	 * whole(firstOperand); int numerF = numerator(firstOperand); int denomF =
	 * denominator(firstOperand); char operator = input.charAt(firstSpace + 1);
	 * String secondOperand = input.substring(firstSpace + 3); int wholeS =
	 * whole(secondOperand); int numerS = numerator(secondOperand); int denomS =
	 * denominator(secondOperand); if (operator == '+') { totalWhole = wholeF +
	 * wholeS; totalNumer = numerF * denomS + numerS * denomF; } else if (operator
	 * == '-') { totalWhole = wholeF - wholeS; totalNumer = numerF * denomS - numerS
	 * * denomF; } else if (operator == '*') { totalNumer = (wholeF*denomF + numerF)
	 * (wholeS*denomS + numerS); totalDenom = denomF * denomS; total = "" +
	 * totalNumer + "/" + totalDenom; } totalDenom = denomF * denomS; String total =
	 * "" + totalWhole + "_" + totalNumer + "/" + totalDenom; return total; }
	 */

	public static String produceAnswer(String input) {
		if (input.charAt(0) == ' ') {
			input = input.substring(1);
		}
		int firstSpace = input.indexOf(" ");
		String firstOperand = input.substring(0, firstSpace);
		firstOperand = "whole:" + whole(firstOperand) + "numerator:" + numerator(firstOperand) + "denominator:"
				+ denominator(firstOperand);
		String operator = input.substring(firstSpace + 1, firstSpace + 2);
		String secondOperand = input.substring(firstSpace + 3);
		secondOperand = "whole:" + whole(secondOperand) + " numerator:" + numerator(secondOperand) + " denominator:"
				+ denominator(secondOperand);
		return secondOperand;
	}

	public static int whole(String operand) {
		int whole = 0;
		if (operand.indexOf("_") != -1) {
			whole = Integer.parseInt(operand.substring(0, operand.indexOf("_")));
		} else if (operand.indexOf("/") == -1) {
			whole = Integer.parseInt(operand);
		}
		return whole;
	}

	public static int numerator(String operand) {
		int numer = 0;
		if (operand.indexOf("/") != -1) {
			numer = Integer.parseInt(operand.substring(operand.indexOf("_") + 1, operand.indexOf("/")));
		}
		return numer;
	}

	public static int denominator(String operand) {
		int denom = 1;
		if (operand.indexOf("/") != -1) {
			denom = Integer.parseInt(operand.substring(operand.indexOf("/") + 1));
		}
		return denom;
	}

}
