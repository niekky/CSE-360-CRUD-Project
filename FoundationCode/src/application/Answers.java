package application;

import java.util.ArrayList;

import databasePart1.DatabaseHelper;

public class Answers {
	// Variable Declaration
	private ArrayList<Answer> answer_bank;
	// Constructor
	public Answers(DatabaseHelper database, Question question) {
		ArrayList<Answer> rs = new ArrayList<Answer>();
		
		// Request API to retrieve questions
		
		// Iterate through the polled data
		
		this.answer_bank = rs;
	}
	
	// API Functions
	public void fetchQuestions(){
		ArrayList<Answer> rs = new ArrayList<Answer>();
		
		// Request API to retrieve questions
		
		// Iterate through the polled data
		
		this.answer_bank = rs;
	}
	
	public ArrayList<Answer> getAllAnswers(){
		return this.answer_bank;
	}
	
	public Answer getAnswer(int answer_id) {
		Answer result = null;
		// Iterate ArrayList
		
		return result;
	}
	
}
