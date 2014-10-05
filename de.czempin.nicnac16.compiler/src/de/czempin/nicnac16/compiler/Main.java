package de.czempin.nicnac16.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

public class Main {


	public static void main(String[] args) throws IOException {

		File file = new File("test001.c");
		Charset charset = Charset.defaultCharset();
		ImmutableList<String> lines = Files.asCharSource(file, charset).readLines();

		for (String line : lines) {
			StringTokenizer st = new StringTokenizer(line);
			String token = null;
			while (st.hasMoreTokens()) {
				token = st.nextToken().trim();
				System.out.println("-"+token);
			}
		}

	}

}