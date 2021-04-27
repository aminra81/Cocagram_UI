package ir.sharif.aminra.models.media;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Message extends Media {
    ID receiver;

    static private final Logger logger = LogManager.getLogger(Message.class);
    private static final File dbDirectory = new File("./src/main/resources/DB/Messages");

    public Message(String content, ID writer, ID receiver) {
        super(content, writer);
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
