package view_controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

public class Accessories extends GridPane {
	private GridPane grid = new GridPane();
	//private BorderPane leaderPane;
	//private LeaderBoard leaderboardPane = new LeaderBoard();
	private User currPlayer;
	

	public Accessories() {
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);

		ImageView settingsImg = new ImageView("documents/settings.png");
		settingsImg.setFitWidth(40);
		settingsImg.setFitHeight(40);

		ImageView statsImg = new ImageView("documents/stats.png");
		statsImg.setFitWidth(40);
		statsImg.setFitHeight(40);

		ImageView helpImg = new ImageView("documents/help.png");
		helpImg.setFitWidth(40);
		helpImg.setFitHeight(40);

		ImageView leaderboardImg = new ImageView("documents/leaderboard.jpeg");
		leaderboardImg.setFitWidth(40);
		leaderboardImg.setFitHeight(40);

		Button help = new Button("", helpImg);
		help.setFocusTraversable(false);
		Button stats = new Button("", statsImg);
		stats.setFocusTraversable(false);
		Button settings = new Button("", settingsImg);
		settings.setFocusTraversable(false);
		Button leaderboard = new Button("", leaderboardImg);
		leaderboard.setFocusTraversable(false);

		grid.add(help, 0, 0);
		grid.add(stats, 1, 0);
		grid.add(settings, 2, 0);
		grid.add(leaderboard, 3, 0);

		help.setOnAction((event) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			GridPane helpPane = new GridPane();
			alert.getDialogPane().setContent(helpPane);
			alert.setTitle(null);
			helpPane.add(new Label("How To Play"), 0, 0);
			helpPane.add(new Label("Guess the Wordle in 6 or less tries to win!"), 0, 1);
			helpPane.add(new Label("- Each guess must be a valid 5 letter word"), 0, 2);
			helpPane.add(new Label("     - Submitting an invalid word will result in a red border, delete the word and try again."), 0, 3);
			helpPane.add(new Label("- The color of the tiles will change to show how close your guess is to the target"), 0, 4);
			helpPane.add(new Label("     - An orange tile represents a correct letter in an incorrect spot"), 0, 5);
			helpPane.add(new Label("     - A green tile represents a letter in the correct spot"), 0, 6);
			helpPane.add(new Label("- Start a new game at any time by clicking the button on the bottom right, unlimited Wordles 24/7!"), 0, 7);
			alert.getDialogPane().setHeaderText(null);
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
			alert.showAndWait();

		});

		stats.setOnAction((event) -> {
			currPlayer = Keyboard.getCurrPlayer();
			Alert alert = new Alert(AlertType.INFORMATION);
			GridPane statsPage = new GridPane();
			alert.getDialogPane().setContent(statsPage);
			alert.setTitle(null);
			if (currPlayer != null) {
				statsPage.add(new Label("Statistics"), 0, 0);
				statsPage.add(new Label("Games Played: " + currPlayer.getGamesPlayed()), 1 ,1);
				int [] wins = currPlayer.getGamesWon();
				int totalWins = 0;
				for (int i = 0; i < 6; i++) {
					totalWins += wins[i];
				}
				int totalGames = currPlayer.getGamesPlayed();
				double winPer;
				if (totalGames== 0) {
					winPer = 0;
				}
				else {
					winPer = Math.floor(((double) totalWins / (double)totalGames) * 100);
				}
				statsPage.add(new Label("Current Streak: " + currPlayer.getStreak()), 1 ,2);
				statsPage.add(new Label("Max Streak: " + currPlayer.getMaxStreak()), 1 ,3);
				statsPage.add(new Label("Win %: " + winPer), 1 ,4);
				statsPage.add(new Label("Score: " + currPlayer.getScore()), 1, 5);
				statsPage.add(new Label("Guess Distribution"), 0, 6);
				statsPage.add(new Label("1: " + wins[0]), 1, 7);
				statsPage.add(new Label("2: " + wins[1]), 1, 8);
				statsPage.add(new Label("3: " + wins[2]), 1, 9);
				statsPage.add(new Label("4: " + wins[3]), 1, 10);
				statsPage.add(new Label("5: " + wins[4]), 1, 11);
				statsPage.add(new Label("6: " + wins[5]), 1, 12);
			}
			else {
				statsPage.add(new Label ("Create an account to record your game statistics!"), 0, 0);
			}
			alert.getDialogPane().setHeaderText(null);
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
			alert.showAndWait();
		});

		settings.setOnAction((event) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			GridPane settingsPage = new GridPane();
			BorderPane main = MainGUI.getMainPane();
			settingsPage.setHgap(10);
			alert.getDialogPane().setContent(settingsPage);
			alert.setTitle(null);

			String newStyle = "";
			Button hard = new Button("OFF");
			if (main.getStyle().contains("#FFFFFF")) {
				newStyle = "DARK";
			}
			else {
				newStyle = "LIGHT";
			}
			Button theme = new Button(newStyle);
			settingsPage.add(new Label("Settings"), 0, 0);
			//settingsPage.add(new Label("Hard Mode"), 0, 1);
			//settingsPage.add(hard, 1, 1);
			settingsPage.add(new Label("Theme"), 0, 2);
			settingsPage.add(theme, 1, 2);

			hard.setOnAction((difficultyEvent) -> {
				// to implement
			});

			theme.setOnAction((themeEvent) -> {
				if (theme.getText().equals("DARK")) {
					main.setStyle("-fx-background-color: #4F504E;");
					theme.setText("LIGHT");
				} else {
					main.setStyle("-fx-background-color: #FFFFFF;");
					theme.setText("DARK");
				}
			});

			alert.getDialogPane().setHeaderText(null);
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
			alert.showAndWait();
		});

		leaderboard.setOnAction((event) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			ArrayList<User> allPlayers = User.readFromSer();
			User current = LoginPane.getCurrent();
			int currentExisting = -1;
			for (User existing: allPlayers) {
				if (current != null && existing.getName().equals(current.getName())) {
					currentExisting = allPlayers.indexOf(existing);
				}
			}
			if (current != null) {
				allPlayers.remove(currentExisting);
				allPlayers.add(current);
			}
			GridPane x = new GridPane();
			x.setAlignment(Pos.CENTER);
			x.setPadding(new Insets(10, 10, 10, 10));
			x.setHgap(10);
			x.setVgap(10);

			alert.getDialogPane().setContent(x);
			
			Label title = new Label("LeaderBoard");
			title.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			title.setTextFill(Color.valueOf("#f1356d"));

			ObservableList<User> players = FXCollections.observableArrayList(allPlayers);

			// Create the TableView and set the data
			TableView<User> tableView = new TableView<>(players);

			// Create the columns and associate them with the Person properties
			TableColumn<User, String> firstNameCol = new TableColumn<>("First Name");
			firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

			TableColumn<User, String> lastNameCol = new TableColumn<>("Last Name");
			lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

			TableColumn<User, Integer> scoreCol = new TableColumn<>("Score");
			scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

			// Add the columns to the TableView
			tableView.getColumns().addAll(firstNameCol, lastNameCol, scoreCol);
			tableView.setMinWidth(260);

			x.add(title, 0, 0);
			x.add(tableView, 0, 1);
			alert.getDialogPane().setHeaderText(null);
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
			alert.showAndWait();
		});
	}

	public GridPane getPane() {
		return this.grid;
	}
}
