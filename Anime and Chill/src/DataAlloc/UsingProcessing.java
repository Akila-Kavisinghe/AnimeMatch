package DataAlloc;

import java.util.Scanner;

import UserProgram.LoginAccount;
import UserProgram.User;
import processing.core.PApplet;
import processing.core.PImage;

public class UsingProcessing extends PApplet {

	PImage main_menu, main_bar, friends_list, login_screen, loading_screen;
	
	int currentScreen;

	int loginScreen = 0;
	int searchScreen = 1;
	int friendsScreen = 2;
	int locationScreen = 3;
	
	int escape = 0;

	public static void main(String[] args) {
		PApplet.main("DataAlloc.UsingProcessing");
	}

	public void settings() {
		size(1600 / 2, (960 / 2) + 38);
	}

	public void setup() {
		
		Integer[] animeList1 = {1, 2, 3, 4, 5};
		int[] eps1 = {1, 2, 3, 4, 5};
		double[] scores1 = {1.0, 2.0, 3.0, 4.0, 5.0};
		
		User Akila = new User("Akila Kavisinghe", animeList1, eps1, scores1, 12);
		
		loading_screen = loadImage("loading_screen.jpg");
		
		image(loading_screen, -410, -190);
		
		/* IMAGE LOADING */
		
		login_screen = loadImage("logo_anime.png");
		login_screen.resize(365, 189);
	
		main_menu = loadImage("main_menu.jpg");
		main_menu.resize(1600 / 2, 960 / 2);
		
		main_bar = loadImage("main_bar.jpg");
		main_bar.resize(820, 52);
		
		friends_list = loadImage("friends_list.jpg");
		friends_list.resize(1600 / 2, 960 / 2);
		
		/* ************* */

		System.out.println("hello");
		
		delay(500);
		
		System.out.println("hello");
		currentScreen = 0;
	}

	public void draw() {
		
		escape = 0;
		background(255);
		
		while(currentScreen == loginScreen) {
			
			boolean password = true;
			
			LoginAccount login = new LoginAccount("Data/userAndPass.txt");

			Scanner input = new Scanner(System.in);
			
			if(escape == 0) {
				background(255);
				System.out.println("RUNNING");
				image(login_screen, 200, 160);
				
				escape = 1;
			}
			
			while(password) {
				
				System.out.println("Type in username");
				String userType = input.next();
				System.out.println("Type in password");
				String passwordType = input.next();
				
				if(login.check(userType, passwordType)) {
					break;
				}
			}
			
			input.close();
			
			currentScreen = searchScreen;
			
		}
		
		escape = 0;
		background(255);
		
		while(currentScreen == searchScreen) {
			
			if(escape == 0) {
				image(main_menu, 0, 38);
				image(main_bar, 0, 0);
				
				escape = 1;
			}
			
		}
		
		escape = 0;
		background(255);
		
		while(currentScreen == friendsScreen){
			if(escape == 0) {
				background(255);
				image(friends_list, 0, 38);
				image(main_bar, 0, 0);
				escape = 1;
			}
			
			
		}
		
		escape = 0;
		background(255);
		
		while(currentScreen == locationScreen){
			if(escape == 0) {
				background(255);
				image(friends_list, 0, 38);
				image(main_bar, 0, 0);
				escape = 1;
			}
		}
	}
}

//main_menu = loadImage("main_menu.jpg");
//main_bar = loadImage("main_bar.jpg");
//
//main_menu.resize(1600 / 2, 960 / 2);
//main_bar.resize(820, 52);
//
//image(main_menu, 0, 38);
//image(main_bar, 0, 0);


//main_menu = loadImage("main_menu.jpg");
//main_bar = loadImage("main_bar.jpg");
//
//main_menu.resize(1600 / 2, 960 / 2);
//main_bar.resize(820, 52);
//
//image(main_menu, 0, 38);
//image(main_bar, 0, 0);

//PImage main_menu, main_bar;
//
//// Holds states of application:
//// login screen = 0, search = 1, friends = 2, chat = 3
//int[] STATES = { 0, 1, 2, 3 };
//boolean[] drawn_states = { false, false, false, false };
//int current_state = 1;
//
//void setup() {
//	fill(120, 50, 240);
//
//	main_menu = loadImage("main_menu.jpg");
//	main_bar = loadImage("main_bar.jpg");
//	main_menu.resize(width, height);
//	main_bar.resize(width, 90);
//
//	fullScreen();
//	Integer[] animeList1 = { 1, 2, 3, 4, 5 };
//	int[] eps1 = { 4, 2, 7, 5, 6 };
//	double[] scores1 = { 4.0, 2.0, 7.0, 2.0, 3.0 };
//
//	User yo = new User("yo", animeList1, eps1, scores1, "12");
//	yo.fillPotential();
//}
//
//void mousePressed() {
//	if (this.current_state == 1) {
//		fill(255, 0, 0);
//		ellipse(mouseX, mouseY, 100, 100);
//	}
//}
//
//void draw() {
//	if (!drawn_states[1]) {
//		image(main_menu, 0, 0);
//		image(main_bar, 0, 0);
//		for (boolean draw_state : this.drawn_states) {
//			draw_state = false;
//		}
//		drawn_states[1] = true;
//	}
//}

//void draw() {
//if (!drawn_states[1]) {
//	image(main_menu, 0, 0);
//	image(main_bar, 0, 0);
//	for (boolean draw_state : this.drawn_states) {
//		draw_state = false;
//	}
//	drawn_states[1] = true;
//}

//public static void main(String[] args) {
//	PApplet.main("DataAlloc.UsingProcessing");
//}
//
//public void settings() {
//	size(300, 300);
//}
//
//public void setup() {
//	fill(120, 50, 240);
//}
//
//public void draw() {
//	ellipse(width / 2, height / 2, second()/2, second()/2);
//}