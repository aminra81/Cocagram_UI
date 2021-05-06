package ir.sharif.aminra.view.explorerPage;

import ir.sharif.aminra.events.explorerPage.ExplorerPageEvent;
import ir.sharif.aminra.events.explorerPage.ExplorerPageEventType;
import ir.sharif.aminra.listeners.explorerPage.ExplorerListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ExplorerFXController extends FXController implements Initializable {
    ExplorerListener explorerListener;

    @FXML
    private VBox tweetBox;

    @FXML
    private TextField usernameLabel;

    @FXML
    private Label errorLabel;

    @FXML
    public void search() {
        explorerListener.eventOccurred(new ExplorerPageEvent(ExplorerPageEventType.SEARCH,
                userID, usernameLabel.getText(), this));
    }
    
    @Override
    public void refresh() {
        explorerListener.eventOccurred(new ExplorerPageEvent(ExplorerPageEventType.REFRESH,
                userID, usernameLabel.getText(), this));
    }

    public VBox getTweetBox() { return tweetBox; }

    public void setErrorLabel(String error) { errorLabel.setText(error); }

    @Override
    public void clear() {
        tweetBox.getChildren().clear();
        usernameLabel.clear();
        errorLabel.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        explorerListener = new ExplorerListener();
    }
}
