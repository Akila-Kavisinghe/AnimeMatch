package UserProgram;

import java.util.ArrayList;

public class SearchBar {

	private ArrayList<User> allLikedUsers;
	private User[] results = new User[4];

	private String[] string1 = {"", "", "", "", ""};
	private String[] string2 = {"", "", "", "", ""};
	private String[] string3 = {"", "", "", "", ""};
	private String[] string4 = {"", "", "", "", ""};

	public SearchBar(ArrayList<User> allLikedUsers) {

		this.allLikedUsers = allLikedUsers;
	}

	public void search(String searchString) {

		// This searches for a user based on substring

		int counter = 0;

		for (int i = 0; i < this.allLikedUsers.size(); i++) {

			if (isStart(searchString, this.allLikedUsers.get(i).getUser())) {
				this.results[counter] = this.allLikedUsers.get(i);
				counter++;
			}
		}
	}

	private boolean isStart(String a, String b) {
		if (b.startsWith(a)) {
			return true;
		} else
			return false;
	}

	public void populate() {

		try {
			this.string1[0] = results[0].getUser(); // USER
			this.string1[1] = (results[0].getAnimeList()[0]) + " " + (results[0].getAnimeList()[1]) + " "
					+ (results[0].getAnimeList()[3]); // AnimeList
			this.string1[2] = (results[0].getEpisodes()[0]) + " " + (results[0].getEpisodes()[1]) + " "
					+ (results[0].getEpisodes()[2]); // eps
			this.string1[3] = (results[0].getScores()[0]) + " " + (results[0].getScores()[1]) + " "
					+ (results[0].getScores()[2]); // Scores
			this.string1[4] = Integer.toString(results[0].getLocation());
			; // Location
		} catch (NullPointerException e) {
			System.out.println("No results");
			return;
		}

		try {
			this.string2[0] = results[1].getUser(); // USER
			this.string2[1] = (results[1].getAnimeList()[0]) + " " + (results[1].getAnimeList()[1]) + " "
					+ (results[1].getAnimeList()[3]); // AnimeList
			this.string2[2] = (results[1].getEpisodes()[0]) + " " + (results[1].getEpisodes()[1]) + " "
					+ (results[1].getEpisodes()[2]); // eps
			this.string2[3] = (results[1].getScores()[0]) + " " + (results[1].getScores()[1]) + " "
					+ (results[1].getScores()[2]); // Scores
			this.string2[4] = Integer.toString(results[1].getLocation());
			; // Location
		} catch (NullPointerException e) {
			this.string2[0] = "";
			this.string2[1] = "";
			this.string2[2] = "";
			this.string2[3] = "";
			this.string2[4] = "";
		}

		try {
			this.string3[0] = results[2].getUser(); // USER
			this.string3[1] = (results[2].getAnimeList()[0]) + " " + (results[2].getAnimeList()[1]) + " "
					+ (results[2].getAnimeList()[3]); // AnimeList
			this.string3[2] = (results[2].getEpisodes()[0]) + " " + (results[2].getEpisodes()[1]) + " "
					+ (results[2].getEpisodes()[2]); // eps
			this.string3[3] = (results[2].getScores()[0]) + " " + (results[2].getScores()[1]) + " "
					+ (results[2].getScores()[2]); // Scores
			this.string3[4] =  Integer.toString(results[2].getLocation());
			; // Location
		} catch (NullPointerException e) {
			this.string3[0] = "";
			this.string3[1] = "";
			this.string3[2] = "";
			this.string3[3] = "";
			this.string3[4] = "";
		}

		try {
			this.string4[0] = results[3].getUser(); // USER
			this.string4[1] = (results[3].getAnimeList()[0]) + " " + (results[3].getAnimeList()[1]) + " "
					+ (results[3].getAnimeList()[3]); // AnimeList
			this.string4[2] = (results[3].getEpisodes()[0]) + " " + (results[3].getEpisodes()[1]) + " "
					+ (results[3].getEpisodes()[2]); // eps
			this.string4[3] = (results[3].getScores()[0]) + " " + (results[3].getScores()[1]) + " "
					+ (results[3].getScores()[2]); // Scores
			this.string4[4] = Integer.toString(results[3].getLocation());
			; // Location
		} catch (NullPointerException e) {
			this.string4[0] = "";
			this.string4[1] = "";
			this.string4[2] = "";
			this.string4[3] = "";
			this.string4[4] = "";
		}
	}

	public String[] getString1() {
		return this.string1;
	}

	public String[] getString2() {
		return this.string2;
	}

	public String[] getString3() {
		return this.string3;
	}

	public String[] getString4() {
		return this.string4;
	}

	public static void main(String[] args) {

		Integer[] animeList = { 5, 1, 2, 3, 4 };
		int[] eps = { 1, 2, 4, 3, 2 };
		double[] scores = { 5.0, 2.0, 3.0, 1.0, 7.0 };

		User akila = new User("aeila", animeList, eps, scores, 2);

		Integer[] animeList1 = { 5, 1, 2, 4, 5 };
		int[] eps1 = { 1, 2, 4, 3, 5 };
		double[] scores1 = { 2.0, 6.0, 3.1, 5.0, 1.5 };

		User eric = new User("aehhh", animeList1, eps1, scores1, 4);

		Integer[] animeList2 = { 5, 2, 4, 3, 1 };
		int[] eps2 = { 12, 234, 97, 82, 72 };
		double[] scores2 = { 8.3, 9.4, 4.2, 9.5, 3.7 };

		User oleg = new User("aeeg", animeList2, eps2, scores2, 7);

		Integer[] animeList3 = { 5, 2, 4, 3, 1 };
		int[] eps3 = { 672, 40, 273, 38, 38 };
		double[] scores3 = { 9.2, 8.5, 3.7, 7.9, 8.2 };

		User billy = new User("billy", animeList3, eps3, scores3, 10);

		ArrayList <User> usersss = new ArrayList<User>();
		
		usersss.add(billy);
		usersss.add(oleg);
		usersss.add(eric);
		usersss.add(akila);

		SearchBar cool = new SearchBar(usersss);

		cool.search("ae");
		
		cool.populate();

		for (int i = 0; i < cool.getString1().length; i++) {
			System.out.print(cool.getString1()[i] + ":::::");
		}
		
		System.out.println();
		
		for (int i = 0; i < cool.getString2().length; i++) {
			System.out.print(cool.getString2()[i] + ":::::");
		}
		
		System.out.println();
		
		for (int i = 0; i < cool.getString3().length; i++) {
			System.out.print(cool.getString3()[i] + ":::::");
		}
		
		System.out.println();
		
		for (int i = 0; i < cool.getString4().length; i++) {
			System.out.print(cool.getString4()[i] + ":::::");
		}
		
		System.out.println();
	}
}
