package ir.sharif.aminra.listeners.personalPage.listsPage;

import ir.sharif.aminra.controller.personalPage.listsPage.GroupController;
import ir.sharif.aminra.view.personalPage.listsPage.GroupPanelFXController;

public class GroupPanelListener {
    GroupPanelFXController groupPanelFXController;

    public GroupPanelListener(GroupPanelFXController groupPanelFXController) {
        this.groupPanelFXController = groupPanelFXController;
    }

    public void stringEventOccurred(String event) {
        GroupController groupController = new GroupController();
        if(event.equals("view"))
            groupController.switchPage(groupPanelFXController);
    }
}
