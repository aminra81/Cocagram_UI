package ir.sharif.aminra.controller.messagingPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.Chat;
import ir.sharif.aminra.models.ChatState;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagingValidator {
    public List<ChatState> getValidatedChatStates(User user) {
        List<ChatState> validatedChatStates = new ArrayList<>();
        for (ChatState chatState : user.getChatStates()) {
            ID chatID = chatState.getChat();
            Chat chat = Context.getInstance().getChatDB().getByID(chatID);
            if(chat.isGroup() || chat.getUsers().size() != 2)
                validatedChatStates.add(chatState);
            else {
                ID otherUser = chat.getUsers().get(0);
                if(otherUser.equals(user.getID()))
                    otherUser = chat.getUsers().get(1);
                if(Context.getInstance().getUserDB().getByID(otherUser).isActive())
                    validatedChatStates.add(chatState);
            }
        }
        return validatedChatStates;
    }

    public List<Message> getValidatedMessages(List<ID> messages) {
        List<Message> validatedMessages = new ArrayList<>();
        for (ID messageID : messages) {
            Message message = Context.getInstance().getMessageDB().getByID(messageID);
            if(message.isDeleted())
                continue; //message deleted
            if(!message.isForwardedTweet() && message.getMainMedia() != null &&
            Context.getInstance().getMessageDB().getByID(message.getMainMedia()).isDeleted())
                continue; //parent message deleted
            if(!Context.getInstance().getUserDB().getByID(message.getWriter()).isActive())
                continue; //writer not active
            validatedMessages.add(message);
        }
        return validatedMessages;
    }
}
