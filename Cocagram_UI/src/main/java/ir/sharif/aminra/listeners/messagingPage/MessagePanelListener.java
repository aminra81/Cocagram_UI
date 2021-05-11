package ir.sharif.aminra.listeners.messagingPage;

import ir.sharif.aminra.controller.messagingPage.MessageViewerController;
import ir.sharif.aminra.view.messagingPage.MessagePanelFXController;

public class MessagePanelListener {
    MessagePanelFXController messagePanelFXController;

    public MessagePanelListener(MessagePanelFXController messagePanelFXController) {
        this.messagePanelFXController = messagePanelFXController;
    }

    public void stringEventOccurred(String event) {
        MessageViewerController messageViewerController = new MessageViewerController();
        if(event.equals("view"))
            messageViewerController.switchPage(messagePanelFXController.getUserID(), messagePanelFXController.getMessageID());
    }
}
