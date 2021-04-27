package ir.sharif.aminra.view.enterPage;

import ir.sharif.aminra.Main;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.view.Page;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SignInPage extends Page {
    public SignInPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource(Config.getConfig("fxmls").getProperty(String.class, "signInPage")));
            this.scene = new Scene(fxmlLoader.load());
            this.fxController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}