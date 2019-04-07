package UserProgram;

/**
 * The Anime class is an abstract object for the anime
 */
public class Anime {
	private String genre;
	private String[] users;
	private int animeID;
	
	/**
	 * Anime object constructor creates an instance of anime
	 * @param animeID - integer representing the anime Id
	 * @param genre - string representing the anime genre
	 * @param users - array string representing the list of users watching the anime
	 */
	public Anime(int animeID, String genre, String[] users) {
		this.animeID = animeID;
		this.genre = genre;
		this.users = users;
	}
	
	/**
	 * getGenre is a getter that fetches the genre of the anime
	 * @return String - genre of anime
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * getUsers is a getter that fetches the users of the anime
	 * @return array of Strings - users of the anime
	 */
	public String[] getUsers() {
		return this.users;
	}
	
	/**
	 * getID is a getter that fetches the ID of the anime
	 * @return Integer - ID of anime
	 */
	public int getanimeID() {
		return this.animeID;
	}
}
