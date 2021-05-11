package ir.sharif.aminra.events.messagingPage.messageSendingPage;

import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.ChatSelectingFXController;

import java.util.List;

public class ChatSelectingEvent {
    ID message;
    List<ID> chats;
    List<Group> groups;
    ID userID;
    ChatSelectingFXController chatSelectingFXController;

    public ChatSelectingEvent(ID message, List<ID> chats, List<Group> groups, ID userID, ChatSelectingFXController chatSelectingFXController) {
        this.message = message;
        this.chats = chats;
        this.groups = groups;
        this.userID = userID;
        this.chatSelectingFXController = chatSelectingFXController;
    }

    public ID getMessage() {
        return message;
    }

    public List<ID> getChats() {
        return chats;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public ID getUserID() {
        return userID;
    }
}
