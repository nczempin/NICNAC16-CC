package de.czempin.nicnac16.compiler;

public abstract class Statement {

	@Override
	public String toString() {
				return "UNKNOWN_STATEMENT\n";
	}

	public abstract void compile();

}
