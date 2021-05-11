package ir.sharif.aminra.listeners.messagingPage.messageSendigPage;

import ir.sharif.aminra.controller.messagingPage.messageSendingPage.ChatSelectingController;
import ir.sharif.aminra.events.messagingPage.messageSendingPage.ChatSelectingEvent;

public class ChatSelectingPageListener {
    public void eventOccurred(ChatSelectingEvent chatSelectingEvent) {
        ChatSelectingController chatSelectingController = new ChatSelectingController();
        chatSelectingController.sendMessage(chatSelectingEvent);
    }
}
