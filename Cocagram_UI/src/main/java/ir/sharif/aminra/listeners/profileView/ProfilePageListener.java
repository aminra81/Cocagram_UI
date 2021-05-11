package ir.sharif.aminra.listeners.profileView;

import ir.sharif.aminra.controller.messagingPage.messageSendingPage.MessageSendingController;
import ir.sharif.aminra.controller.profileView.ProfileViewController;
import ir.sharif.aminra.events.profileView.ProfilePageEvent;

public class ProfilePageListener {
    public void eventOccurred(ProfilePageEvent profilePageEvent) {

        ProfileViewController profileViewController = new ProfileViewController();

        switch (profilePageEvent.getProfilePageEventType()) {
            case MUTE:
                profileViewController.muteHandling(profilePageEvent.getUserID(),
                        profilePageEvent.getUserToBeVisitedID(), profilePageEvent.getProfileFXController());
                break;
            case BLOCK:
                profileViewController.blockHandling(profilePageEvent.getUserID(),
                        profilePageEvent.getUserToBeVisitedID(), profilePageEvent.getProfileFXController());
                break;
            case FOLLOW:
                profileViewController.followHandling(profilePageEvent.getUserID(),
                        profilePageEvent.getUserToBeVisitedID(), profilePageEvent.getProfileFXController());
                break;
            case REFRESH:
                profileViewController.refresh(profilePageEvent.getProfileFXController());
                break;
            case MESSAGE:
                MessageSendingController messageSendingController = new MessageSendingController();
                messageSendingController.switchPage(profilePageEvent.getUserID(), profilePageEvent.getUserToBeVisitedID());
        }
    }
}
