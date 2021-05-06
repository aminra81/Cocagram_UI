package ir.sharif.aminra.events.personalPage.listsPage;

import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.personalPage.listsPage.GroupFXController;

public class GroupPageEvent {
    GroupPageEventType groupPageEventType;
    ID user;
    String username;
    Group group;
    GroupFXController groupFXController;

    public GroupPageEvent(GroupPageEventType groupPageEventType, ID user, String username, Group group, GroupFXController groupFXController) {
        this.groupPageEventType = groupPageEventType;
        this.user = user;
        this.username = username;
        this.group = group;
        this.groupFXController = groupFXController;
    }

    public GroupPageEventType getGroupPageEventType() {
        return groupPageEventType;
    }

    public ID getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public Group getGroup() {
        return group;
    }

    public GroupFXController getGroupFXController() {
        return groupFXController;
    }
}
