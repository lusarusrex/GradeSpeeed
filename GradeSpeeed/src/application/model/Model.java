package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Model 
{
	private static HashMap<String, String> h = new HashMap<String, String> (); 
	private static Properties properties = new Properties();
	
	public static String info(String keyID) throws IOException
	{
		String info;
		
		//Open file to hold GradeBook data, student name, id, grades, etc. 
		File file = new File("GradeBook.properties"); //opens file
		FileInputStream read = new FileInputStream(file); //open read file
		properties.load(read);//load the reader to see what is in the properties file
		
		//close read file; 
		read.close(); 
		
		for (String key: properties.stringPropertyNames()) 
		{
			h.put(key, properties.get(key).toString());
			
		}//end for loop
		
		if (h.containsKey(keyID))
		{
			info = h.get(keyID);
		}
		
		else 
		{
			info = "Invalid Student ID"; //ID does not match 
		}
		
		return info; 
		
	}//end info method
	
	public static void alertMessage(String type)
	{
		new Alert(Alert.AlertType.INFORMATION, "You can now update to desired " + type + ", click update when all fields are properly up to date.").showAndWait();
		
	}//end alertMessage method
	
	public static String checkFields(TextField studentName, TextField studentID) throws IOException
	{
		String fields = ""; 
		
		if (studentName.getText().isEmpty() && studentID.getText().isEmpty())
		{
			new Alert(Alert.AlertType.ERROR, "Please fill out both STUDENT NAME and STUDENT ID fields to Add New Student").showAndWait();
			fields = "empty"; 
		}//end if statement 
		
		else
		{
			if (studentName.getText().isEmpty()) //If studentName TextField is not filled out, print error message
			{
				fields = "incomplete";
				new Alert(Alert.AlertType.ERROR, "Please fill out STUDENT NAME field").showAndWait();  
			}
			
			//If studentID TextField is not filled out or does not match regex, print error message
			if ((!studentID.getText().matches("[a-z]{3}[0-9]{3}")))
			{
				fields = "incomplete";
				new Alert(Alert.AlertType.ERROR, "Please fill out valid STUDENT ID field" +"\nEx. abc123").showAndWait();
			}

		}//end else statement
		
		return fields; 
		
	}//end checkFields method
	
	public static void addInfo(String studentID, String studentInfo) throws IOException
	{
		File file = new File("GradeBook.properties");
		FileInputStream read= new FileInputStream(file);
		properties.load(read);
		read.close();
		
		for (String key: properties.stringPropertyNames())
		{
			h.put(key, properties.get(key).toString()); 
			
		}//end for loop 
		
		//Store all student information into the hash map
		h.put(studentID, studentInfo);
		
		//Store hash map into the properties
		properties.putAll(h);
		
		//Write hash map information into the properties file
		FileOutputStream write = new FileOutputStream(file);
		properties.store(write, null);
		
		//close writer file
		write.close(); 
		
		new Alert(Alert.AlertType.INFORMATION, "Student information successfully added.").showAndWait();
		
	}//end addInfo method
	
	public static void removeStudent(String studentID) {
		File file = new File("GradeBook.properties");
		FileInputStream read;
		try {
			read = new FileInputStream(file);
			properties.load(read);
			read.close();
			
			for (String key: properties.stringPropertyNames())
			{
				h.put(key, properties.get(key).toString()); 
				
			}//end for loop 
			
			h.remove(studentID);
			
			//Store hash map into the properties
			properties.putAll(h);
			properties.remove(studentID);
			
			//Write hash map information into the properties file
			FileOutputStream write = new FileOutputStream(file);
			properties.store(write, null);
			
			//close writer file
			write.close(); 
			
			new Alert(Alert.AlertType.INFORMATION, "Student information successfully removed.").showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateInfo(String studentID, String studentInfo) {
		File file = new File("GradeBook.properties");
		FileInputStream read;
		try {
			read = new FileInputStream(file);
			properties.load(read);
			read.close();
			
			for (String key: properties.stringPropertyNames())
			{
				h.put(key, properties.get(key).toString()); 
				
			}//end for loop 
			
			//Store all student information into the hash map
			h.replace(studentID, studentInfo);
			
			//Store hash map into the properties
			properties.putAll(h);
			properties.replace(studentID, studentInfo);
			
			//Write hash map information into the properties file
			FileOutputStream write = new FileOutputStream(file);
			properties.store(write, null);
			
			//close writer file
			write.close(); 
			
			new Alert(Alert.AlertType.INFORMATION, "Student information successfully updated.").showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}//end Model class
