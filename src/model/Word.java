/*
 * A class representing a single five-letter word 
 * that can be used as the secret word in the game. 
 * This class might have properties such as the word 
 * itself, a method to determine if a guess matches 
 * the word, and a method to provide feedback on the 
 * correctness of a guess.
 */
package model;


public class Word {
	private char[] word; 

	//constructor with char array
	public Word(char[] w) {
		if (w.length != 5) {
			// System.out.println("ERROR: Word must be 5 letters"); 
			return; 
		}
		this.word = w; 
		makeUpperCase(); 
	}
	
	//constructor with strings 
	public Word(String s) {
		if (s.length() != 5) {
			// System.out.println("ERROR: Word must be 5 letters"); 
			return; 
		}
		
		char[] c = {s.charAt(0), s.charAt(1), s.charAt(2), s.charAt(3), s.charAt(4)}; 
		this.word = c; 
		makeUpperCase(); 
	}
	
	public String toString() {
		String theString = ""; 
		for (int i=0; i<5; i++) {
			theString += word[i]; 
		}
		return theString; 
	}
	
	public char[] getWord() {
		return this.word; 
	}
	

	private void makeUpperCase() {
		for (int i=0; i<5; i++) {
			this.word[i] = Character.toUpperCase(this.word[i]);  
		}
	}
	
	//returns true if they are the same word, false otherwise
	public boolean compareTo(Word compared) {
		if (word == null || compared.getWord() == null) {
			return false; 
		}
		return compareTo(compared.getWord()) ;
	}
	
	//returns true if they are the same word, false otherwise
	public boolean compareTo(char[] compared) {
		if (word == null || compared == null || compared.length != 5) {
			return false; 
		}
		for (int i=0; i<5; i++) {
			if (Character.compare(word[i], compared[i]) != 0) {
				return false; 
			}
		}
		return true; 
	}
		
		
	}


