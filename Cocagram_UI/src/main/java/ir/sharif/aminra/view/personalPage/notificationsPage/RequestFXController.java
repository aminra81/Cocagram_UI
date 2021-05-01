package ir.sharif.aminra.view.personalPage.notificationsPage;

import ir.sharif.aminra.events.personalPage.notificationsPage.RequestAnswerType;
import ir.sharif.aminra.events.personalPage.notificationsPage.RequestHandlingEvent;
import ir.sharif.aminra.listeners.personalPage.notificationsPage.RequestHandlingListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestFXController extends FXController implements Initializable {

    RequestHandlingListener requestHandlingListener;

    ID requesterID;

    @FXML
    private AnchorPane requestPane;

    public AnchorPane getRequestPane() { return requestPane; }

    @FXML
    private Label requester;

    public void setRequester(String requesterName) {
        requester.setText(requesterName);
    }

    @FXML
    public void accept() {
        requestHandlingListener.eventOccurred(new RequestHandlingEvent(this,
                RequestAnswerType.ACCEPT, userID, requesterID));
    }

    @FXML
    public void muteReject() {
        requestHandlingListener.eventOccurred(new RequestHandlingEvent(this,
                RequestAnswerType.MUTE_REJECT, userID, requesterID));
    }

    @FXML
    public void reject() {
        requestHandlingListener.eventOccurred(new RequestHandlingEvent(this,
                RequestAnswerType.REJECT, userID, requesterID));
    }

    public void setRequesterID(ID requesterID) {
        this.requesterID = requesterID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requestHandlingListener = new RequestHandlingListener(this);
    }
}
