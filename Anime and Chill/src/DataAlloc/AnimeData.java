package DataAlloc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class AnimeData {

	public static void main(String[] args) throws SQLException {
		String user = "Akila";
		String ID = "100";
		add_data(ID, user);
		show_data("users");
	}
	
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
