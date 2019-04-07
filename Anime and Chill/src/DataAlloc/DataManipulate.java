package DataAlloc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * The Data Manipulate class provides functions that work with retrieving data from the database
 */
public class DataManipulate {
	
	/**
	 * main
	 * @param args - array of strings
	 * @throws SQLException - database access error
	 */
	public static void main(String[] args) throws SQLException {
		String user = "Chris";
		String ID = "1";
		String eps = "100";
		String score = "10";
		String field = "username";
		int location = 1;
		//System.out.println(retrieve_data("Chris", "animeID"));
		add_data(user, ID, eps, score, location);
		//show_data(field);
	}
	
	/**
	 * retrieve_data is used to fetch data for user in specific field from the database
	 * @param username - string corresponding to user name
	 * @param field - string corresponding to wanted field
	 * @return String - data retrieved from database for the input user from required field
	 * @throws SQLException - database access error
	 */
	public static String retrieve_data(String username, String field) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		String entry = null;
		
		PreparedStatement ps = null;
		try {
			String query = "select * from users where username = " + "'" + username + "'";
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
	 * graball_data is used to fetch all the data from the database
	 * @return String - containing all of the data
	 * @throws SQLException - database access error
	 */
	public static String graball_data() throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		
		PreparedStatement ps = null;
		try {
			String query = "select * from users";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			String output = "";
			
			while(rs.next()) {
				output += rs.getString("username") + " ";
			}
			connection.close();
			return output;
		}
		
		catch(Exception e) {
			System.out.println(e);
			connection.close();
			return "";
		}
	}
	
	/**
	 * random_data fetches data for one random user
	 * @return String - data retrieved from database for a random user
	 * @throws SQLException - database access error
	 */
	public static String random_data() throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		
		PreparedStatement ps = null;
		try {
			String query = "select * from users";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			Random rand = new Random();
			int n = rand.nextInt(2000);
			int count = 0;
			
			while(rs.next()) {
				if (n == count)
					return rs.getString("username");
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
			String query = "select * from users";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("Users - " + rs.getString(field));
			}
			connection.close();
		}
		
		catch(Exception e) {
			System.out.println(e);
			connection.close();
		}
	}
	
	/**
	 * check_data is used to check data for user in specific field from the database
	 * @param name - string corresponding to user's name
	 * @param field - string corresponding to target field
	 * @return boolean - weather user exists in database
	 * @throws SQLException - database access error
	 */
	public static boolean check_data(String name, String field) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		
		PreparedStatement ps = null;
		try {
			String query = "select * from users";
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
	 * add_data is used to add user to the database
	 * @param username - string corresponding to user name
	 * @param animeID - string corresponding to anime's ID
	 * @param episodes - string corresponding to episodes watched
	 * @param score - string corresponding to score
	 * @param location - integer corresponding to location
	 * @throws SQLException - database access error
	 */
	public static void add_data(String username, String animeID, String episodes, String score, int location) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		boolean is_used = check_data(username, "username");
		if (is_used) {
			String ID = retrieve_data(username, "animeID");
			String eps = retrieve_data(username, "episodes");
			String sc = retrieve_data(username, "score");
			String lc = retrieve_data(username, "location");
			
			if(!(animeID.equals(ID) && episodes.equals(eps) && score.equals(sc))) {
				update_data(username, "animeID", animeID + " " + ID);
				update_data(username, "episodes", episodes+ " " + eps);
				update_data(username, "score", score+ " " + sc);
				update_data(username, "location", location + " " + lc);
			}
			connection.close();
			return;
		}
		else {
			String values = "Values('" + username + "', '" + animeID + "', '" + episodes + "', '" + score + "', '" + location + "')";
			
			
			PreparedStatement ps = null;
			try {
				String command = "insert into users(username, animeID, episodes, score, location)";
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
	 * update_data is used to update user data in the database
	 * @param username - string corresponding to target user's name
	 * @param field - string corresponding to wanted field to be changed
	 * @param neval - string corresponding to new value of altered field
	 * @throws SQLException - database access error
	 */
	public static void update_data(String username, String field, String newval) throws SQLException {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		boolean is_used = check_data(username, "username");
		if (is_used) {
			PreparedStatement ps = null;
			try {
				String command = "update users set " + field + " = " + "'" + newval + "'"; 
			    String command2 = " where username = '" + username + "'";
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
