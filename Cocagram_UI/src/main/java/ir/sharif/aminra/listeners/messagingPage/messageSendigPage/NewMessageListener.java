package ir.sharif.aminra.listeners.messagingPage.messageSendigPage;

import ir.sharif.aminra.controller.messagingPage.messageSendingPage.MessageSendingController;
import ir.sharif.aminra.events.messagingPage.messageSendingPage.NewMessageEvent;

public class NewMessageListener {
    MessageSendingController messageSendingController;
    public void eventOccurred(NewMessageEvent newMessageEvent) {
        messageSendingController = new MessageSendingController();
        messageSendingController.addMessage(newMessageEvent);
    }
}
