package ir.sharif.aminra.listeners.explorerPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.controller.explorerPage.ExplorerController;
import ir.sharif.aminra.controller.profileView.ProfileViewController;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.explorerPage.ExplorerPageEvent;
import ir.sharif.aminra.models.User;

public class ExplorerListener {
    public void eventOccurred(ExplorerPageEvent explorerPageEvent) {
        switch (explorerPageEvent.getExplorerPageEventType()) {
            case REFRESH:
                ExplorerController explorerController = new ExplorerController();
                explorerController.refresh(explorerPageEvent.getExplorerFXController());
                break;
            case SEARCH:
                User user = Context.getInstance().getUserDB().getByID(explorerPageEvent.getUserID());
                User userToBeVisited = Context.getInstance().getUserDB().getUser(explorerPageEvent.getSearchedUsername());
                if(userToBeVisited == null)
                    explorerPageEvent.getExplorerFXController().setErrorLabel(
                            Config.getConfig("explorerPage").getProperty(String.class, "notFoundError"));
                else {
                    ProfileViewController profileViewController = new ProfileViewController();
                    profileViewController.switchPage(user, userToBeVisited);
                }
                break;
            default:
                break;
        }
    }
}
