package ir.sharif.aminra.controller.messagingPage.messageSendingPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.controller.messagingPage.MessagingValidator;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.messagingPage.messageSendingPage.ChatSelectingEvent;
import ir.sharif.aminra.listeners.messagingPage.messageSendigPage.ChatPanelToChatPageListener;
import ir.sharif.aminra.listeners.messagingPage.messageSendigPage.GroupPanelToChatPageListener;
import ir.sharif.aminra.models.*;
import ir.sharif.aminra.models.media.Message;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.ChatSelectingFXController;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.ChatSelectingPanelFXController;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.GroupSelectingPanelFXController;
import ir.sharif.aminra.view.tweets.TweetFXController;

import java.time.LocalDateTime;
import java.util.List;

public class ChatSelectingController {
    public void switchPage(ID userID, ID messageID) {
        Page chatSelectingPage = new Page("chatSelectingPage");
        ChatSelectingFXController chatSelectingFXController = (ChatSelectingFXController) chatSelectingPage.getFxController();
        chatSelectingFXController.setUserID(userID);
        chatSelectingFXController.setMessageID(messageID);
        refresh(chatSelectingFXController);
        ViewManager.getInstance().setPage(chatSelectingPage);
    }

    public void refresh(ChatSelectingFXController chatSelectingFXController) {
        User user = Context.getInstance().getUserDB().getByID(chatSelectingFXController.getUserID());
        chatSelectingFXController.clear();
        //adding groups
        for (Group group : user.getGroups()) {
            Page groupSelectingPanel = new Page("groupSelectingPanel");
            GroupSelectingPanelFXController groupSelectingPanelFXController = (GroupSelectingPanelFXController)
                    groupSelectingPanel.getFxController();
            groupSelectingPanelFXController.setUserID(user.getID());
            groupSelectingPanelFXController.setGroup(group);
            groupSelectingPanelFXController.setGroupPanelToChatPageListener(new
                    GroupPanelToChatPageListener(chatSelectingFXController));
            groupSelectingPanelFXController.setGroupNameLabel(group.getGroupName());
            chatSelectingFXController.getGroupsBox().getChildren().add(groupSelectingPanelFXController.getGroupSelectingPanel());
        }
        //adding chats
        MessagingValidator messagingValidator = new MessagingValidator();
        List<ChatState> validatedChatStates = messagingValidator.getValidatedChatStates(user);
        for (ChatState chatState : validatedChatStates) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            Page chatSelectingPanel = new Page("chatSelectingPanel");
            ChatSelectingPanelFXController chatSelectingPanelFXController = (ChatSelectingPanelFXController)
                    chatSelectingPanel.getFxController();
            chatSelectingPanelFXController.setUserID(user.getID());
            chatSelectingPanelFXController.setChatID(chat.getID());
            chatSelectingPanelFXController.setChatPanelToChatPageListener(new
                    ChatPanelToChatPageListener(chatSelectingFXController));
            chatSelectingPanelFXController.setChatNameLabel(chatName(user, chat));
            chatSelectingFXController.getChatsBox().getChildren().add(chatSelectingPanelFXController.getChatSelectingPanel());
        }
    }

    public String chatName(User user, Chat chat) {
        if (chat.isGroup() || chat.getUsers().size() == 1)
            return chat.getChatName();
        else if (chat.getUsers().get(0).equals(user.getID()))
            return Context.getInstance().getUserDB().
                    getByID(chat.getUsers().get(1)).getUsername();
        else
            return Context.getInstance().getUserDB().
                    getByID(chat.getUsers().get(0)).getUsername();
    }

    public void sendMessage(ChatSelectingEvent chatSelectingEvent) {
        User writer = Context.getInstance().getUserDB().getByID(chatSelectingEvent.getUserID());
        List<ID> chats = chatSelectingEvent.getChats();

        //first create chats.
        List<Group> groups = chatSelectingEvent.getGroups();
        for (Group group : groups)
            for (ID userID : group.getUsers()) {
                User receiver = Context.getInstance().getUserDB().getByID(userID);
                MessageSendingValidator messageSendingValidator = new MessageSendingValidator();
                if (!messageSendingValidator.SendMessageError(writer, receiver).equals(""))
                    continue;
                Chat chat = createChat(writer, receiver);
                if(!chats.contains(chat.getID()))
                    chats.add(chat.getID());
            }
        //sending message to chats.
        Message message = Context.getInstance().getMessageDB().getByID(chatSelectingEvent.getMessage());
        message.setDatetime(LocalDateTime.now());
        for (ID chatID : chats) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatID);
            chat.addMessage(message.getID());
        }
        ViewManager.getInstance().back();
    }

    public Chat createChat(User writer, User receiver) {
        for (ChatState chatState : writer.getChatStates()) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            if (!chat.isGroup() && chat.getUsers().contains(writer.getID()) && chat.getUsers().contains(receiver.getID()))
                return chat;
        }
        //creating chat
        Chat chat = new Chat("", false);
        chat.addUser(writer.getID());
        chat.addUser(receiver.getID());
        writer.addChatState(new ChatState(chat.getID()));
        receiver.addChatState(new ChatState(chat.getID()));
        return chat;
    }

    public void saveTweet(ID userID, ID tweetID, TweetFXController tweetFXController) {
        tweetFXController.setVerdictLabelText(Config.getConfig("tweets").getProperty("successfulSave"), false);
        Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetID);
        User user = Context.getInstance().getUserDB().getByID(userID);

        Message message = new Message(tweet.getContent(), user.getID(), tweetID, tweet.getImage(), true);
        Chat savedMessages = getSavedMessages(user);
        savedMessages.addMessage(message.getID());
    }

    public Chat getSavedMessages(User user) {
        for (ChatState chatState : user.getChatStates()) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            if(!chat.isGroup() && chat.getUsers().size() == 1)
                return chat;
        }
        return null;
    }

    public void forwardTweet(ID userID, ID tweetID) {
        Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetID);
        User user = Context.getInstance().getUserDB().getByID(userID);
        Message message = new Message(tweet.getContent(), user.getID(), tweetID, tweet.getImage(), true);
        switchPage(userID, message.getID());
    }
}
