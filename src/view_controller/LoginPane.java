package view_controller;

import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.User;

public class LoginPane extends GridPane implements Serializable{
	private GridPane pane = new GridPane();
	private Label accStatusLabel;
	private boolean isLoggedIn = false;
	private TextField firstNameTextField;
	private static User current;

	public LoginPane() {
		accStatusLabel = new Label("");
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setVgap(10);
		pane.setHgap(10);

		// Create the Labels and TextFields
		Label firstNameLabel = new Label("First Name:");
		firstNameTextField = new TextField();
		Label lastNameLabel = new Label("Last Name:");
		TextField lastNameTextField = new TextField();
		Label passwordLabel = new Label("Password:");
		PasswordField passwordField = new PasswordField();

		// Add the Labels and TextFields to the GridPane
		pane.add(firstNameLabel, 0, 0);
		pane.add(firstNameTextField, 1, 0);
		pane.add(lastNameLabel, 0, 1);
		pane.add(lastNameTextField, 1, 1);
		pane.add(passwordLabel, 0, 2);
		pane.add(passwordField, 1, 2);

		Label loginLabel = new Label("Welcome Guest!");
		loginLabel.setStyle("-fx-font: 24 arial;");
		pane.add(accStatusLabel, 1, 4);
		
		// Create the Login button
		Button loginButton = new Button("Login");
		loginButton.setOnAction(event -> {
			String status = "";
			ArrayList<User> allUsers = User.readFromSer();
			if (allUsers.size() == 0) {
				status = "Wrong name/password";
			}
			
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String password = passwordField.getText();

			if (firstName.isEmpty() || lastName.isEmpty()) {
				// Display an error message if the name field is empty
				accStatusLabel.setText("");
				accStatusLabel.setText("Fill required fields");
				accStatusLabel.setStyle("-fx-text-fill: red;");
			} 
			else {
				for (User existing: allUsers) {
					if (existing.getName().equalsIgnoreCase(firstName + " " + lastName) && existing.getPassword().equals(password)) {
						current = existing;
						status = "Login Success!";
					}
					else {
						status = "Wrong name/password";
					}
				}
				if (!status.equals("Login Success!")) {
					accStatusLabel.setStyle("-fx-text-fill: red;");
				}
				else {
					accStatusLabel.setStyle("-fx-text-fill: green;");
					isLoggedIn = true;
					loginLabel.setText("Logged in as " + current.getFirstName());

				}
				firstNameTextField.clear();
				lastNameTextField.clear();
				passwordField.clear();
				accStatusLabel.setText(status);
			}
		});

		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction((event) -> {
			isLoggedIn = false;
			loginLabel.setText("Welcome Guest!");
			accStatusLabel.setText("logged out");
		});
		
		
		// Create the HBox for the Login button
		HBox hbLoginButton = new HBox(10);
		hbLoginButton.getChildren().add(loginButton);
		hbLoginButton.setPadding(new Insets(10, 0, 0, 0));

		HBox hbLogoutButton = new HBox(10);
		hbLogoutButton.getChildren().add(logoutButton);
		hbLogoutButton.setPadding(new Insets(10, 0, 0, 0));

		// Create the VBox for the pane
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(hbLoginButton);
		vbox.getChildren().addAll(hbLogoutButton);

		pane.add(vbox, 1, 3);

//		Label loginLabel = new Label("Welcome Guest!");
//		pane.add(loginLabel, 2, 1);

		pane.setStyle("-fx-border-width: 4px;" + "-fx-border-color: #64748B;" + "-fx-border-style: solid;"
				+ "-fx-font-weight: bold;");
	}

	public GridPane getPane() {
		return this.pane;
	}
	
	public static User getCurrent() {
		return current;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		isLoggedIn = false;
		accStatusLabel.setText("logged out");
	}
}