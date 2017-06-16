
/**
 *@author Ben Arterton 14046916
 */

import java.util.Observable;

public class TModel extends Observable{
    private Score player1;
    private Score player2;
    /**
     * constructor
     * @post called initialise() method
     */
    public TModel(){
        initialise();
    }
    /**
     * @pre requires Score object created and assigned to player1 variable
     * @return player1 object
     */
    public Score getPlayer1(){
        return player1;
    }
    /**
     * @pre requires Score object created and assigned to player2 variable
     * @return player2 object
     */
    public Score getPlayer2(){
        return player2;
    }
    
    /**
     * @pre requires objects of 2 players scores being created (initialise())
     * models method of adding points to the 1st player by calling methods from the Score class
     * @post adds points to the 1st players score and applies rules from methods in Score class
     * to determine what aspect of the score changes compared against the 2nd players score
     */
    public void addPointsPlayer1(){
        player1.addPoints();
        Score.checkAll(player1, player2);
        setChanged();
        notifyObservers();
    }
    
    /**
     * @pre requires objects of 2 players scores being created (initialise())
     * models method of adding points to the 2nd player by calling methods from the Score class
     * @post adds points to the 2nd players score and applies rules from methods in Score class
     * to determine what aspect of the score changes compared against the 1st players score 
     */
    public void addPointsPlayer2(){
        player2.addPoints();
        Score.checkAll(player2, player1);
        setChanged();
        notifyObservers();
    }
    
    /**
     * @post two Score objects created and assigned to class variables
     */
    public void initialise(){
        this.player1 = new Score("R. NADAL");
        this.player2 = new Score("A. MURRAY");
        
        setChanged();
        notifyObservers();
    }
    
}
