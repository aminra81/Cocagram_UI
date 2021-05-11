package ir.sharif.aminra.models;

import java.time.LocalDateTime;

public class ChatState {
    ID chat;
    LocalDateTime lastCheck;

    public ChatState(ID chat) {
        this.chat = chat;
        this.lastCheck = LocalDateTime.now();
    }

    public ID getChat() {
        return chat;
    }

    public LocalDateTime getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(LocalDateTime lastCheck) { this.lastCheck = lastCheck; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatState chatState = (ChatState) o;
        return chatState.getChat().equals(getChat());
    }
}
