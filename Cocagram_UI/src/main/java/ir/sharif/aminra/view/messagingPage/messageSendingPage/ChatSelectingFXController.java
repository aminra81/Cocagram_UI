package ir.sharif.aminra.view.messagingPage.messageSendingPage;

import ir.sharif.aminra.events.messagingPage.messageSendingPage.ChatSelectingEvent;
import ir.sharif.aminra.listeners.messagingPage.messageSendigPage.ChatSelectingPageListener;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChatSelectingFXController extends FXController implements Initializable {

    ChatSelectingPageListener chatSelectingPageListener;
    ID messageID;

    List<Group> selectedGroups = new ArrayList<>();
    List<ID> selectedChats = new ArrayList<>();

    public void setMessageID(ID messageID) { this.messageID = messageID; }

    public void addGroup(Group group) { selectedGroups.add(group); }

    public void removeGroup(Group group) { selectedGroups.remove(group); }

    public void addChat(ID chat) { selectedChats.add(chat); }

    public void removeChat(ID chat) { selectedChats.remove(chat); }

    public List<Group> getSelectedGroups() { return selectedGroups; }

    public List<ID> getSelectedChats() { return selectedChats; }

    @FXML
    private VBox chatsBox;

    @FXML
    private VBox groupsBox;

    public VBox getChatsBox() { return chatsBox; }

    public VBox getGroupsBox() { return groupsBox; }

    @Override
    public void clear() {
        chatsBox.getChildren().clear();
        groupsBox.getChildren().clear();
    }

    @FXML
    public void send() {
        chatSelectingPageListener.eventOccurred(new ChatSelectingEvent(messageID, selectedChats, selectedGroups,
                userID, this));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatSelectingPageListener = new ChatSelectingPageListener();
    }
}
