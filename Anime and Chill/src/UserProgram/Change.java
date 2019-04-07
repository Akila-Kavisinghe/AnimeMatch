package UserProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * The Change class is used to change strings for later
 */
public class Change {
	
	/**
	 * strip is used to replace " ", ".", and "__" characters with "_"
	 * @param line - string that needs to be striped
	 * @return String - after being striped
	 */
	public static String strip(String line) {
		line = line.replace(" ", "_");
		line = line.replace(".", "_");
		line = line.replace("__", "_");
		line = line.toLowerCase();
		return line;
	}
	
	/**
	 * main
	 * @param args - array of strings
	 * @throws IOException - Input/Output error
	 */
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
