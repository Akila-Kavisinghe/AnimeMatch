package DataAlloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * The PopAnime class puts/gets data from csv into the database relating to anime
 */
public class PopAnime {
	
	/**
	 * main
	 * @param args - array of strings
	 * @throws IOException - Input/Output error
	 * @throws SQLException - database access error
	 */
	public static void main(String[] args) throws IOException, SQLException{
		populateAnime();
		//DataManipulate.show_data("location");
	}
	
	/**
	 * populateAnime is used to populate anime data into the database
	 * @throws IOException - Input/Output error
	 * @throws SQLException - database access error
	 */
	public static void populateAnime() throws IOException, SQLException{
		File file = new File("Data/AnimeList.csv");
		BufferedReader buff = new BufferedReader(new FileReader(file));
		
		String line = null;
		
		line = buff.readLine();
	
		String[] store = line.split(",", -1);

		String id = store[0]; 
		String name = store[1];
		
		while((line = buff.readLine()) != null) {
			
			store = line.split(",", -1);
			id = store[0];
			name = store[1];
				
			add_data(id, name);
		}
		buff.close();
	}
	
	/**
	 * retrieve_data is used to fetch data for a specific anime from the database
	 * @param animeID - string corresponding to the anime's ID
	 * @param field - string corresponding to wanted field
	 * @return String - data retrieved from database for the input anime from required field
	 * @throws SQLException - database access error
	 */
	public static String retrieve_data(String animeID, String field) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		String entry = null;
		
		PreparedStatement ps = null;
		try {
			String query = "select * from names where animeID = " + "'" + animeID + "'";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				entry = rs.getString(field);
				connection.close();
				return entry;
			}
			return "Akame ga kill";

		}
		
		catch(Exception e) {
			connection.close();
			System.out.println(e);
		}
		connection.close();
		return entry;
	}
	
	/**
	 * check_data is used to check data for an anime in specific field from the database
	 * @param name - string corresponding to anime's name
	 * @param field - string corresponding to target field
	 * @return boolean - weather anime exists in database
	 * @throws SQLException - database access error
	 */
	public static boolean check_data(String name, String field) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		
		PreparedStatement ps = null;
		try {
			String query = "select * from names";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String entry = rs.getString(field);
				if (name.equals(entry)) {
					connection.close();
					return true;
				}
				
			}
			connection.close();
			return false;
		}
		
		catch(Exception e) {
			System.out.println(e);
			connection.close();
			return false;
		}
	}
	
	/**
	 * add_data is used to add anime to the database
	 * @param animeID - string corresponding to anime's ID
	 * @param users - string corresponding to users that watch the anime
	 * @throws SQLException - database access error
	 */
	public static void add_data(String animeID, String name) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		boolean is_used = check_data(animeID, "title");
		if (is_used) {
			String othername= retrieve_data(animeID, "users");

			if((name.equals(othername)))
				return;
			connection.close();
			return;
		}
		else {
			String values = "Values('" + animeID + "', '" + name + "')";
			
			
			PreparedStatement ps = null;
			try {
				String command = "insert into names(animeID, title)";
				String statement = command + values;
				ps = connection.prepareStatement(statement);
				ps.execute();
				connection.close();
			}
			
			catch(Exception e) {
				System.out.println(e);
			}
			
			connection.close();
		}
	}
}
