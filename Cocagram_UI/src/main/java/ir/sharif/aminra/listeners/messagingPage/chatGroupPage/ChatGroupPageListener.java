package ir.sharif.aminra.listeners.messagingPage.chatGroupPage;

import ir.sharif.aminra.controller.messagingPage.chatGroupPage.ChatGroupController;
import ir.sharif.aminra.events.messagingPage.chatGroupPage.ChatGroupEvent;

public class ChatGroupPageListener {
    public void eventOccurred(ChatGroupEvent chatGroupEvent) {
        ChatGroupController chatGroupController = new ChatGroupController();
        switch (chatGroupEvent.getChatGroupEventType()) {
            case ADD:
                chatGroupController.addUser(chatGroupEvent.getUserID(), chatGroupEvent.getGroupName(), chatGroupEvent.getUsername(),
                        chatGroupEvent.getChatGroupFXController());
                break;
            case CREATE:
                chatGroupController.newChatGroup(chatGroupEvent.getUserID(), chatGroupEvent.getGroupName(),
                        chatGroupEvent.getChatGroupFXController());
                break;
        }
    }
}
