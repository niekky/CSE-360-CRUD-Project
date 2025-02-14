package application;

import java.util.ArrayList;

import databasePart1.DatabaseHelper;

public class Questions {
	// Variable Declaration
	private ArrayList<Question> question_bank;
	private DatabaseHelper database;
	
	// Constructor
	public Questions(DatabaseHelper database) {
		this.database = database;
		
		ArrayList<Question> rs = new ArrayList<Question>();
		
		// Request API to retrieve questions
		rs = database.getQuestions();
		
		this.question_bank = rs;
	}
	
	// API Functions
	
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
		
		// Insert to SQL database
		this.database.addQuestion(
				question.getId(), 
				question.getQuestion(), 
				question.getUser().getUserID(), 
				question.getTimeCreated(), 
				question.getResolved()
			);
	}
	
	public void removeQuestion(Question question, User user) {
		// Iterate ArrayList
		for (int i = 0; i<this.question_bank.size(); i++) {
			Question ques = this.question_bank.get(i);
			if (ques.getId() == question.getId()) {
				database.deleteQuestion(this.question_bank.get(i), user);
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
