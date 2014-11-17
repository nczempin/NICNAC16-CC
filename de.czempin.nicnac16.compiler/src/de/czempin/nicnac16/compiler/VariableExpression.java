package de.czempin.nicnac16.compiler;

public class VariableExpression implements Expression{

	private String string;

	public VariableExpression(String string) {
		this.string =string;	}

		@Override
		public String toString() {
			return string;
		}

		@Override
		public void compile() {
			// TODO Auto-generated method stub
			
		}
}
