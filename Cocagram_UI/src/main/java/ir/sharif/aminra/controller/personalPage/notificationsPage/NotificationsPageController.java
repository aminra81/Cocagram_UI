package ir.sharif.aminra.controller.personalPage.notificationsPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.personalPage.MyFXController;
import ir.sharif.aminra.view.personalPage.notificationsPage.NotificationsFXController;
import ir.sharif.aminra.view.personalPage.notificationsPage.RequestFXController;

public class NotificationsPageController {
    public void switchPage(MyFXController myFXController) {
        Page notificationsPage = new Page("notificationsPage");
        NotificationsFXController notificationsFXController = (NotificationsFXController) notificationsPage.getFxController();
        notificationsFXController.setUserID(myFXController.getUserID());
        refresh(notificationsFXController);
        ViewManager.getInstance().setPage(notificationsPage);
    }
    public void refresh(NotificationsFXController notificationsFXController) {
        User user = Context.getInstance().getUserDB().getByID(notificationsFXController.getUserID());
        //add request messages
        for (String requestMessage : user.getRequestNotifications())
            notificationsFXController.addRequestMessage(requestMessage);
        //add system messages
        for (String systemMessage : user.getNotifications())
            notificationsFXController.addSystemMessage(systemMessage);
        //add requests
        for (ID userID : user.getRequests()) {
            User requester = Context.getInstance().getUserDB().getByID(userID);

            Page requestPanel = new Page("requestPanel");
            RequestFXController requestFXController = (RequestFXController) requestPanel.getFxController();
            requestFXController.setUserID(user.getID());
            requestFXController.setRequesterID(requester.getID());
            requestFXController.setRequester(requester.getUsername());
            notificationsFXController.getRequests().getChildren().add(requestFXController.getRequestPane());
        }
    }
}
