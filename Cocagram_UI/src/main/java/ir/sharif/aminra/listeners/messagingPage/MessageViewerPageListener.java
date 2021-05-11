package ir.sharif.aminra.listeners.messagingPage;

import ir.sharif.aminra.controller.messagingPage.MessageViewerController;
import ir.sharif.aminra.controller.profileView.ProfileViewController;
import ir.sharif.aminra.events.messagingPage.MessageViewerEvent;

public class MessageViewerPageListener {
    public void eventOccurred(MessageViewerEvent messageViewerEvent) {
        MessageViewerController messageViewerController = new MessageViewerController();
        switch (messageViewerEvent.getMessageViewerEventType()) {
            case REFRESH:
                messageViewerController.refresh(messageViewerEvent.getMessageViewerFXController());
                break;
            case EDIT:
                messageViewerController.editMessage(messageViewerEvent.getMessageID(),
                        messageViewerEvent.getMessageNewContent(), messageViewerEvent.getMessageViewerFXController());
                break;
            case DELETE:
                messageViewerController.deleteMessage(messageViewerEvent.getMessageID());
                break;
            case FORWARD:
                messageViewerController.forwardMessage(messageViewerEvent.getUserID(), messageViewerEvent.getMessageID());
                break;
            case CHECK_USER_PROFILE:
                ProfileViewController profileViewController = new ProfileViewController();
                profileViewController.switchPageByMessage(messageViewerEvent.getUserID(),
                        messageViewerEvent.getMessageID(), messageViewerEvent.getMessageViewerFXController());
                break;
        }
    }
}
