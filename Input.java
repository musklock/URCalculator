/**
 * Muskaan Mendiratta
 * Class ID 94
 * I did not collaborate with anyone on this assignment
 */
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Input {
	Symbol symbol;
	
	public Input() {
		symbol=new Symbol();
	}

	public String handleLotsOfOperators(String s) {
		String[] array=s.split("");	//compensate for digits

		int i=0;
		int k=1;
		while (i<array.length) {
			//System.out.print(array[i] + "    ");
			if (array[i].equals("+") || array[i].equals("-")){
				if (array[i+k].equals("+")||array[i+k].equals("-")) {
					//+ + = +
					if (array[i].equals("+") && array[i+k].equals("+")){
						array[i]="+";
						array[i+k]=" ";
					}
					//+ - = -
					else if (array[i].equals("+") && array[i+k].equals("-")) {
						array[i]="-";
						array[i+k]=" ";
					}
					//- + = -
					else if (array[i].equals("-") && array[i+k].equals("+")) {
						array[i]="-";
						array[i+k]=" ";
					}
					//- - = +
					else if (array[i].equals("-") && array[i+k].equals("-")) {
						array[i]="+";
						array[i+k]=" ";
					}
					k++;
				}else {
					i++;
					k=1;
				}
			}else {
				i++;
			}
		}
		
		String m="";
		for (String str:array) {
			if (str.equals(" ")) {
			}else {
			m+=str;
			}

		}
		return m;
	}


}
