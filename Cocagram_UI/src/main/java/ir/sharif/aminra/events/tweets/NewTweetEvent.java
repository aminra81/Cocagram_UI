package ir.sharif.aminra.events.tweets;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.tweets.NewTweetFXController;
import javafx.scene.image.Image;

public class NewTweetEvent {
    private final NewTweetFXController newTweetFXController;
    private final Image image;
    private final String content;
    private final ID upPost;

    public NewTweetEvent(NewTweetFXController newTweetFXController, String content, Image image, ID upPost) {
        this.newTweetFXController = newTweetFXController;
        this.content = content;
        this.image = image;
        this.upPost = upPost;
    }

    public NewTweetFXController getNewTweetFXController() {
        return newTweetFXController;
    }

    public Image getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public ID getUpPost() { return upPost; }
}
