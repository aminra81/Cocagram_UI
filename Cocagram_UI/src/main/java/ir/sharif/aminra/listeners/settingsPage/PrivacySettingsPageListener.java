package ir.sharif.aminra.listeners.settingsPage;

import ir.sharif.aminra.controller.settingsPage.PrivacySettingsController;
import ir.sharif.aminra.events.settingsPage.PrivacySettingsEvent;
import ir.sharif.aminra.view.settings.PrivacySettingsFXController;

public class PrivacySettingsPageListener {
    PrivacySettingsFXController privacySettingsFXController;

    public PrivacySettingsPageListener(PrivacySettingsFXController privacySettingsFXController) {
        this.privacySettingsFXController = privacySettingsFXController;
    }

    public void stringEventOccurred(String event) {
        PrivacySettingsController privacySettingsController = new PrivacySettingsController();

        switch (event) {
            case "refresh":
                privacySettingsController.refresh(privacySettingsFXController);
                break;
            case "deactivate":
                privacySettingsController.deactivate(privacySettingsFXController.getUserID());
                break;
            default:
                break;
        }
    }

    public void eventOccurred(PrivacySettingsEvent privacySettingsEvent) {
        PrivacySettingsController privacySettingsController = new PrivacySettingsController();
        privacySettingsController.edit(privacySettingsEvent);
    }
}
