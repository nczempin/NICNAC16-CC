package de.czempin.nicnac16.compiler;

public class Assignment extends Statement {
	private int counter = 0;
	@Override
	public String toString() {
		String retVal = "    ";
		switch (counter) {
		case 0:
			retVal += "A = 7;"; //assignment
			break;
		case 1:
			retVal += "_b = 25;"; //assignment
			break;
		case 2:
			retVal += "c7 = A + _b;"; //assignment (expression on rvalue)
			break;
		case 3:
			retVal += "return c7;"; //return_statement
			break;
		}
		retVal += "\n";
		++counter;
		return retVal;
	}

}
