package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Word;

class WordTest {
	private String apple = "Apple"; 

	private char[] thorn = {'t', 'h', 'o', 'r', 'n'}; 

	@Test
	void validCharArrTest() {
		Word thornWord = new Word(thorn); 
		char[] expWord = {'T','H','O','R','N'}; 
		char[] fakeWord = {'N', 'O', 'T'}; 
		assertTrue(thornWord.compareTo(expWord)); 	
		assertFalse(thornWord.compareTo(fakeWord));  
	}
	
	@Test 
	void invalidCharArrTest() {
		char[] it = {'I','T'}; 
		Word itWord = new Word(it); 
		assertFalse(itWord.compareTo(it)); 
	}
	
	@Test
	void validStringTest() {
		Word appleWord = new Word(apple);
		char[] expWord = {'A','P','P','L','E'}; 
		char[] fakeWord = {'N', 'O', 'T', 'E','D'}; 
		char[] fakeWord2 = {'A', 'p', 'p', 'l','e'}; 
		assertTrue(appleWord.compareTo(expWord)); 	 	
		assertFalse(appleWord.compareTo(fakeWord)); 
		assertFalse(appleWord.compareTo(fakeWord2)); 


	}
	
	@Test 
	void invalidStringTest() {
		String system = "SYSTEM"; 
		Word systemWord = new Word(system); 
		char[] systemChar = {'S', 'Y', 'S', 'T', 'E', 'M'}; 
		assertFalse(systemWord.compareTo(systemChar)); 
	}
	
	@Test
	void compareWordsTest() { 
		Word thornWord = new Word(thorn); 
		Word appleWord = new Word(apple);
		char[] systemChar = {'S', 'Y', 'S', 'T', 'E', 'M'}; 
		Word invalidWord = new Word(systemChar); 
		assertFalse(thornWord.compareTo(appleWord)); 
		assertFalse(thornWord.compareTo(invalidWord)); 
		char[] appleArr = {'A','P','P','L','E'}; 
		Word appleWord2 = new Word(appleArr); 
		assertTrue(appleWord2.compareTo(appleWord)); 
		

	}

}
