package application.controller;

import java.io.FileWriter;
import java.io.IOException;

import application.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class LoginController {
	@FXML
	private AnchorPane Pane;
	@FXML
	private TextField newUserField;
	@FXML
	private TextField newPasswordField;
	@FXML
	private Label incorrectLabel;
	@FXML
    private TextField UserField;
    @FXML
    private PasswordField PasswordField;

	@FXML
	void loginClick(ActionEvent event) {
		User user = new User();
    	incorrectLabel.setVisible(false);
    	//gets user input to check for login
    	try {
			user.loadUsers("logins.csv");
			for (int i =0; i < user.getUsernames().size();i++) {
				if (UserField.getText().equals(user.getUsernames().get(i)) && PasswordField.getText().equals(user.getPasswords().get(i))) {
					try {
			    		
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Menu.fxml"));
						Pane = loader.load();
						Scene scene = new Scene(Pane);
						Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
						window.setScene(scene);
						window.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			incorrectLabel.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void newAccountClick(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/CreateAccount.fxml"));
			Pane = loader.load();
			Scene scene = new Scene(Pane);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void HomeClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Login.fxml"));
			Pane = loader.load();
			Scene scene = new Scene(Pane);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void createClick(ActionEvent event) {
		if (newUserField.getText().equals("") || newPasswordField.getText().equals("")) {
			System.out.println("error");
		} else {
			try {
				FileWriter csvWriter = new FileWriter("logins.csv", true);
				csvWriter.append(newUserField.getText());
				csvWriter.append(",");
				csvWriter.append(newPasswordField.getText());
				csvWriter.append("\n");
				csvWriter.close();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Login.fxml"));
				Pane = loader.load();
				Scene scene = new Scene(Pane);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
