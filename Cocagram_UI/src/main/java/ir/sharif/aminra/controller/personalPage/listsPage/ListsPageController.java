package ir.sharif.aminra.controller.personalPage.listsPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.personalPage.MyFXController;
import ir.sharif.aminra.view.personalPage.listsPage.GroupPanelFXController;
import ir.sharif.aminra.view.personalPage.listsPage.ListsFXController;
import ir.sharif.aminra.view.personalPage.listsPage.ViewPanelFXController;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ListsPageController {
    public void switchPage(MyFXController myFXController) {
        Page listsPage = new Page("listsPage");
        ListsFXController listsFXController = (ListsFXController) listsPage.getFxController();
        listsFXController.setUserID(myFXController.getUserID());
        refresh(listsFXController);
        ViewManager.getInstance().setPage(listsPage);
    }

    public void refresh(ListsFXController listsFXController) {
        listsFXController.clear();
        User user = Context.getInstance().getUserDB().getByID(listsFXController.getUserID());
        addListToBox(user, user.getFollowers(), listsFXController.getFollowersBox());
        addListToBox(user, user.getFollowings(), listsFXController.getFollowingsBox());
        addListToBox(user, user.getBlockList(), listsFXController.getBlockedUsersBox());

        //adding groups to group box
        for (Group group : user.getGroups()) {
            Page groupPanel = new Page("groupPanel");
            GroupPanelFXController groupPanelFXController = (GroupPanelFXController) groupPanel.getFxController();
            groupPanelFXController.setGroup(group);
            groupPanelFXController.setUserID(listsFXController.getUserID());
            groupPanelFXController.setGroupNameLabel(group.getGroupName());
            listsFXController.getGroupsBox().getChildren().add(groupPanelFXController.getGroupPane());
        }
    }

    public void addListToBox(User user, List<ID> userList, VBox box) {
        List<User> activeUsers = new ArrayList<>();
        for (ID currentUser : userList) {
            User userToBeVisited = Context.getInstance().getUserDB().getByID(currentUser);
            if(userToBeVisited.isActive())
                activeUsers.add(userToBeVisited);
        }
        for (User userToBeVisited : activeUsers) {
            Page viewPanel = new Page("viewPanel");
            ViewPanelFXController viewPanelFXController = (ViewPanelFXController) viewPanel.getFxController();
            viewPanelFXController.setUserID(user.getID());
            viewPanelFXController.setUserToBeVisitedID(userToBeVisited.getID());
            viewPanelFXController.setUsernameLabel(userToBeVisited.getUsername());
            box.getChildren().add(viewPanelFXController.getViewPane());
        }
    }
}
