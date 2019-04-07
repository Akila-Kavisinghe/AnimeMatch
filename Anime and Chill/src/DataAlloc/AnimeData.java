package DataAlloc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import UserProgram.User;

/**
 * The AnimeData class provides functions that work with the anime data from the database 
 */
public class AnimeData {

	/**
	 * main
	 * @param args - array of strings
	 * @throws SQLException - database access error
	 */
	public static void main(String[] args) throws SQLException {
		
		String[] allusers = DataManipulate.graball_data().split(" ");
		for(int i = 0; i < allusers.length; i++) {
			String[] animes = DataManipulate.retrieve_data(allusers[i], "animeID").split(" ");
			for(int j = 0; j < animes.length; j++) {
				add_data(animes[j], allusers[i]);
			}
		}
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
			String query = "select * from anime where animeID = " + "'" + animeID + "'";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			entry = rs.getString(field);
			connection.close();
			return entry;

		}
		
		catch(Exception e) {
			connection.close();
			System.out.println(e);
		}
		connection.close();
		return entry;
	}
	
	/**
	 * random_data fetches data for one random anime
	 * @return String - data retrieved from database for a random anime
	 * @throws SQLException - database access error
	 */
	public static String random_data() throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		
		PreparedStatement ps = null;
		try {
			String query = "select * from anime";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			Random rand = new Random();
			int n = rand.nextInt(2000);
			int count = 0;
			
			while(rs.next()) {
				if (n == count)
					return rs.getString("animeID");
				count++;
			}
			connection.close();
			return "";
		}
		
		catch(Exception e) {
			System.out.println(e);
			connection.close();
			return "";
		}
	}
	
	/**
	 * show_data is used to print data from the database onto console
	 * @param field - string corresponding to wanted field's data
	 * @throws SQLException - database access error
	 */
	public static void show_data(String field) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		
		PreparedStatement ps = null;
		try {
			String query = "select * from anime";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("animeID - " + rs.getString(field));
			}
			connection.close();
		}
		
		catch(Exception e) {
			System.out.println(e);
			connection.close();
		}
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
			String query = "select * from anime";
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
	public static void add_data(String animeID, String users) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		boolean is_used = check_data(animeID, "animeID");
		if (is_used) {
			String allusers= retrieve_data(animeID, "users");
			
			String[] ulst = allusers.split(" ");
			for(int i = 0; i < ulst.length; i++)
				if((users.equals(ulst[i]))) {
					connection.close();
					return;
				}
			update_data(animeID, "users", users + " " + allusers);
			connection.close();
			return;
		}
		else {
			String values = "Values('" + animeID + "', '" + users + "')";
			
			
			PreparedStatement ps = null;
			try {
				String command = "insert into anime(animeID, users)";
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
	
	/**
	 * update_data is used to update anime data in the database
	 * @param animeID - string corresponding to anime's ID
	 * @param field - string corresponding to wanted field to be changed
	 * @param neval - string corresponding to new value of altered field
	 * @throws SQLException - database access error
	 */
	public static void update_data(String animeID, String field, String newval) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		boolean is_used = check_data(animeID, "animeID");
		if (is_used) {
			PreparedStatement ps = null;
			try {
				String command = "update anime set " + field + " = " + "'" + newval + "'"; 
			    String command2 = " where animeID = '" + animeID + "'";
			    command = command + command2;
				ps = connection.prepareStatement(command);
				ps.execute();
				connection.close();
			}
			
			catch(Exception e) {
				System.out.println(e);
				connection.close();
			}
			connection.close();
		}
		
		else
			System.out.println("Must add user before being able to update it");
		connection.close();
	}
	
}
