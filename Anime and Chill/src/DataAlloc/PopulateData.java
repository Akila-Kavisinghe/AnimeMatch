package DataAlloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


public class PopulateData {
	public static void main(String[] args) throws IOException, SQLException{
		//populateUsers();
		DataManipulate.show_data("location");
	}
	
	public static String strip(String str) {
		return str.replaceAll("[^\\p{Print}]", "");
	}
	
	public static void populateUsers() throws IOException, SQLException{
		File file = new File("Data/test.csv");
		BufferedReader buff = new BufferedReader(new FileReader(file));
		FilterFunction f = new FilterFunction();
		
		String line = null;
		
		//line = buff.readLine();
		line = f.filter();
	
		String[] store = line.split(",", -1);
		int count = 0;

		String username = strip(store[count++]);
		String animeID = strip(store[count++]);
		String episodes = strip(store[count++]);
		String startdate = strip(store[count++]);
		String finishdate = strip(store[count++]);
		String score = strip(store[count++]);
		String status = strip(store[count++]);
		String rewatching = strip(store[count++]);
		String rewatchingep = strip(store[count++]);
		String lastupdated = strip(store[count++]);
		String tags = strip(store[count++]);
		
		String current = username;
		String currentanimeID = animeID;
		String currentscore = score;
		String currentepisodes = episodes;
		int counter = 0;
		
		while((line = f.filter()) != null) {
			counter++;
			
			if (counter >= 5000)
				break;
			
			store = line.split(",", -1);
			count = 0;
			username = strip(store[count++]);
			animeID = strip(store[count++]);
			episodes = strip(store[count++]);
			startdate = strip(store[count++]);
			finishdate = strip(store[count++]);
			score = strip(store[count++]);
			status = strip(store[count++]);
			rewatching = strip(store[count++]);
			rewatchingep = strip(store[count++]);
			lastupdated = strip(store[count++]);
			tags = strip(store[count++]);
			
			Random rand = new Random();
			int loc = rand.nextInt(33);
			
			
			if (current.equals(username)) {
				currentanimeID += " " + animeID;
				currentscore += " " + score;
				currentepisodes += " " + episodes;
			}
				
			else if (!current.equals(username)) {
				DataManipulate.add_data(current, currentanimeID, currentepisodes, currentscore, loc);
				currentanimeID = animeID; 
				currentscore = score;
				currentepisodes = episodes;
				current = username;
			}
			
		}
		Random rand = new Random();
		int loc = rand.nextInt(32);
		if (!"username".equals(current))
			DataManipulate.add_data(current, currentanimeID, currentepisodes, currentscore, loc);
		DataManipulate.show_data("username");
		buff.close();
	}
}
