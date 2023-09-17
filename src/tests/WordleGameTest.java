package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.WordleGame;

public class WordleGameTest {

	@Test
	public void constructorTest() {
		WordleGame game = new WordleGame();
		//assertEquals(14855, game.getWordList().size());
		//assertEquals(game.getWord(), game.getWordList().get(game.getWordIndex()));
	}

}
