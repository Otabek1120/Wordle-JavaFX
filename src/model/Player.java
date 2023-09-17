package model;

import java.io.Serializable;

/*
 * A class representing a player of the game. 
 * This class might have properties such as 
 * the player's name, account details, game history.
 */

public class Player implements Serializable {
	private String firstName;
	private String lastName;
	private int score;

	public Player(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = 0;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void incrementScore() {
		this.score++;
	}

	public int getScore() {
		return this.score;
	}

}
