package de.czempin.nicnac16.compiler;

public class Return extends Statement {

	private Expression expression;

	public Return(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "    return " + expression + ";\n";
	}

}
