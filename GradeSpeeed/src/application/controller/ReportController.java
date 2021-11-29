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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReportController {
    @FXML
    private AnchorPane MainMenuView;
    @FXML
    private TextField finalexam;

    @FXML
    private Button generateButton;

    @FXML
    private TextField lab;

    @FXML
    private TextField letterGrade;

    @FXML
    private Button mainMenuButton;

    @FXML
    private TextField midterm;

    @FXML
    private TextField project;
    
    @FXML
    private TextField studentName;
    
    @FXML
    private TextField avgGrade;

    @FXML
    private TextField quiz;

    @FXML
    private TextField stuID;
////////////////////////////////////////////////////////////////////////////////////////////
    
// search for the students data and print it
    public void reportData(String studentID) {
		lab.setText("");
		quiz.setText("");
		project.setText("");
		midterm.setText("");
		finalexam.setText("");
		studentName.setText("");
		stuID.setText("");
		avgGrade.setText("");
		
		//get search TextField information for the student ID
		String keyID = studentID;
		
		String info = "";
		try {
			info = Model.info(keyID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(info.equals("Invalid Student ID"))
		{
			new Alert(Alert.AlertType.ERROR, "Entered STUDENT ID does not exist").showAndWait();
			
		}
		
		else
		{
			String[] infoString = info.split("/");
			
			//put all info into the text boxes
			studentName.setText(infoString[1].substring(infoString[1].indexOf('-')+1));
			lab.setText(infoString[2].substring(infoString[2].indexOf('-')+1));
			quiz.setText(infoString[3].substring(infoString[3].indexOf('-')+1));
			project.setText(infoString[4].substring(infoString[4].indexOf('-')+1));
			midterm.setText(infoString[5].substring(infoString[5].indexOf('-')+1));
			finalexam.setText(infoString[6].substring(infoString[6].indexOf('-')+1));
		
			String[] labGrades = lab.getText().split(", ");
			double labs = 0;
			for(int i = 0; i < labGrades.length; i++) {
				labs += Integer.parseInt(labGrades[i]);
			}
			labs = labs / labGrades.length;
			String[] quizGrades = quiz.getText().split(", ");
			double quizzes = 0;
			for(int i = 0; i < quizGrades.length; i++) {
				quizzes += Integer.parseInt(labGrades[i]);
			}
			quizzes = quizzes / quizGrades.length;
			double projectGrade = Integer.parseInt(project.getText());
			double midGrade = Integer.parseInt(midterm.getText());
			double finalGrade = Integer.parseInt(finalexam.getText());
			double finalNumGrade = (0.20 * labs) + (0.15 * quizzes) + (0.25 * midGrade) + (0.10 * projectGrade)
					+ (0.30 * finalGrade);
			char finalLetterGrade = CalculateLetterGrade(finalNumGrade);
			letterGrade.setText(finalLetterGrade + "");
			avgGrade.setText(String.format("%.1f", finalNumGrade));
		}
    }
    
    
 // method that handles letter grade
     private char CalculateLetterGrade(double grade) {
         char letter = 0;
         if (grade >= 90 && grade < 100)
             letter = 'A';
         else if (grade >= 80 && grade < 90)
             letter = 'B';
         else if (grade >= 70 && grade < 80)
             letter = 'C';
         else if (grade >= 60 && grade < 70)
             letter = 'D';
         else
             letter = 'F';
         return letter;
     }
     
//to main menu
     @FXML 
    public void handleMainMenu (ActionEvent event) throws IOException
    {
        URL url = new File("src/application/view/Menu.fxml").toURI().toURL();
        MainMenuView = FXMLLoader.load(url); 
        Scene scene = new Scene(MainMenuView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
        window.setScene(scene);
        window.show();
        Student.setID(null);
    }
    
    @FXML
    public void initialize() {
    	lab.setText("");
		quiz.setText("");
		project.setText("");
		midterm.setText("");
		finalexam.setText("");
		letterGrade.setText("");
		avgGrade.setText("");
		studentName.setText("");
		stuID.setText("");
		if(Student.getID() != null) {
	    	if(Student.getID().length() == 6) {
	    		reportData(Student.getID());
	    		stuID.setText(Student.getID());
	    	}
		}
    }

// generates the student data
      @FXML
     void handleGenerate(ActionEvent event) {
    	  reportData(stuID.getText());
     }
}