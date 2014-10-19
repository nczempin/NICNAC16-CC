package de.czempin.nicnac16.compiler;

public class Declaration extends Statement {
	private int counter = 0;

	@Override
	public String toString() {
		String retVal = "    ";
		switch (counter) {
		case 0:
			retVal += "int A;"; // declaration
			break;
		case 1:
			retVal += "int _b;"; // declaration
			break;
		case 2:
			retVal += "int c7;"; // declaration
			break;
		case 3:
			retVal += "int d;"; // declaration
			break;
		}
		retVal += "\n";
		++counter;
		return retVal;
	}

}
