package ir.sharif.aminra.listeners.personalPage.listsPage;

import ir.sharif.aminra.controller.personalPage.listsPage.NewGroupController;
import ir.sharif.aminra.events.personalPage.listsPage.NewGroupEvent;

public class NewGroupListener {
    public void eventOccurred(NewGroupEvent newGroupEvent) {
        NewGroupController newGroupController = new NewGroupController();
        newGroupController.makeNewGroup(newGroupEvent);
    }
}
