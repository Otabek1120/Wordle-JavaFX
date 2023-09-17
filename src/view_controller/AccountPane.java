package view_controller;

import javafx.scene.layout.GridPane;

import java.io.Serializable;
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

public class AccountPane extends GridPane {
	private GridPane pane = new GridPane();
	private Label accStatusLabel = new Label();

	public AccountPane() {
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setVgap(10);
		pane.setHgap(10);

		// Create the Labels and TextFields
		Label firstNameLabel = new Label("First Name:");
		TextField firstNameTextField = new TextField();
		Label lastNameLabel = new Label("Last Name:");
		TextField lastNameTextField = new TextField();
		Label passwordLabel = new Label("Password:");
		PasswordField passwordField = new PasswordField();
		Label confirmPasswordLabel = new Label("Confirm Password:");
		PasswordField confirmPasswordField = new PasswordField();

		// Add the Labels and TextFields to the GridPane
		pane.add(firstNameLabel, 0, 0);
		pane.add(firstNameTextField, 1, 0);
		pane.add(lastNameLabel, 0, 1);
		pane.add(lastNameTextField, 1, 1);
		pane.add(passwordLabel, 0, 2);
		pane.add(passwordField, 1, 2);
		pane.add(confirmPasswordLabel, 0, 3);
		pane.add(confirmPasswordField, 1, 3);

		// Create the Sign Up button
		pane.add(accStatusLabel, 1, 5);
		Button signUpButton = new Button("Sign Up");
		signUpButton.setOnAction(event -> {
			String status = "";
			ArrayList<User> allUsers = User.readFromSer();
			//read from ser, make sure no first/last name matches
			
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String password = passwordField.getText();
			String confirm = confirmPasswordField.getText();
			
			if (firstName.isEmpty() || lastName.isEmpty()
					|| password.isBlank() || confirm.isBlank()) {
				// Display an error message if the name field is empty
				accStatusLabel.setText("");
				accStatusLabel.setText("Please fill required fields");
				accStatusLabel.setStyle("-fx-text-fill: red;");
			}
			else if (passwordField.getText().equals(confirmPasswordField.getText())){
				accStatusLabel.setText("");
				User current = new User(firstName, lastName, password); 
				String currentName = firstName + " " + lastName;
				for (User existing: allUsers) {
					String existingName = existing.getFirstName() + " " + existing.getLastName();
					if (currentName.equalsIgnoreCase(existingName)) {
						accStatusLabel.setStyle("-fx-text-fill: red;");
						status = "User already exists";
					}
				}
				if (status.equals("")) {
					System.out.println("Added: " + current.getFirstName() + " " + current.getLastName());
					allUsers.add(current);
					current.writeToSer(allUsers);
					System.out.println("Account Pane.java: After Add" + allUsers);
					status = "Account created!";
					accStatusLabel.setStyle("-fx-text-fill: green;");
					firstNameTextField.clear();
					lastNameTextField.clear();
					passwordField.clear();
					confirmPasswordField.clear();
				}
			}
			else {
				status = "Passwords do not match";
				accStatusLabel.setStyle("-fx-text-fill: red;");
			}
			accStatusLabel.setText(status);
		});

		// Create the HBox for the Sign Up button
		HBox hbSignUpButton = new HBox(10);
		hbSignUpButton.getChildren().add(signUpButton);
		hbSignUpButton.setPadding(new Insets(10, 0, 0, 0));

		// Create the VBox for the pane
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(hbSignUpButton);

		pane.add(vbox, 1, 4);

		pane.setStyle("-fx-border-width: 4px;" + "-fx-border-color: #64748B;" + "-fx-border-style: solid;"
				+ "-fx-font-weight: bold;");
	}

	public GridPane getPane() {
		return this.pane;
	}
}
