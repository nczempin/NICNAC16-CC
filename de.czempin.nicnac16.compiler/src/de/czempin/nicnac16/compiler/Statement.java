package de.czempin.nicnac16.compiler;

public class Statement {
	private int counter =0;

	@Override
	public String toString() {
		String retVal = "";
		switch (counter) {
		case 0:
			retVal += "    int A;\n";
			break;
		case 1:
			retVal += "    int _b;\n";
			break;
		case 3:
			retVal += "    int c7;\n";
			break;
		case 4:
			retVal += "    int d;\n";
			break;
		case 5:
			retVal += "    A=7;_b=25;\n";
			break;
		case 6:
			retVal += "    c7 = A + _b;\n";
			break;
		case 7:
			retVal += "    return c7;\n";
			break;
		}
		
		++counter;
		return retVal;
	}

}
