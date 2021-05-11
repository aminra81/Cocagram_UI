package ir.sharif.aminra.events.messagingPage.chatGroupPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.messagingPage.ChatGroupPage.ChatGroupFXController;

public class ChatGroupEvent {
    ChatGroupEventType chatGroupEventType;
    ID userID;
    String groupName;
    String username;
    ChatGroupFXController chatGroupFXController;

    public ChatGroupEvent(ChatGroupEventType chatGroupEventType, ID userID, String groupName, String username, ChatGroupFXController chatGroupFXController) {
        this.chatGroupEventType = chatGroupEventType;
        this.userID = userID;
        this.groupName = groupName;
        this.username = username;
        this.chatGroupFXController = chatGroupFXController;
    }

    public ChatGroupEventType getChatGroupEventType() {
        return chatGroupEventType;
    }

    public ID getUserID() {
        return userID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUsername() {
        return username;
    }

    public ChatGroupFXController getChatGroupFXController() {
        return chatGroupFXController;
    }
}
