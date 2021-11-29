package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.model.Model;
import application.model.Student;
import javafx.event.ActionEvent;
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

public class ModifyController 
{
	@FXML 
	private Button search;
	@FXML
	private Button update;
	@FXML
	private Button mainMenu;
	@FXML
	private Button report;
	@FXML
	private Button editLab;
	@FXML
	private Button editQuiz;	
	@FXML
	private Button editProject;
	@FXML
	private Button editMidterm;
	@FXML
	private Button editFinal;
	@FXML
	private Button delete;
	@FXML
	private AnchorPane MainMenuView; 
	@FXML
	private AnchorPane ReportView; 
	@FXML
	private TextField searchField;
	@FXML
	private TextField studentName;
	@FXML
	private TextField labs;
	@FXML
	private TextField quizzes;
	@FXML
	private TextField midterm;
	@FXML
	private TextField project;
	@FXML
	private TextField finalExam;
	
	@FXML //Move from Modify.fxml to MainMenu.fxml 
	public void handleMainMenu(MouseEvent event) throws IOException
	{
		URL url = new File("src/application/view/Menu.fxml").toURI().toURL();
		MainMenuView = FXMLLoader.load(url); 
		Scene scene = new Scene(MainMenuView);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
		window.setScene(scene);
		window.show();
		
	}//end handleMainMenu
	
	@FXML //Move from Modify.fxml to Report.fxml 
	public void handleReport(ActionEvent event) throws IOException
	{
		URL url = new File("src/application/view/Report.fxml").toURI().toURL();
		ReportView = FXMLLoader.load(url); 
		Scene scene = new Scene(ReportView);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
		window.setScene(scene);
		window.show();
		String id = searchField.getText();
		if(id.length() == 6)
			Student.setID(id);
	}//end handleReport
	
	@FXML
	void search(ActionEvent event)
	{
		studentName.setText("");
		labs.setText("");
		quizzes.setText("");
		project.setText("");
		midterm.setText("");
		finalExam.setText("");
		//get search TextField information for the student ID
		String keyID = searchField.getText();
		
		String info = "";
		try {
			info = Model.info(keyID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(info.equals("Invalid Student ID"))
		{
			new Alert(Alert.AlertType.ERROR, "Entered STUDENT ID does not exist").showAndWait();
			
		}//end if statement
		
		else
		{
			String[] infoString = info.split("/");
			
			//put all info into the text boxes
			studentName.setText(infoString[1].substring(infoString[1].indexOf('-')+1));
			labs.setText(infoString[2].substring(infoString[2].indexOf('-')+1));
			quizzes.setText(infoString[3].substring(infoString[3].indexOf('-')+1));
			project.setText(infoString[4].substring(infoString[4].indexOf('-')+1));
			midterm.setText(infoString[5].substring(infoString[5].indexOf('-')+1));
			finalExam.setText(infoString[6].substring(infoString[6].indexOf('-')+1));
		
		}//end else statement
		Student.setID(keyID);
	}//end search method
	
	@FXML
	void edit(ActionEvent event)
	{
		Button whichBtn = (Button)event.getSource();
		String type; 
		
		if(editLab == whichBtn)
		{
			labs.clear();
			type = "Labs";
			Model.alertMessage(type); 	
		}
		
		if(editQuiz == whichBtn)
		{
			quizzes.clear();
			type = "Quizzes";
			Model.alertMessage(type); 	
		}
		
		if(editProject == whichBtn)
		{
			project.clear();
			type = "Project";
			Model.alertMessage(type); 	
		}
		
		if(editMidterm == whichBtn)
		{
			midterm.clear();
			type = "Midterm";
			Model.alertMessage(type); 	
		}
		
		if( editFinal== whichBtn)
		{
			finalExam.clear();
			type = "Final Exam";
			Model.alertMessage(type); 	
		}
	}
	
	@FXML
	void update(ActionEvent event) {
		String name = studentName.getText().trim();
		String id = searchField.getText().trim();
		String labGrade = labs.getText().trim();
		String quizGrade = quizzes.getText().trim();	
		String projectGrade = project.getText().trim();
		String midtermGrade = midterm.getText().trim(); 
		String finalExamGrade = finalExam.getText().trim(); 
		
		String studentInfo = "/Student Name-" + name + "/Labs-" + labGrade + "/Quizzes-" + quizGrade + "/Project-" + projectGrade + "/Midterm-" + midtermGrade + "/Final Exam-" + finalExamGrade + "/"; //string together non-key information to hold together as second String in hashmap
		
		Model.updateInfo(id, studentInfo);
		
	}
	//end update method
	
	@FXML
	private void delete(ActionEvent event) {
		//Create hash map 
		String id = searchField.getText().trim();
		Model.removeStudent(id);
		studentName.setText("");
		searchField.setText("");
		labs.setText("");
		quizzes.setText("");
		project.setText("");
		midterm.setText("");
		finalExam.setText("");
	}
	
	
	
}//end ModifyController
