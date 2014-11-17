package de.czempin.nicnac16.compiler.threeAddress;

public class ThreeAddressStatement {
	private Operation operation;
	private Location destination;
	private Location operand1;
	private Location operand2;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public Location getOperand1() {
		return operand1;
	}

	public void setOperand1(Location operand1) {
		this.operand1 = operand1;
	}

	public Location getOperand2() {
		return operand2;
	}

	public void setOperand2(Location operand2) {
		this.operand2 = operand2;
	}
}
