package de.czempin.nicnac16.compiler;

public class ConstantExpression implements Expression {

	private String string;

	public ConstantExpression(String string) {
		this.string =string;	}

	@Override
	public String toString() {
		return string;
	}

	@Override
	public void compile() {
		System.out.println(string);
	}

}
