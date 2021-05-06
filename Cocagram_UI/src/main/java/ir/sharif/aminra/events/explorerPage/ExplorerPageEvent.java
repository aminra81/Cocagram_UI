package ir.sharif.aminra.events.explorerPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.explorerPage.ExplorerFXController;

public class ExplorerPageEvent {
    ExplorerPageEventType explorerPageEventType;
    ID userID;
    String searchedUsername;
    ExplorerFXController explorerFXController;

    public ExplorerPageEvent(ExplorerPageEventType explorerPageEventType, ID userID, String searchedUsername, ExplorerFXController explorerFXController) {
        this.explorerPageEventType = explorerPageEventType;
        this.userID = userID;
        this.searchedUsername = searchedUsername;
        this.explorerFXController = explorerFXController;
    }

    public ExplorerPageEventType getExplorerPageEventType() {
        return explorerPageEventType;
    }

    public ID getUserID() {
        return userID;
    }

    public String getSearchedUsername() {
        return searchedUsername;
    }

    public ExplorerFXController getExplorerFXController() {
        return explorerFXController;
    }
}
