import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FinalPractical extends Application {
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		BorderPane bpane = createPane();
		Scene scene = new Scene(bpane, 600, 600);
		window.setScene(scene);
		window.show();
	}

	private BorderPane createPane() {
		BorderPane bpane = new BorderPane();

		HBox topB = new HBox(200);
		Label lblName = new Label("Danial Qumsieh");
		Label lblTitle = new Label("SWER142");
		Label lblNumber = new Label("201904045");
		
		topB.setAlignment(Pos.CENTER);
		topB.getChildren().addAll(lblName, lblTitle, lblNumber);

		
		ClockPane clock = new ClockPane();
		Label digitalClock = new Label();
		// Create a handler for animation
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime();
			Calendar calendar = GregorianCalendar.getInstance();
			String hourString = pad(2, '0', calendar.get(Calendar.HOUR_OF_DAY) + "");
			String minuteString = pad(2, '0', calendar.get(Calendar.MINUTE) + "");
			digitalClock.setText("Time is: " + hourString + ": " + minuteString);
			digitalClock.setStyle("-fx-font-size:20; -fx-background-color: white; -fx-border-color: black;");
		};

		// Create an animation for a running clock
		KeyFrame key = new KeyFrame(Duration.millis(1000), eventHandler);
		Timeline animation = new Timeline(key);
		
		
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		bpane.setBottom(digitalClock);
		BorderPane.setAlignment(digitalClock, Pos.CENTER);

		bpane.setCenter(calculatorPane());
		bpane.setTop(topB);
		

		return bpane;

	}

	private GridPane calculatorPane() {
		GridPane gpane = new GridPane();
		gpane.setVgap(5);
		gpane.setHgap(10);
		gpane.setStyle("-fx-border-color: black; -fx-background-color: white;");
		gpane.setAlignment(Pos.CENTER);

		StackPane fPane = new StackPane();
		TextField txtF = new TextField("0");
		Label lblF = new Label("First Number", txtF);
		lblF.setContentDisplay(ContentDisplay.BOTTOM);
		fPane.getChildren().addAll(lblF, txtF);
		fPane.setAlignment(Pos.CENTER_LEFT);
		gpane.add(fPane, 0, 1);
		gpane.setHalignment(fPane, HPos.RIGHT);

		StackPane sPane = new StackPane();
		TextField txtS = new TextField("0");
		Label lblS = new Label("Second Number", txtS);
		lblS.setContentDisplay(ContentDisplay.BOTTOM);
		sPane.getChildren().addAll(lblS, txtS);
		sPane.setAlignment(Pos.CENTER_RIGHT);
		gpane.add(sPane, 3, 1);
		gpane.setHalignment(sPane, HPos.RIGHT);

		StackPane rPane = new StackPane();
		TextField txtResult = new TextField("0");
		Label lblResult = new Label("result=", txtResult);
		lblResult.setContentDisplay(ContentDisplay.RIGHT);
		rPane.getChildren().addAll(lblResult);
		gpane.add(rPane, 2, 2);

		VBox paneForButtons = new VBox();
		paneForButtons.setPadding(new Insets(5, 5, 5, 5));
		RadioButton rbPlus = new RadioButton("Plus(+)");
		RadioButton rbMinus = new RadioButton("Minus(-)");
		RadioButton rbDivide = new RadioButton("Divide(/)");
		RadioButton rbMultiply = new RadioButton("Multiply(X)");
		paneForButtons.getChildren().addAll(rbPlus, rbMinus, rbMultiply, rbDivide);
		rbPlus.setSelected(true);


		txtS.setOnKeyReleased(e-> {
			Double value1 = Double.valueOf(txtF.getText());
	    	  Double value2 = Double.valueOf(txtS.getText());
			if (rbPlus.isSelected()) {
		    	  
		    	  Double r = value1 + value2;
					txtResult.setText(r.toString());
		      }
			else if (rbMinus.isSelected()) {
		    	 
		    	  Double r = value1 - value2;
					txtResult.setText(r.toString());
		      }
			else if (rbMultiply.isSelected()) {
		    	 
		    	  Double r = value1 * value2;
					txtResult.setText(r.toString());
		      }
			else if (rbDivide.isSelected()) {
		    	  
		    	  Double r = value1 / value2;
					txtResult.setText(r.toString());
		      }
			
		});
		txtF.setOnKeyReleased(e-> {
			Double value1 = Double.valueOf(txtF.getText());
	    	  Double value2 = Double.valueOf(txtS.getText());
			if (rbPlus.isSelected()) {
		    	 
		    	  Double r = value1 + value2;
					txtResult.setText(r.toString());
		      }
			else if (rbMinus.isSelected()) {
		    	 
		    	  Double r = value1 - value2;
					txtResult.setText(r.toString());
		      }
			else if (rbMultiply.isSelected()) {
		    	
		    	  Double r = value1 * value2;
					txtResult.setText(r.toString());
		      }
			else if (rbDivide.isSelected()) {
		    	 
		    	  Double r = value1 / value2;
					txtResult.setText(r.toString());
		      }
			
			
		});
		rbPlus.setOnAction(e -> {
		      if (rbPlus.isSelected()) {
		    	  Double value1 = Double.valueOf(txtF.getText());
		    	  Double value2 = Double.valueOf(txtS.getText());
		    	  Double r = value1 + value2;
					txtResult.setText(r.toString());
		      }
		    });
		rbMinus.setOnAction(e -> {
		      if (rbMinus.isSelected()) {
		    	  Double value1 = Double.valueOf(txtF.getText());
		    	  Double value2 = Double.valueOf(txtS.getText());
		    	  Double r = value1 - value2;
					txtResult.setText(r.toString());
		      }
		    });
		rbDivide.setOnAction(e -> {
		      if (rbDivide.isSelected()) {
		    	  Double value1 = Double.valueOf(txtF.getText());
		    	  Double value2 = Double.valueOf(txtS.getText());
		    	  Double r = value1 / value2;
					txtResult.setText(r.toString());
		      }
		    });
		rbMultiply.setOnAction(e -> {
		      if (rbMultiply.isSelected()) {
		    	  Double value1 = Double.valueOf(txtF.getText());
		    	  Double value2 = Double.valueOf(txtS.getText());
		    	  Double r = value1 * value2;
					txtResult.setText(r.toString());
		      }
		    });
		


		gpane.add(paneForButtons, 2, 1);
		gpane.setHalignment(paneForButtons, HPos.RIGHT);

		return gpane;
	}
	private String pad(int fieldWidth, char c, String string) {
		StringBuilder numbers = new StringBuilder();
		for (int i = string.length(); i < fieldWidth; i++) {
			numbers.append(c);
		}
		numbers.append(string);

		return numbers.toString();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
