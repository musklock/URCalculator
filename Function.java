/**
 * Muskaan Mendiratta
 * Class ID 94
 * I did not collaborate with anyone on this assignment
 */

import java.util.HashMap;
import java.util.InputMismatchException;

public class Function {
	HashMap <String, String> functionTable;
	
	public Function() {
		functionTable=new HashMap<>();		
	}
	/**
	 * checks if operator is valid
	 */
	public boolean isOperator(String s) {
		switch(s) {
		case "+":
			return true;
		case "-":
			return true;
		case "*":
			return true;
		case "/":
			return true;
		case "^":	
			return true;
		default:
			return false;
		}
	}
	public double useOperator(String s, double x, double y) {
		double result = 0;
		switch(s) {
		case "+":
			result=add(x, y);
			break;
			
		case "-":
			result=subtract(x, y);
			break;
			
		case "*":
			result=multiply(x, y);
			break;
			
		case "/":
			result=divide (x, y);
			break;
		case "^":
			result=Math.pow(x, y);
			break;
		default:
			throw new InputMismatchException();
		}
		return result;
	}
	public double add(double x,double y) {
		return x+y;
	}
	public double subtract(double x, double y) {
		return x-y;
	}
	public double multiply (double x, double y) {
		return x*y;
	}
	public double divide(double x, double y) {
		if (Double.isInfinite(x/y)) {
			System.out.println("Division by zero error");
			return 0;
		}else {
			return x/y;
		}
	}

	

	public int getPrecendence(String s) {
		switch(s) {
		case "+":
			return 2;
		case "-":
			return 2;
		case "*":
			return 3;
		case "/":
			return 3;
		case "^":
			return 4;
		default:
			return 0;
		}

	}
	
	public int checkPrecendence(String operator, String operator2) {
		return getPrecendence(operator) - getPrecendence(operator2);
	}

}
