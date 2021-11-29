package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
	ArrayList<String> usernames;
	ArrayList<String> passwords;

	// loads users from the passed file
	public void loadUsers(String file) throws IOException {
		// gets the directory of the data folder and saves the string to file
		//file = System.getProperty("user.dir") + "\\data\\" + file;
		file = System.getProperty("user.dir") + "\\" + file;
		setUsers(this.usernames);
		setPass(this.passwords);
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			// splits the line according to the commas and saves the words to an array
			String[] words = line.split(",");
			usernames.add(words[0]);
			passwords.add(words[1]);
			;
		}
		br.close();
	}

	// getters and setters
	public void setUsers(ArrayList<String> list) {
		this.usernames = new ArrayList<>();
	}

	public void setPass(ArrayList<String> list) {
		this.passwords = new ArrayList<>();
	}

	public ArrayList<String> getUsernames() {
		return usernames;
	}

	public ArrayList<String> getPasswords() {
		return passwords;
	}

	public void addUsernames(String Users) {
		usernames.add(Users);
	}

}
