package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class readID {
	private String id;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void createCSV(String file) throws IOException {
		file = System.getProperty("user.dir") + "\\" + file;
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		br.readLine();
		while ((line = br.readLine()) != null) {
			// splits the line to only get the student id
			String[] words = line.split("=");
			//creates the csv files named after student id's
			FileWriter csvWriter = new FileWriter(words[0] + ".csv", true);
			csvWriter.close();
		}
		br.close();
	}
}