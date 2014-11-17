package de.czempin.nicnac16.compiler;

import java.util.List;

import de.czempin.nicnac16.compiler.threeAddress.ThreeAddressStatement;

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
	public List<ThreeAddressStatement> compile() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
