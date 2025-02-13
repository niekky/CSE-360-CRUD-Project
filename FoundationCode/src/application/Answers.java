package application;

import java.util.ArrayList;

import databasePart1.DatabaseHelper;

public class Answers {
	// Variable Declaration
	private ArrayList<Answer> answer_bank;
	private DatabaseHelper database;
	// Constructor
	public Answers(DatabaseHelper database, Question question) {
		ArrayList<Answer> rs = new ArrayList<Answer>();
		this.database = database;
		
		// Request API to retrieve questions
		rs = this.database.getAnswers(question);

		this.answer_bank = rs;
	}
	
	// API Functions	
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
	
	public void addAnswer(Answer answer) {
		this.answer_bank.add(answer);
		this.database.addAnswer(answer);
	}
	
	public void removeAnswer(Answer answer) {
		// Iterate ArrayList
		for (int i = 0; i<this.answer_bank.size(); i++) {
			Answer ans = this.answer_bank.get(i);
			if (ans.getAnswerId() == ans.getAnswerId()) {
				this.database.deleteAnswer(answer);
				this.answer_bank.remove(i);
			}
		}
	}
	
	public int getAnswerCount() {
		return this.answer_bank.size();
	}
	
}
