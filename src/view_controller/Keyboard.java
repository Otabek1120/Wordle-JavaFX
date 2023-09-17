package view_controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.User;
import model.WordleGame;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

//import java.awt.Color;
import java.util.ArrayList;

// import java.awt.Label;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class Keyboard extends GridPane {
	private GuessGrid guessGrid;
	private WordleGame game;
	private String guess = "";
	private static User currPlayer;

	private String[][] keyboardLayout = { { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
			{ "A", "S", "D", "F", "G", "H", "J", "K", "L" }, { "ENTER", "Z", "X", "C", "V", "B", "N", "M", "BACK" }, };
	private GridPane keyboardPane;

	Button buttonQ = new Button("Q");
	Button buttonW = new Button("W");
	Button buttonE = new Button("E");
	Button buttonR = new Button("R");
	Button buttonT = new Button("T");
	Button buttonY = new Button("Y");
	Button buttonU = new Button("U");
	Button buttonI = new Button("I");
	Button buttonO = new Button("O");
	Button buttonP = new Button("P");

	Button buttonA = new Button("A");
	Button buttonS = new Button("S");
	Button buttonD = new Button("D");
	Button buttonF = new Button("F");
	Button buttonG = new Button("G");
	Button buttonH = new Button("H");
	Button buttonJ = new Button("J");
	Button buttonK = new Button("K");
	Button buttonL = new Button("L");

	Button buttonENTER = new Button("ENTER");
	Button buttonZ = new Button("Z");
	Button buttonX = new Button("X");
	Button buttonC = new Button("C");
	Button buttonV = new Button("V");
	Button buttonB = new Button("B");
	Button buttonN = new Button("N");
	Button buttonM = new Button("M");
	Button buttonBACK = new Button("BACK");

	// Label guessLabel = new Label("Word: ");

	Button[][] keyboardButtons = {
			{ buttonQ, buttonW, buttonE, buttonR, buttonT, buttonY, buttonU, buttonI, buttonO, buttonP },
			{ buttonA, buttonS, buttonD, buttonF, buttonG, buttonH, buttonJ, buttonK, buttonL },
			{ buttonENTER, buttonZ, buttonX, buttonC, buttonV, buttonB, buttonN, buttonM, buttonBACK } };

	public Keyboard(GuessGrid guessGrid, WordleGame game) {
		this.guessGrid = guessGrid;
		this.game = game;

		keyboardPane = new GridPane();
		keyboardPane.setPadding(new Insets(10));
		keyboardPane.setHgap(5);
		keyboardPane.setVgap(5);
		for (int i = 0; i < 20; i++) {
			keyboardPane.getColumnConstraints().add(new ColumnConstraints(20));
		}

		for (int i = 0; i < keyboardLayout.length; i++) {
			for (int j = 0; j < keyboardLayout[i].length; j++) {
				Button button = keyboardButtons[i][j];
				button.setFocusTraversable(isDisabled());
				button.setMinWidth(45);
				button.setMinHeight(40);
				if (i == 0) {
					keyboardPane.add(button, 2 * j, i);
					GridPane.setColumnSpan(button, 2);
				} else if (i == 1) {
					keyboardPane.add(button, 2 * j + 1, i);
					GridPane.setColumnSpan(button, 2);
				} else if (i == 2) {
					if (j == 0) {
						keyboardPane.add(button, 2 * j, i);
						GridPane.setColumnSpan(button, 3);
						button.setMinWidth(70);
					} else if (j == 8) {
						keyboardPane.add(button, 2 * j + 1, i);
						GridPane.setColumnSpan(button, 3);
						button.setMinWidth(70);
					} else {
						keyboardPane.add(button, 2 * j + 1, i);
						GridPane.setColumnSpan(button, 2);
					}
				}
				keyboardButtons[i][j].setOnAction(new MoveHandler());
			}
		}

	}

	public void setCurrPlayer(User player) {
		this.currPlayer = player;
	}

	public static User getCurrPlayer() {
		return currPlayer;
	}

	private class MoveHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Button buttonClicked = (Button) event.getSource();
			System.out.println(buttonClicked.getText() + " clicked");
			if (buttonClicked == buttonENTER) {
				if (guess.length() == 5) {
					String checkWord = game.makeAGuess(guess);
					ArrayList<String> guessList = game.getGuessList();
					if (checkWord.equals("Error: word not valid")) {
						guessGrid.colorCodeRow(checkWord);
					} else {
						guessGrid.colorCodeRow(checkWord);
						guessGrid.moveToNextRow();
						guess = "";
						if (checkWord.equals("!!!!!")) {
							System.out.println("CurrPlayer: " + currPlayer.getName());
							if (guessList.size() == 1) {
								currPlayer.setGamesWon(0);
								currPlayer.addToScore(6);
							} else if (guessList.size() == 2) {
								currPlayer.setGamesWon(1);
								currPlayer.addToScore(5);
							} else if (guessList.size() == 3) {
								currPlayer.setGamesWon(2);
								currPlayer.addToScore(4);
							} else if (guessList.size() == 4) {
								currPlayer.setGamesWon(3);
								currPlayer.addToScore(3);
							} else if (guessList.size() == 5) {
								currPlayer.setGamesWon(4);
								currPlayer.addToScore(2);
							} else if (guessList.size() == 6) {
								currPlayer.setGamesWon(5);
								currPlayer.addToScore(1);
							}
							for (Button[] buttonRow : keyboardButtons) {
								for (Button button : buttonRow) {
									button.setDisable(true);
								}
							}
						} 
						else if (guessList.size() == 6) {
							for (Button[] buttonRow : keyboardButtons) {
								for (Button button : buttonRow) {
									button.setDisable(true);
								}
							}
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setHeaderText("Nice try!");
							alert.setContentText("The secret word was " + game.getWord());
							alert.setTitle(null);
							alert.showAndWait();
						}

					}
				}

				// testing
				//System.out.println("checkWord: " + checkWord);
				//System.out.println("GuessList: " + guessList);
				System.out.println("SecretWord: " + game.getWord());

			} else if (buttonClicked == buttonBACK) {
				if (guess.length() > 0) {
					guess = guess.substring(0, guess.length() - 1);
					guessGrid.removeLetter();
				}
			} else {
				if (guess.length() < 5) {
					String letter = buttonClicked.getText();
					guess += letter;
					// pass the letter to the GuessGrid
					guessGrid.setNextLetter(letter);
				}
			}
			// FIXME: Guess here should be stored in wordlegame.guess
			updateButtonColors();
			System.out.println("Guess: " + guess);

		}
	}

	private void updateButtonColors() {
		for (int i = 0; i < keyboardLayout.length; i++) {
			for (int j = 0; j < keyboardLayout[i].length; j++) {
				if (keyboardButtons[i][j].getText() != "ENTER" && keyboardButtons[i][j].getText() != "BACK") {
					if (game.greenChars.contains(keyboardButtons[i][j].getText().charAt(0))) {
						keyboardButtons[i][j].setStyle("-fx-background-color: #8fc941");
					} else if (game.yellowChars.contains(keyboardButtons[i][j].getText().charAt(0))) {
						keyboardButtons[i][j].setStyle("-fx-background-color: #ffb000");
					} else if (game.greyChars.contains(keyboardButtons[i][j].getText().charAt(0))) {
						keyboardButtons[i][j].setStyle("-fx-background-color: #9e9fb4");
					} else {
						keyboardButtons[i][j].setStyle(null);
					}
				}
			}
		}
	}

	public String getGuess() {
		return guess;
	}

	public GridPane getPane() {
		return this.keyboardPane;
	}

	public String[][] getKeyboardLayout() {
		return this.keyboardLayout;
	}

	public Button[][] getKeyboardButtons() {
		return this.keyboardButtons;
	}

	public void enableButtons() {
		for (Button[] buttonRow : keyboardButtons) {
			for (Button button : buttonRow) {
				button.setDisable(false);
			}
		}
	}

	public void startNewGame() {
		enableButtons();
		game = new WordleGame();
		guessGrid.clearLetters();
		updateButtonColors();
		guessGrid.createGrid(Color.WHITE);
		game.greenChars = new ArrayList<>();
		game.yellowChars = new ArrayList<>();
		game.greyChars = new ArrayList<>();
		updateButtonColors();
		guess = "";
	}
}
