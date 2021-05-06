package ir.sharif.aminra.controller.settingsPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.settingsPage.PrivacySettingsEvent;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.settings.PrivacySettingsFXController;
import ir.sharif.aminra.view.settings.SettingsFXController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrivacySettingsController {

    static private final Logger logger = LogManager.getLogger(PrivacySettingsController.class);

    public void switchPage(SettingsFXController settingsFXController) {
        Page privacySettingsPage = new Page("privacySettingsPage");
        PrivacySettingsFXController privacySettingsFXController = (PrivacySettingsFXController)
                privacySettingsPage.getFxController();
        privacySettingsFXController.setUserID(settingsFXController.getUserID());
        refresh(privacySettingsFXController);
        ViewManager.getInstance().setPage(privacySettingsPage);
    }

    public void refresh(PrivacySettingsFXController privacySettingsFXController) {
        User user = Context.getInstance().getUserDB().getByID(privacySettingsFXController.getUserID());

        privacySettingsFXController.setPrivacy(user.isPrivate());
        privacySettingsFXController.setLastSeen(user.getLastSeenType());
        privacySettingsFXController.setPasswordField(user.getPassword());
    }

    public void deactivate(ID userID) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        logger.info(String.format("user %s deactivated his/her account.", user.getUsername()));
        user.setActive(false);
        SettingsController settingsController = new SettingsController();
        settingsController.logout(userID);
    }

    public void edit(PrivacySettingsEvent privacySettingsEvent) {
        User user = Context.getInstance().getUserDB().getByID(privacySettingsEvent.getUserID());
        logger.info(String.format("user %s changed his/her privacy settings", user.getUsername()));
        user.setPassword(privacySettingsEvent.getPassword());
        user.setLastSeenType(privacySettingsEvent.getLastSeenType());
        user.setPrivate(privacySettingsEvent.isPrivate());
        SettingsController settingsController = new SettingsController();
        settingsController.logout(privacySettingsEvent.getUserID());
    }
}
