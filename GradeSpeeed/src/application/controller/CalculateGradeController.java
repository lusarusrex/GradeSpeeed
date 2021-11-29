package application.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import application.model.Model;
import application.model.Student;
import application.model.readID;

public class CalculateGradeController implements Initializable {
	@FXML
	private AnchorPane MainMenuView;
	@FXML
	private Button mainMenu;
	@FXML
	private Label labsLabel;
	@FXML
	private Label quizLabel;
	@FXML
	private TextField labsField;
	@FXML
	private TextField quizField;
	@FXML
	private Button computeButton;
	@FXML
	private TextField gradeField;
	@FXML
	private Label midtermLabel;
	@FXML
	private TextField midtermField;
	@FXML
	private Label finalXamLabel;
	@FXML
	private TextField finalXamField;
	@FXML
	private Label projectLabel;
	@FXML
	private TextField projectField;
	@FXML
	private Label errorLabel;
	@FXML
	private Label letterGradeLabel;
	@FXML
	private TextField studentIDField;
	
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
	
	@FXML
	private void gradeMeSon(ActionEvent event) {
		//handles error label for labs
		if (labsField.getText().equals("")) {
			labsLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		
		//handles error label for labs
		labsLabel.setTextFill(Color.WHITE);
		if (quizField.getText().equals("")) {
			quizLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		//handles error label for quiz
		quizLabel.setTextFill(Color.WHITE);
		if (midtermField.getText().equals("")) {
			midtermLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		
		//handles error label for midterm
		midtermLabel.setTextFill(Color.WHITE);
		if (finalXamField.getText().equals("")) {
			finalXamLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		//handles error label for final exam 
		finalXamLabel.setTextFill(Color.WHITE);
		if (projectField.getText().equals("")) {
			projectLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		projectLabel.setTextFill(Color.WHITE);
		errorLabel.setVisible(false);
////////////////////////////////////////////////////////////////////////////////////////////
// labs
		String labGradesEntered = labsField.getText().trim();
		String[] labDataRecorded = labGradesEntered.split(", ");
		int labTotals = 0;
		int countLabs = 0;
		
		for (int i = 0; i < labDataRecorded.length; i++) {
			if (!isInteger(labDataRecorded[i])) {
				labsLabel.setTextFill(Color.RED);
				errorLabel.setVisible(true);
				gradeField.setText("");
				letterGradeLabel.setVisible(false);
				break;
			} else {
				labsLabel.setTextFill(Color.WHITE);
				errorLabel.setVisible(false);
				labTotals += Integer.parseInt(labDataRecorded[i]);
				countLabs++;
			}
		}
		double weightedLabGrade = (labTotals / (double) countLabs);
////////////////////////////////////////////////////////////////////////////////////////////
		
// quizzes
		String QuizGradesEntered = quizField.getText().trim();
		String[] quizDataEntered = QuizGradesEntered.split(", ");
		
		int quizSum = 0;
		int numQuiz = 0;
		
		for (int i = 0; i < quizDataEntered.length; i++) {
			if (!isInteger(quizDataEntered[i])) {
				quizLabel.setTextFill(Color.RED);
				errorLabel.setVisible(true);
				gradeField.setText("");
				letterGradeLabel.setVisible(false);
				break;
			} else {
				quizLabel.setTextFill(Color.WHITE);
				errorLabel.setVisible(false);
				quizSum += Integer.parseInt(quizDataEntered[i]);
				numQuiz++;
			}
		}
		double weightedQuizGrade = (quizSum / (double) numQuiz);
////////////////////////////////////////////////////////////////////////////////////////////
		
// midterm
		String MidtermGradesEntered = midtermField.getText().trim();
		if (!isInteger(MidtermGradesEntered)) {
			midtermLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		midtermLabel.setTextFill(Color.WHITE);
		errorLabel.setVisible(false);
		int weightedMidtermGrade = Integer.parseInt(MidtermGradesEntered);
		
////////////////////////////////////////////////////////////////////////////////////////////
		
// exam
		String FinalExamGradesEntered = finalXamField.getText().trim();
		if (!isInteger(FinalExamGradesEntered)) {
			finalXamLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		finalXamLabel.setTextFill(Color.WHITE);
		errorLabel.setVisible(false);
		int finalExamGrade = Integer.parseInt(FinalExamGradesEntered);
		
////////////////////////////////////////////////////////////////////////////////////////////
		
// project
		String projectGrade = projectField.getText().trim();
		if (!isInteger(projectGrade)) {
			projectLabel.setTextFill(Color.RED);
			errorLabel.setVisible(true);
			gradeField.setText("");
			letterGradeLabel.setVisible(false);
			return;
		}
		projectLabel.setTextFill(Color.WHITE);
		errorLabel.setVisible(false);
		int projectGradeTOTAL = Integer.parseInt(projectGrade);
		
////////////////////////////////////////////////////////////////////////////////////////////
		
// grade
		double finalNumGrade = (0.20 * weightedLabGrade) + (0.15 * weightedQuizGrade) + (0.25 * weightedMidtermGrade) + (0.10 * projectGradeTOTAL)
				+ (0.30 * finalExamGrade);
		char finalLetterGrade = letterGrade(finalNumGrade);
		gradeField.setText(String.format("%.1f", finalNumGrade));
		letterGradeLabel.setVisible(true);
		letterGradeLabel.setText(finalLetterGrade + "");
		
////////////////////////////////////////////////////////////////////////////////////////////
		Student student = new Student(QuizGradesEntered, labGradesEntered, MidtermGradesEntered, FinalExamGradesEntered, projectGrade);
		readID getID = new readID();
		
		try {
			getID.createCSV("GradeBook.properties");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// SEPERATES CSV 
		try {
			String CSV_SEPARATOR = ",";
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(studentIDField.getText() + ".csv"), "UTF-8"));
			StringBuffer line = new StringBuffer();
			line.append(student.getLabs());
			line.append(CSV_SEPARATOR);
			line.append(student.getQuiz());
			line.append(CSV_SEPARATOR);
			line.append(student.getMidterm());
			line.append(CSV_SEPARATOR);
			line.append(student.getFinalExam());
			line.append(CSV_SEPARATOR);
			line.append(student.getProject());
			bw.write(line.toString());
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}	
		String info = "";
		try {
			info = Model.info(studentIDField.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] infoString = info.split("/");
		String studentInfo = "/Student Name-" + infoString[1].substring(infoString[1].indexOf('-')+1) + "/Labs-" + labsField.getText() + "/Quizzes-" + quizField.getText() + "/Project-" + projectField.getText() + "/Midterm-" + midtermField.getText() + "/Final Exam-" + finalXamField.getText() + "/";
		try {
			Model.addInfo(studentIDField.getText(), studentInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void Student(String[] labData) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		errorLabel.setVisible(false);
		letterGradeLabel.setVisible(false);
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			if (Integer.parseInt(s) < 0)
				return false;
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	// method that handles letter grade
	private char letterGrade(double grade) {
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
}