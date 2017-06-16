
/**
 *
 * @author Ben
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;

public class TennisMainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        TModel model = new TModel();
        TController controller = new TController(model);
        TViewFX view = new TViewFX(controller, model);

        Scene scene = new Scene(view.asParent(), 600, 130);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}