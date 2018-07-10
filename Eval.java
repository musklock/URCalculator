/**
 * Muskaan Mendiratta
 * Class ID 94
 * I did not collaborate with anyone on this assignment
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Eval {
	Function function;
	Symbol symbol;
	Input input;
	boolean zeroNeeded=false;
	String variable;
	ArrayList<String> listOfVariables;
	boolean undeclaredVariable=false;
	boolean assignmentMode=false;
	boolean hasVariables=false;
	boolean evaluated=false;
	String undeclared;
	public Eval() {
		function =new Function();
		symbol=new Symbol();
		input=new Input();
		listOfVariables=new ArrayList<>();
	}
	public void showAll() {
		symbol.showAll();
	}
	public void clearAll() {
		symbol.clearAll();
	}


	public void clearVar(String s) {
		String[] array=s.split(" ");

		symbol.clearVar(array[1]);


	}
	/**
	 * returns a list that is ready to be converted into post-fix notation
	 */
	public String[] makeReady(String input) {
		String raw = input.replace(" ", "");	//removing white spaces
		String[] list = raw.split("");		//splitting string into an array
		String m="";
		for (String c:list) {
			String n="";
			char a=c.charAt(0);
			if (Character.isDigit(a)||c.equals(".")) {
				n=n+a;
			}else {
				n=n+" " +a+" ";
			}
			m=m+n;
		}
		m=m.replace("  ", " ");
		if (m.split(" ")[0].equals("")) {
			m=m.substring(1);
		}
		list=m.split(" ");
		String temp="";
		String remove="";
		for (int i=0; i<list.length;i++) {
			if (list[i].equals("*") || list[i].equals("/") || list[i].equals("^") || 
					list[i].equals("(") 
					|| list[i].equals("{") || list[i].equals(("["))){
				if (list[i+1].equals("-")) {

					if (Character.isDigit(list[i+2].charAt(0))) {

						remove=list[i+2];
						temp=list[i+1]+list[i+2];
						list[i+1]=temp;
						list[i+2]="";
					}
				}


			}
		}

		String newS="";

		for (String m1:list) {
			if (!m1.equals("")) {
				newS+=m1+" ";
			}
		}

		list=newS.split(" ");
		for (String r:list) {
			if ((!function.isOperator(r) )|| (!Character.isDigit(r.charAt(0)))) {
			}
		}
		return list;

	}

	//only for assignment
	public double miniEval(String input) {
		if (checkForVariables(input)) {
			input=workWithVariables(input);	
			hasVariables=false;
		}
		String string=this.input.handleLotsOfOperators(input);	


		String[] tokens = makeReady(string);		//splitting string into an array

		String[] polish = makePolish(tokens);	//making reverse polish notification


		return evaluate(polish);				//returns evaluated notification

	}

	public double eval(String input) {
		evaluated=false;
		undeclaredVariable=false;
		boolean readyToEvaluate=true;


		if (readyToEvaluate) {
			checkForAssignment(input);
			//if expression is taht of assignment, will not evaluate
			if (assignmentMode) {
				workWithAssignment(input);
				assignmentMode=false;
				evaluated=false;
				return 0;
			}		
			// if contains variables
			if (checkForVariables(input)) {
				input=workWithVariables(input);	
				//hasVariables=false;
				evaluated=true;
			}
			String string=this.input.handleLotsOfOperators(input);	

			//checking for undeclared variables
			String[] tokens = makeReady(string);		//splitting string into an array
			for (String i:tokens) {
				if (!Character.isDigit(i.charAt(0))){
					if (!symbol.isSymbol(i)) {
						if (!function.isOperator(i)) {
							if(!i.equals("(")) {
								if(!i.equals("{")) {
									if(!i.equals("[")) {
										if(!i.equals(")")) {
											if (!i.equals("}")) {
												if(!i.equals("]")) {
													undeclared=i;
													undeclaredVariable=true;
												}
											}
										}
									}
								}
							}
						}
					}
				}

			}
			//converts to Reverse Polish Notation
			String[] polish = makePolish(tokens);	//making reverse polish notification

			evaluated=true;
			return evaluate(polish);				//returns evaluated notification

		}else {
			evaluated=false;
			return 0;
		}
	}
	
	
	public boolean checkForAssignment(String s) {
		if (s.contains("=")) {
			assignmentMode=true;
			return assignmentMode;
		}else {
			assignmentMode=false;
			return assignmentMode;
		}
	}

	public boolean checkForVariables(String s) {
		hasVariables=false;
		for (String key:symbol.symbolTable.keySet()) {
			if (s.contains(key)){
				listOfVariables.add(key);
				hasVariables=true;
			}
		}

		return hasVariables;
	}

	public String workWithVariables(String input) {

		String raw = input.replace(" ", "");	//removing white spaces
		String[] list = raw.split("");		//splitting string into an array
		String m="";
		for (String c:list) {
			String n="";
			char a=c.charAt(0);
			if (Character.isDigit(a)||c.equals(".")) {
				n=n+a;
			}else if (function.isOperator(c)){
				n=n+" " +a+" ";
			}
			else if (c.equals("(") || c.equals("{") || 
					c.equals("[") || c.equals(")") || c.equals("}") || c.equals("]")){
				n=n+" " +a+" ";
			}else {
				n=n+a;
			}
			m=m+n;
		}
		m=m.replace("  ", " ");
		if (m.split(" ")[0].equals("")) {
			m=m.substring(1);
		}
		list=m.split(" ");
		for (int i=0; i<list.length;i++) {
			for (String variable:listOfVariables) {
				if (list[i].equals(variable)) {
					list[i]=Double.toString(symbol.symbolTable.get(variable));
				}
			}
		}
		String q="";
		for (String f:list) {
			q+=f;
		}
		String p=this.input.handleLotsOfOperators(q);
		return p;


	}

	public void workWithAssignment(String s) {
		StringTokenizer st=new StringTokenizer(s,"=");
		ArrayList<String> a=new ArrayList<>();
		while(st.hasMoreTokens()) {

			a.add(st.nextToken().trim());
		}
		a.remove(" ");
		String b=a.get(a.size()-1);
		if(checkForVariables(b)) {
			b=workWithVariables(b);
		}
		for (int i=0; i<a.size()-1;i++) {
			if (symbol.symbolTable.containsKey(a.get(i))) {
				symbol.symbolTable.replace(a.get(i), symbol.symbolTable.get(a.get(i)),
						miniEval(b));
			}
			symbol.addSymbol((a.get(i)), miniEval(b));
		}
	}

	public double evaluate(String[] polish) {
		Stack<String> stack = new Stack<String>();
		if(zeroNeeded) {
			stack.push("0");
			stack.push("0");
			stack.push("0");
		}

		for(String element : polish) //Going through everything

			if(!function.isOperator(element)) {
				stack.push(element); 

			}
			else if(element.equals(" ")){
			}
			else {


				double x = Double.parseDouble((String)stack.pop());
				double y = Double.parseDouble((String)stack.pop());
				stack.push(String.valueOf(function.useOperator(element, y, x)));
			}

		return Double.parseDouble((String)stack.pop());
	}

	public String[] makePolish(String[] input) {
		if (input[0].equals("+") || input[0].equals("-")) {
			zeroNeeded=true;
		}
		ArrayList<String> polish = new ArrayList<String>(); 
		Stack<String> inqueue = new Stack<String>(); 

		for(String token : input) {
			if (token.equals(" ")) {

			}
			if(function.isOperator(token)) { 
				while(!inqueue.isEmpty() && function.isOperator(inqueue.peek())) {  
					//if precedence is equal or lesser
					if(function.checkPrecendence(token, (String)inqueue.peek()) <= 0)
						polish.add((String)inqueue.pop());      
					break;
				}
				inqueue.push(token);

			
			} else if (token.equals("(") || token.equals("{") || token.equals("[")) {
				switch(token) {
				case "(":
					inqueue.push(token);
					break;
				case "{":
					inqueue.push(token);
					break;
				case "[":
					inqueue.push(token);
					break;
				}

			}else if (token.equals(")") || token.equals("}") || token.equals("]")) {
				switch(token) {
				case ")":
					while(!inqueue.isEmpty() && !inqueue.peek().equals("("))
						polish.add((String)inqueue.pop());
					inqueue.pop();
					break;
				case "}":
					while(!inqueue.isEmpty() && !inqueue.peek().equals("{"))
						polish.add((String)inqueue.pop());
					inqueue.pop();
					break;
				case "]":
					while(!inqueue.isEmpty() && !inqueue.peek().equals("["))
						polish.add((String)inqueue.pop());
					inqueue.pop();
					break;
				}



			}else 
				polish.add(token);
		}

		while(!inqueue.isEmpty())
			polish.add((String)inqueue.pop());
		String[] output = new String[polish.size()];


		return polish.toArray(output);
	}



}
