package UserProgram;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The SearchBar class is an abstract object used to represent the search bar and searching for friends 
 * in the app
 */
public class SearchBar {

	private ArrayList<User> allLikedUsers;
	private User[] results = new User[1000];

	private String[] string1 = {"", "", "", "", ""};
	private String[] string2 = {"", "", "", "", ""};
	private String[] string3 = {"", "", "", "", ""};
	private String[] string4 = {"", "", "", "", ""};

	/**
	 * SearchBar object constructor creates an instance of SearchBar
	 * @param allLikedUsers - arrayList of users that the current user has liked (swiped right)
	 */
	public SearchBar(ArrayList<User> allLikedUsers) {

		this.allLikedUsers = allLikedUsers;
	}

	/**
	 * search is used for the searching of friends functionality 
	 * @param searchString - sub-string entered in search bar to be search for ex.name of friend or starting letter
	 */
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

	/**
	 * isStart is used to inspect the array of friends and find the friends that start with entered substring
	 * is used as helper function
	 * @param a - string entered to check what starts with this
	 * @param b - string being compared to a to see if it starts with the sub-string a
	 * @return boolean - True if b does start with sub-string a, False otherwise
	 */
	private boolean isStart(String a, String b) {
		if (b.startsWith(a)) {
			return true;
		} else
			return false;
	}

	/**
	 * populate is used to populate items (aka friends) in search bar
	 */
	public void populate() {

		try {
			this.string1[0] = results[0].getUser(); // USER
			for(int i = 0; i < 3; i++) {
				if(results[0].getAnimeList().length == i)
					break;
				else if(results[0].getAnimeList().length - 1 == i) {
					this.string1[1] += results[0].getAnimeList()[i]; //animelist
					this.string1[2] += results[0].getEpisodes()[i];  //eps
					this.string1[3] += results[0].getScores()[i];   //scores
				}
				else {
					this.string1[1] += results[0].getAnimeList()[i] + ", "; //animelist
					this.string1[2] += results[0].getEpisodes()[i] + ", ";  //eps
					this.string1[3] += results[0].getScores()[i] + ", ";    //scores
				}
			}
			
			this.string1[4] = Integer.toString(results[0].getLocation());
			; // Location
		} catch (NullPointerException e) {
			System.out.println("No results");
			return;
		}

		try {
			this.string2[0] = results[1].getUser(); // USER
			
			for(int i = 0; i < 3; i++) {
				if(results[1].getAnimeList().length == i)
					break;
				else if(results[1].getAnimeList().length - 1 == i) {
					this.string2[1] += results[1].getAnimeList()[i]; //animelist
					this.string2[2] += results[1].getEpisodes()[i];  //eps
					this.string2[3] += results[1].getScores()[i];   //scores
				}
				else {
					this.string2[1] += results[1].getAnimeList()[i] + ", "; //animelist
					this.string2[2] += results[1].getEpisodes()[i] + ", ";  //eps
					this.string2[3] += results[1].getScores()[i] + ", ";    //scores
				}
			}
			
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
			
			for(int i = 0; i < 3; i++) {
				if(results[2].getAnimeList().length == i)
					break;
				else if(results[2].getAnimeList().length - 1 == i) {
					this.string3[1] += results[2].getAnimeList()[i]; //animelist
					this.string3[2] += results[2].getEpisodes()[i];  //eps
					this.string3[3] += results[2].getScores()[i];   //scores
				}
				else {
					this.string3[1] += results[2].getAnimeList()[i] + ", "; //animelist
					this.string3[2] += results[2].getEpisodes()[i] + ", ";  //eps
					this.string3[3] += results[2].getScores()[i] + ", ";    //scores
				}
			}
			
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
			
			for(int i = 0; i < 3; i++) {
				if(results[3].getAnimeList().length == i)
					break;
				else if(results[3].getAnimeList().length - 1 == i) {
					this.string4[1] += results[3].getAnimeList()[i]; //animelist
					this.string4[2] += results[3].getEpisodes()[i];  //eps
					this.string4[3] += results[3].getScores()[i];   //scores
				}
				else {
					this.string4[1] += results[3].getAnimeList()[i] + ", "; //animelist
					this.string4[2] += results[3].getEpisodes()[i] + ", ";  //eps
					this.string4[3] += results[3].getScores()[i] + ", ";    //scores
				}
			}

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

	/**
	 * getString1 is a getter that fetches string1's of the object
	 * @return array of Strings - array of strings representing String1
	 */
	public String[] getString1() {
		return this.string1;
	}

	/**
	 * getString2 is a getter that fetches string2's of the object
	 * @return array of Strings - array of strings representing String2
	 */
	public String[] getString2() {
		return this.string2;
	}

	/**
	 * getString3 is a getter that fetches string3's of the object
	 * @return array of Strings - array of strings representing String3
	 */
	public String[] getString3() {
		return this.string3;
	}

	/**
	 * getString4 is a getter that fetches string4's of the object
	 * @return array of Strings - array of strings representing String4
	 */
	public String[] getString4() {
		return this.string4;
	}

	/**
	 * main
	 * @param args - array of strings
	 */
	public static void main(String[] args) {

//		Integer[] animeList = { 5, 1, 2, 3, 4 };
//		int[] eps = { 1, 2, 4, 3, 2 };
//		double[] scores = { 5.0, 2.0, 3.0, 1.0, 7.0 };
//
//		User akila = new User("aeila", animeList, eps, scores, 2);
//
//		Integer[] animeList1 = { 5, 1, 2, 4, 5 };
//		int[] eps1 = { 1, 2, 4, 3, 5 };
//		double[] scores1 = { 2.0, 6.0, 3.1, 5.0, 1.5 };
//
//		User eric = new User("aehhh", animeList1, eps1, scores1, 4);
//
//		Integer[] animeList2 = { 5, 2, 4, 3, 1 };
//		int[] eps2 = { 12, 234, 97, 82, 72 };
//		double[] scores2 = { 8.3, 9.4, 4.2, 9.5, 3.7 };
//
//		User oleg = new User("aeeg", animeList2, eps2, scores2, 7);
//
//		Integer[] animeList3 = { 5, 2, 4, 3, 1 };
//		int[] eps3 = { 672, 40, 273, 38, 38 };
//		double[] scores3 = { 9.2, 8.5, 3.7, 7.9, 8.2 };//
//		User billy = new User("billy", animeList3, eps3, scores3, 10);
//
//		ArrayList <User> usersss = new ArrayList<User>();
//		
//		usersss.add(billy);
//		usersss.add(oleg);
//		usersss.add(eric);
//		usersss.add(akila);
//
//		SearchBar cool = new SearchBar(usersss);
//
//		cool.search("ae");
//		
//		cool.populate();
//
//		for (int i = 0; i < cool.getString1().length; i++) {
//			System.out.print(cool.getString1()[i] + ":::::");
//		}
//		
//		System.out.println();
//		
//		for (int i = 0; i < cool.getString2().length; i++) {
//			System.out.print(cool.getString2()[i] + ":::::");
//		}
//		
//		System.out.println();
//		
//		for (int i = 0; i < cool.getString3().length; i++) {
//			System.out.print(cool.getString3()[i] + ":::::");
//		}
//		
//		System.out.println();
//		
//		for (int i = 0; i < cool.getString4().length; i++) {
//			System.out.print(cool.getString4()[i] + ":::::");
//		}
//		
//		System.out.println();
	}
}
