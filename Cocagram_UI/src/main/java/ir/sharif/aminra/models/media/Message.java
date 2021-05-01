package ir.sharif.aminra.models.media;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;

public class Message extends Media {
    ID receiver;

    public Message(String content, ID writer, ID receiver, ID image) {
        super(content, writer, image);
        this.receiver = receiver;

        Context.getInstance().getMessageDB().saveIntoDB(this);
    }

    public ID getReceiver() { return receiver; }


    public static void sortByDateTime(List<Message> messages) {
        Comparator<Message> byDateTime = Comparator.comparing(Message::getDateTime).reversed();
        messages.sort(byDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return message.getId().equals(getId());
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
