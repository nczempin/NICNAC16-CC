package de.czempin.nicnac16.compiler;

import java.util.List;

public class Return extends Statement {

	private Expression expression;

	public Return(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "    return " + expression + ";\n";
	}

	@Override
	public List<ThreeAddressStatement> compile() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
