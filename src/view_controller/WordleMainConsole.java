package view_controller;

import model.WordleGame;
import java.util.Scanner;

public class WordleMainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome to Wordle! In this console version, an 'x' indicates that the letter is not in the word, a \n"
							+ "'*' indicates it is but is in the wrong position, and an '!' indicates that it is in both the correct\n"
							+ " word and position. You have 6 tries"); 
	    WordleGame wordleGame = new WordleGame(); 
	    int guessNumber = 1; 
	    Scanner scanner = new Scanner(System.in);  
	    while (!wordleGame.isComplete()) {
	    	System.out.println("Enter guess:"); 
	    	String curGuess = scanner.nextLine(); 
	    	String response =  wordleGame.makeAGuess(curGuess); 
	    	System.out.println(response); 
	    	//System.out.println("Green letters: " + wordleGame.greenChars.toString() + "\nYellow Letters: " 
	    	//+ wordleGame.yellowChars.toString() + "\nIncorrect Letters: " + wordleGame.greyChars.toString()); 

	    }
	    if (wordleGame.isWon()) {
	    	System.out.println("Congratulations, you win!"); 
	    }
	    else {
	    	System.out.println("Good game. The correct word was " + wordleGame.getWord().toString()) ;
	    }
	   }

}
