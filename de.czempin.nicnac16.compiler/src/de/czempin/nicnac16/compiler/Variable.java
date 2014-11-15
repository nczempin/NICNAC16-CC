package de.czempin.nicnac16.compiler;

public class Variable {

	private Type type;
	private String name;

	public Variable(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
