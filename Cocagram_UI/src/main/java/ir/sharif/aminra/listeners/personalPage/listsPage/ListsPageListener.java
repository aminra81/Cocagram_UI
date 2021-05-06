package ir.sharif.aminra.listeners.personalPage.listsPage;

import ir.sharif.aminra.controller.personalPage.listsPage.ListsPageController;
import ir.sharif.aminra.controller.personalPage.listsPage.NewGroupController;
import ir.sharif.aminra.view.personalPage.listsPage.ListsFXController;

public class ListsPageListener {
    ListsFXController listsFXController;

    public ListsPageListener(ListsFXController listsFXController) {
        this.listsFXController = listsFXController;
    }

    public void stringEventOccurred(String event) {
        switch (event) {
            case "refresh":
                ListsPageController listsPageController = new ListsPageController();
                listsPageController.refresh(listsFXController);
                break;
            case "newGroup":
                NewGroupController newGroupController = new NewGroupController();
                newGroupController.switchPage(listsFXController);
                break;
            default:
                break;
        }
    }
}
