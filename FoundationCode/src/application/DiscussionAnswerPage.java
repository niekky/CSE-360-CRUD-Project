package application;

import databasePart1.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This page displays a simple welcome message for the Student.
 */

public class DiscussionAnswerPage {

	private final DatabaseHelper databaseHelper;  // Store the database helper
	private Question question;
	
	static class AnswerCard {
		private String answer;
		private String author;
		private String dateCreated;
		private boolean read;
		
		public AnswerCard(String answer, String author, String dateCreated, boolean read) {
			super();
			this.answer = answer;
			this.author = author;
			this.dateCreated = dateCreated;
			this.read = read;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
		}

		public boolean isRead() {
			return read;
		}

		public void setRead(boolean read) {
			this.read = read;
		}
	}
	
    // Constructor to accept DatabaseHelper
    public DiscussionAnswerPage(DatabaseHelper databaseHelper, Question question) {
        this.databaseHelper = databaseHelper;
        this.question = question;
    }

    public void show(Stage primaryStage, User user) {
	    // Label to display page question header
	    Label questionLabel = new Label("Question: " + this.question.getQuestion());
	    questionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    Label authorLabel = new Label("Posted by: " + this.question.getUsername());
	    authorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    Label dateLabel = new Label("Created on: ");
	    dateLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Initialize a list containing card for listView
	    ObservableList<AnswerCard> cardList = FXCollections.observableArrayList(
	    		new AnswerCard(
	    				"Answer111",
	    				"test_author",
	    				"02/12/2025",
	    				false
    				),
	    		new AnswerCard(
	    				"Answer122",
	    				"test_author",
	    				"02/12/2025",
	    				true
    				)
    		);
	    
	    ListView<AnswerCard> answerView = new ListView<AnswerCard>(cardList);
	    
	    // Manipulate custom card in list view
	    answerView.setCellFactory(param -> new ListCell<>() {
	    	@Override
	    	protected void updateItem(AnswerCard answerCard, boolean empty) {
	    		super.updateItem(answerCard, empty);
	    		
	    		if (empty || answerCard == null) {
                    setGraphic(null);
	    		}
                else {
                	Button delete = new Button("x");
                	Label answer = new Label(answerCard.getAnswer());
                	answer.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    	    		Label author = new Label("Author: " + answerCard.getAuthor());
    	    		Label dateCreated = new Label("Posted on: " + answerCard.getDateCreated());
    	    		Label statusLabel = new Label("Read ");
    	    		CheckBox readCheckBox = new CheckBox();
    	    		
    	    		readCheckBox.setIndeterminate(answerCard.isRead());
    	    		
    	    		if (readCheckBox.isSelected()) {
    	    			// If already check, do nothing. Otherwise, update read status on DB.
    	    		}
    	    		
    	    		HBox readLayout = new HBox(statusLabel, readCheckBox);
    	    		VBox cardBox = new VBox(5, answer, author, dateCreated, readLayout, delete);
    	    		setGraphic(cardBox);
                }
	    		
	    	}
	    });
	    
	    Button filterByDate = new Button("Filter by Date");
	    Button backButton = new Button("Go back");
	    
	    backButton.setOnAction(a -> {
	    	new DiscussionMainPage(databaseHelper).show(primaryStage, user);
        });

	    VBox layout = new VBox(questionLabel, authorLabel, dateLabel, answerView, backButton);
//	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Scene userScene = new Scene(layout, 800, 400);
	    // Set the scene to primary stage
	    primaryStage.setScene(userScene);
	    primaryStage.setTitle("Student Page");
        primaryStage.show();

    	
    }
}