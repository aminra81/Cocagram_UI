package ir.sharif.aminra.listeners.settingsPage;

import ir.sharif.aminra.controller.settingsPage.PrivacySettingsController;
import ir.sharif.aminra.controller.settingsPage.SettingsController;
import ir.sharif.aminra.view.settings.SettingsFXController;

public class SettingsPageListener {
    SettingsFXController settingsFXController;

    public SettingsPageListener(SettingsFXController settingsFXController) {
        this.settingsFXController = settingsFXController;
    }

    public void stringEventOccurred(String event) {
        SettingsController settingsController = new SettingsController();
        switch (event) {
            case "deleteAccount":
                settingsController.deleteAccount(settingsFXController.getUserID());
                break;
            case "logout":
                settingsController.logout(settingsFXController.getUserID());
                break;
            case "privacySettings":
                PrivacySettingsController privacySettingsController = new PrivacySettingsController();
                privacySettingsController.switchPage(settingsFXController);
                break;
        }
    }
}
