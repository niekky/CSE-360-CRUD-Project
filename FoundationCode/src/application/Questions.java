package application;

import java.util.ArrayList;

import databasePart1.DatabaseHelper;

public class Questions {
	// Variable Declaration
	private ArrayList<Question> question_bank;
	// Constructor
	public Questions(DatabaseHelper database) {
		ArrayList<Question> rs = new ArrayList<Question>();
		
		// Request API to retrieve questions
		
		// Iterate through the polled data
		
		this.question_bank = rs;
	}
	
	// API Functions
	public void fetchQuestions(){
		ArrayList<Question> rs = new ArrayList<Question>();
		
		// Request API to retrieve questions
		
		// Iterate through the polled data
		
		this.question_bank = rs;
	}
	
	public ArrayList<Question> getAllQuestions(){
		return this.question_bank;
	}
	
	public Question getQuestion(int question_id) {
		Question result = null;
		// Iterate ArrayList
		
		return result;
	}
	
	public void filterQuestionBank() {
		ArrayList<Question> rs = new ArrayList<Question>();
		
		this.question_bank = rs;
	}
}
