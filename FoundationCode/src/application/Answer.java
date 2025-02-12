package application;

public class Answer {
	private int id;
	// Variable Declaration
	private String ans_content;
	private int question_id;
	private String time_created;
	private boolean read_flag;
	
	// Constructor
	public Answer() {
		this.id = -1;
		this.ans_content = null;;
		this.question_id = -1;
		this.time_created = null;
		this.read_flag = false;			// 1: Read, 0: Unread
	}
	
	public Answer(int id, String ans_content, int question_id, String time_created, boolean read_flag) {
		this.id = id;
		this.ans_content = ans_content;
		this.question_id = question_id;
		this.time_created = time_created;
		this.read_flag = read_flag;			// 1: Read, 0: Unread
	}

	// API Functions
	public String getAnswer() {
		return this.ans_content;
	}
	
	public String getTimeCreated() {
		return this.time_created;
	}
	
	public int getAnswerId() {
		return this.id;
	}
	
	public int getQuestionId() {
		return this.question_id;
	}
	
	public boolean getReadStatus() {
		return this.read_flag;
	}
	
	public void setReadStatus(boolean read_flag) {
		this.read_flag = read_flag;
	}
}
