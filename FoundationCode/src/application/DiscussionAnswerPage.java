package application;

import java.time.LocalDateTime;
import java.util.Random;

import databasePart1.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This page displays a simple welcome message for the Student.
 */

public class DiscussionAnswerPage {

	private final DatabaseHelper databaseHelper;  // Store the database helper
	private Question question;
	private Answers answerClass;
	private final ObservableList<Answer> cardList;

    // Constructor to accept DatabaseHelper
    public DiscussionAnswerPage(DatabaseHelper databaseHelper, Question question) {
        this.databaseHelper = databaseHelper;
        this.question = question;
        this.answerClass = new Answers(this.databaseHelper, this.question);
        this.cardList = FXCollections.observableArrayList();
    }

    public void fetchView() {
    	this.cardList.removeAll();
	    for (int i = 0; i<this.answerClass.getAnswerCount(); i++) {
	    	Answer answer = this.answerClass.getAllAnswers().get(i);
	    	cardList.add(answer);
	    }
    }
    
    public void show(Stage primaryStage, User user) {
	    // Label to display page question header
	    Label questionLabel = new Label("Question: " + this.question.getQuestion());
	    questionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    Label authorLabel = new Label("Posted by: " + this.question.getUser().getUserName());
	    authorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    Label dateLabel = new Label("Created on: ");
	    dateLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Initialize a list containing card for listView
	    fetchView();
	    
	    ListView<Answer> answerView = new ListView<Answer>(cardList);
	    
	    // Manipulate custom card in list view
	    answerView.setCellFactory(param -> new ListCell<>() {
	    	@Override
	    	protected void updateItem(Answer answer, boolean empty) {
	    		super.updateItem(answer, empty);
	    		
	    		if (empty || answer == null) {
                    setGraphic(null);
	    		}
                else {
                	Button delete = new Button("x");
                	Label answerLabel = new Label(answer.getAnswer());
                	answerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    	    		Label author = new Label("Author: " + answer.getUsername());
    	    		Label dateCreated = new Label("Posted on: " + answer.getTimeCreated());
    	    		Label statusLabel = new Label("Read ");
    	    		CheckBox readCheckBox = new CheckBox();
    	    		
    	    		readCheckBox.setIndeterminate(answer.getReadStatus());
    	    		
    	    		if (readCheckBox.isSelected()) {
    	    			// If already check, do nothing. Otherwise, update read status on DB.
    	    		}
    	    		
    	    		HBox readLayout = new HBox(statusLabel, readCheckBox);
    	    		VBox cardBox = new VBox(5, answerLabel, author, dateCreated, readLayout, delete);
    	    		setGraphic(cardBox);
                }
	    		
	    	}
	    });
	    
	    // Button to sort the question asked by date
	    Button filterByDate = new Button("Sort by Date");
	    // Go back to the last view
	    Button backButton = new Button("Go back");
	    
	    backButton.setOnAction(a -> {
	    	new DiscussionMainPage(databaseHelper).show(primaryStage, user);
        });

	    // Answer text field
	    TextField answerInput = new TextField();
	    Button submitBtn = new Button("Submit");
	    
	    // Handle answer submission event
	    submitBtn.setOnAction(a -> {
	    	// Collect input from the input text field
	    	String submittedAnswer = answerInput.getText();
	    	
	    	// Validate user input and log status to app console
	    	
	    	// Initialize new Question object
	    	Answer answer = new Answer(
	    			new Random().nextInt(100),
	    			submittedAnswer,
	    			user.getUserName(),
	    			this.question.getId(),
	    			LocalDateTime.now().toString(),
	    			false
    			);
	    	
	    	this.answerClass.addAnswer(answer);
	    	
	    	fetchView();
	    	
	    	// Clear input field
	    	answerInput.setText("");
	    });
	    
	    HBox answerInputLayout = new HBox(new Label("Type your answer: "), answerInput, submitBtn);
	    answerInputLayout.setHgrow(answerInput, Priority.ALWAYS);
	    
	    VBox layout = new VBox(questionLabel, authorLabel, dateLabel, answerView, answerInputLayout, backButton);
//	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Scene userScene = new Scene(layout, 800, 400);
	    // Set the scene to primary stage
	    primaryStage.setScene(userScene);
	    primaryStage.setTitle("Student Page");
        primaryStage.show();

    	
    }
}