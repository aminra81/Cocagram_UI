package ir.sharif.aminra.controller.messagingPage.chatGroupPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.Chat;
import ir.sharif.aminra.models.ChatState;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.messagingPage.ChatGroupPage.ChatGroupFXController;
import ir.sharif.aminra.view.messagingPage.MessagingFXController;

public class ChatGroupController {

    Config errorsConfig = Config.getConfig("chatGroupPage");

    public void switchPage(MessagingFXController messagingFXController) {
        Page chatGroupPage = new Page("chatGroupPage");
        ChatGroupFXController chatGroupFXController = (ChatGroupFXController) chatGroupPage.getFxController();
        chatGroupFXController.setUserID(messagingFXController.getUserID());
        ViewManager.getInstance().setPage(chatGroupPage);
    }

    public void newChatGroup(ID userID, String groupName, ChatGroupFXController chatGroupFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        for (ChatState chatState : user.getChatStates()) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            if(chat.isGroup() && chat.getChatName().equals(groupName)) {
                chatGroupFXController.setErrorLabel(errorsConfig.getProperty("groupWithSameNameError"));
                return;
            }
        }
        Chat chat = new Chat(groupName, true);
        chat.addUser(user.getID());
        user.addChatState(new ChatState(chat.getID()));
        ViewManager.getInstance().back();
    }

    public void addUser(ID userID, String groupName, String username, ChatGroupFXController chatGroupFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        Chat chat = findGroup(user, groupName);
        if(chat == null) {
            chatGroupFXController.setErrorLabel(errorsConfig.getProperty("groupNotExistError"));
            return;
        }
        User userToBeAdded = Context.getInstance().getUserDB().getUser(username);
        if(userToBeAdded == null || !userToBeAdded.isActive()) {
            chatGroupFXController.setErrorLabel(errorsConfig.getProperty("userNotExistError"));
            return;
        }
        if(chat.getUsers().contains(userToBeAdded.getID())) {
            chatGroupFXController.setErrorLabel(errorsConfig.getProperty("userAlreadyInGroupError"));
            return;
        }
        if(!user.getFollowings().contains(userToBeAdded.getID())) {
            chatGroupFXController.setErrorLabel(errorsConfig.getProperty("userNotInFollowingsError"));
            return;
        }
        chat.addUser(userToBeAdded.getID());
        userToBeAdded.addChatState(new ChatState(chat.getID()));
        ViewManager.getInstance().back();
    }

    public Chat findGroup(User user, String groupName) {
        for (ChatState chatState : user.getChatStates()) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            if(chat.getChatName().equals(groupName))
                return chat;
        }
        return null;
    }
}
