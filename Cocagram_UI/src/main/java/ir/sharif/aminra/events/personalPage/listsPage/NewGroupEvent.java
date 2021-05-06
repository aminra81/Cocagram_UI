package ir.sharif.aminra.events.personalPage.listsPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.personalPage.listsPage.NewGroupFXController;

public class NewGroupEvent {
    String groupName;
    ID userID;
    NewGroupFXController newGroupFXController;

    public NewGroupEvent(String groupName, ID userID, NewGroupFXController newGroupFXController) {
        this.groupName = groupName;
        this.userID = userID;
        this.newGroupFXController = newGroupFXController;
    }

    public String getGroupName() {
        return groupName;
    }

    public ID getUserID() {
        return userID;
    }

    public NewGroupFXController getNewGroupFXController() {
        return newGroupFXController;
    }
}
