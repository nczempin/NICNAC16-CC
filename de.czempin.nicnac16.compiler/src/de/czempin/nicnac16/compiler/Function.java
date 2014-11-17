package de.czempin.nicnac16.compiler;

public class Function {

	private Type returnType;
	private String name;
	private Signature signature;
	private Block content;

	public Function(Type returnType, String name, Signature signature, Block content) {
		this.returnType = returnType;
		this.name = name;
		this.signature = signature;
		this.content = content;
	}

	public Function(Type type, String name) {
		this(type, name, null, null);
	}

	public void print() {
		System.out.print(returnType);
		System.out.print(" ");
		System.out.print(name);
		System.out.print(signature);

		System.out.println(content);
	}

	public void setBlock(Block block) {
		this.content = block;
	}

}
