package UserProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginAccount {

	private String user;
	private String password;
	
	public LoginAccount(String fileName) {
		
		// Don't worry about this the password and user name is ALWAYS stored locally

		File f = new File(fileName);
		Scanner s;
		try {
			s = new Scanner(f);
			String output = s.nextLine();

			String[] info = output.split(" ");

			this.user = info[0];
			this.password = info[1];

			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean check(String inputUser, String inputPass) {
		
		// This checks if the typed user name and password match

		if (inputUser.equals(this.user)) { // Condition input should come from front end
			
			if (inputPass.equals(this.password)) { // Condition input should come from front end
				System.out.println("Successful login!");
				return true;
			}

			else {
				System.out.println("Password is Incorrect!"); // Type password again!
				return false;
			}
		} 
		
		else {
			System.out.println("Username/Password is Incorrect!"); // Type password again!
			return false;
		}

	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		LoginAccount akila = new LoginAccount("Data/userAndPass.txt");
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Type in username");
		String user = input.next();
		System.out.println("Type in password");
		String password = input.next();
		
		input.close();
		
		akila.check(user, password);
	}

}