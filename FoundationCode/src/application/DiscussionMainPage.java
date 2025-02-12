package application;

import databasePart1.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This page displays a simple welcome message for the Student.
 */

public class DiscussionMainPage {

	private final DatabaseHelper databaseHelper;  // Store the database helper


	static class DiscussionCard {
		private String question;
		private String author;
		private String dateCreated;
		private int question_id;
		private boolean resolved;
		
		public DiscussionCard(String question, String author, String dateCreated, int question_id, boolean resolved) {
			super();
			this.question = question;
			this.author = author;
			this.dateCreated = dateCreated;
			this.question_id = question_id;
			this.resolved = resolved;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
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

		public int getQuestion_id() {
			return question_id;
		}

		public void setQuestion_id(int question_id) {
			this.question_id = question_id;
		}

		
		public boolean isResolved() {
			return resolved;
		}

		
		public void setResolved(boolean resolved) {
			this.resolved = resolved;
		}
		
		public String getResolvedStatus() {
			if (this.resolved) {
				return "Resolved";
			} else return "Active";
		}
		
	}
	
    // Constructor to accept DatabaseHelper
    public DiscussionMainPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage, User user) {
	    // Label to display page header
	    Label headerLabel = new Label("Discussion");
	    headerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Initialize a list containing card for listView
	    ObservableList<DiscussionCard> cardList = FXCollections.observableArrayList(
	    		new DiscussionCard(
	    				"Question mark question makr discussion carter?",
	    				"me",
	    				"02/12/2025",
	    				12,
	    				false
    				),
	    		new DiscussionCard(
	    				"Question mark question makr  carter?",
	    				"me",
	    				"02/12/1025",
	    				14,
	    				false
    				),
	    		new DiscussionCard(
	    				"Question mark question makr discussion carter?",
	    				"me",
	    				"02/12/2025",
	    				12,
	    				false
    				),
	    		new DiscussionCard(
	    				"Question mark question makr  carter?",
	    				"me",
	    				"02/12/1025",
	    				14,
	    				false
    				),
	    		new DiscussionCard(
	    				"Question mark question makr discussion carter?",
	    				"me",
	    				"02/12/2025",
	    				12,
	    				false
    				),
	    		new DiscussionCard(
	    				"Question mark question makr  carter?",
	    				"me",
	    				"02/12/1025",
	    				14,
	    				false
    				)
    		);
	    
	    ListView<DiscussionCard> discussionView = new ListView<DiscussionCard>(cardList);
	    
	    // Manipulate custom card in list view
	    discussionView.setCellFactory(param -> new ListCell<>() {
	    	@Override
	    	protected void updateItem(DiscussionCard discussionCard, boolean empty) {
	    		super.updateItem(discussionCard, empty);
	    		
	    		if (empty || discussionCard == null) {
                    setGraphic(null);
	    		}
                else {
                	Button delete = new Button("x");
                	Label question = new Label(discussionCard.getQuestion());
                	question.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    	    		Label author = new Label("Author: " + discussionCard.getAuthor());
    	    		Label dateCreated = new Label("Posted on: " + discussionCard.getDateCreated());
    	    		Label tagNumber = new Label("#" + discussionCard.getQuestion_id());
    	    		Label status = new Label("Status: " + discussionCard.getResolvedStatus());
    	    		VBox cardBox = new VBox(5, question, author, dateCreated, tagNumber, delete);
    	    		setGraphic(cardBox);
                }
	    		
	    	}
	    });
	    
	    // Question onclick event handler
	    discussionView.setOnMouseClicked(click -> {
	    	if (click.getClickCount() == 2) {
	    		DiscussionCard selectedCard = discussionView.getSelectionModel().getSelectedItem();
	    		    		
	    		// Go to answer view of the selected question
	    		new DiscussionAnswerPage(databaseHelper, null, selectedCard.question, selectedCard.author).show(primaryStage, user);
	    	}
	    });
	    
	    Button filterByDate = new Button("Filter by Date");
	    Button backButton = new Button("Go back");
	    
	    backButton.setOnAction(a -> {
	    	new WelcomeLoginPage(databaseHelper).show(primaryStage, user);
        });

	    VBox layout = new VBox(headerLabel, filterByDate, discussionView, backButton);
//	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Scene userScene = new Scene(layout, 800, 400);
	    // Set the scene to primary stage
	    primaryStage.setScene(userScene);
	    primaryStage.setTitle("Student Page");
        primaryStage.show();

    	
    }
}