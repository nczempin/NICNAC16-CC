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
		File file = new File("test002.c");
		InputStream is = new FileInputStream(file);
		Reader r = new BufferedReader(new InputStreamReader(is));
		parse(r);
	}

	private static void parse(Reader r) throws IOException, ParseException {
		StreamTokenizer strt = new StreamTokenizer(r);
		strt.wordChars('_', '_');
		boolean done = false;
		ParseState ps = ParseState.FILE;
		Type currentType = null;
		String currentSymbol = null;
		Function currentFunction = null;
		Block currentBlock = null;
		Signature currentSignature = null;

		while (!done) {
			int t = strt.nextToken();
			switch (t) {
			case StreamTokenizer.TT_WORD:
				Reserved found = Reserved.find(strt.sval);
				if (found != null) {
					System.out.print("*");
					String name = found.name();
					if ("INT".equals(name)) { // TODO obviously this needs to be expanded and generalized
						currentType = Type.INT;
					} else if ("RETURN".equals(name)) {
						ps = ParseState.RETURN;
					}else if ("FOR".equals(name)){
						ps = ParseState.FOR;
					}else if ("WHILE".equals(name)){
						ps = ParseState.WHILE;
					}else if ("IF".equals(name)){
						ps = ParseState.IF;
					}
				} else {
					System.out.print("SYMBOL:");
					currentSymbol = strt.sval;
					if (currentType == null) {
						// assignment, expect "="
						if (ps == ParseState.BLOCK) {
							ps = ParseState.ASSIGNMENT;
						}
					} else {
						// declaration, expect ";"
						ps = ParseState.DECLARATION;
					}
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
					if (ps == ParseState.DECLARATION) {
						if (currentType == null) {
							throw new ParseException("Syntax Error: function has no return value", 0);
						}
						if (currentSymbol == null) {
							throw new ParseException("Syntax Error: function has no name", 0);
						}
						currentSignature = new Signature();
						currentFunction = new Function(currentType, currentSymbol, currentSignature, null);
						System.out.println("##SIGNATURE##");
						ps = ParseState.SIGNATURE;
					}
				} else if (")".equals(punctuation)) {
					if (ps == ParseState.SIGNATURE) {
						// good
						System.out.println("##END_OF_SIG##");
						ps = ParseState.FUNCTION;
					}else if (ps == ParseState.IF){
						
					} else {
						throw new ParseException("Syntax Error: not in function signature context", 0);
					}
				} else if ("{".equals(punctuation)) {
					if (ps == ParseState.FUNCTION) {
						// good
						System.out.println("##BLOCK##");
						ps = ParseState.BLOCK;
						currentBlock = new Block();
						currentType = null;
						currentSymbol = null;
					}else if (ps==ParseState.IF){
						
					} else {
						throw new ParseException("Syntax Error: not in function context", 0);
					}
				} else if ("}".equals(punctuation)) {
					if (ps == ParseState.BLOCK) {
						System.out.println("##END_OF_BLOCK##");

						currentFunction.setBlock(currentBlock);
						currentFunction.print();
						ps = ParseState.FILE; // TODO: allow block nesting, remember the surrounding construct
					} else {
						throw new ParseException("Syntax Error: not in block context", 0);
					}
				} else if (";".equals(punctuation)) {
					switch (ps) {
					case ASSIGNMENT:
						System.out.println(currentSymbol + "= something");
						break;
					case DECLARATION:
						System.out.println(currentType + " " + currentSymbol);
						break;
					case FOR:
					case RETURN:
					case WHILE:
					case IF:
						System.out.println(ps.name());
						break;
					default:
						System.out.println("unknown (for now) statement");
					}
					System.out.println("##END_OF_STATEMENT##");
					currentType = null;
					currentSymbol = null;
					ps = ParseState.BLOCK;
				} else if ("=".equals(punctuation)) {
				} else if ("+".equals(punctuation)) {
				} else if ("<".equals(punctuation)) {
				} else {
					throw new ParseException("Syntax Error: unknown symbol, " + punctuation, 0);
				}
			}
		}
	}

}
