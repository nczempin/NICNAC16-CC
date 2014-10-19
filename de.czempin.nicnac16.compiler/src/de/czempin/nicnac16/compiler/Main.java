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
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Type returnValue = new Type("int");
		String name = "main";
		Signature signature = new Signature(null);
		List<Statement> statements = new ArrayList<Statement>();
		Statement s = new Statement();
		statements.add(s );
		statements.add(s );
		statements.add(s );
		statements.add(s );
		statements.add(s );
		statements.add(s );
		statements.add(s );
		statements.add(s );
		Block content = new Block(statements);
		Function f = new Function(returnValue, name, signature, content);
		f.print();

		//compile();
	}

	private static void compile() throws FileNotFoundException, IOException {
		File file = new File("test001.c");
		InputStream is = new FileInputStream(file);
		Reader r = new BufferedReader(new InputStreamReader(is));
		StreamTokenizer strt = new StreamTokenizer(r);
		strt.wordChars('_', '_');
		boolean done = false;
		while (!done) {
			int t = strt.nextToken();
			switch (t) {
			case StreamTokenizer.TT_WORD:
				Reserved found = Reserved.find(strt.sval);
				if (found != null) {
					System.out.print("*");
				} else {
					System.out.print("SYMBOL:");
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
			}
		}
	}

}
