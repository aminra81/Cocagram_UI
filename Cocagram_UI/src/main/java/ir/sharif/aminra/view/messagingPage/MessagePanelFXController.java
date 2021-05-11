package ir.sharif.aminra.view.messagingPage;

import ir.sharif.aminra.listeners.messagingPage.MessagePanelListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagePanelFXController extends FXController implements Initializable {
    MessagePanelListener messagePanelListener;
    ID messageID;

    public void setMessageID(ID messageID) { this.messageID = messageID; }

    public ID getMessageID() { return messageID; }

    @FXML
    private AnchorPane messagePanel;

    @FXML
    private Label senderLabel;

    @FXML
    private Label messageTextLabel;

    @FXML
    private Label messageDateLabel;

    @FXML
    public void view() {
        messagePanelListener.stringEventOccurred("view");
    }

    public void setSenderLabel(String senderText) { senderLabel.setText(senderText); }

    public void setMessageTextLabel(String messageText) { messageTextLabel.setText(messageText); }

    public void setMessageDateLabel(String messageDate) { messageDateLabel.setText(messageDate); }

    public AnchorPane getMessagePanel() { return messagePanel; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messagePanelListener = new MessagePanelListener(this);
    }
}
