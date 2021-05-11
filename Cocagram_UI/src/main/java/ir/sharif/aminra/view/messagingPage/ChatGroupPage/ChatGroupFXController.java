package ir.sharif.aminra.view.messagingPage.ChatGroupPage;

import ir.sharif.aminra.events.messagingPage.chatGroupPage.ChatGroupEvent;
import ir.sharif.aminra.events.messagingPage.chatGroupPage.ChatGroupEventType;
import ir.sharif.aminra.listeners.messagingPage.chatGroupPage.ChatGroupPageListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatGroupFXController extends FXController implements Initializable {

    ChatGroupPageListener chatGroupPageListener;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField newGroupNameField;

    @FXML
    private TextField groupNameField;

    @FXML
    private TextField usernameField;

    @FXML
    public void addUser() {
        chatGroupPageListener.eventOccurred(new ChatGroupEvent(ChatGroupEventType.ADD, userID, groupNameField.getText(),
                usernameField.getText(), this));
    }

    @FXML
    public void newGroup() {
        chatGroupPageListener.eventOccurred(new ChatGroupEvent(ChatGroupEventType.CREATE, userID, newGroupNameField.getText(),
                "", this));
    }

    public void setErrorLabel(String error) { errorLabel.setText(error); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatGroupPageListener = new ChatGroupPageListener();
    }
}
