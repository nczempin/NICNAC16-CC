package de.czempin.nicnac16.compiler;

import java.util.ArrayList;
import java.util.List;

public class Block {
	private List<Statement> statements= new ArrayList<Statement>();
	private ParseState type;

	@Override
	public String toString() {
		String retVal = "";
		
		retVal += "{\n";
		
		
		retVal += convertToString();
		retVal += "}\n";
		return retVal;
	}

	private String convertToString() {
		String retVal ="";

		for (Statement statement : statements) {
			retVal+=statement.toString();
		}
		return retVal;
	}

	public Block(List<Statement> statements) {
		super();
		this.statements = statements;
	}

	public Block() {
	}

	public ParseState getType() {
		return this.type;
	}

	public void setType(ParseState type) {
		this.type = type;
	}

	public void append(Statement assignment) {
		this.statements.add(assignment);
	}

}
