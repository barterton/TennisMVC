
import java.util.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

/**
 * @author Ben Arterton 14046916
 */
public class TView implements Observer, ActionListener{
    
    //Definitions of the model and the controller
    private final TModel model;
    private final TController controller;
    
    private JFrame frame;
    
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel previousSets;
    private JPanel players;
    private JPanel score;
    private JPanel bottomPanel;
    
    //Button creation
    final private JButton scoreP1Button = new JButton("WIN POINT");
    final private JButton scoreP2Button = new JButton("WIN POINT");
    
    //Label creation for score of the game
    final private JLabel points = new JLabel("POINTS");
    final private JLabel games = new JLabel("GAMES");
    final private JLabel sets = new JLabel("SETS");
    
    //Text field creation of the score
    final private JTextField pointP1 = new JTextField();
    final private JTextField pointP2 = new JTextField();
    final private JTextField gameP1 = new JTextField();
    final private JTextField gameP2 = new JTextField();
    final private JTextField setP1 = new JTextField();
    final private JTextField setP2 = new JTextField();
    
    //Label creation for the previous sets of the game
    final private JLabel previousSets1 = new JLabel("PREVIOUS");
    final private JLabel previousSets2 = new JLabel(" SETS");
    final private JLabel previousSets3 = new JLabel();
    final private JLabel previousSets4 = new JLabel();
    
    //Text field creation of the previous sets of the game
    final private JTextField set1p1 = new JTextField();
    final private JTextField set1p2 = new JTextField();
    final private JTextField set2p1 = new JTextField();
    final private JTextField set2p2 = new JTextField();
    final private JTextField set3p1 = new JTextField();
    final private JTextField set3p2 = new JTextField();
    final private JTextField set4p1 = new JTextField();
    final private JTextField set4p2 = new JTextField();
    
    final private JLabel blankSpaceTop = new JLabel();
    
    //Text field definition for the names of the players
    final private JTextField player1;
    final private JTextField player2; 
    
    //Definition of font variables to be used
    int fontSizeLabels = 9; //Font size 9
    int fontSizePlayers = 20; //Font size 20
    int fontSizeTextFields = 40; //Font size 40 
    int plainFont = 0; //PLAIN font
    int boldFont = 1; //BOLD font
    
    //Used by 3 panels
    int panelDimension = 150;
    //used by the previous sets panel
    int panelDimensionExtended = 200;
    
    //Creation of a color object to be used for the green colours
    Color tennisGreen = new Color(50,101,0);
    
    /**
     * Constructor for the view 
     * @param model to gain state of the tennis match from
     * @param controller 
     */
    public TView(TModel model, TController controller)  {        
        this.model = model; 
        this.controller = controller;
        player1 = new JTextField(model.getPlayer1().getName());
        player2 = new JTextField(model.getPlayer2().getName());
        createControls();
        model.addObserver(this);
        controller.setView(this);
        update(model, null);
    }
    
    /**
     * Method for setting up the JFrame and adding a container to the frame
     * Calls the createPanel() method to construct the view of the scoreboard
     */
    
    public void createControls(){
        
        //Construct the JFrame
        frame = new JFrame("Tennis");
        //Setup close top exit button on the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Setup container for the frame
        Container contentPane = frame.getContentPane();
        //Set the layout of the container for the frame
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
        //Call createPanel() to setup the layout of the scoreboard
        createPanel();
        
        //Add the left and right panel to the boxLayout container
        contentPane.add(leftPanel);
        contentPane.add(rightPanel);
      
        //Pack the frame so that all contents are set to their preffered sizes
        frame.pack();
        //Disable the ability to resize the frame
        frame.setResizable(false);
        //Allow the frame to be visible
        frame.setVisible(true);
    }
    
    /**
     * Overrides observable method update for displaying the model state
     * This method is called whenever the observed object is changed. 
     * An application calls an Observable object's notifyObservers method to have all the object's observers notified of the change.
     * Documentation adapted from Interface Observers documentation
     * @param o observable object (model)
     * @param arg argument passed to notify observers method
     */
    @Override
    public void update(java.util.Observable o, Object arg) {
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
            scoreP1Button.setEnabled(false);
            scoreP2Button.setEnabled(false);
            //Set all the fields font/foreground colours to gray
            setForegroundColours(Color.gray);
            //Set player 1's font colour to red
            player1.setForeground(Color.red);
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
            scoreP1Button.setEnabled(false);
            scoreP2Button.setEnabled(false);
            //Set all the fields font/foreground colours to gray
            setForegroundColours(Color.gray);
            //Set player 2's font colour to red
            player2.setForeground(Color.red);
        }
        //Set the text of the previous sets based off the value stored in the model for player 2
        set1p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[0]));
        set2p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[1]));
        set3p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[2]));
        set4p2.setText(String.valueOf(model.getPlayer2().getPreviousSet()[3]));
    }
    
    /**
     * Method for setting up the layout and construction of the tennis scoreboard GUI
     */
    private void createPanel() {
        
        //Construction of panels
        //Right and left panel
        rightPanel = new JPanel();
        leftPanel = new JPanel(new BorderLayout());   
        
        //Panels to be added to the left panel
        players = new JPanel();
        previousSets = new JPanel();
        score = new JPanel();   
        bottomPanel = new JPanel();
        
        //Adding panels to the left panel
        leftPanel.add(previousSets, BorderLayout.LINE_START);
        leftPanel.add(players, BorderLayout.CENTER);
        leftPanel.add(score, BorderLayout.LINE_END);
        //Adding a space to the bottom of the left panel of the scoreboard
        leftPanel.add(bottomPanel, BorderLayout.PAGE_END);
        
        //Set the preferred size of all the panels
        bottomPanel.setPreferredSize(new Dimension(0,30));
        rightPanel.setPreferredSize(new Dimension(panelDimension,panelDimension));
        score.setPreferredSize(new Dimension(panelDimension,panelDimension));
        players.setPreferredSize(new Dimension(panelDimension,panelDimension));
        previousSets.setPreferredSize(new Dimension(panelDimensionExtended,panelDimension));
        
        //Set the backgrounds of all the JPanels to green
        previousSets.setBackground(tennisGreen);
        players.setBackground(tennisGreen);
        score.setBackground(tennisGreen);
        rightPanel.setBackground(tennisGreen);
        bottomPanel.setBackground(tennisGreen);
        
        //Set all foreground colours
        setForegroundColours(Color.yellow);
        
        //RIGHT PANEL BUTTONS
        scoreP1Button.setBackground(tennisGreen);
        scoreP2Button.setBackground(tennisGreen);
     
        rightPanel.setLayout(new GridLayout(2,1));
        rightPanel.add(scoreP1Button);
        rightPanel.add(scoreP2Button);
       
        //LEFT PANEL
        
        //PREVIOUS SETS PANEL OPERATIONS
        
        //Set colours of the background for the previous sets panel text fields to black
        set1p1.setBackground(Color.black);
        set1p2.setBackground(Color.black);
        set2p1.setBackground(Color.black);
        set2p2.setBackground(Color.black);
        set3p1.setBackground(Color.black);
        set3p2.setBackground(Color.black);
        set4p1.setBackground(Color.black);
        set4p2.setBackground(Color.black);
        
        //Set the border colour of the previous sets panel text fields to green
        set1p1.setBorder(new LineBorder(tennisGreen));
        set1p2.setBorder(new LineBorder(tennisGreen));
        set2p1.setBorder(new LineBorder(tennisGreen));
        set2p2.setBorder(new LineBorder(tennisGreen));
        set3p1.setBorder(new LineBorder(tennisGreen));
        set3p2.setBorder(new LineBorder(tennisGreen));
        set4p1.setBorder(new LineBorder(tennisGreen));
        set4p2.setBorder(new LineBorder(tennisGreen));
        
        //Set the horizontal (x) alignment of the text in the previous sets text fields
        //to the centre of the text fields
        set1p1.setHorizontalAlignment(SwingConstants.CENTER);
        set1p2.setHorizontalAlignment(SwingConstants.CENTER);
        set2p1.setHorizontalAlignment(SwingConstants.CENTER);
        set2p2.setHorizontalAlignment(SwingConstants.CENTER);
        set3p1.setHorizontalAlignment(SwingConstants.CENTER);
        set3p2.setHorizontalAlignment(SwingConstants.CENTER);
        set4p1.setHorizontalAlignment(SwingConstants.CENTER);
        set4p2.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Change font for text in the previous sets text fields
        set1p1.setFont(courier(fontSizeTextFields, boldFont));
        set1p2.setFont(courier(fontSizeTextFields, boldFont));
        set2p1.setFont(courier(fontSizeTextFields, boldFont));
        set2p2.setFont(courier(fontSizeTextFields, boldFont));
        set3p1.setFont(courier(fontSizeTextFields, boldFont));
        set3p2.setFont(courier(fontSizeTextFields, boldFont));
        set4p1.setFont(courier(fontSizeTextFields, boldFont));
        set4p2.setFont(courier(fontSizeTextFields, boldFont));
        
        //Set layout of the previousSets panel to a grid layout (3,4)
        previousSets.setLayout(new GridLayout(3,4));   
        //Disable editing text fields
        set1p1.setEditable(false);
        set1p2.setEditable(false);
        set2p1.setEditable(false);
        set2p2.setEditable(false);
        set3p1.setEditable(false);
        set3p2.setEditable(false);
        set4p1.setEditable(false);
        set4p2.setEditable(false);
        
        //title text fields for previous sets alignment and colour
        previousSets1.setHorizontalAlignment(SwingConstants.CENTER);
        previousSets1.setVerticalAlignment(SwingConstants.BOTTOM);
        previousSets2.setVerticalAlignment(SwingConstants.BOTTOM);
        
        //Change the font for the labels of the previous sets
        previousSets1.setFont(courier(fontSizeLabels, plainFont));
        previousSets2.setFont(courier(fontSizeLabels, plainFont));

        //Add text fields to the previous sets frame
        previousSets.add(previousSets1); //"previous"
        previousSets.add(previousSets2);//"sets"
        //Also adding two empty text fields for layout purposes
        previousSets.add(previousSets3);
        previousSets.add(previousSets4);
        //Recorded sets text fields
        previousSets.add(set1p1);
        previousSets.add(set2p1);
        previousSets.add(set3p1);
        previousSets.add(set4p1);
        previousSets.add(set1p2);
        previousSets.add(set2p2);
        previousSets.add(set3p2);
        previousSets.add(set4p2);
        
        //SCORE PANEL OPERATIONS
        
        //Set colours of the background for the score panel text fields to black
        pointP1.setBackground(Color.black);
        pointP2.setBackground(Color.black);
        gameP1.setBackground(Color.black);
        gameP2.setBackground(Color.black);
        setP1.setBackground(Color.black);
        setP2.setBackground(Color.black);
        
        //Set the border colour of the score panel text fields to green
        setP1.setBorder(new LineBorder(tennisGreen));
        setP2.setBorder(new LineBorder(tennisGreen));
        gameP1.setBorder(new LineBorder(tennisGreen));
        gameP2.setBorder(new LineBorder(tennisGreen));
        pointP1.setBorder(new LineBorder(tennisGreen));
        pointP2.setBorder(new LineBorder(tennisGreen));
        
        //Set the vertical (y) alignments of text to the bottom of the heading labels
        sets.setVerticalAlignment(SwingConstants.BOTTOM);
        games.setVerticalAlignment(SwingConstants.BOTTOM);
        points.setVerticalAlignment(SwingConstants.BOTTOM);
        //Set the horizontal (x) alignments of the text to the center of the heading labels
        sets.setHorizontalAlignment(SwingConstants.CENTER);
        games.setHorizontalAlignment(SwingConstants.CENTER);
        points.setHorizontalAlignment(SwingConstants.CENTER);
        //Set the horizontal (x) alignment of the text in the score text fields
        //to the centre of the fields
        setP1.setHorizontalAlignment(SwingConstants.CENTER);
        setP2.setHorizontalAlignment(SwingConstants.CENTER);
        gameP1.setHorizontalAlignment(SwingConstants.CENTER);
        gameP2.setHorizontalAlignment(SwingConstants.CENTER);
        pointP1.setHorizontalAlignment(SwingConstants.CENTER);
        pointP2.setHorizontalAlignment(SwingConstants.CENTER);
      
        //Set the font type for the score heading labels
        points.setFont(courier(fontSizeLabels, plainFont));
        games.setFont(courier(fontSizeLabels, plainFont));
        sets.setFont(courier(fontSizeLabels, plainFont));
        
        //Set the font type for current score text fields 
        setP1.setFont(courier(fontSizeTextFields, boldFont));
        setP2.setFont(courier(fontSizeTextFields, boldFont));
        gameP1.setFont(courier(fontSizeTextFields, boldFont));
        gameP2.setFont(courier(fontSizeTextFields, boldFont));
        pointP1.setFont(courier(fontSizeTextFields, boldFont));
        pointP2.setFont(courier(fontSizeTextFields, boldFont));
        
        //Set the layout of the score panel to a grid layout (3,3)
        //To emable the addition of all the text fields for recording players
        //current scores
        score.setLayout(new GridLayout(3,3)); 
        
        //Disable editing of all textFields for recording players current scores
        pointP1.setEditable(false);
        pointP2.setEditable(false);
        gameP1.setEditable(false);
        gameP2.setEditable(false);
        setP1.setEditable(false);
        setP2.setEditable(false);
        
        //Add all the textFields for recording a players score to the score panel
        //With grid layout so text fields will be added horizontally
        score.add(sets);
        score.add(games);
        score.add(points);     
        score.add(setP1);
        score.add(gameP1);
        score.add(pointP1);
        score.add(setP2);
        score.add(gameP2);
        score.add(pointP2);
        
        //PLAYERS PANEL OPERATIONS   
        
        //Set the horizontal (x) alignment of the text in the player text fields
        //to the centre of the fields
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Set the font of the player name text fields to courier size 20
        player1.setFont(courier(fontSizePlayers, boldFont));
        player2.setFont(courier(fontSizePlayers, boldFont));
        
        //Set the border of the text fields to black
        player1.setBorder(new LineBorder(Color.black));
        player2.setBorder(new LineBorder(Color.black));
        
        //Set the background colour of the player name text fields to black
        player1.setBackground(Color.black);
        player2.setBackground(Color.black);
        //Disable the editing of the player name text fields
        player1.setEditable(false);
        player2.setEditable(false);
        
        //Set the layout of the player names panel to a grid layout (3,0)
        players.setLayout(new GridLayout(3,0));
        
        //Add the textFields to the grid layout of the panel
        players.add(blankSpaceTop);
        players.add(player1);    
        players.add(player2);
        
        //Add the actionListeners to the score buttons
        scoreP1Button.addActionListener(this);
        scoreP2Button.addActionListener(this);
    }
    /**
     * Method for setting the font to courier easily
     * @param fontSize int value for the font size
     * @param fontType int value according to Font e.g. 0 = Plain, 1 = Bold etc
     * @return new courier font of type chosen
     */
    private Font courier(int fontSize, int fontType){
        //Create new font 
        Font font = new Font("Courier", fontType, fontSize);
        return font;
    }
    
    /**
     * Method for changing all font colour at once
     * @param colour 
     */
    private void setForegroundColours(Color colour){
        //Previous sets foregrounds
        previousSets1.setForeground(colour);
        previousSets2.setForeground(colour);
        set1p1.setForeground(colour);
        set1p2.setForeground(colour);
        set2p1.setForeground(colour);
        set2p2.setForeground(colour);
        set3p1.setForeground(colour);
        set3p2.setForeground(colour);
        set4p1.setForeground(colour);
        set4p2.setForeground(colour);
        //add score buttons foregrounds
        scoreP1Button.setForeground(colour);
        scoreP2Button.setForeground(colour);
        //Score foregrounds
        sets.setForeground(colour);
        games.setForeground(colour);
        points.setForeground(colour);
        pointP1.setForeground(colour);
        pointP2.setForeground(colour);
        gameP1.setForeground(colour);
        gameP2.setForeground(colour);
        setP1.setForeground(colour);
        setP2.setForeground(colour);
        //Player foregrounds
        player1.setForeground(colour);
        player2.setForeground(colour);    
    }
    
    /**
     * Called after a user performs an action
     * @param event 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
    if (event.getSource() == scoreP1Button){
        controller.addPointsP1();
    }
    if (event.getSource() == scoreP2Button){
        controller.addPointsP2();
    }
    }
}

