*UPDATED*
Name: Muskaan Mendiratta
NetID: mmendira
Class ID: 94
Assignment Number: Project 02
Lab section:MW 18.15-19.30

Classes:

1. Input.java
2. Function.java
3. Symbol.java
4. Eval.java
5. URCalculator.java	(contains main method)

Project description
This project is a simulation of a calculator that handles basic operations and assignments. The initial output is 'URCalculator has been initiated' and will keep prompting the user to enter expressions. The user can enter infix expressions or assign values to variables. All numbers and variables are handled as doubles.    

Keywords
1. exit		: will stop the program
2. show all	: will output the symbol table of variables and their corresponding values
3. clear all	: will clear the symbol table of all variables 

Operators
1. +	: addition
2. -	: subtraction
3. *	: multiplication
4. /	: division
5. ^	: exponent	(for extra credit)	

Errors in expressions

All errors will be printed out not thrown so as to not disturb the flow of input from the user. Errors are as follow: 

1. Parenthesis mismatch
2. Undeclared Variables
3. Invalid expressions


Classes

1. Input.java

Functions

Function name								Function return type			Execution

handleLotsOfOperators(String s)						String					Handles all cases of repetetive + - operators. 


2. Function.java

Functions

Function name								Function return type			Execution

isOperator(String s)							boolean					Checks if operator is +, -. *, /, ^

useOperator(String s, double x, double y)				double					Calls the appropriate function (listed below) to do the operation

add(double x, double y)							double					adds x and y

subtract(double x, double y)						double					subtracts y from x

multiply(double x, double y)						double					multiplies x and y

divide(double x, double y)						double					divides x by y, prompts the user of division by zero error is detected.

getPrecedence(String s)							int					returns precedence of operator

checkPrecedence(String operator, String operator2)			int					returns the difference between the two precedences


3. Symbol.java

Functions
		
Function name								Function return type			Execution

isSymbol(String s)							boolean					checks if the hash map contains the given symbol

addSymbol(String symbol, double value)					void					adds the variable and its value to the hash map

clearAll()								void					clears all symbols and values from the hash map

clearVar(String key)							void					removes given key from the hash map

showAll()								void					prints out the symbol table		
	


4. Eval.java

Functions

Function name								Function return type			Execution

showAll()								void					calls showAll() from Symbol

clearAll()								void					calls clearAll() from Symbol

clearVar(String key)							void					identifies the variable to be cleared and calls clearVar(String key) from Symbol

makeReady(String input)							String[]				returns a list with all elements in the expression by cleanly splitting and compensating for double digits and decimal points

miniEval(String input)							double					evaluates the expression to which a variable is being assigned (used only in cases of assignment)

eval(String input)							double					evaluates the expression to give the final output to the user

checkForAssignment(String s)						boolean					checks if user wants to declare a variable

checkForVariables(String s)						boolean					checks if expression contains a declared variable

workWithVariables(String input)						String					replaces the variables in the string with their double values

workWithAssignment(String s)						void					looks for variable declarations and adds them to the table

evaluate(String [] polish)						double					solves the RPN expression

makePolish(String [] input)						String[]				converts infix to postfix using the Shunting-Yard Algorithm

			

5. URCalculator.java

Runs a while loop, executes the program, and calls a bunch of exceptions. 


Concepts Used:
1. HashMap
2. Stack
3. Reverse Polish Notation
4. Shunting-Yard Algorithm


Sources referred to:
https://github.com/stheakanath/Data-Structures-Honors/blob/master/Topic%203%20Stacks%20%26%20Queues/CalculatorTester_Theakanath.java 

Limitations:
1. My program prints 0.0 after each assignment. It doesn't affect the back-end in any way, but it is just an extra output statement. Please ignore it.  
2. My code doesn't handle cases like 3 --4 5. Because of the way I parse the expressions that the user inputs, it doesn't compensate for such operations. Instead of throwing/catching an exception, it will print out 48.0. 
3. For undeclared variable cases, my program will only prompt the user 'undeclared variable' and not the actual undeclared variable. 


To get to the src files: Proj2(folder)-->Proj2(project)-->src

This project was made and designed by Muskaan Mendiratta.

