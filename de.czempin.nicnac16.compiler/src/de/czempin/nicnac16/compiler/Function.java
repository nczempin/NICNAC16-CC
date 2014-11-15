package de.czempin.nicnac16.compiler;

public class Function {

	private Type returnValue;
	private String name;
	private Signature signature;
	private Block content;

	public Function(Type returnValue, String name, Signature signature, Block content) {
		this.returnValue = returnValue;
		this.name = name;
		this.signature = signature;
		this.content = content;
	}

	public void print() {
		System.out.print(returnValue);
		System.out.print(" ");
		System.out.print(name);
		System.out.print(signature);

		System.out.println(content);
	}

}
