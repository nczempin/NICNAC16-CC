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
		statements.add(new Assignment(new Variable(Type.INT, "A"), new Expression("7")));
		statements.add(new Assignment(new Variable(Type.INT, "_b"), new Expression("25")));
		statements.add(new Assignment(new Variable(Type.INT, "c7"), new Expression("A + _b")));
		statements.add(new Return(new Expression("c7")));
		Block content = new Block(statements);
		Function f = new Function(returnValue, name, signature, content);
		// f.print();
	}

	private static void compile() throws FileNotFoundException, IOException, ParseException {
		File file = new File("test001.c");
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
		Block currentBlock = new Block();
		Signature currentSignature = null;
		String expression = "";
		String rvalue = "";
		while (!done) {
			int t = strt.nextToken();
			int number =(int) strt.nval; //TODO simply casting for now
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
					} else if ("FOR".equals(name)) {
						ps = ParseState.FOR;
					} else if ("WHILE".equals(name)) {
						ps = ParseState.WHILE;
					} else if ("IF".equals(name)) {
						ps = ParseState.IF;
					}
				} else {
					System.out.print("SYMBOL:");
					switch (ps) {
					case RVALUE:
						rvalue += strt.sval;
						break;
					default:
						currentSymbol = strt.sval;
						expression += currentSymbol;
						break;
					}
					if (currentType == null) {
						// assignment, expect "="
						switch (ps) {
						case BLOCK:
							ps = ParseState.ASSIGNMENT;
							break;
						}
					} else {

						ps = ParseState.DECLARATION;
					}
				}
				System.out.println(strt.sval);
				break;
			case StreamTokenizer.TT_NUMBER:
				System.out.println("#" + number);
				expression += number;
				switch (ps) {
				case RVALUE:
					rvalue += number;
					break;
				default:
				}
				break;
			case StreamTokenizer.TT_EOL:
				System.out.println(strt.sval);
				break;
			case StreamTokenizer.TT_EOF:
				done = true;
				break;
			default:
				String punctuation = strt.toString().substring(7, 8); // crude way to extract brackets etc.
				System.out.println(punctuation);
				if ("(".equals(punctuation)) {
					switch (ps) {
					case DECLARATION:
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
						break;
					case IF:
						System.out.println("##CONDITION##");
						ps = ParseState.CONDITION;
						expression = "";
						break;
					default:
					}
				} else if (")".equals(punctuation)) {
					if (ps == ParseState.SIGNATURE) {
						// good
						System.out.println("##END_OF_SIG##");
						ps = ParseState.FUNCTION;
					} else if (ps == ParseState.CONDITION) {
						System.out.print(currentSymbol);
						System.out.println(expression);
						System.out.println("##END_OF_CONDITION##");
						ps = ParseState.BLOCK;
					} else {
						throw new ParseException("Syntax Error: not in function signature context", 0);
					}
				} else if ("{".equals(punctuation)) {
					expression = "";
					ParseState type = null;
					if (currentBlock != null) {
						type = currentBlock.getType();
					}
					if (type != null) {
						switch (type) {
						case FUNCTION:
							System.out.println("##BLOCK##");
							ps = ParseState.BLOCK;
							currentBlock.setType(ps);
							currentType = null;
							currentSymbol = null;
							break;
						case IF:
							currentType = null;
							currentSymbol = null;
							break;

						default:
							throw new ParseException("Syntax Error: unexpected context", 0);
						}
						currentBlock = new Block();
					}
				} else if ("}".equals(punctuation)) {
					if (ps == ParseState.BLOCK) {
						System.out.println("##END_OF_BLOCK##");
						ParseState type = null;
						if (currentBlock != null) {
							type = currentBlock.getType();

						}
						if (type == null) { // outermost block

							currentFunction.setBlock(currentBlock);
							
							ps = ParseState.FILE; // TODO: allow block nesting, remember the surrounding construct
						}
					} else {
						throw new ParseException("Syntax Error: not in block context", 0);
					}
				} else if (";".equals(punctuation)) {
					switch (ps) {
					case ASSIGNMENT:
						System.out.println(expression);

						Statement assignment0 = new Assignment(new Variable(currentType, currentSymbol), new RValue(expression));
						currentBlock.append(assignment0);
						System.out.println("##END_OF_ASSIGNMENT##");
						break;
					case RVALUE:
						System.out.println(rvalue);

						Statement assignment1 = new Assignment(new Variable(currentType, currentSymbol), new RValue(rvalue));
						currentBlock.append(assignment1);
						System.out.println("##END_OF_ASSIGNMENT(RVALUE)##");
						break;
					case DECLARATION:
						System.out.println(currentType + " " + currentSymbol);
						System.out.println("##END_OF_DECLARATION##");
						break;
					case RETURN:
						System.out.println("return " + expression);
						break;
					case WHILE:
					case FOR:
					case IF:
						System.out.println("TODO" + ps.name());
						break;
					default:
						System.out.println("unknown (for now) statement");
					}
					System.out.println("##END_OF_STATEMENT##");
					currentType = null;
					currentSymbol = null;
					ps = ParseState.BLOCK;
					expression = "";
				} else if ("=".equals(punctuation)) {
					if (ps.equals(ParseState.ASSIGNMENT)) {
						ps = ParseState.RVALUE;
						rvalue="";
					} else {
						expression += punctuation;
					}
				} else if ("!".equals(punctuation)) {
					expression += punctuation;
					rvalue += punctuation;
				} else if ("+".equals(punctuation)) {
					expression += punctuation;
					rvalue += punctuation;
				} else if ("<".equals(punctuation)) {
					expression += punctuation;
					rvalue += punctuation;
				} else {
					throw new ParseException("Syntax Error: unknown symbol, " + punctuation, 0);
				}
			}
		}
		currentFunction.print();
		currentFunction.compile();
	}

}
