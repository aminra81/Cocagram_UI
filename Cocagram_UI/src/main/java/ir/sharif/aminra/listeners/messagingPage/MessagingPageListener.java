package ir.sharif.aminra.listeners.messagingPage;

import ir.sharif.aminra.controller.messagingPage.MessagingController;
import ir.sharif.aminra.controller.messagingPage.chatGroupPage.ChatGroupController;
import ir.sharif.aminra.controller.messagingPage.messageSendingPage.MessageSendingController;
import ir.sharif.aminra.view.messagingPage.MessagingFXController;

public class MessagingPageListener {
    MessagingFXController messagingFXController;

    public MessagingPageListener(MessagingFXController messagingFXController) {
        this.messagingFXController = messagingFXController;
    }

    public void stringEventOccurred(String event) {
        switch (event) {
            case "refresh":
                MessagingController messagingController = new MessagingController();
                messagingController.refresh(messagingFXController);
                break;
            case "sendMessage":
                MessageSendingController messageSendingController = new MessageSendingController();
                messageSendingController.switchPage(messagingFXController.getUserID(), null);
                break;
            case "chatGroup":
                ChatGroupController chatGroupController = new ChatGroupController();
                chatGroupController.switchPage(messagingFXController);
                break;
            default:
                break;
        }
    }
}
