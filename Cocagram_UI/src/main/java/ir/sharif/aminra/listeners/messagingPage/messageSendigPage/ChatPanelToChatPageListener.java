package ir.sharif.aminra.listeners.messagingPage.messageSendigPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.ChatSelectingFXController;

public class ChatPanelToChatPageListener {
    ChatSelectingFXController chatSelectingFXController;

    public ChatPanelToChatPageListener(ChatSelectingFXController chatSelectingFXController) {
        this.chatSelectingFXController = chatSelectingFXController;
    }

    public void switchState(ID chat) {
        if(chatSelectingFXController.getSelectedChats().contains(chat))
            chatSelectingFXController.removeChat(chat);
        else
            chatSelectingFXController.addChat(chat);
    }
}
