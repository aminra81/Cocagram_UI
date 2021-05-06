package ir.sharif.aminra.view.personalPage.listsPage;

import ir.sharif.aminra.listeners.personalPage.listsPage.GroupPanelListener;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupPanelFXController extends FXController implements Initializable {

    GroupPanelListener groupPanelListener;

    Group group;

    @FXML
    private AnchorPane groupPane;

    @FXML
    private Label groupNameLabel;

    public void setGroupNameLabel(String groupName) {
        groupNameLabel.setText(groupName);
    }

    public Group getGroup() { return group; }

    public void setGroup(Group group) { this.group = group; }

    public AnchorPane getGroupPane() { return groupPane; }

    @FXML
    public void view() {
        groupPanelListener.stringEventOccurred("view");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupPanelListener = new GroupPanelListener(this);
    }
}
