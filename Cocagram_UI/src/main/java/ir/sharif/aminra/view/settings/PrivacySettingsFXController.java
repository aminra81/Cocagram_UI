package ir.sharif.aminra.view.settings;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.events.settingsPage.PrivacySettingsEvent;
import ir.sharif.aminra.listeners.settingsPage.PrivacySettingsPageListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PrivacySettingsFXController extends FXController implements Initializable {

    PrivacySettingsPageListener privacySettingsPageListener;

    @FXML
    private CheckBox privacy;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> lastSeenChoice;

    private final String[] lastSeenChoices = Config.getConfig("signUpPage").
            getPropertyArray(String.class, "allOptions");

    @FXML
    public void deactivate() {
        privacySettingsPageListener.stringEventOccurred("deactivate");
    }

    @FXML
    public void edit() {
        privacySettingsPageListener.eventOccurred(new PrivacySettingsEvent(this, userID,
                passwordField.getText(), lastSeenChoice.getValue(), privacy.isSelected()));
    }

    @FXML
    public void setPasswordField(String password) {
        passwordField.setText(password);
    }

    @FXML
    public void setLastSeen(String lastSeen) {
        lastSeenChoice.setValue(lastSeen);
    }

    @FXML
    public void setPrivacy(boolean isPrivate) {
        privacy.setSelected(isPrivate);
    }

    @Override
    public void refresh() {
        privacySettingsPageListener.stringEventOccurred("refresh");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastSeenChoice.getItems().addAll(lastSeenChoices);
        privacySettingsPageListener = new PrivacySettingsPageListener(this);
    }
}
