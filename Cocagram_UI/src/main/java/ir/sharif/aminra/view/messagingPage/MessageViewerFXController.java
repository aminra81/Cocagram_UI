package ir.sharif.aminra.view.messagingPage;

import ir.sharif.aminra.events.messagingPage.MessageViewerEvent;
import ir.sharif.aminra.events.messagingPage.MessageViewerEventType;
import ir.sharif.aminra.listeners.messagingPage.MessageViewerPageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageViewerFXController extends FXController implements Initializable {

    MessageViewerPageListener messageViewerPageListener;
    ID messageID;

    public void setMessageID(ID messageID) { this.messageID = messageID; }

    public ID getMessageID() { return messageID; }

    @FXML
    private Rectangle photoBox;

    @FXML
    private TextArea messageContentField;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label messageDateLabel;

    @FXML
    private Label senderLabel;

    @FXML
    private Label errorLabel;

    @FXML
    public void delete() {
        messageViewerPageListener.eventOccurred(new MessageViewerEvent(MessageViewerEventType.DELETE, userID, messageID,
                messageContentField.getText(), this));
    }

    @FXML
    public void edit() {
        messageViewerPageListener.eventOccurred(new MessageViewerEvent(MessageViewerEventType.EDIT, userID, messageID,
                messageContentField.getText(), this));
    }

    @FXML
    public void forward() {
        messageViewerPageListener.eventOccurred(new MessageViewerEvent(MessageViewerEventType.FORWARD, userID, messageID,
                messageContentField.getText(), this));
    }

    @FXML
    public void checkUserProfile() {
        messageViewerPageListener.eventOccurred(new MessageViewerEvent(MessageViewerEventType.CHECK_USER_PROFILE,
                userID, messageID, messageContentField.getText(), this));
    }

    @Override
    public void refresh() {
        messageViewerPageListener.eventOccurred(new MessageViewerEvent(MessageViewerEventType.REFRESH, userID, messageID,
                messageContentField.getText(), this));
    }

    public void setErrorLabel(String error) { errorLabel.setText(error); }

    public void deactivateButtons() {
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    public void setMessageContentField(String messageContent) { this.messageContentField.setText(messageContent); }

    public void setSenderLabel(String senderText) { senderLabel.setText(senderText); }

    public void setMessageDateLabel(String messageDate) { messageDateLabel.setText(messageDate); }

    public Rectangle getPhotoBox() { return photoBox; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        photoBox.setFill(Color.TRANSPARENT);
        messageViewerPageListener = new MessageViewerPageListener();
    }
}
