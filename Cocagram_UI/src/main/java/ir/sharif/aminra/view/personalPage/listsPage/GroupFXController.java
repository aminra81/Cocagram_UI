package ir.sharif.aminra.view.personalPage.listsPage;

import ir.sharif.aminra.events.personalPage.listsPage.GroupPageEvent;
import ir.sharif.aminra.events.personalPage.listsPage.GroupPageEventType;
import ir.sharif.aminra.listeners.personalPage.listsPage.GroupPageListener;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupFXController extends FXController implements Initializable {
    GroupPageListener groupPageListener;
    Group group;

    public Group getGroup() { return group; }

    public void setGroup(Group group) { this.group = group; }

    @FXML
    private VBox membersBox;

    @FXML
    private TextField addUserField;

    @FXML
    private TextField removeUserField;

    @FXML
    private Label errorLabel;

    @FXML
    public void addUser() {
        groupPageListener.eventOccurred(new GroupPageEvent(GroupPageEventType.ADD_USER, userID,
                addUserField.getText(), group, this));
    }

    @FXML
    public void removeGroup() {
        groupPageListener.eventOccurred(new GroupPageEvent(GroupPageEventType.REMOVE_GROUP, userID,
                "", group,  this));
    }

    @FXML
    public void removeUser() {
        groupPageListener.eventOccurred(new GroupPageEvent(GroupPageEventType.REMOVE_USER, userID,
                removeUserField.getText(), group,  this));
    }

    public VBox getMembersBox() { return membersBox; }

    public void setErrorLabel(String error) { errorLabel.setText(error); }

    @Override
    public void refresh() {
        groupPageListener.eventOccurred(new GroupPageEvent(GroupPageEventType.REFRESH, userID,
                "", group, this));
    }

    @Override
    public void clear() {
        membersBox.getChildren().clear();
        addUserField.clear();
        removeUserField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupPageListener = new GroupPageListener();
    }
}
