package de.czempin.nicnac16.compiler;

public class Statement {
	private int counter = 0;

	@Override
	public String toString() {
		String retVal = "    ";
		switch (counter) {
		case 0:
			retVal += "int A;";
			break;
		case 1:
			retVal += "int _b;";
			break;
		case 2:
			retVal += "int c7;";
			break;
		case 3:
			retVal += "int d;";
			break;
		case 4:
			retVal += "A=7;";
			break;
		case 5:
			retVal += "_b=25;";
			break;
		case 6:
			retVal += "c7 = A + _b;";
			break;
		case 7:
			retVal += "return c7;";
			break;
		}
		retVal += "\n";
		++counter;
		return retVal;
	}

}
