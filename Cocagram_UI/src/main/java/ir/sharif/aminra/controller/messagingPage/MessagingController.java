package ir.sharif.aminra.controller.messagingPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.listeners.messagingPage.ChatPanelToMessagingPageListener;
import ir.sharif.aminra.models.Chat;
import ir.sharif.aminra.models.ChatState;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Message;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.mainPage.MainFXController;
import ir.sharif.aminra.view.messagingPage.ChatPanelFXController;
import ir.sharif.aminra.view.messagingPage.MessagePanelFXController;
import ir.sharif.aminra.view.messagingPage.MessagingFXController;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MessagingController {

    MessagingValidator messagingValidator = new MessagingValidator();

    public void switchPage(MainFXController mainFXController) {
        Page messagingPage = new Page("messagingPage");
        MessagingFXController messagingFXController = (MessagingFXController) messagingPage.getFxController();
        messagingFXController.setUserID(mainFXController.getUserID());
        refresh(messagingFXController);
        ViewManager.getInstance().setPage(messagingPage);
    }

    public void refresh(MessagingFXController messagingFXController) {
        User user = Context.getInstance().getUserDB().getByID(messagingFXController.getUserID());
        messagingFXController.clear();
        messagingFXController.getScroll().setVvalue(messagingFXController.getScroll().getVmax()); //scroll to bottom.
        refreshChatList(user, messagingFXController);
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

    public void refreshChatList(User user, MessagingFXController messagingFXController) {
        VBox chatList = messagingFXController.getChatList();
        chatList.getChildren().clear();

        List<ChatState> validatedChatStates = messagingValidator.getValidatedChatStates(user);
        validatedChatStates.sort((chatState1, chatState2) -> {
            int unreadCount1 = unreadCount(user, chatState1);
            int unreadCount2 = unreadCount(user, chatState2);
            return Integer.compare(unreadCount2, unreadCount1);
        }); //sort by unread messages.

        for (ChatState chatState : validatedChatStates) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            Page chatPanel = new Page("chatPanel");
            ChatPanelFXController chatPanelFXController = (ChatPanelFXController) chatPanel.getFxController();
            chatPanelFXController.setUserID(messagingFXController.getUserID());
            chatPanelFXController.setChatID(chatState.getChat());
            chatPanelFXController.setChatNameLabel(chatName(user, chat));
            if (unreadCount(user, chatState) > 0)
                chatPanelFXController.setUnreadCountLabel(unreadCount(user, chatState));
            chatPanelFXController.setListener(new ChatPanelToMessagingPageListener(messagingFXController));
            messagingFXController.getChatList().getChildren().add(chatPanelFXController.getChatPanel());
        }
    }

    public int unreadCount(User user, ChatState chatState) {
        Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
        int unreadMessages = 0;
        for (ID messageID : chat.getMessages()) {
            Message message = Context.getInstance().getMessageDB().getByID(messageID);
            if (message.getDateTime().isAfter(chatState.getLastCheck()) && !message.getWriter().equals(user.getID()) &&
                    !message.isDeleted())
                unreadMessages++;
        }
        return unreadMessages;
    }

    public void changeChat(MessagingFXController messagingFXController, ID chatID) {
        User user = Context.getInstance().getUserDB().getByID(messagingFXController.getUserID());
        Chat chat = Context.getInstance().getChatDB().getByID(chatID);
        //update last chat check
        if (messagingFXController.getSelectedChatID() != null)
            user.updateChatLastCheck(messagingFXController.getSelectedChatID());

        ViewManager.getInstance().back();
        Page messagingPage = new Page("messagingPage");
        MessagingFXController tmpMessagingFXController = (MessagingFXController) messagingPage.getFxController();
        tmpMessagingFXController.setUserID(messagingFXController.getUserID());

        //update chat name
        tmpMessagingFXController.setChatNameLabel(chatName(user, chat));

        //update chat box.
        tmpMessagingFXController.setSelectedChatID(chatID);
        user.updateChatLastCheck(tmpMessagingFXController.getSelectedChatID());
        List<Message> validatedMessages = messagingValidator.getValidatedMessages(chat.getMessages());
        Message.sortByDateTime(validatedMessages);
        for (Message message : validatedMessages) {
            Page messagePanel = new Page("messagePanel");
            MessagePanelFXController messagePanelFXController = (MessagePanelFXController) messagePanel.getFxController();
            messagePanelFXController.setUserID(user.getID());
            messagePanelFXController.setMessageID(message.getID());
            messagePanelFXController.setMessageTextLabel(message.getContent());
            messagePanelFXController.setMessageDateLabel(message.getDateTime().
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            String writerUsername = Context.getInstance().getUserDB().getByID(message.getWriter()).getUsername();
            if (message.getMainMedia() == null)
                messagePanelFXController.setSenderLabel(writerUsername);
            else if (message.isForwardedTweet()) {
                Tweet mainTweet = Context.getInstance().getTweetDB().getByID(message.getMainMedia());
                String tweetWriter = Context.getInstance().getUserDB().getByID(mainTweet.getWriter()).getUsername();
                messagePanelFXController.setSenderLabel(String.format("%s forwarded a tweet from %s",
                        writerUsername, tweetWriter));
            } else {
                Message mainMessage = Context.getInstance().getMessageDB().getByID(message.getMainMedia());
                String messageWriter = Context.getInstance().getUserDB().getByID(mainMessage.getWriter()).getUsername();
                messagePanelFXController.setSenderLabel(String.format("%s forwarded a message from %s",
                        writerUsername, messageWriter));
            }
            tmpMessagingFXController.getChatBox().getChildren().add(messagePanelFXController.getMessagePanel());
        }
        tmpMessagingFXController.getScroll().setVvalue(tmpMessagingFXController.getScroll().getVmax());

        //refresh chat list.
        refreshChatList(user, tmpMessagingFXController);
        ViewManager.getInstance().setPage(messagingPage);
    }
}
