package DataAlloc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The DB Connection class provides serves as a connecting class to the database
 */
public class DB_Connection {
	
	/**
	 * main
	 * @param args - array of strings
	 */
	public static void main(String[] args) {
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		System.out.println(connection);
	}

	/**
	 * get_connection is used to fetch a Connection to database
	 * @return Connection - the connection to the database
	 */
	public Connection get_connection() {
		Connection connection = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AnimeUsers?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "myuser", "anime&Chill");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return connection;
	}
}
