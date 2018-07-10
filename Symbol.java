/**
 * Muskaan Mendiratta
 * Class ID 94
 * I did not collaborate with anyone on this assignment
 */
import java.util.HashMap;
/**
 * contains addSymbol, clearAll, clearVar and showAll functions
 *
 */
public class Symbol {
	HashMap <String, Double> symbolTable;
	Function function;
	
	public Symbol() {
		symbolTable=new HashMap<>();	
		function=new Function();
	}
	
	public boolean isSymbol(String s) {
		if(symbolTable.keySet().contains(s)) {
			return true;
		}
		return false;
	}
	
	public void addSymbol(String symbol, double value) {
		symbolTable.put(symbol, value);
	}
	
	public void clearAll() {
		symbolTable.clear();
	}
	
	public void clearVar(String key) {
		symbolTable.remove(key);
	}
		
	public void showAll() {
		System.out.println("SYMBOL TABLE");
		if (symbolTable.isEmpty()) {
			System.out.println("NO CURRENT ENTRIES TO DISPLAY");
		}
		for (String key: symbolTable.keySet()) {
			System.out.print(key + " = " + symbolTable.get(key) + "\n");
		}
	}



}
