package ir.sharif.aminra;

import ir.sharif.aminra.view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    static private final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) {
        ViewManager.getInstance().start(stage);
    }

    public static void main(String[] args) {
        logger.info("program started.");
        launch(args);
    }
}
