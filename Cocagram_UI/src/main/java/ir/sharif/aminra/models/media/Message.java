package ir.sharif.aminra.models.media;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;

public class Message extends Media {

    ID mainMedia;
    boolean isForwardedTweet;
    boolean isDeleted;

    public Message(String content, ID writer, ID mainMedia, ID image, boolean isForwardedTweet) {
        super(content, writer, image);
        this.mainMedia = mainMedia;
        this.isForwardedTweet = isForwardedTweet;
        isDeleted = false;

        Context.getInstance().getMessageDB().saveIntoDB(this);
    }


    public static void sortByDateTime(List<Message> messages) {
        Comparator<Message> byDateTime = Comparator.comparing(Message::getDateTime);
        messages.sort(byDateTime);
    }

    public ID getMainMedia() { return mainMedia; }

    public boolean isForwardedTweet() { return isForwardedTweet; }

    public boolean isDeleted() { return isDeleted; }

    public void setContent(String content) {
        this.content = content;

        Context.getInstance().getMessageDB().saveIntoDB(this);
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;

        Context.getInstance().getMessageDB().saveIntoDB(this);
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;

        Context.getInstance().getMessageDB().saveIntoDB(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return message.getID().equals(getID());
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
