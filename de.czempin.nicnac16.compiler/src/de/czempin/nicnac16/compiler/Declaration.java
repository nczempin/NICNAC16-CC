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
		retVal += ";\n";

		return retVal;
	}

	@Override
	public void compile() {
		// TODO Auto-generated method stub
		
	}

}
