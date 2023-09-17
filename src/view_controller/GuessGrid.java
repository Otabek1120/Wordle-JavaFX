package view_controller;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GuessGrid extends GridPane {
	private String[][] letters = new String[6][5];
	private int tries = 6;
	private int wordWidth = 5;
	private int currRow = 0;
	private int currCol = 0;

	private GridPane grid = new GridPane();

	public GuessGrid() {
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);

		Label title = new Label("GuessGrid");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		title.setTextFill(Color.valueOf("#f1356d"));
		grid.add(title, 3, 0);

		createGrid(Color.WHITE);
	}

	public void createGrid(Color white) {
		for (int i = 0; i < tries; i++) {
			for (int j = 0; j < wordWidth; j++) {
				StackPane cell = new StackPane();
				Rectangle cellBg = new Rectangle(50, 50);
				cellBg.setFill(white); // bg color
				cellBg.setStroke(Color.BLACK);
				cell.getChildren().add(cellBg);
				Text cellText = new Text(letters[i][j]);
				cellText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
				cell.getChildren().add(cellText);
				grid.add(cell, j + 1, i + 1);
			}
		}
	}


	public void setNextLetter(String letter) {
		letters[currRow][currCol] = letter;
		currCol++;
		updateRow(Color.rgb(245, 245, 201), Color.BLACK, Color.BLACK);
	}

	public void removeLetter() {
		currCol -= 1; 
		letters[currRow][currCol] = " ";
		
		updateRow(Color.rgb(245, 245, 201), Color.BLACK, Color.BLACK);

	}
	
	private void updateRow(Color bgColor, Color cellColor, Color letterColor) {
		System.out.println("UPDATE ROW");
		for (int j = 0; j < wordWidth; j++) {
			StackPane cell = new StackPane();
			Rectangle cellBg = new Rectangle(50, 50);
			cellBg.setFill(bgColor); // bg color
			cellBg.setStroke(letterColor); // cell fill color
			cell.getChildren().add(cellBg);
			Text cellText = new Text(letters[currRow][j]);
			cellText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			cellText.setFill(letterColor); // letter color
			cell.getChildren().add(cellText);
			grid.add(cell, j + 1, currRow + 1);
		}
		
	}
	
	

	public void moveToNextRow() {
		currRow++;
		currCol = 0;

	}
	
	/*
	 * red 		--> invalid word or letter
	 * green 	--> correct letter and position
	 * orange 	--> correct letter, but not position
	 * gray 	--> neither correct letter, not position
	 */
	public void colorCodeRow(String checkWord) {
		if (checkWord.equals("Error: word not valid")) {
			// TODO: show the error on a pop-up window
			updateRow(Color.WHITE, Color.RED, Color.RED);

		} else {
			for (int j = 0; j < wordWidth; j++) {
				StackPane cell = new StackPane();
				Rectangle cellBg = new Rectangle(50, 50);
				if (checkWord.charAt(j) == '!') {
					cellBg.setFill(Color.rgb(143, 201, 65)); // bg color
					cellBg.setStroke(Color.BLACK);
				} else if (checkWord.charAt(j) == '*') {
					cellBg.setFill(Color.rgb(255, 176, 0));
					cellBg.setStroke(Color.BLACK);
				} else {
					cellBg.setFill(Color.rgb(158, 159, 180));
					cellBg.setStroke(Color.BLACK);
				}
				cell.getChildren().add(cellBg);
				Text cellText = new Text(letters[currRow][j]);
				cellText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
				cell.getChildren().add(cellText);
				grid.add(cell, j + 1, currRow + 1);

			}

		}

	}
	public void clearLetters() {
		letters = new String[6][5];
		currRow = 0;
		currCol = 0;
	}
	
	public GridPane getPane() {
		return this.grid;
	}
}
