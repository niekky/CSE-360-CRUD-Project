package application;

import java.util.ArrayList;

import databasePart1.DatabaseHelper;

public class Answers {
	// Variable Declaration
	private ArrayList<Answer> answer_bank;
	private int answers_count;
	
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
		// Iterate ArrayList
		for (int i = 0; i<this.answer_bank.size(); i++) {
			Answer ans = this.answer_bank.get(i);
			if (ans.getAnswerId() == answer_id) {
				return ans;
			}
		}
		return null;
	}
	
}
