package ir.sharif.aminra.view.messagingPage;

import ir.sharif.aminra.listeners.messagingPage.ChatPanelToMessagingPageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ChatPanelFXController extends FXController {

    ID chatID;

    ChatPanelToMessagingPageListener listener;

    @FXML
    private AnchorPane chatPanel;

    @FXML
    private Label chatNameLabel;

    @FXML
    private Label unreadCountLabel;

    @FXML
    public void viewChat() { listener.eventOccurred(chatID); }

    public AnchorPane getChatPanel() { return chatPanel; }

    public void setListener(ChatPanelToMessagingPageListener listener) { this.listener = listener; }

    public void setChatID(ID chatID) { this.chatID = chatID; }

    public void setChatNameLabel(String chatName) { chatNameLabel.setText(chatName); }

    public void setUnreadCountLabel(int unreadCount) { unreadCountLabel.setText(String.valueOf(unreadCount)); }
}
