/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

import org.checkerframework.checker.units.qual.s;

public class FracCalc {
	public static void main(String[] args) {
		System.out.println("Welcome to Fraction Calculator.");
		System.out.println("Enter two fractions to calculate either +, -, *, or / .");
		System.out.println("Remember the proper formating.");
		System.out.print("Enter here: ");
		Scanner userInput = new Scanner(System.in);
		// TODO: Read the input from the user and call produceAnswer with an equation
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
		int totalWhole = 0;
		String total = "";
		int numerF = 0;
		int numerS = 0;
		if (input.charAt(0) == ' ') {
			input = input.substring(1);
		}
		int firstSpace = input.indexOf(" ");
		String firstOperand = input.substring(0, firstSpace);
		int wholeF = whole(firstOperand);
		if (wholeF < 0) {
			numerF = -1 * numerator(firstOperand);
		}else {
			numerF = numerator(firstOperand);
		}
		int denomF = denominator(firstOperand);
		String operator = input.substring(firstSpace + 1, firstSpace + 2);
		String secondOperand = input.substring(firstSpace + 3);
		int wholeS = whole(secondOperand);
		if (wholeS < 0) {
			numerS = -1 * numerator(secondOperand);
		}else {
			numerS = numerator(secondOperand);
		}
		int denomS = denominator(secondOperand);
		//the following if and else if statements perform the calculations.
		if (operator.equals("+")) {
			totalWhole = wholeF + wholeS;
			totalNumer = numerF * denomS + numerS * denomF;
			totalDenom = denomF * denomS;
		} else if (operator.equals("-")) {
			totalWhole = wholeF - wholeS;
			totalNumer = numerF * denomS - numerS * denomF;
			totalDenom = denomF * denomS;
		} else if (operator.equals("*")) {
			totalNumer = (wholeF * denomF + numerF) * (wholeS * denomS + numerS);
			totalDenom = denomF * denomS;
		} else {
			totalNumer = (wholeF * denomF + numerF) * denomS;
			totalDenom = denomF * (wholeS * denomS + numerS);
		}
		// The following if and else if statements combine the whole, numerator, and denominator to print out.
		if (totalWhole == 0 ) {
			total = "" + totalNumer + "/" + totalDenom;
		}else if (totalWhole < 0){
			total = "" + totalWhole + "_" + (-1* totalNumer) + "/" + totalDenom;
		}else{
			total = "" + totalWhole + "_" + totalNumer + "/" + totalDenom;
		}
		return total;
		// return secondOperand;
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
