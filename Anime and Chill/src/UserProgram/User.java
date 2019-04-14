package UserProgram;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAlloc.AnimeData;
import DataAlloc.DataManipulate;
import Graphs.ShortPath;

/**
 * The User class is an abstract object used to represent a user including
 * username, scores, etc.
 */
public class User {

	private String username; // User name
	private String[] animeList; // List of ANIMES watched by user
	private int[] eps; // List of episodes watched per ANIME corresponding to animeList
	private double[] scores; // List of scores given by user per ANIME corresponding to animeList

	private ArrayList<User> friends = new ArrayList<User>(); // Users accepted friends

	private int friendCount = 0; // Keeps tracks of how many friends are in "friends

	private ArrayList<User> potMat = new ArrayList<User>(); // Randomly filled potential mates
	private int potMatCount = 0;

	private int location;

	/**
	 * User object constructor creates an instance of user
	 * 
	 * @param username  - String representing the user's username
	 * @param animeList - array of Strings representing the animes that the user has
	 *                  watched
	 * @param eps       - array of integers showing how many episodes the user has
	 *                  watched of each anime in animeList
	 * @param scores    -array of doubles showing what score the user has giver to
	 *                  each anime they watch
	 * @param location  - integer value representing the location of the user
	 */
	public User(String username, String[] animeList, int[] eps, double[] scores, int location) {
		this.username = username;
		this.animeList = animeList;
		this.eps = eps;
		this.scores = scores;
		this.location = location;
	}

	/* ***************************************************** */
	/* ***************** FUNCTION METHODS ********************* */
	/* ***************************************************** */

	/**
	 * swipe serves the functionality of the user liking or pasing the displayed
	 * potential match
	 * 
	 * @param verdict - boolean representing like or pass (revieved from UI) depends
	 *                on what user chose
	 */
	public void swipe(boolean verdict) {

		if (verdict) {
			this.friends.add(this.potMat.get(this.potMatCount));

			this.friendCount++;
			this.potMatCount++;
		}

		else {
			this.potMatCount++;
		}
		if (potMatCount > potMat.size()) {
			potMatCount = 0;
		}
	}

	/**
	 * fillPotentialTest is used to add users to the list of potential matches
	 * depending on the graphing algorithm This function serves as a test
	 * 
	 * @param users - the array of all users
	 */
	public void fillPotentialTest(User[] users) {
		for (int i = 0; i < users.length; i++) {
			if (i < potMat.size())
				this.potMat.set(i, users[i]);
			else
				break;
		}
	}

	/**
	 * fillPotential is used to add users to the list of potential matches depending
	 * on the graphing algorithm
	 * 
	 * @throws IOException  - Input/Output error
	 * @throws SQLException - database access error
	 */
	public void fillPotential() throws SQLException, IOException {

		this.potMatCount = 0;
		ArrayList<String> allmates = new ArrayList<String>();

		for (int i = 0; i < this.animeList.length; i++) {
			String[] mates = AnimeData.retrieve_data(this.animeList[i], "users").split(" ");
			for (int j = 0; j < mates.length; j++) {
				if (!(mates[j].equals(this.username)))
					allmates.add(mates[j]);
			}
			
			if(allmates.size() < 100) {
				int ogsize = allmates.size();
				for(int j = 0; j < 100-ogsize; j++) {
					String randomuser = DataManipulate.random_data();
					if(!(randomuser.equals(this.username)))
						allmates.add(randomuser);
				}
			}
		}

		String[] strmates = new String[allmates.size()];
		
		for (int j = 0; j < allmates.size(); j++) {
			strmates[j] = allmates.get(j);
			String[] strthisanimes = DataManipulate.retrieve_data(strmates[j], "animeID").split(" ");
			String[] strthiseps = DataManipulate.retrieve_data(strmates[j], "episodes").split(" ");
			String[] strthisscore = DataManipulate.retrieve_data(strmates[j], "score").split(" ");

			int n = strthisanimes.length;

			int[] thiseps = new int[n];
			double[] thisscore = new double[n];

			for (int i = 0; i < n; i++) {
				thiseps[i] = Integer.parseInt(strthiseps[i]);
				thisscore[i] = Double.parseDouble(strthisscore[i]);
			}

			String thisloc = DataManipulate.retrieve_data(strmates[j], "location");
			//this.potMat.set(j, new User(strmates[j], strthisanimes, thiseps, thisscore, Integer.parseInt(thisloc)));
			this.potMat.add(new User(strmates[j], strthisanimes, thiseps, thisscore, Integer.parseInt(thisloc)));
			this.potMatCount++;
		}
		this.potMatCount = 0;
		sortPotMat();
	}

	/**
	 * compareUser is used to compare a user to the current user object to determine
	 * a compatibility value
	 * 
	 * @param that - User object that needs to be compared against this
	 * @return double - represents compatibility score
	 * @throws IOException - Input/Output error
	 */
	private double compareUser(User that) throws IOException {

		double totalDifference = 0.0;
		String[] thatAnimeList = that.getAnimeList();
		double[] thatScores = that.getScores();
		int count = 0;

		for (int i = 0; i < this.animeList.length; i++) {
			for (int j = 0; j < thatAnimeList.length; j++) {
				if (this.animeList[i].equals(thatAnimeList[j])) {
					// this computes the compatability score between the user and another user
					totalDifference += Math.abs((this.scores[i] - thatScores[j]));
					count++;
				}
			}
		}

		double compate = totalDifference / count + ShortPath.path(this.getLocation(), that.getLocation());

		return compate;
	}

	/**
	 * sortPotMat is used to sort the list of potential matches using compatibility
	 * scores This is a helper function
	 * 
	 * @throws IOException - Input/Output error
	 */
	private void sortPotMat() throws IOException {

		// Trying to sort potMat by the scores received by compareUser

		for (int i = 1; i < this.potMat.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (this.compareUser(this.potMat.get(j)) < this.compareUser(this.potMat.get(j - 1))) {
					User temp = this.potMat.get(j);
					this.potMat.set(j, this.potMat.get(j - 1));
					this.potMat.set(j - 1, temp);
				} else
					break;
			}
		}
	}

	/* ***************************************************** */
	/* ***************** ACCESSOR METHODS ****************** */
	/* ***************************************************** */

	/**
	 * getUser is a getter used to fetch username of user
	 * 
	 * @return string - represents user's username
	 */
	public String getUser() {
		return this.username; // Returns user name
	}

	/**
	 * getFriends is a getter used to fetch friends list of user
	 * 
	 * @return arrayList of users - represents user's friend list (liked users)
	 */
	public ArrayList<User> getFriends() {
		return this.friends; // Returns friends list
	}

	/**
	 * getLocation is a getter used to fetch location of user
	 * 
	 * @return integer - represents user's location
	 */
	public int getLocation() {
		return this.location; // Returns location
	}

	/**
	 * getAnimeList is a getter used to fetch list of anime that the user watched
	 * 
	 * @return array of strings - represents user's anime list
	 */
	public String[] getAnimeList() {
		return this.animeList; // Returns a list of watched animes (by anime IDS)
	}

	/**
	 * getEpisodes is a getter used to fetch the episode array corresponding to
	 * anime list of user
	 * 
	 * @return array of integers - represents user's watched episodes for each anime
	 */
	public int[] getEpisodes() {
		return this.eps;
	}

	/**
	 * getScores is a getter used to fetch scores that user gave their watched anime
	 * 
	 * @return array of doubles - represents user's scores array
	 */
	public double[] getScores() {
		return this.scores; // Returns a list of scores corresponding to animes in anime list
	}

	/**
	 * getPotMatUser is a getter used to fetch potential match of user
	 * 
	 * @return User - a single user that is a potential match
	 */
	public User getPotMatUser() {
		
		
		
		
		return this.potMat.get(this.potMatCount);
	}

	/**
	 * getPotMat is a getter used to fetch array of potential matches of the user
	 * 
	 * @return array of users - contains user's potential matches
	 */
	public ArrayList<User> getPotMat() {
		return this.potMat; // Gets list of potential mate (generated by data base).
	}

	/* ***************************************************** */
	/* ***************** MAIN TEST METHODS ***************** */
	/* ***************************************************** */

	/**
	 * main
	 * 
	 * @param args - array of strings
	 * @throws IOException  - Input/Output error
	 * @throws SQLException - database access error
	 */
	public static void main(String[] args) throws SQLException, IOException {

		String[] animeList1 = { "5680", "106", "122", "481", "75" };
		int[] eps1 = { 4, 2, 7, 5, 6 };
		double[] scores1 = { 4.0, 2.0, 7.0, 2.0, 3.0 };

		User yo = new User("yo", animeList1, eps1, scores1, 12);

		yo.fillPotential();

		ArrayList<User> hello = yo.getFriends();

		yo.swipe(true);

		for (int i = 0; i < hello.size(); i++) {
			if (hello.get(i) != null)
				System.out.println(hello.get(i));
		}

	}

}