/*
 * A class representing a collection of words 
 * that can be used in the game. This class might 
 * have properties such as an array or ArrayList 
 * of Word objects, methods to add or remove words 
 * from the list, and methods to choose a random 
 * word from the list.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WordList {
	private ArrayList<Word> words;

	// Constructor, making it empty
	public WordList() {
		this.words = new ArrayList<Word>(); 
	}


	// Constructor from filename
	public WordList(String filename) throws FileNotFoundException {
		this.words = new ArrayList<Word>();
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				Word word = new Word(scanner.nextLine().strip());
				this.words.add(word);
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} 
	
	public void addWord(Word newWord) {
		words.add(newWord); 
	}
	
	public Word getRandomWord() {
		int wordIndex = (int) Math.round(Math.random() * words.size());
		return words.get(wordIndex);
	}
	
	public boolean hasWord(Word thisWord) {
		if (words.contains(thisWord)) {
			return true; 
		}
		for (int i=0; i<words.size(); i++) { 
			if (words.get(i).compareTo(thisWord)) {
				return true; 	
			}
		}
		return false; 
	}
	
	public void removeWord(Word oldWord) {
		for (int i=0; i<words.size(); i++) {
			if (words.get(i).compareTo(oldWord)) {
				words.remove(i); 
			}
		}
		
	}

}
