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
		
		// DEV SEC
		rs.add(new Question(1, "What is CSE360 for?", "tnguy333", "02/13/2025", false));
		rs.add(new Question(2, "What is CSE325 for?", "tnguy444", "02/14/2025", false));
		rs.add(new Question(3, "What the H?", "tnguy555", "02/15/2025", false));
		
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
		// Iterate ArrayList
		for (int i = 0; i<this.question_bank.size(); i++) {
			Question ques = this.question_bank.get(i);
			if (ques.getId() == question_id) {
				return ques;
			}
		}
		return null;
	}
	
	public void addQuestion(Question question) {
		this.question_bank.add(question);
	}
	
	public void removeQuestion(Question question) {
		// Iterate ArrayList
		for (int i = 0; i<this.question_bank.size(); i++) {
			Question ques = this.question_bank.get(i);
			if (ques.getId() == question.getId()) {
				this.question_bank.remove(i);
			}
		}
	}
	
	public int getQuestionCount() {
		return this.question_bank.size();
	}
	
	public void filterQuestionBank() {
		ArrayList<Question> rs = new ArrayList<Question>();
		
		this.question_bank = rs;
	}
}
