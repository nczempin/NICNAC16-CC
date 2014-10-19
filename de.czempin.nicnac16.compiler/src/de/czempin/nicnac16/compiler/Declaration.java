package de.czempin.nicnac16.compiler;

public class Declaration extends Statement {
	private Type type;
	private String symbol;

	public Declaration(Type type, String symbol) {
		this.type = type;
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		String retVal = "    ";
		retVal += type.toString();
		retVal += " ";
		retVal += symbol.toString();
		/*
		 * switch (counter) { case 0: retVal += "int A;"; // declaration break; case 1: retVal += "int _b;"; // declaration break; case 2: retVal += "int c7;";
		 * // declaration break; case 3: retVal += "int d;"; // declaration break; }
		 */
		retVal += "\n";

		return retVal;
	}

}
