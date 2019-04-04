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

public class PopAnime {
	public static void main(String[] args) throws IOException, SQLException{
		populateAnime();
		//DataManipulate.show_data("location");
	}
	
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
