package ir.sharif.aminra.events.personalPage.listsPage;

import ir.sharif.aminra.models.ID;

public class ViewPanelEvent {
    ID userID;
    ID userToBeVisitedID;

    public ViewPanelEvent(ID userID, ID userToBeVisitedID) {
        this.userID = userID;
        this.userToBeVisitedID = userToBeVisitedID;
    }

    public ID getUserID() {
        return userID;
    }

    public ID getUserToBeVisitedID() {
        return userToBeVisitedID;
    }
}
