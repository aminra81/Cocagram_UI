package ir.sharif.aminra.view.mainPage;

import ir.sharif.aminra.listeners.mainPage.MainPageListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFXController extends FXController implements Initializable {

    MainPageListener mainPageListener;

    @FXML
    public void personalPage() {
        mainPageListener.stringEventOccurred("myPage");
    }

    @FXML
    public void settings() { mainPageListener.stringEventOccurred("settings"); }

    @FXML
    public void timeline() { mainPageListener.stringEventOccurred("timeline"); }

    @FXML
    public void explorer() { mainPageListener.stringEventOccurred("explorer"); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPageListener = new MainPageListener(this);
    }
}
