package ir.sharif.aminra.view.personalPage.notificationsPage;

import ir.sharif.aminra.listeners.personalPage.notificationsPage.NotificationsListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationsFXController extends FXController implements Initializable {

    NotificationsListener notificationsListener;

    @FXML
    private TextArea requestMessages;

    @FXML
    private TextArea systemMessages;

    @FXML
    public VBox requests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificationsListener = new NotificationsListener(this);
        requestMessages.setEditable(false);
        systemMessages.setEditable(false);
    }

    public void addRequestMessage(String message) {
        requestMessages.appendText("\n" + message);
    }

    public void addSystemMessage(String message) {
        systemMessages.appendText("\n" + message);
    }

    public VBox getRequests() { return requests; }

    @Override
    public void refresh() {
        notificationsListener.stringEventOccurred("refresh");
    }

    @Override
    public void clear() {
        requests.getChildren().clear();
        systemMessages.clear();
        requestMessages.clear();
    }
}
