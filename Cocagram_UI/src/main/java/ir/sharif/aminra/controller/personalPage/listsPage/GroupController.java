package ir.sharif.aminra.controller.personalPage.listsPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.personalPage.listsPage.GroupPageEvent;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.personalPage.listsPage.GroupFXController;
import ir.sharif.aminra.view.personalPage.listsPage.GroupPanelFXController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupController {

    static private final Logger logger = LogManager.getLogger(GroupController.class);

    public void switchPage(GroupPanelFXController groupPanelFXController) {
        Page groupPage = new Page("groupPage");
        GroupFXController groupFXController = (GroupFXController) groupPage.getFxController();
        groupFXController.setUserID(groupPanelFXController.getUserID());
        groupFXController.setGroup(groupPanelFXController.getGroup());
        refresh(groupFXController);
        ViewManager.getInstance().setPage(groupPage);
    }

    public void refresh(GroupFXController groupFXController) {
        groupFXController.clear();
        User user = Context.getInstance().getUserDB().getByID(groupFXController.getUserID());
        Group group = groupFXController.getGroup();
        ListsPageController listsPageController = new ListsPageController();
        listsPageController.addListToBox(user, group.getUsers(), groupFXController.getMembersBox());
    }

    public void removeGroup(ID userID, Group group) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        user.removeGroup(group);
        logger.info(String.format("user %s removed group %s", user.getUsername(), group));
        ViewManager.getInstance().back();
    }

    public void removeUser(GroupPageEvent groupPageEvent) {
        User user = Context.getInstance().getUserDB().getByID(groupPageEvent.getUser());
        User currentUser = Context.getInstance().getUserDB().getUser(groupPageEvent.getUsername());
        Group group = groupPageEvent.getGroup();
        Config errorsConfig = Config.getConfig("groupPage");

        if(currentUser == null || !currentUser.isActive()) {
            groupPageEvent.getGroupFXController().setErrorLabel(errorsConfig.getProperty("notExistedUserError"));
            logger.info(String.format("user %s wants to remove a user which doesn't exist.",
                    user.getUsername()));
        }
        else if(!group.getUsers().contains(currentUser.getID())) {
            groupPageEvent.getGroupFXController().setErrorLabel(errorsConfig.getProperty("userNotInGroupError"));
            logger.info(String.format("user %s wants to remove a user which doesn't exist from group.",
                    user.getUsername()));
        }
        else {
            group.removeUser(currentUser.getID());
            user.removeGroup(group);
            user.addGroup(group);
            ViewManager.getInstance().back();
        }
    }

    public void addUser(GroupPageEvent groupPageEvent) {
        User user = Context.getInstance().getUserDB().getByID(groupPageEvent.getUser());
        User currentUser = Context.getInstance().getUserDB().getUser(groupPageEvent.getUsername());
        Group group = groupPageEvent.getGroup();
        Config errorsConfig = Config.getConfig("groupPage");

        if(currentUser == null || !currentUser.isActive()) {
            groupPageEvent.getGroupFXController().setErrorLabel(errorsConfig.getProperty("notExistedUserError"));
            logger.info(String.format("user %s wants to add a user which doesn't exist to group.",
                    user.getUsername()));
        }
        else if(!user.getFollowings().contains(currentUser.getID())) {
            groupPageEvent.getGroupFXController().setErrorLabel(errorsConfig.getProperty("userNotInFollowingsError"));
            logger.info(String.format("user %s wants to add a user which doesn't exist to group.",
                    user.getUsername()));
        }
        else if(group.getUsers().contains(currentUser.getID())) {
            groupPageEvent.getGroupFXController().setErrorLabel(errorsConfig.getProperty("userAlreadyInGroupError"));
            logger.info(String.format("user %s wants to add a user which doesn't exist to group.",
                    user.getUsername()));
        }
        else {
            group.addUser(currentUser.getID());
            user.removeGroup(group);
            user.addGroup(group);
            ViewManager.getInstance().back();
        }
    }
}
