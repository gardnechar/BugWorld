package com.karlund.javafx;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	int height = 500;
	int width = 800;

	int mx;
	int my;

	int fx;
	int fy;

	int speed = 40;
	int number = 15;
	int foodLeft = 15;	

	Image bugImg = new Image(getClass().getResourceAsStream("kbugbuster.png"));
	Image foodImg = new Image(getClass().getResourceAsStream("leaf.png"));


	//	ArrayLists 
	static ArrayList<Bug> ladybugs = new ArrayList<Bug>();
	static ArrayList<Bug> foods = new ArrayList<Bug>();


	//List of Circles to represent objects on the map
	final Circle[] circles = new Circle[(number*2)];


	@Override
	public void start(Stage primaryStage) throws Exception {


		//create new objects for the world
		for (int i=0; i < number; i++){

			Bug moth = new Bug();
			Bug food = new Bug();

			//set a random position within the Bug World for each object
			moth.setXpos (200 + (int)(Math.random() * width-10));
			moth.setYpos (10 + (int)(Math.random() * height-10));

			food.setXpos (200 + (int)(Math.random() * width-10));
			food.setYpos (10 + (int)(Math.random() * height-10));

			//add new objects to their ArrayLists
			ladybugs.add(moth);
			foods.add(food);

		}


		//Create circles to represent the objects on the map
		for (int j = 0; j < number; j++) {

			mx = ladybugs.get(j).bugXPosition();
			my = ladybugs.get(j).bugYPosition();

			fx = foods.get(j).bugXPosition();
			fy = foods.get(j).bugYPosition();


			//new moth object with (Xpos, Ypos, radius)
			final Circle moth = new Circle (mx, my, 10);
			moth.setFill(new ImagePattern(bugImg));

			//new food object with (Xpos, Ypos, radius)
			final Circle food = new Circle (fx, fy, 10);
			food.setFill(new ImagePattern(foodImg));

			circles[j] = moth;
			circles[j+number] = food;

		}
		




		//Animation construction 
		Group root = new Group(circles); 

		VBox vb = new VBox();
		vb.setPadding(new Insets( height, 100, 50, 50));
		vb.setSpacing(10);
		vb.setStyle("-fx-background-color: rgba(139,69,19);");

		root.getChildren().add(vb);


		//add buttons
		Button button1 = new Button("Play");
		Button button2 = new Button("Pause");
		button2.setLayoutY(30);
		Button button3 = new Button("Restart");
		button3.setLayoutY(60);
		root.getChildren().add(button1); 
		root.getChildren().add(button2); 
		root.getChildren().add(button3); 

		//add labels
		final Label objectCaption = new Label("Number of Objects:");
		objectCaption.setLayoutY(90);
		root.getChildren().add(objectCaption);

		//add labels
		final Label speedCaption = new Label("Speed:");
		speedCaption.setLayoutY(140);
		root.getChildren().add(speedCaption);

		//add text field
		TextField inputText = new TextField();
		inputText.setLayoutY(110);
		root.getChildren().add(inputText);

		//add slider
		Slider sliderTime = new Slider();
		sliderTime.setMin(10);
		sliderTime.setMax(100);
		sliderTime.setValue(speed);
		sliderTime.setShowTickLabels(true);
		sliderTime.setShowTickMarks(true);
		sliderTime.setMajorTickUnit(50);
		sliderTime.setMinorTickCount(5);
		sliderTime.setBlockIncrement(10);
		sliderTime.setLayoutY(170);
		root.getChildren().add(sliderTime); 

		final Scene scene = new Scene(root, width, height, Color.GREEN);

		KeyFrame frame = new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				//search method
				searchForFood();


				//food remaining counter
				String strI = String.valueOf(foodLeft);
				primaryStage.setTitle("Food remaining " + strI);



			}
		});

		Timeline tl = TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build();


		//play button action on click
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				button1.setText("Playing");
				tl.play();
				button2.setText("Pause");

			}
		});

		//pause button action on click
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				button2.setText("Paused");
				tl.pause();
				button1.setText("Play");

			}
		});

		//reset button action on click
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				tl.pause();
				int v;
				if (inputText.getText() == null || inputText.getText().trim().isEmpty()) {
				
					v = 15;
					
				} else {
					
					int inputNumber = (int)Integer.parseInt(inputText.getText());
					
					v = inputNumber;
					inputText.clear();
					
				}
				
				number = v;
				ladybugs.clear();
				foods.clear();
				foodLeft = v;
			
				
				try {
					
					
					start(primaryStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}		

			}

			
		});


		// Listen for Slider value changes
		sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				tl.pause();
				speed = newValue.intValue();
			}
		});


		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();

	}

	public void searchForFood(){

		for (int j = 0; j < number; j++) {

			//find current positions of objects
			mx = ladybugs.get(j).bugXPosition();
			my = ladybugs.get(j).bugYPosition();

			fx = foods.get(j).bugXPosition();
			fy = foods.get(j).bugYPosition();


			//Move West if food spotted
			if (my == fy && mx > fx) {
				ladybugs.get(j).setXpos(mx-1);

			}

			//Move South if food spotted
			else if (mx == fx && mx < fx) {
				ladybugs.get(j).setYpos(my+1);

			}

			//Move North if food spotted
			else if (mx == fx && my > fy) {
				ladybugs.get(j).setYpos(my-1);	

			}

			//Move East if food spotted
			else if (my == fy && mx < fx) {
				ladybugs.get(j).setXpos(mx+1);


			}
			//no food found move north 1 step
			else {	
				ladybugs.get(j).moveBug (0, 1);

				//if you go over top boarded reset position at bottom border
				if (my == 0) {
					ladybugs.get(j).setYpos(height);
				}

			}

			//hide food off map
			if (fx == mx && my == fy) {
				circles[j+number].setCenterY(1000000);
				circles[j+number].setCenterX(1000000);

				foods.get(j).setXpos(100000);
				foods.get(j).setYpos(100000);


				foodLeft--;

				if (foodLeft == 0) {

					try        
					{
						Thread.sleep(2000);
					} 
					catch(InterruptedException ex) 
					{
						Thread.currentThread().interrupt();
					}
					System.exit(0);
				}

			}
			//update map with new bug positions
			circles[j].setCenterX(ladybugs.get(j).bugXPosition());
			circles[j].setCenterY(ladybugs.get(j).bugYPosition());



		}

	}

}