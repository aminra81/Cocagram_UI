package ir.sharif.aminra.view.personalPage;

import ir.sharif.aminra.listeners.personalPage.MyPageListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MyFXController extends FXController implements Initializable {
    @FXML
    private Circle avatar;

    MyPageListener myPageListener;

    public Circle getAvatar() {
        return avatar;
    }

    @FXML
    public void newTweet() {
        myPageListener.stringEventOccurred("newTweet");
    }

    @FXML
    public void edit() {
        myPageListener.stringEventOccurred("edit");
    }

    @FXML
    public void notifications() { myPageListener.stringEventOccurred("notifications"); }

    @Override
    public void refresh() {
        myPageListener.stringEventOccurred("refresh");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myPageListener = new MyPageListener(this);
    }
}