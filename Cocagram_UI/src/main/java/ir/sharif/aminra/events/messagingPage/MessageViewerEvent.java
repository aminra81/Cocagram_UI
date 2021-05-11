package ir.sharif.aminra.events.messagingPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.messagingPage.MessageViewerFXController;

public class MessageViewerEvent {

    MessageViewerEventType messageViewerEventType;
    ID userID;
    ID messageID;
    String messageNewContent;
    MessageViewerFXController messageViewerFXController;

    public MessageViewerEvent(MessageViewerEventType messageViewerEventType, ID userID, ID messageID, String messageNewContent, MessageViewerFXController messageViewerFXController) {
        this.messageViewerEventType = messageViewerEventType;
        this.userID = userID;
        this.messageID = messageID;
        this.messageNewContent = messageNewContent;
        this.messageViewerFXController = messageViewerFXController;
    }

    public MessageViewerEventType getMessageViewerEventType() {
        return messageViewerEventType;
    }

    public ID getUserID() {
        return userID;
    }

    public ID getMessageID() {
        return messageID;
    }

    public String getMessageNewContent() {
        return messageNewContent;
    }

    public MessageViewerFXController getMessageViewerFXController() {
        return messageViewerFXController;
    }
}
