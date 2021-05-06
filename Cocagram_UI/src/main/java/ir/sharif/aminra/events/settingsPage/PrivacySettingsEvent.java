package ir.sharif.aminra.events.settingsPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.settings.PrivacySettingsFXController;

public class PrivacySettingsEvent {

    PrivacySettingsFXController privacySettingsFXController;
    ID userID;
    String password;
    String lastSeenType;
    boolean isPrivate;

    public PrivacySettingsEvent(PrivacySettingsFXController privacySettingsFXController,
                                ID userID, String password, String lastSeenType, boolean isPrivate) {
        this.privacySettingsFXController = privacySettingsFXController;
        this.userID = userID;
        this.password = password;
        this.lastSeenType = lastSeenType;
        this.isPrivate = isPrivate;
    }

    public PrivacySettingsFXController getPrivacySettingsFXController() {
        return privacySettingsFXController;
    }

    public ID getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getLastSeenType() {
        return lastSeenType;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
