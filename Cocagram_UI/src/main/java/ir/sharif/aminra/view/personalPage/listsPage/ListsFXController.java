package ir.sharif.aminra.view.personalPage.listsPage;

import ir.sharif.aminra.listeners.personalPage.listsPage.ListsPageListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ListsFXController extends FXController implements Initializable {

    ListsPageListener listsPageListener;

    @FXML
    private VBox followersBox;

    @FXML
    private VBox followingsBox;

    @FXML
    private VBox blockedUsersBox;

    @FXML
    private VBox groupsBox;

    public VBox getFollowersBox() { return followersBox; }

    public VBox getFollowingsBox() { return followingsBox; }

    public VBox getBlockedUsersBox() { return blockedUsersBox; }

    public VBox getGroupsBox() { return groupsBox; }

    @Override
    public void clear() {
        followersBox.getChildren().clear();
        followingsBox.getChildren().clear();
        blockedUsersBox.getChildren().clear();
        groupsBox.getChildren().clear();
    }

    @Override
    public void refresh() { listsPageListener.stringEventOccurred("refresh"); }

    @FXML
    public void newGroup() { listsPageListener.stringEventOccurred("newGroup"); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listsPageListener = new ListsPageListener(this);
    }
}
