package ir.sharif.aminra.listeners.messagingPage.messageSendigPage;

import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.view.messagingPage.messageSendingPage.ChatSelectingFXController;

public class GroupPanelToChatPageListener {
    ChatSelectingFXController chatSelectingFXController;

    public GroupPanelToChatPageListener(ChatSelectingFXController chatSelectingFXController) {
        this.chatSelectingFXController = chatSelectingFXController;
    }

    public void switchState(Group group) {
        if(chatSelectingFXController.getSelectedGroups().contains(group))
            chatSelectingFXController.removeGroup(group);
        else
            chatSelectingFXController.addGroup(group);
    }
}
