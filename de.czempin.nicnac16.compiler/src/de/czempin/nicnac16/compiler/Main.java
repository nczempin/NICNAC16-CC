package de.czempin.nicnac16.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {

		// dummyParse();

		compile();
	}

	private static void dummyParse() {
		Type returnValue = Type.INT;
		String name = "main";
		Signature signature = new Signature(null);
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(new Declaration(Type.INT, "A"));
		statements.add(new Declaration(Type.INT, "_b"));
		statements.add(new Declaration(Type.INT, "c7"));
		statements.add(new Declaration(Type.INT, "d"));
		statements.add(new Assignment(new Variable(Type.INT, "A"), new ConstantExpression("7")));
		statements.add(new Assignment(new Variable(Type.INT, "_b"), new ConstantExpression("25")));
		statements.add(new Assignment(new Variable(Type.INT, "c7"), new BinaryExpression("A + _b")));
		statements.add(new Return(new VariableExpression("c7")));
		Block content = new Block(statements);
		Function f = new Function(returnValue, name, signature, content);
		// f.print();
	}

	private static void compile() throws FileNotFoundException, IOException, ParseException {
		File file = new File("test001.c");
		InputStream is = new FileInputStream(file);
		Reader r = new BufferedReader(new InputStreamReader(is));
		StreamTokenizer strt = new StreamTokenizer(r);
		strt.wordChars('_', '_');
		boolean done = false;
		ParseState ps = ParseState.FILE;
		Type currentType = null;
		String currentSymbol = null;
		Function currentFunction = null;
		while (!done) {
			int t = strt.nextToken();
			switch (t) {
			case StreamTokenizer.TT_WORD:
				Reserved found = Reserved.find(strt.sval);
				if (found != null) {
					System.out.print("*");
					if ("INT".equals(found.name())) { // TODO obviously this needs to be expanded and generalized
						currentType = Type.INT;
					}
				} else {
					System.out.print("SYMBOL:");
					currentSymbol = strt.sval;
				}
				System.out.println(strt.sval);
				break;
			case StreamTokenizer.TT_NUMBER:
				System.out.println("#" + strt.nval);
				break;
			case StreamTokenizer.TT_EOL:
				System.out.println(strt.sval);
				break;
			case StreamTokenizer.TT_EOF:
				done = true;
				break;
			default:
				String punctuation = strt.toString().substring(7, 8); // crude way to extract brackets etc.
				System.out.println("°" + punctuation);
				if ("(".equals(punctuation)) {
					if (currentType == null) {
						throw new ParseException("Syntax Error: function has no return value", 0);
					}
					if (currentSymbol == null) {
						throw new ParseException("Syntax Error: function has no name", 0);
					}
					currentFunction = new Function(currentType, currentSymbol);
					System.out.println("##FUNCTION##");
					ps = ParseState.SIGNATURE;
				} else if (")".equals(punctuation)) {
					if (ps == ParseState.SIGNATURE) {
						// good
						System.out.println("##END_OF_SIG##");
						ps = ParseState.FUNCTION;
					} else {
						throw new ParseException("Syntax Error: not in function signature context", 0);
					}
				} else if ("{".equals(punctuation)) {
					if (ps == ParseState.FUNCTION) {
						// good
						System.out.println("##BLOCK##");
						ps = ParseState.BLOCK;
					} else {
						throw new ParseException("Syntax Error: not in function context", 0);
					}
				} else if ("}".equals(punctuation)) {
					if (ps == ParseState.BLOCK) {
						ps = ParseState.FILE;
					}else{
						throw new ParseException("Syntax Error: not in block context", 0);
					}
				} else if (";".equals(punctuation)) {
				} else if ("=".equals(punctuation)) {
				} else if ("+".equals(punctuation)) {
			} else {
					throw new ParseException("Syntax Error: unknown symbol, " + punctuation, 0);
				}
			}
		}
		String name = null;
		Type returnValue = null;
		Block content = null;
		Signature signature = null;
		Function f = new Function(returnValue, name, signature, content);
		f.print();
	}

}
