package DataAlloc;

import java.util.Random;
import java.util.Scanner;

import UserProgram.LoginAccount;
import UserProgram.SearchBar;
import UserProgram.User;
import processing.core.PApplet;
import processing.core.PImage;

public class UsingProcessing extends PApplet {

	PImage main_menu, main_bar, friends_list, login_screen, loading_screen, talk_screen;
	int currentScreen = 0;
	LoginAccount login = new LoginAccount("Data/userAndPass.txt");
	Scanner input = new Scanner(System.in);
	User Akila;
	PImage[] profilePictures = new PImage[5];
	Random rand = new Random();
	int random = rand.nextInt(4);

	public static void main(String[] args) {
		PApplet.main("DataAlloc.UsingProcessing");
	}

	public void settings() {
		size(800, 480);
//		fullScreen();
	}

	
	public void setup() {
		Integer[] animeList1 = { 1, 2, 3, 4, 5 };
		int[] eps1 = { 1, 2, 3, 4, 5 };
		double[] scores1 = { 1.0, 2.0, 3.0, 4.0, 5.0 };
		Akila = new User("Akila Kavisinghe", animeList1, eps1, scores1, 12);

		Integer[] animeList = { 5, 1, 2, 3, 4 };
		int[] eps = { 1, 2, 4, 3, 2 };
		double[] scores = { 5.0, 2.0, 3.0, 1.0, 7.0 };

		User bobby = new User("bobby", animeList, eps, scores, 2);

		Integer[] animeList11 = { 5, 1, 2, 4, 5 };
		int[] eps11 = { 1, 2, 4, 3, 5 };
		double[] scores11 = { 2.0, 6.0, 3.1, 5.0, 1.5 };

		User eric = new User("behhh", animeList11, eps11, scores11, 4);

		Integer[] insAnimes = { 5, 2, 4, 3, 1 };
		int[] insEps = { 12, 234, 97, 82, 72 };
		double[] insScores = { 8.3, 9.4, 4.2, 9.5, 3.7 };

		User oleg = new User("oleg", insAnimes, insEps, insScores, 7);

		Integer[] insAnimes1 = { 5, 2, 4, 3, 1 };
		int[] insEps1 = { 672, 40, 273, 38, 38 };
		double[] insScores1 = { 9.2, 8.5, 3.7, 7.9, 8.2 };

		User billy = new User("billy", insAnimes1, insEps1, insScores1, 10);

		User[] users = { bobby, eric, oleg, billy };
		background(255);

		loading_screen = loadImage("loading_screen.jpg");
		loading_screen.resize(width, height);
		image(loading_screen, 0, 0);
		
		delay(7000);

		/* IMAGE LOADING */
		login_screen = loadImage("logo_anime.png");
		login_screen.resize(width / 2 + 20, height / 2);

		background(255);
		imageMode(CENTER);
		image(login_screen, width / 2, height / 2);

		main_menu = loadImage("main_menu.jpg");
		main_menu.resize(width, height);

		main_bar = loadImage("main_bar.jpg");
		main_bar.resize(width, 52);
		
		talk_screen = loadImage("chat_room.jpg");
		talk_screen.resize(width, height);

		friends_list = loadImage("friends_list.jpg");
		friends_list.resize(width, height - 52);
		Akila.fillPotentialTest(users);

		for (int i = 0; i < 4; i++) {
			profilePictures[i] = loadImage("profile" + (i + 1) + ".jpg");
			profilePictures[i].resize(220, 220);
		}
	}

	private void login() {
		boolean validpass = false;
		if (!validpass) {
			System.out.println("Type in username");
			String username = input.next();
			System.out.println("Type in password");
			String password = input.next();
			if (login.check(username, password)) {
				validpass = true;
				currentScreen = 1;
			}
		}
	}

	public void mousePressed() {
		if (currentScreen > 0) {
			if (60 < mouseX && mouseX < width / 3 && mouseY < 52) {
				currentScreen = 1;
			}
			if (width / 3 + 20 < mouseX && mouseX < 2 * width / 3 - 40 && mouseY < 52) {
				currentScreen = 2;
			}
			if (2 * width / 3 < mouseX && mouseX < width - 30 && mouseY < 52) {
				currentScreen = 3;
			}
			if (currentScreen == 1) {
				if (mouseX < 200 && mouseY > 52) {
					System.out.println(Akila.getPotMatUser().getUser());
					Akila.swipe(false);
					random = rand.nextInt(4);
				}
				if (mouseX > width - 200 && mouseY > 52) {
					System.out.println("Like!");
					Akila.swipe(true);
					random = rand.nextInt(4);
				}
			}
			
			if (currentScreen == 2) {
				if (mouseX < 200 && mouseY > 52) {
					search();
				}
			}
		}
	}

	public void draw() {
		if (currentScreen == 0) {
			image(login_screen, width / 2, height / 2);
			login();
		} else if (currentScreen == 1) {
			image(main_menu, width / 2, height / 2);
			image(main_bar, width / 2, 26);
			fill(0, 0, 0, 150);
			noStroke();
			rectMode(CENTER);
			rect(width / 2, height / 2 - 35, 250, 250);
			rect(width / 2, height - 95, 250, 100);

			User potMatUser = Akila.getPotMatUser();

			String userName = potMatUser.getUser();
			String userAnimeList = potMatUser.getAnimeList()[0] + ", " + potMatUser.getAnimeList()[1] + ", "
					+ potMatUser.getAnimeList()[2];
			String userCity = "" + city.values()[potMatUser.getLocation()];

			textSize(24);
			textAlign(CENTER);
			fill(255);
			text(userName, width / 2, height - 120);
			text(userAnimeList, width / 2, height - 96);
			text(userCity, width / 2, height - 72);
			image(profilePictures[random], width / 2, height / 2 - 35);
		} else if (currentScreen == 2) {
			image(friends_list, width / 2, height / 2 + 26);
			image(main_bar, width / 2, 26);
			
		} else if (currentScreen == 3) {
			image(talk_screen, width / 2, height / 2);
			image(main_bar, width / 2, 26);
		}
	}
	
	public void search() {
		SearchBar findSearch = new SearchBar(Akila.getFriends());
		System.out.println("Search for someone: ");
		String searcher = input.next();
		findSearch.search(searcher);
		findSearch.populate();
		
		System.out.println("\nSearch Results");
		
		for (int i = 0; i < findSearch.getString1().length; i++) {
			if(findSearch.getString1()[i].equals(" ")){
				break;
			}
			else {
				System.out.print(findSearch.getString1()[i] + "|| ");
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < findSearch.getString2().length; i++) {
			if(findSearch.getString2()[i].equals(" ")){
				break;
			}
			else {
				System.out.print(findSearch.getString2()[i] + "|| ");
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < findSearch.getString3().length; i++) {
			if(findSearch.getString3()[i].equals(" ")){
				break;
			}
			else {
				System.out.print(findSearch.getString3()[i] + "|| ");
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < findSearch.getString4().length; i++) {
			if(findSearch.getString4()[i].equals(" ")){
				break;
			}
			else {
				System.out.print(findSearch.getString4()[i] + "|| ");
			}
		}
		
		System.out.println();
	}
	
}