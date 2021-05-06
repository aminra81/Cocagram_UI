package ir.sharif.aminra.listeners.personalPage.listsPage;

import ir.sharif.aminra.controller.profileView.ProfileViewController;
import ir.sharif.aminra.events.personalPage.listsPage.ViewPanelEvent;

public class ViewPanelListener {
    public void eventOccurred(ViewPanelEvent viewPanelEvent) {
        ProfileViewController profileViewController = new ProfileViewController();
        profileViewController.switchPageByID(viewPanelEvent.getUserID(), viewPanelEvent.getUserToBeVisitedID());
    }
}
