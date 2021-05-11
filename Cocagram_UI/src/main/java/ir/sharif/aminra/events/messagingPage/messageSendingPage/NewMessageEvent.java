package ir.sharif.aminra.events.messagingPage.messageSendingPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.MessageSendingFXController;
import javafx.scene.image.Image;

public class NewMessageEvent {
    private final MessageSendingFXController messageSendingFXController;
    private final Image image;
    private final String content;
    private final ID receiverID;
    private final ID writerID;

    public NewMessageEvent(MessageSendingFXController messageSendingFXController, Image image, String content, ID receiverID, ID writerID) {
        this.messageSendingFXController = messageSendingFXController;
        this.image = image;
        this.content = content;
        this.receiverID = receiverID;
        this.writerID = writerID;
    }

    public MessageSendingFXController getMessageSendingFXController() {
        return messageSendingFXController;
    }

    public Image getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public ID getReceiverID() {
        return receiverID;
    }

    public ID getWriterID() {
        return writerID;
    }
}
