package de.czempin.nicnac16.compiler;

public class Type {

	private String name;

	public Type(String string) {
		this.name = string;
	}

	@Override
	public String toString() {
		return name;
	}

}
