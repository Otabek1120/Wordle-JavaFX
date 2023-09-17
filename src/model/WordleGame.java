package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view_controller.Keyboard;
import view_controller.LoginPane;

public class WordleGame {
	private WordList wordList;
	private Word word;
	private int wordIndex;
	private ArrayList<String> guessList;
	public String currentGuess = "";
	private String checkWord = "";
	public ArrayList<Character> greenChars = new ArrayList<Character>(); // characters that are in the word in the
																			// correct position
	public ArrayList<Character> yellowChars = new ArrayList<Character>(); // characters that are in the word in the
																			// incorrect position
	public ArrayList<Character> greyChars = new ArrayList<Character>(); // characters that are not in the word
	WordList possible;
	
	public WordleGame() {
		try {
			wordList = new WordList("valid_wordle_words.txt");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		guessList = new ArrayList<>();
		word = wordList.getRandomWord();
	}

	public WordList getWordList() {
		// TODO Auto-generated method stub
		return wordList;
	}

	public Word getWord() {
		return word;
	}

	public int getWordIndex() {
		return wordIndex;
	}

	public String makeAGuess(String guess) {
		checkWord = "";
		Word guessWord = new Word(guess);
		if (wordList.hasWord(guessWord)) {
			currentGuess = guess;
			guessList.add(guess);
			for (int i = 0; i < 5; i++) {
				if (guessWord.getWord()[i] == word.getWord()[i]) {
					checkWord += "!"; // guessed char correctly
					greenChars.add(word.getWord()[i]);

				} 
				else {
					checkWord += "P"; // placeholder for when we check for yellows and greens
				}
			}
			for (int i = 0; i < 5; i++) {
				if (checkWord.charAt(i) == 'P') {
					boolean hasCharElsewhere = false;
					for (int j = 0; j < 5; j++) {
						if (greenChars.contains(guessWord.getWord()[i])) { // this shows that there is already a green
							// char of this letter while one in the wrong position
							int totalvsGuessed = 0; // measures the difference in the chars of the letter in the word
							for (int x = 0; x < 5; x++) {
								if (word.getWord()[x] == guessWord.getWord()[i]) {
									totalvsGuessed += 1;
								}
								if (checkWord.charAt(x) == guessWord.getWord()[i]) {
									totalvsGuessed -= 1;
								}
							}
							if (totalvsGuessed > 1) { // if there are more letters in the word than what are already in
													  // the right position, it shows as yellow instead of grey
								hasCharElsewhere = true;
							}

						} else if (guessWord.getWord()[i] == word.getWord()[j]) {
							hasCharElsewhere = true;
						}

					}
					if (hasCharElsewhere) {
						checkWord = checkWord.substring(0, i) + '*' + checkWord.substring(i + 1);
						// checkWord += "*"; //char exists, just in the wrong position
						yellowChars.add(guessWord.getWord()[i]);
					} else {
						greyChars.add(guessWord.getWord()[i]);
						checkWord = checkWord.substring(0, i) + 'x' + checkWord.substring(i + 1);
					}
				}
			}
			
			
			
			
			if (isComplete()) {
				endGame();
			}
			return checkWord;
		} else {
			return "Error: word not valid";
		}

	}

	public int guesses() {
		return guessList.size();
	}

	public String getGuess() {
		return currentGuess;
	}

	public boolean isComplete() {
		User current = LoginPane.getCurrent();
		if (isWon()) {
			current.incGames();
			current.incStreak();
			if (current.getStreak() > current.getMaxStreak()) {
				current.setMaxStreak(current.getStreak());
			}
			return true;
		}
		else if (guessList.size() == 6) { //loss
			current.incGames();
			current.resetStreak();
			return true;
		} 
		return false;
	}

	public boolean isWon() {
		return word.compareTo(new Word(currentGuess));
	}

	void endGame() {
		String path;
		if (isWon()) {
			path = "winner.mp3";
		}
		else {
			path = "loser.mp3";
		}

		// Need a File and URI object so the path works on all OSs
		File file = new File(path);
		URI uri = file.toURI();
		System.out.println(uri);
		// Play one mp3 and and have code run when the song ends
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

	}

	public ArrayList<String> getGuessList() {
		return this.guessList;
	}

}
