/**
 * Muskaan Mendiratta
 * Class ID 94
 * I did not collaborate with anyone on this assignment
 */

import java.util.EmptyStackException;
import java.util.Scanner;

public class URCalculator {
	public static void main(String[] args) {
		Symbol symbol=new Symbol();
		Input input=new Input();
		Eval eval=new Eval();
		Scanner scan = new Scanner(System.in);
		boolean isRunning = true;
		System.out.println("URCalculator has been initiated");
		while (isRunning) {
			boolean needsToBeEvaluated=true;

			System.out.println("Enter an expression");
			String expression=scan.nextLine();

			if (expression.contains("exit") || expression.contains("show") || 
					expression.contains("clear")) {
				needsToBeEvaluated=false;

				if (expression.contains("show")) {
					eval.showAll();
				}else if (expression.equals("exit")) {
					System.out.println("let's get you out");
					break;	
				}
				else if(expression.contains("clear")){
					if (expression.contains("all")) {
						eval.clearAll();
					}else {
						eval.clearVar(expression);;
					}
				}
			}

			if (needsToBeEvaluated){
				try {

					System.out.println(eval.eval(expression));



				}catch (IndexOutOfBoundsException e) {
					System.out.println("invalid expression");
				}catch (NumberFormatException e) {
					if (eval.undeclaredVariable) {
						System.out.println("undeclared variable");

					}else {
						System.out.println("parenthesis mismatch");
					}
				}catch (EmptyStackException e) {
					System.out.println("(parenthesis mismatch)");
				}

			}
		}

		scan.close();


		System.out.println("URCalculator has stopped running");


	}
}

