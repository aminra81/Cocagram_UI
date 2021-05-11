package ir.sharif.aminra.view.messagingPage.messageSendingPage;

import ir.sharif.aminra.listeners.messagingPage.messageSendigPage.ChatPanelToChatPageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ChatSelectingPanelFXController extends FXController {
    ID chatID;
    ChatPanelToChatPageListener chatPanelToChatPageListener;

    @FXML
    private Label chatNameLabel;

    @FXML
    private AnchorPane chatSelectingPanel;

    @FXML
    public void toggle() {
        chatPanelToChatPageListener.switchState(chatID);
    }

    public void setChatID(ID chatID) { this.chatID = chatID; }

    public void setChatPanelToChatPageListener(ChatPanelToChatPageListener chatPanelToChatPageListener) {
        this.chatPanelToChatPageListener = chatPanelToChatPageListener;
    }

    public void setChatNameLabel(String chatName) { chatNameLabel.setText(chatName); }

    public AnchorPane getChatSelectingPanel() { return chatSelectingPanel; }
}
