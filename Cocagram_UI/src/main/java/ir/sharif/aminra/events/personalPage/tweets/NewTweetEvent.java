package ir.sharif.aminra.events.personalPage.tweets;

import ir.sharif.aminra.view.personalPage.tweets.NewTweetFXController;
import javafx.scene.image.Image;

public class NewTweetEvent {
    private final NewTweetFXController newTweetFXController;
    private final Image image;
    private final String content;
    public NewTweetEvent(NewTweetFXController newTweetFXController, String content, Image image) {
        this.newTweetFXController = newTweetFXController;
        this.content = content;
        this.image = image;
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
}
