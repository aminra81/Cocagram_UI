package ir.sharif.aminra.view.messagingPage;

import ir.sharif.aminra.listeners.messagingPage.MessagingPageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagingFXController extends FXController implements Initializable {
    ID selectedChatID;
    MessagingPageListener messagingPageListener;

    @FXML
    private VBox chatList;

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label chatNameLabel;

    @FXML
    public void chatGroup() {
        messagingPageListener.stringEventOccurred("chatGroup");
    }

    @FXML
    public void sendMessage() {
        messagingPageListener.stringEventOccurred("sendMessage");
    }

    @Override
    public void clear() {
        chatBox.getChildren().clear();
        setSelectedChatID(null);
        chatNameLabel.setText("");
    }

    @Override
    public void refresh() {
        messagingPageListener.stringEventOccurred("refresh");
    }

    public ID getSelectedChatID() { return selectedChatID; }

    public void setSelectedChatID(ID selectedChatID) { this.selectedChatID = selectedChatID; }

    public ScrollPane getScroll() { return scroll; }

    public VBox getChatList() { return chatList; }

    public VBox getChatBox() { return chatBox; }

    public void setChatNameLabel(String chatName) { chatNameLabel.setText(chatName); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messagingPageListener = new MessagingPageListener(this);
    }
}
