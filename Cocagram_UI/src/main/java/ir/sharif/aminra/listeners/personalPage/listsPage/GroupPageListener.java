package ir.sharif.aminra.listeners.personalPage.listsPage;

import ir.sharif.aminra.controller.personalPage.listsPage.GroupController;
import ir.sharif.aminra.events.personalPage.listsPage.GroupPageEvent;

public class GroupPageListener {
    public void eventOccurred(GroupPageEvent groupPageEvent) {
        GroupController groupController = new GroupController();
        switch (groupPageEvent.getGroupPageEventType()) {
            case REFRESH:
                groupController.refresh(groupPageEvent.getGroupFXController());
                break;
            case ADD_USER:
                groupController.addUser(groupPageEvent);
                break;
            case REMOVE_GROUP:
                groupController.removeGroup(groupPageEvent.getUser(), groupPageEvent.getGroup());
                break;
            case REMOVE_USER:
                groupController.removeUser(groupPageEvent);
                break;
            default:
                break;
        }
    }
}
