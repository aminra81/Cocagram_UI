package ir.sharif.aminra.view.timelinePage;

import ir.sharif.aminra.listeners.timelinePage.TimelineListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TimelineFXController extends FXController implements Initializable {
    TimelineListener timelineListener;

    @FXML
    private VBox tweetBox;

    public VBox getTweetBox() { return tweetBox; }

    @Override
    public void clear() { tweetBox.getChildren().clear(); }

    @Override
    public void refresh() {
        timelineListener.stringEventOccurred("refresh");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timelineListener = new TimelineListener(this);
    }
}
