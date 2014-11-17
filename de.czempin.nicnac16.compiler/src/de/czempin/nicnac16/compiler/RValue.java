package de.czempin.nicnac16.compiler;

public class RValue implements Expression {

	private String string;

	public RValue(String expression) {
		this.string = expression;	}

	@Override
	public String toString() {
		return string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
