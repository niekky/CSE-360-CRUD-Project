package application;

import java.util.ArrayList;

public class Question {
	private int id;
	// Variable Declaration
	private String ques_content;
	private String username;
	private String time_created;
	private boolean resolved;
	// Constructor
	public Question() {
		this.id = -1;
		this.ques_content = null;
		this.username = null;
		this.time_created = null;
		this.resolved = false;
	}
	
	public Question(int id, String ques_content, String username, String time_created, boolean resolved) {
		this.id = id;
		this.ques_content = ques_content;
		this.username = username;
		this.time_created = time_created;
		this.resolved = resolved;			// 1: Resolved, 0: Unresolved
	}

	// API Functions
	public String getQuestion() {
		return this.ques_content;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getTimeCreated() {
		return this.time_created;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean getResolved() {
		return this.resolved;
	}
	
	public void setResolved(boolean resolved_status) {
		this.resolved = resolved_status;
	}
	
	public ArrayList<Answer> getAllAnswers(){
		ArrayList<Answer> rs = new ArrayList<Answer>();
		
		return rs;
	}
}
