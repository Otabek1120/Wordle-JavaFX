package view_controller;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import model.WordleGame;

/** TODO
 * 		1. Add a "Start a New Game" button in the Accessories.java, maybe
 * 		2. Add a "Text Field" if the game is won
 * 		3. Create a separate window for login and create account
 * 		4. Add a wow factor
 */
/**
 * 
 * 
 * JavaFX App
 */
public class MainGUI extends Application {
	private WordleGame      wordleGame     = new WordleGame();
	private Accessories     accessories    = new Accessories();
	private GuessGrid       guessGrid      = new GuessGrid();
	private Keyboard        keyboard       = new Keyboard(guessGrid, wordleGame);
	private LoginPane       loginPane      = new LoginPane();
	private AccountPane     accPane        = new AccountPane();
	public static BorderPane   wordlePane;
	public static GridPane     accLoginPane;
   

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		BorderPane welcomePane = new BorderPane();
		accLoginPane = new GridPane();
		accLoginPane.add(accPane.getPane(), 1, 1);
		accLoginPane.add(loginPane.getPane(), 2, 1);
		accLoginPane.setStyle("-fx-background-color: #BBD0BA;");
		welcomePane.setTop(accLoginPane);
		Button goToGame = new Button("Play Wordle!");
		
		GridPane goToGamePane = new GridPane();
		goToGamePane.add(goToGame, 1, 1);
		goToGamePane.setPadding(new Insets(10, 20, 10, 20));
		goToGamePane.setAlignment(Pos.CENTER);
		goToGamePane.setStyle("-fx-background-color: #64748B;");
		welcomePane.setCenter(goToGamePane);
		var scene1 = new Scene(welcomePane, 600, 310);
		Stage stage1 = new Stage();
		stage1.setScene(scene1);
		stage1.show();
		goToGame.setOnAction((event) -> {
			stage1.close();
			wordlePane = new BorderPane();
			wordlePane.setStyle("-fx-background-color: #FFFFFF;");
			// top is the pane for accessories
			wordlePane.setTop(accessories.getPane());
			// guessGrid, leaderBoard panes and keyboardPane are in the center
			GridPane mainStage = new GridPane();
			mainStage.add(guessGrid.getPane(), 1, 0);
			mainStage.add(keyboard.getPane(), 1, 2);
			wordlePane.setCenter(mainStage);
			if (loginPane.isLoggedIn()) {
				GridPane namePane = new GridPane();
				Label nameLabel = new Label("Welcome " + LoginPane.getCurrent() + "!");
				User currentPlayer = LoginPane.getCurrent();
				System.out.println("currPlayer in Main: " + currentPlayer.getName());
				System.out.println("score: " + currentPlayer.getScore());
				keyboard.setCurrPlayer(currentPlayer);
				
				nameLabel.setStyle("-fx-font-size: 20px;");
				Button newGameButton = new Button("New Game");
				newGameButton.setOnAction((event2) -> {
					keyboard.startNewGame();
				});
				Button logoutButton = new Button("Logout");
				logoutButton.setOnAction((event1) -> {
					loginPane.logout();
					stage.close();
					stage1.show();
				});
				namePane.add(nameLabel, 1, 1);
				namePane.add(newGameButton, 2, 2);
				namePane.add(logoutButton, 0, 2);
				namePane.setPadding(new Insets(10, 20, 10, 20));
				namePane.setAlignment(Pos.CENTER);
				namePane.setStyle("-fx-background-color: #64748B;");
				wordlePane.setBottom(namePane);
			} else {
				GridPane namePane = new GridPane();
				Label nameLabel = new Label("Welcome Guest");
				nameLabel.setStyle("-fx-font-size: 20px;");
				Button newGameButton = new Button("New Game");
				newGameButton.setOnAction((event2) -> {
					keyboard.startNewGame();
				});
				Button loginButton = new Button("Login");
				loginButton.setOnAction((event1) -> {
					stage.close();
					stage1.show();
				});
				namePane.add(nameLabel, 1, 1);
				namePane.add(newGameButton, 2, 2);
				namePane.add(loginButton, 0, 2);
				namePane.setPadding(new Insets(10, 20, 10, 20));
				namePane.setAlignment(Pos.CENTER);
				namePane.setStyle("-fx-background-color: #64748B;");
				wordlePane.setBottom(namePane);
			}
			var scene = new Scene(wordlePane, 510, 690);
			stage.setScene(scene);
			stage.show();
		});
		
		
		stage.setOnCloseRequest(event -> {
			ArrayList <User> allUsers = User.readFromSer();
			User current = LoginPane.getCurrent();
			int currentExisting = -1;
			for (User existing: allUsers) {
				if (current != null && existing.getName().equals(current.getName())) {
					currentExisting = allUsers.indexOf(existing);
				}
			}
			if (current != null) {
				allUsers.remove(currentExisting);
				allUsers.add(current);
				User.writeToSer(allUsers);
			}
		});
	}

	public static BorderPane getMainPane() {
		System.out.println(wordlePane);
		return wordlePane;
	}

	public static GridPane getLoginPane() {
		return accLoginPane;
	}

	
	
}