package ir.sharif.aminra.view.personalPage.listsPage;

import ir.sharif.aminra.events.personalPage.listsPage.ViewPanelEvent;
import ir.sharif.aminra.listeners.personalPage.listsPage.ViewPanelListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewPanelFXController extends FXController implements Initializable {

    ID userToBeVisitedID;
    ViewPanelListener viewPanelListener;

    public void setUserToBeVisitedID(ID userToBeVisitedID) {
        this.userToBeVisitedID = userToBeVisitedID;
    }

    @FXML
    private AnchorPane viewPane;

    @FXML
    private Label usernameLabel;

    public void setUsernameLabel(String username) {
        usernameLabel.setText(username);
    }

    public AnchorPane getViewPane() { return viewPane; }

    @FXML
    public void view() {
        viewPanelListener.eventOccurred(new ViewPanelEvent(userID, userToBeVisitedID));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewPanelListener = new ViewPanelListener();
    }
}
