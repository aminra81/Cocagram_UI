package ir.sharif.aminra.view;

import ir.sharif.aminra.models.ID;
import javafx.fxml.FXML;

public abstract class FXController {
    protected ID userID;

    public ID getUserID() {
        return userID;
    }

    public void setUserID(ID userID) {
        this.userID = userID;
    }

    @FXML
    public void back() { ViewManager.getInstance().back(); }

    @FXML
    public void goToMainPage() { ViewManager.getInstance().goToMainPage(userID); }

    public void refresh() {}

    public void clear() {}
}
