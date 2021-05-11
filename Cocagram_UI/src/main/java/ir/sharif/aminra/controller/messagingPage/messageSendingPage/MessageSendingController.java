package ir.sharif.aminra.controller.messagingPage.messageSendingPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.messagingPage.messageSendingPage.NewMessageEvent;
import ir.sharif.aminra.models.Chat;
import ir.sharif.aminra.models.ChatState;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Message;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.MessageSendingFXController;

public class MessageSendingController {

    MessageSendingValidator messageSendingValidator = new MessageSendingValidator();

    public void switchPage(ID userID, ID receiverID) {
        Page messageSendingPage = new Page("messageSendingPage");
        MessageSendingFXController messageSendingFXController = (MessageSendingFXController) messageSendingPage.getFxController();
        messageSendingFXController.setUserID(userID);
        messageSendingFXController.setReceiverID(receiverID);
        ViewManager.getInstance().setPage(messageSendingPage);
    }

    public Chat createChat(User firstUser, User secondUser) {
        for (ChatState chatState : firstUser.getChatStates()) {
            Chat chat = Context.getInstance().getChatDB().getByID(chatState.getChat());
            if (!chat.isGroup() && chat.getUsers().contains(firstUser.getID()) && chat.getUsers().contains(secondUser.getID()))
                return chat;
        }
        Chat chat = new Chat("", false);
        chat.addUser(firstUser.getID());
        chat.addUser(secondUser.getID());

        firstUser.addChatState(new ChatState(chat.getID()));
        secondUser.addChatState(new ChatState(chat.getID()));

        return chat;
    }

    public void addMessage(NewMessageEvent newMessageEvent) {

        User writer = Context.getInstance().getUserDB().getByID(newMessageEvent.getWriterID());
        User receiver = Context.getInstance().getUserDB().getByID(newMessageEvent.getReceiverID());
        ID imageID = null;
        if (newMessageEvent.getImage() != null)
            imageID = Context.getInstance().getImageDB().saveIntoDB(newMessageEvent.getImage());
        String content = newMessageEvent.getContent();

        if (newMessageEvent.getReceiverID() != null) {
            String error = messageSendingValidator.SendMessageError(writer, receiver);
            if (error.equals("")) {
                Chat chat = createChat(writer, receiver);
                Message message = new Message(content, writer.getID(), null, imageID, false);
                chat.addMessage(message.getID());
                ViewManager.getInstance().back();
            } else //error occurred
                newMessageEvent.getMessageSendingFXController().setErrorLabel(error);
        } else {
            Message message = new Message(content, writer.getID(), null, imageID, false);
            ChatSelectingController chatSelectingController = new ChatSelectingController();
            chatSelectingController.switchPage(writer.getID(), message.getID());
        }
    }
}
