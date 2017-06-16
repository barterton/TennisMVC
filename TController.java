
/**
 * @author Ben Arterton 14046916
 */

public class TController {
    private final TModel model;
    private TView view; 
    private TViewFX viewFX;
    
    /**
     * Constructor for Controller 
     * @param model to set the model the controller interacts with
     */
    public TController(TModel model) {
        this.model = model;
        }
    /**
     * Set the view for the controller to interact with
     * @param view 
     */
    public void setView(TView view) {
        this.view = view;
    }
    /**
     * The view for Java FX
     * Only called by the Java FX main
     * @param view 
     */
    public void setViewJFX(TViewFX view){
        this.viewFX = view;
    }
    /**
     * Initialise the state of the model not used
     * but could be if needed to reset the game
     */
    public void initialise() {
        model.initialise();
    }
    /**
     * Add points to the model of player 1
     */
    public void addPointsP1(){
        model.addPointsPlayer1();
    }
    /**
     * Add points to the model of player 2
     */
    public void addPointsP2(){
        model.addPointsPlayer2();
    }
}

