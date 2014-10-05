package de.czempin.nicnac16.compiler;

import java.util.HashSet;
import java.util.Set;

public enum Reserved {
	INT("int"), IF("if"), WHILE("while"), FOR("for"), RETURN("return")

	;

	private String word;

	Reserved(String word) {
		this.word = word;

	}

	public static Reserved find(String term) {
		Reserved[] v = Reserved.values();
		Set<String> all = new HashSet<String>();
		for (Reserved reserved : v) {
			all.add(reserved.word);
		}
		if (all.contains(term)) {
			Reserved retVal = Reserved.valueOf(term.toUpperCase());

			return retVal;
		} else {
			return null;
		}
	}
}
