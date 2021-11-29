package application.model;

public class Student{
	String quiz;
	String labs;
	String midterm;
	String finalExam;
	String project;
	static private String id;
	
	//getters and setters
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	public String getLabs() {
		return labs;
	}
	public void setLabs(String labs) {
		this.labs = labs;
	}
	public String getMidterm() {
		return midterm;
	}
	public void setMidterm(String midterm) {
		this.midterm = midterm;
	}
	public String getFinalExam() {
		return finalExam;
	}
	public void setFinalExam(String finalExam) {
		this.finalExam = finalExam;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	static public String getID() {
		return id;
	}
	static public void setID(String i) {
		id = i;
	}
	
	
	//constructor
	public Student(String quiz, String labs, String midterm, String finalExam, String project) {
		this.quiz = quiz;
		this.labs = labs;
		this.midterm = midterm;
		this.finalExam = finalExam;
		this.project = project;
	}
}
