package UserProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Change {
	public static String strip(String line) {
		line = line.replace(" ", "_");
		line = line.replace(".", "_");
		line = line.replace("__", "_");
		line = line.toLowerCase();
		return line;
	}
	
	public static void main(String[] args) throws IOException {
		PrintStream output;
    	output = new PrintStream(new File("Data/connections"));
		File cities = new File("Data/connectedCities.txt");
		BufferedReader buff = new BufferedReader(new FileReader(cities));
		String line = null;
		
		while((line = buff.readLine()) != null) {
			line = strip(line);
			output.println(line);
		}
		buff.close();
	}
	
}
