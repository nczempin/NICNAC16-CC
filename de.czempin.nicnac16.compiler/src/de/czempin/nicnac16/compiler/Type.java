package de.czempin.nicnac16.compiler;

public enum Type {
	INT("int"),CHAR("char")
	;

	private String name;

	private Type(String string) {
		this.name = string;
	}

	@Override
	public String toString() {
		return name;
	}

}
