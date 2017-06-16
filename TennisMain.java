

/**
 *@author Ben Arterton 14046916
 */
public class TennisMain {
        public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run () {createAndShowGUI();}
            }
        );
    }

    public static void createAndShowGUI() {
        TModel model = new TModel();
        TController controller = new TController(model);
        TView view = new TView(model, controller);
    }
}
