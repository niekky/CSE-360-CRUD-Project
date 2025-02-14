package testing;

import java.util.ArrayList;

import application.Answer;
import application.Answers;
import application.Question;
import application.Questions;
import application.User;
import databasePart1.DatabaseHelper;

public class CRUD_testing {
	static DatabaseHelper databaseHelper = new DatabaseHelper();
	static User user1 = databaseHelper.getUser("tnguy231");
	static User user2 = databaseHelper.getUser("tnguy232");
	
	
	public static void main(String[] args) {
		Question ques1 = new Question(
				500,
				"This is a testing question?",
				user1,
				"02/13/2025",
				false);
		
		Answer ans1 = new Answer(
				1,
				"This is a test answer",
				user1,
				ques1,
				"02/13/2025",
				false
			);
		
//		// Test creating a question
//		System.out.println(performCreateQuesTesting(questionClass, ques1, user1));
//		
//		// Test creating a answer
//		System.out.println(performCreateAnsTesting(ans1, ques1, user1));
//		
//		// Test reading all questions
//		performReadAllQuesTesting(questionClass);
//		
//		// Test reading all answers
//		performReadAllAnsTesting(questionClass);
//		
//		// Test remove the question
//		performQuestionDeletion(questionClass, user1);
//		performQuestionDeletion(questionClass, user2);
//		
//		// Test update the question
//		performQuestionStatusUpdate();
	}
	
	public static boolean performCreateQuesTesting(Questions questionClass, Question question, User user) {
		// Create a question
		questionClass.addQuestion(question);
		
		// Test if the question is in the database
		ArrayList<Question> retrieve = databaseHelper.getQuestions();
		for (int i = 0; i < retrieve.size(); i++) {
			if (retrieve.get(i).getId() == question.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean performCreateAnsTesting(Answer answer, Question question, User user) {
		// Create a question
		Answers answerClass = new Answers(databaseHelper, question);
		answerClass.addAnswer(answer);
		
		// Test if the question is in the database
		ArrayList<Answer> retrieve = databaseHelper.getAnswers(question);
		for (int i = 0; i < retrieve.size(); i++) {
			if (retrieve.get(i).getAnswerId() == answer.getAnswerId()) {
				return true;
			}
		}
		return false;
	}
	
	public static void performReadAllQuesTesting(Questions questionClass) {
		// Delete the database
		
		// Insert 2 questions
		Question ques1 = new Question(
				1,
				"Test question 1",
				user1,
				"11/02/2025",
				false
			);
		
		Question ques2 = new Question(
				2,
				"Test question 2",
				user1,
				"11/02/2025",
				false
			);
		
		questionClass.addQuestion(ques1);
		questionClass.addQuestion(ques2);
		
		// Check expected data
		ArrayList<Question> retrieve = databaseHelper.getQuestions();
		for (int i = 0; i < retrieve.size(); i++) {
			System.out.println("Data " + i);
			System.out.println("Ques_id " + retrieve.get(i).getId());
			System.out.println("Ques content " + retrieve.get(i).getQuestion());
			System.out.println("User id " + retrieve.get(i).getUser().getUserID());
			System.out.println("Time created " + retrieve.get(i).getTimeCreated());
			System.out.println("Resolved " + retrieve.get(i).getResolved());
		}
	}
	
	public static void performReadAllAnsTesting(Questions questionClass) {
		// Insert 1 questions
		Question ques = new Question(
				1,
				"Test question 1",
				user1,
				"11/02/2025",
				false
			);
		
		// Insert 2 answers
		Answer ans1 = new Answer(
				1,
				"This is a test answer",
				user1,
				ques,
				"02/13/2025",
				false
			);
		
		Answer ans2 = new Answer(
				2,
				"This is a test answer 2",
				user2,
				ques,
				"02/13/2025",
				false
			);
		
		questionClass.addQuestion(ques);
		Answers answerClass = new Answers(databaseHelper, ques);
		answerClass.addAnswer(ans1);
		answerClass.addAnswer(ans2);
		
		// Check expected data
		ArrayList<Answer> retrieve = databaseHelper.getAnswers(ques);
		for (int i = 0; i < retrieve.size(); i++) {
			System.out.println("Data " + i);
			System.out.println("Ans_id " + retrieve.get(i).getAnswerId());
			System.out.println("Ans content " + retrieve.get(i).getAnswer());
			System.out.println("User id " + retrieve.get(i).getUser().getUserID());
			System.out.println("Question_id " + retrieve.get(i).getQuestion().getId());
			System.out.println("Time created " + retrieve.get(i).getTimeCreated());
			System.out.println("Read " + retrieve.get(i).getReadStatus());
		}
	}
	
	public static void performQuestionDeletion(Questions questionClass, User user) {
		// Insert two questions
		Question ques1 = new Question(
				1,
				"Test question 1",
				user1,
				"11/02/2025",
				false
			);
		Question ques2 = new Question(
				2,
				"Test question 2",
				user2,
				"11/02/2025",
				false
			);
		
		questionClass.addQuestion(ques1);
		questionClass.addQuestion(ques2);
		
		// Delete 
		questionClass.removeQuestion(ques1, user);
		
		questionClass.removeQuestion(ques2, user);
		
		// Check expected data
		ArrayList<Question> retrieve = databaseHelper.getQuestions();
		for (int i = 0; i < retrieve.size(); i++) {
			System.out.println("Data " + i);
			System.out.println("Ques_id " + retrieve.get(i).getId());
			System.out.println("Ques content " + retrieve.get(i).getQuestion());
			System.out.println("User id " + retrieve.get(i).getUser().getUserID());
			System.out.println("Time created " + retrieve.get(i).getTimeCreated());
			System.out.println("Resolved " + retrieve.get(i).getResolved());
		}
	}
	
	public static void performQuestionStatusUpdate() {
		// Insert a question
		Question ques = new Question(
			1,
			"Test question 1",
			user1,
			"11/02/2025",
			false
		);
		
		databaseHelper.addQuestion(ques.getId(), ques.getQuestion(), ques.getUser().getUserID(), ques.getTimeCreated(), ques.getResolved());
		
		System.out.println("Before update");
		System.out.println("Ques_id " + ques.getId());
		System.out.println("Ques content " + ques.getQuestion());
		System.out.println("User id " + ques.getUser().getUserID());
		System.out.println("Time created " + ques.getTimeCreated());
		System.out.println("Resolved " + ques.getResolved());
		
		// Update a question
		databaseHelper.updateQuestionStatus(ques);
		
		System.out.println("After update");
		System.out.println("Ques_id " + ques.getId());
		System.out.println("Ques content " + ques.getQuestion());
		System.out.println("User id " + ques.getUser().getUserID());
		System.out.println("Time created " + ques.getTimeCreated());
		System.out.println("Resolved " + ques.getResolved());
	}
}