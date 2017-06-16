/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observer;
import java.awt.event.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;


/**
 *
 * @author Ben
 */
public class TViewFX implements Observer{
    
    private TModel model;
    private TController controller;
    
    private HBox view;
    GridPane left;
    GridPane buttons;
    GridPane previousSets;
    GridPane players;
    GridPane currentScore;
    GridPane currentScoreHeadings;
    
    //Text field creation of the score
    final private TextField pointP1 = new TextField();
    final private TextField pointP2 = new TextField();
    final private TextField gameP1 = new TextField();
    final private TextField gameP2 = new TextField();
    final private TextField setP1 = new TextField();
    final private TextField setP2 = new TextField();
    
    //Text field creation for previous sets
    final private Label previousSetHeading = new Label("PREVIOUS SETS");
    
    final private TextField set1p1 = new TextField();
    final private TextField set1p2 = new TextField();
    final private TextField set2p1 = new TextField();
    final private TextField set2p2 = new TextField();
    final private TextField set3p1 = new TextField();
    final private TextField set3p2 = new TextField();
    final private TextField set4p1 = new TextField();
    final private TextField set4p2 = new TextField();
    
    private TextField player1;
    final private TextField vs = new TextField("VS");
    private TextField player2;
    
    final private Label points = new Label("POINTS");
    final private Label games = new Label("GAMES");
    final private Label sets = new Label("SETS");
    
    final private Button scoreP1Button = new Button("Win Point");
    final private Button scoreP2Button = new Button("Win Point");
    
    //Dimensions for the three main panes
    int x = 300;
    int y = 100;
    //Dimensions for the headings
    int headingX = 300;
    int headingY = 25;
    //Dimensions for scoring fields
    int pointSize = 50;
   
    public TViewFX(TController controller, TModel model) {
        this.controller = controller;
        this.model = model;
        
        player1 = new TextField(model.getPlayer1().getName());
        player2 = new TextField(model.getPlayer2().getName());
        
        configurePane();
        model.addObserver(this);
        //Give the controller the view for Java FX
        controller.setViewJFX(this);
        //Run update just like in the normal view
        update(model, null);
    }
    
    public Parent asParent() {
        return view;
    }
    
    @Override
    public void update(java.util.Observable o, Object arg){
        //Rules for displaying the points fields getting the values from player 1's state
        if (model.getPlayer1().getPoints()==0)pointP1.setText("0");
        if (model.getPlayer1().getPoints()==1)pointP1.setText("15");
        if (model.getPlayer1().getPoints()==2)pointP1.setText("30");
        if (model.getPlayer1().getPoints()==3)pointP1.setText("40");
        if (model.getPlayer1().getAdv()==false) pointP1.setText("40");
        //If the player has an advantage display "A"
        if (model.getPlayer1().getAdv()==true && model.getPlayer1().getPoints()>=4) pointP1.setText("A");
        //Set the text of the games and sets based off the models values
        gameP1.setText(String.valueOf(model.getPlayer1().getGames()));
        setP1.setText(String.valueOf(model.getPlayer1().getSets()));
        //If the player 1 has won
        if (model.getPlayer1().getWin()==true){
            //Disable all the buttons
            scoreP1Button.setDisable(true);
            scoreP2Button.setDisable(true);
            setLabelColour(Color.GRAY);
            setTextColour("gray");
            player1.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-inner-color: red; fx-background-color: black");
        }
        //Set the text of the previous sets based off the value stored in the model for player 1
        set1p1.setText(String.valueOf(model.getPlayer1().getPreviousSet()[0]));
        set2p1.setText(String.valueOf(model.getPlayer1().getPreviousSet()[1]));
        set3p1.setText(String.valueOf(model.getPlayer1().getPreviousSet()[2]));
        set4p1.setText(String.valueOf(model.getPlayer1().getPreviousSet()[3]));
        
        //Rules for displaying the points fields getting the values from player 2's state
        if (model.getPlayer2().getPoints()==0)pointP2.setText("0");
        if (model.getPlayer2().getPoints()==1)pointP2.setText("15");
        if (model.getPlayer2().getPoints()==2)pointP2.setText("30");
        if (model.getPlayer2().getPoints()==3)pointP2.setText("40");
        if (model.getPlayer2().getAdv()==false) pointP2.setText("40");
        //If the player has an advantage display "A"
        if (model.getPlayer2().getAdv()==true && model.getPlayer2().getPoints()>=4) pointP2.setText("A");
        //Set the text of the games and sets based off the models values
        gameP2.setText(String.valueOf(model.getPlayer2().getGames()));
        setP2.setText(String.valueOf(model.getPlayer2().getSets()));
        //If the player 2 has won
        if (model.getPlayer2().getWin()==true){
            //Disable all the buttons
            scoreP1Button.setDisable(true);
            scoreP2Button.setDisable(true);   
            setLabelColour(Color.GRAY);
            setTextColour("gray");
            player2.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-inner-color: red; fx-background-color: black");
        }
        //Set the text of the previous sets based off the value stored in the model for player 2
        set1p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[0]));
        set2p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[1]));
        set3p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[2]));
        set4p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[3]));
    }
    
    private void configurePane() {
        view = new HBox();
        
        VBox right = new VBox();
        left = new GridPane();
        
        view.setStyle("-fx-background-color: rgba(50,101,0);");
        buttons = new GridPane();
        
        previousSets = new GridPane();
        players = new GridPane();
        currentScore = new GridPane();
        currentScoreHeadings = new GridPane();
        
        view.getChildren().addAll(left,right);
        
        //RIGHT PANEL BUTTONS
        buttons.setMinWidth(100);
        
        right.getChildren().add(buttons);
        
        buttons.add(scoreP1Button, 0, 0);
        buttons.add(scoreP2Button, 0, 1);
        
        buttons.setStyle("-fx-font-size: 12px; fx-background-color: rgba(50,101,0);");
        buttons.setAlignment(Pos.CENTER);
        scoreP1Button.setPrefSize(110, 75);
        scoreP2Button.setPrefSize(110, 75);
        scoreP1Button.setStyle("-fx-background-color: rgba(50,101,0); -fx-border-color: gray; -fx-font-weight: bold;");
        scoreP2Button.setStyle("-fx-background-color: rgba(50,101,0); -fx-border-color: gray; -fx-font-weight: bold;");
        
        //LEFT PANEL add all other panels
        left.add(previousSetHeading, 0, 0);
        left.add(previousSets, 0, 1);
        left.add(players, 1, 1);
        left.add(currentScore, 2, 1);
        left.add(currentScoreHeadings, 2, 0);
        
        //LEFT PANEL PREVIOUS SETS
        
        //heading size and alignment
        previousSetHeading.setPrefSize(headingX, headingY);
        
        previousSetHeading.setStyle("-fx-font-size: 10px; -fx-background-color: rgba(50,101,0);");
        
        previousSetHeading.setAlignment(Pos.BOTTOM_LEFT);
        
        //previous sets field operations
        previousSets.setPrefSize(x, y);
        
        //Make the backgrounds black and remove borders
        set1p1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set1p2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set2p1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set2p2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set3p1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set3p2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set4p1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        set4p2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        
        //Center the font in previous sets
        set1p1.setAlignment(Pos.CENTER);
        set1p2.setAlignment(Pos.CENTER);
        set2p1.setAlignment(Pos.CENTER);
        set2p2.setAlignment(Pos.CENTER);
        set3p1.setAlignment(Pos.CENTER);
        set3p2.setAlignment(Pos.CENTER);
        set4p1.setAlignment(Pos.CENTER);
        set4p2.setAlignment(Pos.CENTER);
        
        //set preferred dimensions of previous sets
        set1p1.setPrefSize(pointSize, pointSize);
        set1p2.setPrefSize(pointSize, pointSize);
        set2p1.setPrefSize(pointSize, pointSize);
        set2p2.setPrefSize(pointSize, pointSize);
        set3p1.setPrefSize(pointSize, pointSize);
        set3p2.setPrefSize(pointSize, pointSize);
        set4p1.setPrefSize(pointSize, pointSize);
        set4p2.setPrefSize(pointSize, pointSize);
        
        //heading size and alignment
        previousSetHeading.setPrefSize(headingX, headingY);
        previousSetHeading.setAlignment(Pos.BOTTOM_LEFT);
        
        //Add to the grid pane        
        previousSets.add(set1p1, 0, 1);
        previousSets.add(set1p2, 0, 2);
        previousSets.add(set2p1, 1, 1);
        previousSets.add(set2p2, 1, 2);
        previousSets.add(set3p1, 2, 1);
        previousSets.add(set3p2, 2, 2);
        previousSets.add(set4p1, 3, 1);
        previousSets.add(set4p2, 3, 2);
        
        //Disable the text fields
        set1p1.setEditable(false);
        set1p2.setEditable(false);
        set2p1.setEditable(false);
        set2p2.setEditable(false);
        set3p1.setEditable(false);
        set3p2.setEditable(false);
        set4p1.setEditable(false);
        set4p2.setEditable(false);
        
        previousSets.setAlignment(Pos.CENTER);
        
        //LEFT PANEL PLAYER NAMES
        
        players.setStyle("-fx-font-size: 14px;");
        
        players.add(player1, 0, 0);
        players.add(vs, 0, 1);
        players.add(player2, 0, 2);
        
        //Alignment setting
        players.setAlignment(Pos.CENTER);
        //Dimension of middle panel
        players.setPrefSize(x, y);
        
        //pref size
        player1.setPrefSize(x, y/3);
        vs.setPrefSize(x, y/3);
        player2.setPrefSize(x, y/3);
        
        //alignment
        player1.setAlignment(Pos.CENTER);
        vs.setAlignment(Pos.CENTER);
        player2.setAlignment(Pos.CENTER);
        
        //style of font for players
        player1.setStyle("-fx-background-color: black; -fx-font-weight: bold; -fx-border-color: black");
        vs.setStyle("-fx-background-color: black; -fx-font-weight: bold; -fx-border-color: black");
        player2.setStyle("-fx-background-color: black; -fx-font-weight: bold; -fx-border-color: black");
        
        //Set editable false
        player1.setEditable(false);
        vs.setEditable(false);
        player2.setEditable(false);
        
        
        //LEFT PANEL CURRENT SCORES
        //headings
        currentScoreHeadings.setPrefSize(headingX, headingY);
        
        currentScoreHeadings.setStyle("-fx-font-size: 10px");
        
        sets.setPrefSize(pointSize, pointSize);
        games.setPrefSize(pointSize, pointSize);
        points.setPrefSize(pointSize, pointSize);
        
        currentScoreHeadings.add(sets, 0, 0);
        currentScoreHeadings.add(games, 1, 0);
        currentScoreHeadings.add(points, 2, 0);
        
        sets.setAlignment(Pos.BOTTOM_CENTER);
        games.setAlignment(Pos.BOTTOM_CENTER);
        points.setAlignment(Pos.BOTTOM_CENTER);
        
        //score fields
        currentScore.setPrefSize(x, y);
        
        //Setup the backgrounds of the score fields
        pointP1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        pointP2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        gameP1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        gameP2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        setP1.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        setP2.setStyle("-fx-background-color: black; -fx-border-color: rgba(50,101,0); -fx-font-weight: bold;");
        
        //Add the fields to the grid pane
        currentScore.add(setP1, 0, 0);
        currentScore.add(setP2, 0, 1);
        currentScore.add(gameP1, 1, 0);
        currentScore.add(gameP2, 1, 1);
        currentScore.add(pointP1, 2, 0);
        currentScore.add(pointP2, 2, 1);
        
        //Set the dimensions of the scoring fields
        pointP1.setPrefSize(pointSize, pointSize);
        pointP2.setPrefSize(pointSize, pointSize);
        gameP1.setPrefSize(pointSize, pointSize);
        gameP2.setPrefSize(pointSize, pointSize);
        setP1.setPrefSize(pointSize, pointSize);
        setP2.setPrefSize(pointSize, pointSize);
        
        pointP1.setAlignment(Pos.CENTER);
        pointP2.setAlignment(Pos.CENTER);
        gameP1.setAlignment(Pos.CENTER);
        gameP2.setAlignment(Pos.CENTER);
        setP1.setAlignment(Pos.CENTER);
        setP2.setAlignment(Pos.CENTER);
        
        //Disable editing of text fields
        pointP1.setEditable(false);
        pointP2.setEditable(false);
        gameP1.setEditable(false);
        gameP2.setEditable(false);
        setP1.setEditable(false);
        setP2.setEditable(false);
        
        setLabelColour(Color.YELLOW);
        setTextColour("yellow");
        
        //ACTION EVENT HANDLING
        scoreP1Button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.addPointsP1();
            }
        });
        scoreP2Button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                controller.addPointsP2();
            }
        });
        
    }
    /**
     * change all the labels font colours 
     * @param colour Color object
     */
    public void setLabelColour(Color colour){
        sets.setTextFill(colour);
        games.setTextFill(colour);
        points.setTextFill(colour);
        previousSetHeading.setTextFill(colour);
        scoreP1Button.setTextFill(colour);
        scoreP2Button.setTextFill(colour);
    }
    
    /**
     * Change all text field font colours
     * @param colour String for CSS 
     */
    public void setTextColour(String colour){
        currentScore.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-inner-color: "+colour);
        players.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-inner-color: "+colour);
        previousSets.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-inner-color: "+colour);
    }
}