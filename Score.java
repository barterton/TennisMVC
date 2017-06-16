/**
 * @author Ben Arterton 14046916
 */
public class Score{
    private String playerName;
    private int points;
    private int games;
    private int sets;
    private boolean adv;
    private boolean tieBreaker;
    private boolean win;
    int[] previousSets;
    int nextSetIndex = 0;
    
    public Score (String playerName){
        this.playerName = playerName;
        this.points = 0;
        this.games = 0;
        this.sets = 0;
        this.win = false; 
        this.adv = true;
        this.tieBreaker = false;
        this.previousSets = new int[5];
    }
    /**
     * @pre points >= 0
     * @post points++
     */
    public void addPoints(){
        points++;
    }
    /**
     * @post points = 0
     */
    public void resetPoints(){
        points = 0;
    }
    /**
     * @pre points >= 0
     * @return points
     */
    public int getPoints(){
        return points;
    }
    /**
     * @pre games >= 0
     * @return games
     */
    public int getGames(){
        return games;
    }
    /**
     * @pre games >= 0
     * @post games++
     */
    public void addGames(){
        games++;
    }
    /**
     * for JUnit testing
     * @param game 
     */
    public void setGames(int game){
        this.games = game;
    }
    /**
     * @post games = 0
     */
    public void resetGames(){
        games = 0;
    }
    /**
     * @pre sets >= 0
     * @return sets
     */
    public int getSets(){
        return sets;
    }
    /**
     * @pre sets >= 0
     * @post sets++
     */
    public void addSets(){
        sets++;
    }
    public void setSets(int set){
        this.sets = set;
    }
    /**
     * @pre tieBreaker = true/false
     * @return tieBreaker
     */
    public boolean getTieBreaker(){
        return tieBreaker;
    }
    /**
     * @param tieBreak 
     * @post tieBreaker value will be changed to the parameter value
     */
    public void setTieBreaker(boolean tieBreak){
        this.tieBreaker = tieBreak;
    }
    /**
     * @pre adv must be a boolean value
     * @return adv
     */
    public boolean getAdv(){
        return adv;
    }
    /**
     * @param adv 
     * @post adv value will be changed to the parameter value
     */
    public void setAdv(boolean adv){
        this.adv = adv;
    }
    /**
     * @pre win must be true or false
     * @return win
     */
    public boolean getWin(){
        assert(true);
        return win;
    }
    /**
     * @param win boolean true or false
     * @post win variable changed to parameter value
     */
    public void setWin(boolean win){
        this.win = win;
    }
    /**
     * @pre playerName must be a String value
     * @return playerName
     */
    public String getName(){
        return playerName;
    }
    /**
     * turns previous set array into a stack to allow it to store previous games
     * @param game to be pushed to previous set array
     */
    public void pushToPreviousSet(int game) {
        previousSets[nextSetIndex] = game;
        ++nextSetIndex;
    }
    /**
     * @return previousSets array
     */
    public int[] getPreviousSet(){
        return previousSets;
    }
    /**
     * @pre getPoints() must return a positive number
     * @pre getAdv() must be a true or false value
     * @param chosenPlayer player to have points checked to increase games
     * @param comparePlayer player to check against
     * @post games variable increased by 1 when player points = 4 and their adv = true
     */
    public static void checkAddPoints(Score chosenPlayer, Score comparePlayer){
        if (chosenPlayer.getPoints() >= 4 && chosenPlayer.getAdv()==true){
                chosenPlayer.addGames();
                chosenPlayer.resetPoints();
                comparePlayer.resetPoints();  
                comparePlayer.setAdv(true);
                chosenPlayer.setAdv(true);
        }
        else if (chosenPlayer.getPoints() == 3 && comparePlayer.getPoints() == 3 ){
            System.out.println("Deuce");
            chosenPlayer.setAdv(false);
            comparePlayer.setAdv(false);
        }
        else if (chosenPlayer.getAdv()==false  && comparePlayer.getAdv()==false){
            System.out.println("Advantage "+chosenPlayer.getName());
            chosenPlayer.setAdv(true);
            comparePlayer.setAdv(false);
        }
        
        else if(chosenPlayer.getAdv()==false && comparePlayer.getAdv()==true){
            System.out.println("Deuce");
            chosenPlayer.setAdv(false);
            comparePlayer.setAdv(false);
        }
    }
    /**
     * @param chosenPlayer player to have games checked to see if able to increase sets
     * @param comparePlayer player to check against
     * @post chosenPlayer sets increased by 1  if games is 6 and there is a difference of 2 between the games of both players
     */
    public static void checkGames(Score chosenPlayer, Score comparePlayer){
        int gameDifference;
        gameDifference = chosenPlayer.getGames() - comparePlayer.getGames();
        if (chosenPlayer.getGames()>=6 && chosenPlayer.getTieBreaker()==false || gameDifference == 2 && chosenPlayer.getGames()>=6){
            chosenPlayer.addSets();
            if (chosenPlayer.getSets() <= 2){
                chosenPlayer.pushToPreviousSet(chosenPlayer.getGames());
                comparePlayer.pushToPreviousSet(comparePlayer.getGames());
                chosenPlayer.resetGames();
                comparePlayer.resetGames();
            }
            chosenPlayer.setTieBreaker(false);
            comparePlayer.setTieBreaker(false);
        }
        else if (chosenPlayer.getGames()==5 && comparePlayer.getGames() ==5){
            chosenPlayer.setTieBreaker(true);
            comparePlayer.setTieBreaker(true);
        } 
        
    }
    /**
     * @param player player to check if they have won by getting 3 sets
     * @post if sets = 3 then set win boolean to true
     */
    
    public static void checkWin(Score player){
        if (player.getSets()==3){        
            player.setWin(true);
        }
    }
    /**
     * @param chosenPlayer player to check points, games and win
     * @param comparePlayer player to check against
     */
    public static void checkAll(Score chosenPlayer, Score comparePlayer){
        Score.checkAddPoints(chosenPlayer, comparePlayer);
        Score.checkGames(chosenPlayer, comparePlayer);
        Score.checkWin(chosenPlayer);
    }
}
    