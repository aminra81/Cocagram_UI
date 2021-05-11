package ir.sharif.aminra.models;

import ir.sharif.aminra.db.Context;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    ID id;
    String chatName;
    List<ID> messages;
    List<ID> users;
    boolean isGroup;

    public Chat(String chatName, boolean isGroup) {
        this.id = new ID(true);
        this.chatName = chatName;
        this.isGroup = isGroup;
        this.users = new ArrayList<>();
        messages = new ArrayList<>();

        Context.getInstance().getChatDB().saveIntoDB(this);
    }

    public ID getID() { return id; }

    public String getChatName() {
        return chatName;
    }

    public List<ID> getMessages() {
        return messages;
    }

    public List<ID> getUsers() {
        return users;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void addUser(ID userID) {
        users.add(userID);

        Context.getInstance().getChatDB().saveIntoDB(this);
    }

    public void addMessage(ID messageID) {
        messages.add(messageID);

        Context.getInstance().getChatDB().saveIntoDB(this);
    }

    public void removeMessage(ID messageID) {
        messages.remove(messageID);

        Context.getInstance().getChatDB().saveIntoDB(this);
    }

    public void removeUser(ID userID) {
        users.remove(userID);

        Context.getInstance().getChatDB().saveIntoDB(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return chat.getID().equals(getID());
    }
}
