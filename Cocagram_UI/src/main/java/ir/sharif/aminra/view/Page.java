package ir.sharif.aminra.view;

import ir.sharif.aminra.models.User;
import javafx.scene.Scene;

public abstract class Page {
    protected Scene scene;
    protected FXController fxController;
    public static void create(User user) {

    }
    public Scene getScene() {
        return scene;
    }
}
