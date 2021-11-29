package application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Properties;

import application.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddController 
{
	@FXML 
	private Button addStudent;
	@FXML
	private Button mainMenu;
	@FXML
	private AnchorPane MainMenuView;
	@FXML
	private TextField studentName;
	@FXML 
	private TextField studentID;
	
	@FXML //from Add.fxml to MainMenu.fxml
	public void handleMainMenu(MouseEvent event) throws IOException
	{
		URL url = new File("src/application/view/Menu.fxml").toURI().toURL();
		MainMenuView = FXMLLoader.load(url); 
		Scene scene = new Scene(MainMenuView);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
		window.setScene(scene);
		window.show();
		
	}//end handleMainMenu method
	
	public void AddStudentInfo(MouseEvent event) throws IOException, ParseException
	{
		//Check for empty fields
		String fields = Model.checkFields(studentName, studentID); 
		
		//check if studentID already exists in system 
		try (BufferedReader br = new BufferedReader(new FileReader("GradeBook.properties")))
		{
			byte[] bytes = Files.readAllBytes(Paths.get("GradeBook.properties")); 
			String ID = new String(bytes); //ID that is already in properties file
			String currentID = studentID.getText(); //ID that is being added by user

			if(ID.contains(currentID) == true)
			{
				fields = "incomplete";
				new Alert(Alert.AlertType.ERROR, "Student ID already exists in system").showAndWait(); 
			
			}//end if statement
			
		}//end try 
		
		if (fields.isEmpty())
		{
			//Set variables from text fields
			String name = studentName.getText();
			String id = studentID.getText();
			
			String studentInfo = "/Student Name-" + name; //string together non-key information to hold together as second String in hashmap 
			
			//Create hash map 
			HashMap <String, String> h = new HashMap< String, String>();
			File file = new File("GradeBook.properties");
			FileInputStream read = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(read);
			read.close(); 
			
			for (String key: properties.stringPropertyNames())
			{
				h.put(key, properties.get(key).toString());
				
			}//end for loop
			
			h.put(id, studentInfo); 
			
			//Store hashmap into properties
			properties.putAll(h);
			
			//write properties to the GradeBook file
			FileOutputStream write = new FileOutputStream(file);
			properties.store(write, null);
			
			//Close write file
			write.close(); 
			
			//Display confirmation message
			new Alert(Alert.AlertType.CONFIRMATION, "New Student successfully added.").showAndWait();
			
			
		}//end if statement
	}//end AddStudent method
	
}//end AddController

