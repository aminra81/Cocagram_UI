package ir.sharif.aminra.controller.settingsPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.mainPage.MainFXController;
import ir.sharif.aminra.view.settings.SettingsFXController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;

public class SettingsController {

    static private final Logger logger = LogManager.getLogger(SettingsController.class);

    public void switchPage(MainFXController mainFXController) {
        Page settingsPage = new Page("settingsPage");
        SettingsFXController settingsFXController = (SettingsFXController) settingsPage.getFxController();
        settingsFXController.setUserID(mainFXController.getUserID());
        ViewManager.getInstance().setPage(settingsPage);
    }

    public void logout(ID userID) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        logger.info(String.format("user %s logged out from the app.", user.getUsername()));
        user.setLastSeen(LocalDateTime.now());
        ViewManager.getInstance().setPage(new Page("enterPage"));
    }

    public void deleteAccount(ID userID) {
        Context.getInstance().getTweetDB().deleteTweets(userID);
        Context.getInstance().getMessageDB().deleteMessages(userID);
        Context.getInstance().getChatDB().deleteChats(userID);
        Context.getInstance().getUserDB().deleteUser(userID);
        ViewManager.getInstance().setPage(new Page("enterPage"));
    }
}
