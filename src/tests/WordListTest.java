package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import model.Word;
import model.WordList;

class WordListTest {

	@Test
	void WordListFromWords() {
		Word apple = new Word("apple");   
		Word thorn = new Word("Thorn"); 
		Word water = new Word ("water"); 
		WordList a = new WordList(); 
		assertFalse(a.hasWord(apple)); 
		a.addWord(apple);
		a.addWord(thorn);
		a.addWord(water);
		assertTrue(a.hasWord(apple));  
		assertTrue(a.hasWord(thorn)); 
		assertTrue(a.hasWord(water)); 
		a.removeWord(water);
		assertFalse(a.hasWord(water)); 
	}
	
	@Test
	void WordListFromFile() throws FileNotFoundException {
		Word acted = new Word ("acted"); 
		WordList a = new WordList("valid_wordle_words.txt");
		assertTrue(a.hasWord(acted)); 
		a.removeWord(acted);
		assertFalse(a.hasWord(acted)); 
		
		
	}

}
