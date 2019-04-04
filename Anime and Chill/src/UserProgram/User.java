package UserProgram;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAlloc.AnimeData;
import DataAlloc.DataManipulate;
import Graphs.ShortPath;

public class User {

	private String username; // User name
	private String[] animeList; // List of ANIMES watched by user
	private int[] eps; // List of episodes watched per ANIME corresponding to animeList
	private double[] scores; // List of scores given by user per ANIME corresponding to animeList

	private ArrayList <User> friends = new ArrayList<User>(); // Users accepted friends

	private int friendCount = 0; // Keeps tracks of how many friends are in "friends

	private User[] potMat = new User[1000]; // Randomly filled potential mates
	private int potMatCount = 0;

	private int location;

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

	public void swipe(boolean verdict) {

		if (verdict) {
			this.friends.add(this.potMat[this.potMatCount]);

			this.friendCount++;
			this.potMatCount++;
		}

		else {
			this.potMatCount++;
		}
	}

	public void fillPotentialTest(User[] users) {
		for (int i = 0; i < users.length; i++) {
			this.potMat[i] = users[i];
		}
	}

	public void fillPotential() throws SQLException, IOException {

		this.potMatCount = 0;

		ArrayList<String> allmates= new ArrayList<String>();
		
		for(int i = 0; i < this.animeList.length; i++) {
			String[] mates = AnimeData.retrieve_data(this.animeList[i], "users").split(" ");
			for(int j = 0; j < mates.length; j++) {
				if(!(mates[i].equals(this.username)))
					allmates.add(mates[i]);
			}
		}
		
		String[] strmates = new String[allmates.size()];
		
		for(int j = 0; j< allmates.size(); j++) {
			strmates[j] = allmates.get(j);
			String[] strthisanimes = DataManipulate.retrieve_data(strmates[j], "animeID").split(" ");
			String[] strthiseps = DataManipulate.retrieve_data(strmates[j], "episodes").split(" ");
			String[] strthisscore = DataManipulate.retrieve_data(strmates[j], "score").split(" ");
			
			int n = strthisanimes.length;
			
			int[] thiseps = new int[n];
			double[] thisscore = new double[n];
			
			for(int i = 0; i < n; i++) {
				thiseps[i] = Integer.parseInt(strthiseps[i]);
				thisscore[i] = Double.parseDouble(strthisscore[i]);
			}
			
			String thisloc = DataManipulate.retrieve_data(strmates[j], "location");
			this.potMat[j] = new User(strmates[j], strthisanimes, thiseps, thisscore, Integer.parseInt(thisloc));
			
		}
		sortPotMat();
		
		
//		for (int i = 0; i < this.potMat.length; i++) {
//
//			String userNameRet = DataManipulate.random_data();
//
//			String[] animeListStr = DataManipulate.retrieve_data(userNameRet, "animeID").split(" ");
//			Integer[] animeList = new Integer[animeListStr.length];
//
//			for (int j = 0; j < animeList.length; j++) {
//				animeList[j] = Integer.parseInt(animeListStr[j]);
//			}
//
//			String[] epsStr = DataManipulate.retrieve_data(userNameRet, "episodes").split(" ");
//			int[] eps = new int[epsStr.length];
//
//			for (int j = 0; j < eps.length; j++) {
//				eps[j] = Integer.parseInt(epsStr[j]);
//			}
//
//			String[] scoresStr = DataManipulate.retrieve_data(userNameRet, "episodes").split(" ");
//			double[] scores = new double[scoresStr.length];
//
//			for (int j = 0; j < scores.length; j++) {
//				scores[j] = Integer.parseInt(scoresStr[j]);
//			}
//
//			int location = Integer.parseInt(DataManipulate.retrieve_data(userNameRet, "location"));
//
//			this.potMat[i] = new User(userNameRet, animeList, eps, scores, location);
//		}
//		sortPotMat();
	}

	private double compareUser(User that) throws IOException{

		double totalDifference = 0.0;
		String[] thatAnimeList = that.getAnimeList();
		double[] thatScores = that.getScores();
		int count = 0;

		for (int i = 0; i < this.animeList.length; i++) {
			for (int j = 0; j < thatAnimeList.length; j++) {
				if (this.animeList[i].equals(thatAnimeList[j])) {
					//this computes the compatability score between the user and another user
					totalDifference += Math.abs((this.scores[i] - thatScores[j]));
					count++;
				}
			}
		}
		
		double compate = totalDifference / count + ShortPath.path(this.getLocation(), that.getLocation());
		
		return compate;
	}

	private void sortPotMat() throws IOException{

		// Trying to sort potMat by the scores received by compareUser

		for (int i = 1; i < this.potMat.length; i++) {
			for (int j = i; j > 0; j--) {
				if (this.compareUser(this.potMat[j]) < this.compareUser(this.potMat[j - 1])) {
					User temp = this.potMat[j];
					this.potMat[j] = this.potMat[j - 1];
					this.potMat[j - 1] = temp;
				} else
					break;
			}
		}
	}

	/* ***************************************************** */
	/* ***************** ACCESSOR METHODS ****************** */
	/* ***************************************************** */

	public String getUser() {
		return this.username; // Returns user name
	}

	public ArrayList<User> getFriends() {
		return this.friends; // Returns friends list
	}

	public int getLocation() {
		return this.location; // Returns location
	}

	public String[] getAnimeList() {
		return this.animeList; // Returns a list of watched animes (by anime IDS)
	}

	public int[] getEpisodes() {
		return this.eps;
	}
	
	public double[] getScores() {
		return this.scores; // Returns a list of scores corresponding to animes in anime list
	}

	public User getPotMatUser() {
		return this.potMat[this.potMatCount];
	}

	public User[] getPotMat() {
		return this.potMat; // Gets list of potential mate (generated by data base).
	}

	/* ***************************************************** */
	/* ***************** MAIN TEST METHODS ***************** */
	/* ***************************************************** */

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