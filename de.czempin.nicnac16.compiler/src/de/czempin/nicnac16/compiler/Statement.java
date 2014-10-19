package de.czempin.nicnac16.compiler;

public class Statement {
	private int counter = 0;

	@Override
	public String toString() {
		String retVal = "    ";
		switch (counter) {
		case 0:
			retVal += "int A;"; //declaration
			break;
		case 1:
			retVal += "int _b;"; //declaration
			break;
		case 2:
			retVal += "int c7;"; //declaration
			break;
		case 3:
			retVal += "int d;"; //declaration
			break;
		case 4:
			retVal += "A = 7;"; //assignment
			break;
		case 5:
			retVal += "_b = 25;"; //assignment
			break;
		case 6:
			retVal += "c7 = A + _b;"; //assignment (expression on rvalue)
			break;
		case 7:
			retVal += "return c7;"; //return_statement
			break;
		}
		retVal += "\n";
		++counter;
		return retVal;
	}

}
