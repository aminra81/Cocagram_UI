package ir.sharif.aminra.view.profileView;

import ir.sharif.aminra.events.profileView.ProfilePageEvent;
import ir.sharif.aminra.events.profileView.ProfilePageEventType;
import ir.sharif.aminra.listeners.profileView.ProfilePageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFXController extends FXController implements Initializable {
    ProfilePageListener profilePageListener;
    ID userToVeVisited;

    public ID getUserToVeVisited() {
        return userToVeVisited;
    }

    public void setUserToVeVisited(ID userToVeVisited) {
        this.userToVeVisited = userToVeVisited;
    }

    @FXML
    private Circle avatar;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Label lastSeenLabel;

    @FXML
    private Label bioLabel;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Button muteButton;

    @FXML
    private Button blockButton;

    @FXML
    private Button followButton;

    public Circle getAvatar() {
        return avatar;
    }
    
    public void setUsernameLabel(String username) { usernameLabel.setText(username); }
    
    public void setFirstnameLabel(String firstname) { firstnameLabel.setText(firstname); }
    
    public void setLastnameLabel(String lastname) { lastnameLabel.setText(lastname); }

    public void setLastSeenLabel(String lastSeen) { lastSeenLabel.setText(lastSeen); }

    public void setBioLabel(String bio) { bioLabel.setText(bio); }

    public void setBirthDateLabel(String birthDate) { birthDateLabel.setText(birthDate); }

    public void setEmailLabel(String email) { emailLabel.setText(email); }

    public void setPhoneNumberLabel(String phoneNumber) { phoneNumberLabel.setText(phoneNumber); }

    public void setBlockButtonText(String text) { blockButton.setText(text); }

    public void setMuteButtonText(String text) { muteButton.setText(text); }

    public void setFollowButton(String text) { followButton.setText(text); }

    @FXML
    public void blockHandling() {
        profilePageListener.eventOccurred(new ProfilePageEvent(ProfilePageEventType.BLOCK, userID, userToVeVisited,
                this));
    }

    @FXML
    public void followHandling() {
        profilePageListener.eventOccurred(new ProfilePageEvent(ProfilePageEventType.FOLLOW, userID, userToVeVisited,
                this));
    }

    @FXML
    public void muteHandling() {
        profilePageListener.eventOccurred(new ProfilePageEvent(ProfilePageEventType.MUTE, userID, userToVeVisited,
                this));
    }

    @Override
    public void refresh() {
        profilePageListener.eventOccurred(new ProfilePageEvent(ProfilePageEventType.REFRESH, userID, userToVeVisited,
                this));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePageListener = new ProfilePageListener();
    }
}
