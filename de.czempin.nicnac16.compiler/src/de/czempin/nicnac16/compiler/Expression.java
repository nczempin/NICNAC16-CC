package de.czempin.nicnac16.compiler;

public class Expression {

	private String string;

	public Expression(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

	void compile();

}
