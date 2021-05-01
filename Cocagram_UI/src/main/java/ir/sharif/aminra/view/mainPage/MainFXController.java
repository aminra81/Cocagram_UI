package ir.sharif.aminra.view.mainPage;

import ir.sharif.aminra.listeners.mainPage.MainPageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFXController extends FXController implements Initializable {
    @FXML
    private Button personalPageButton;

    @FXML
    private Button timelineButton;

    @FXML
    private Button explorerButton;

    @FXML
    private Button messagingButton;

    @FXML
    private Button settingsButton;

    ID userID;
    MainPageListener mainPageListener;

    public void setUserID(ID userID) {
        this.userID = userID;
    }
    public ID getUserID() { return userID; }

    @FXML
    public void personalPage() {
        mainPageListener.stringEventOccurred("myPage");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPageListener = new MainPageListener(this);
    }
}
