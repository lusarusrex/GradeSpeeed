package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController 
{
    @FXML 
    private AnchorPane ModifyView;
    @FXML
    private AnchorPane AddView; 
    @FXML
    private AnchorPane LoginView; 
    @FXML
    private AnchorPane CalculateView; 
    @FXML
    private AnchorPane ReportView; 
    @FXML
    private Button Modify;
    @FXML
    private Button Add; 
    @FXML
    private Button logout;
    @FXML
    private Button calculate;
    @FXML
    private Button report;
    
    @FXML //from MainMenu.fxml to Modify.fxml
    public void handleModify(MouseEvent event) throws IOException
    {
        URL url = new File("src/application/view/Modify.fxml").toURI().toURL();
        ModifyView = FXMLLoader.load(url); 
        Scene scene = new Scene(ModifyView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
        window.setScene(scene);
        window.show();
        
    }//end handleModify method
    
    @FXML //from MainMenu.fxml to Add.fxml
    public void handleAdd(MouseEvent event) throws IOException
    {
        URL url = new File("src/application/view/Add.fxml").toURI().toURL();
        AddView = FXMLLoader.load(url); 
        Scene scene = new Scene(AddView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
        window.setScene(scene);
        window.show();
        
    }//end handleAdd method
    
    @FXML //from MainMenu.fxml to Login.fxml
    public void handleLogout(MouseEvent event) throws IOException
    {
        URL url = new File("src/application/view/Login.fxml").toURI().toURL();
        LoginView = FXMLLoader.load(url); 
        Scene scene = new Scene(LoginView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
        window.setScene(scene);
        window.show();
        
    }//end handleModify method
    
    @FXML //from MainMenu.fxml to CalculateGradeWindowFXML.fxml
    public void handleCalculate(MouseEvent event) throws IOException
    {
        URL url = new File("src/application/view/CalculateGradeWindowFXML.fxml").toURI().toURL();
        CalculateView = FXMLLoader.load(url); 
        Scene scene = new Scene(CalculateView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
        window.setScene(scene);
        window.show();
        
    }//end handleCalculate method
    
    @FXML //from MainMenu.fxml to Report.fxml
    public void handleReport(MouseEvent event) throws IOException {
        URL url = new File("src/application/view/Report.fxml").toURI().toURL();
        ReportView = FXMLLoader.load(url); 
        Scene scene = new Scene(ReportView);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); //pane you are on 
        window.setScene(scene);
        window.show();
    }
    
}//end MainMenuController