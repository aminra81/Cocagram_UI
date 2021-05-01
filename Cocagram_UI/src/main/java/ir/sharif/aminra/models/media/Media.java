package ir.sharif.aminra.models.media;
import ir.sharif.aminra.models.ID;
import java.time.LocalDateTime;

public abstract class Media {
    ID id;
    String content;
    ID writer;
    ID image;
    LocalDateTime datetime;
    public Media(String content, ID writer, ID image) {
        this.id = new ID(true);
        this.content = content;
        this.writer = writer;
        this.image = image;
        datetime = LocalDateTime.now();
    }

    public ID getWriter() { return writer; }

    public String getContent() { return content; }

    public ID getId() {
        return id;
    }

    public ID getImage() { return image; }

    public LocalDateTime getDateTime() {
        return datetime;
    }
}
