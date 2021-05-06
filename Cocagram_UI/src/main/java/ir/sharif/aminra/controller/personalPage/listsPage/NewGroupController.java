package ir.sharif.aminra.controller.personalPage.listsPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.personalPage.listsPage.NewGroupEvent;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.personalPage.listsPage.ListsFXController;
import ir.sharif.aminra.view.personalPage.listsPage.NewGroupFXController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewGroupController {

    static private final Logger logger = LogManager.getLogger(NewGroupController.class);

    public void switchPage(ListsFXController listsFXController) {
        Page newGroupPage = new Page("newGroupPage");
        NewGroupFXController newGroupFXController = (NewGroupFXController) newGroupPage.getFxController();
        newGroupFXController.setUserID(listsFXController.getUserID());
        ViewManager.getInstance().setPage(newGroupPage);
    }

    public void makeNewGroup(NewGroupEvent newGroupEvent) {
        User user = Context.getInstance().getUserDB().getByID(newGroupEvent.getUserID());
        Group newGroup = new Group(newGroupEvent.getGroupName());
        if(user.getGroups().contains(newGroup)) {
            logger.info(String.format("user %s wants to make a group which doesn't exist.", user.getUsername()));
            newGroupEvent.getNewGroupFXController().setErrorLabel(Config.getConfig("newGroupPage").
                    getProperty("alreadyExistedGroupError"));
        }
        else {
            logger.info(String.format("user %s created a group.", user.getUsername()));
            user.addGroup(newGroup);
            ViewManager.getInstance().back();
        }
    }
}
