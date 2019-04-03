package UserProgram;

public class Anime {
	private String genre;
	private String[] users;
	private int animeID;
	
	public Anime(int animeID, String genre, String[] users) {
		this.animeID = animeID;
		this.genre = genre;
		this.users = users;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public String[] getUsers() {
		return this.users;
	}
	
	public int getanimeID() {
		return this.animeID;
	}
}
