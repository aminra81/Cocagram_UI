package ir.sharif.aminra.listeners.messagingPage;

import ir.sharif.aminra.controller.messagingPage.MessagingController;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.messagingPage.MessagingFXController;

public class ChatPanelToMessagingPageListener {
    MessagingFXController messagingFXController;

    public ChatPanelToMessagingPageListener(MessagingFXController messagingFXController) {
        this.messagingFXController = messagingFXController;
    }

    public void eventOccurred(ID chatID) {
        MessagingController messagingController = new MessagingController();
        messagingController.changeChat(messagingFXController, chatID);
    }
}
