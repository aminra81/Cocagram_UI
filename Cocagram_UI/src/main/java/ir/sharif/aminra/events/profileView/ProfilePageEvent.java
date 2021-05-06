package ir.sharif.aminra.events.profileView;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.profileView.ProfileFXController;

public class ProfilePageEvent {

    ProfilePageEventType profilePageEventType;
    ID userID;
    ID userToBeVisitedID;
    ProfileFXController profileFXController;

    public ProfilePageEvent(ProfilePageEventType profilePageEventType, ID userID, ID userToBeVisited, ProfileFXController profileFXController) {
        this.profilePageEventType = profilePageEventType;
        this.userID = userID;
        this.userToBeVisitedID = userToBeVisited;
        this.profileFXController = profileFXController;
    }

    public ProfilePageEventType getProfilePageEventType() {
        return profilePageEventType;
    }

    public ID getUserID() {
        return userID;
    }

    public ID getUserToBeVisitedID() {
        return userToBeVisitedID;
    }

    public ProfileFXController getProfileFXController() {
        return profileFXController;
    }
}
