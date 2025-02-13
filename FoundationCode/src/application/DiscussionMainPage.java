package application;

import java.time.LocalDateTime;
import java.util.Random;

import databasePart1.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class DiscussionMainPage {

	private final DatabaseHelper databaseHelper;  // Store the database helper
	private final Questions questionClass;
	private final ObservableList<Question> cardList;
	
	
    // Constructor
    public DiscussionMainPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        this.questionClass = new Questions(this.databaseHelper);
        this.cardList = FXCollections.observableArrayList();
    }

    public void fetchView() {
    	this.cardList.removeAll();
	    for (int i = 0; i<this.questionClass.getQuestionCount(); i++) {
	    	Question question = this.questionClass.getAllQuestions().get(i);
	    	cardList.add(question);
	    }
    }
    
    public void show(Stage primaryStage, User user) {
	    // Label to display page header
	    Label headerLabel = new Label("Discussion");
	    headerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Initialize a list containing card for listView
	    fetchView();
	    
	    
	    ListView<Question> discussionView = new ListView<Question>(cardList);
	    
	    // Manipulate custom card in list view
	    discussionView.setCellFactory(param -> new ListCell<>() {
	    	@Override
	    	protected void updateItem(Question question, boolean empty) {
	    		super.updateItem(question, empty);
	    		
	    		if (empty || question == null) {
                    setGraphic(null);
	    		}
                else {
                	Button delete = new Button("x");
                	Label questionLabel = new Label(question.getQuestion());
                	questionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    	    		Label author = new Label("Author: " + question.getUsername());
    	    		Label dateCreated = new Label("Posted on: " + question.getTimeCreated());
    	    		Label tagNumber = new Label("#" + question.getId());
    	    		Label status = new Label("Status: " + question.getResolved());
    	    		VBox cardBox = new VBox(5, questionLabel, author, dateCreated, tagNumber, delete);
    	    		setGraphic(cardBox);
                }
	    		
	    	}
	    });
	    
	    // Question onclick event handler
	    discussionView.setOnMouseClicked(click -> {
	    	if (click.getClickCount() == 2) {
	    		Question selectedQuestion = discussionView.getSelectionModel().getSelectedItem();
	    		    		
	    		// Go to answer view of the selected question
	    		new DiscussionAnswerPage(databaseHelper, selectedQuestion).show(primaryStage, user);
	    	}
	    });
	    
	    // Button to sort the question asked by date
	    Button filterByDate = new Button("Sort by Date");
	    // Go back to the last view
	    Button backButton = new Button("Go back");
	    backButton.setOnAction(a -> {
	    	new WelcomeLoginPage(databaseHelper).show(primaryStage, user);
        });
	    
	    
	    // Question text field
	    TextField questionInput = new TextField();
	    Button submitBtn = new Button("Submit");
	    
	    // Handle question submission event
	    submitBtn.setOnAction(a -> {
	    	// Collect input from the input text field
	    	String submittedQuestion = questionInput.getText();
	    	
	    	// Validate user input and log status to app console
	    	
	    	// Initialize new Question object
	    	Question question = new Question(
	    			new Random().nextInt(100),
	    			submittedQuestion,
	    			user.getUserName(),
	    			LocalDateTime.now().toString(),
	    			false
    			);
	    	
	    	this.questionClass.addQuestion(question);
	    	
	    	fetchView();
	    	
	    	// Clear input field
	    	questionInput.setText("");
	    });
	    
	    HBox questionInputLayout = new HBox(new Label("Type your question: "), questionInput, submitBtn);
	    questionInputLayout.setHgrow(questionInput, Priority.ALWAYS);
	    
	    VBox layout = new VBox(headerLabel, filterByDate, discussionView, questionInputLayout, backButton);
//	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Scene userScene = new Scene(layout, 800, 400);
	    // Set the scene to primary stage
	    primaryStage.setScene(userScene);
	    primaryStage.setTitle("Student Page");
        primaryStage.show();

    	
    }
}