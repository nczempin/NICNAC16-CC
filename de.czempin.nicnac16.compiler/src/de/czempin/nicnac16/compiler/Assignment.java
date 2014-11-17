package de.czempin.nicnac16.compiler;

public class Assignment extends Statement {
	private Variable variable;
	private Expression expression;

	public Assignment(Variable variable, Expression expression) {
		this.variable = variable;
		this.expression = expression;
	}

	@Override
	public String toString() {
		String retVal = "    ";
		retVal += variable.toString();
		retVal += " = ";
		retVal += expression.toString();
		retVal += ";\n";
		return retVal;
	}

	@Override
	public void compile() {

		System.out.println("dest: "+variable);
		expression.compile();
	}

}
