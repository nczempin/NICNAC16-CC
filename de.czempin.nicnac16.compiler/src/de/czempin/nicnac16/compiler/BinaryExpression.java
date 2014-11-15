package de.czempin.nicnac16.compiler;

public class BinaryExpression implements Expression{

	private String string;

	public BinaryExpression(String string, Expression e1, Expression e2) {
		// TODO Auto-generated constructor stub
	}

	public BinaryExpression(String string) {
	
		this.string =string;	}

	@Override
	public String toString() {
		return string;
	}
}
