package de.czempin.nicnac16.compiler;

import java.util.List;

import de.czempin.nicnac16.compiler.threeAddress.ThreeAddressStatement;

public abstract class Statement {

	@Override
	public String toString() {
				return "UNKNOWN_STATEMENT\n";
	}

	public abstract List<ThreeAddressStatement> compile();

}
