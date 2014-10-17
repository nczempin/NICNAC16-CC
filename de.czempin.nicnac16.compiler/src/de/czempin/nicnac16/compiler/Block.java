package de.czempin.nicnac16.compiler;

public class Block {

	@Override
	public String toString() {
		String retVal = "";
		
		retVal += "{\n";
		
		
		retVal += "    int A;\n";
		retVal += "    int _b;\n";
		retVal += "    int c7;\n";
		retVal += "    \n";
		retVal += "    A=7;_b=25;\n";
		retVal += "    c7 = A + _b;\n";

		retVal += "    return c7;\n";
		retVal += "}\n";
		return retVal;
	}

}
