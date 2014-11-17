package de.czempin.nicnac16.compiler;

public class BinaryExpression implements Expression {

	private String string;
	private Expression e1;
	private Expression e2;

	public BinaryExpression(String string, Expression e1, Expression e2) {
		this.string = string;
		this.e1 = e1;
		this.e2 = e2;
	}

	public BinaryExpression(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

	@Override
	public void compile() {
		System.out.println(string);
		e1.compile();
		e2.compile();

	}
}
