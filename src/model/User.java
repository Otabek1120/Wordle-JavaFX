package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

// elodie and otabek

public class User implements Serializable {
	private String firstName;
	private String lastName;
	private String password;
	private int score;
	private int gamesPlayed;
	private int[] gamesWon;
	private int currStreak;
	private int maxStreak;

	public User(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		score = 0;
		gamesPlayed = 0;
		currStreak = 0;
		maxStreak = 0;
		gamesWon = new int[] { 0, 0, 0, 0, 0, 0 };
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int[] getGamesWon() {
		return gamesWon;
	}
	
	public void setGamesWon(int index) {
		gamesWon[index] += 1;
	}


	public void incGames() {
		gamesPlayed +=1 ;
		
	}
	
	public void incStreak() {
		currStreak += 1;
	}
	
	public int getStreak() {
		return currStreak;
	}
	
	public int getMaxStreak() {
		return maxStreak;
	}
	
	public void setMaxStreak(int currStreak) {
		maxStreak = currStreak ;
	}
	
	public void resetStreak() {
		currStreak = 0;
	}

	public int getScore() {
		return score;
	}
	
	public void addToScore(int add) {
		score += add;
	}
	/*
	public void addUser(User current) {
		allUsers.add(current);
	}
	*/
	public static ArrayList<User> readFromSer() {
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			FileInputStream rawBytes = new FileInputStream("users.ser"); // Read the .ser file just created
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			// Read the entire object from the file on disk. Casts required
			
			allUsers = (ArrayList<User>) inFile.readObject();
			inFile.close();

			System.out.println("User.java: All Users Before Add" + allUsers);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			
		} catch (IOException e) {
			System.out.println("Empty File");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
		}
		return allUsers;
	}
	
	public static void writeToSer(ArrayList<User> allUsers) {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream("users.ser");
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(allUsers);
			outFile.close();
		} 
		catch (IOException ioe) {
			System.out.println("Writing objects failed");
		}
	}
	

	public String toString() {
		return firstName +  " " + lastName;
	}

}
