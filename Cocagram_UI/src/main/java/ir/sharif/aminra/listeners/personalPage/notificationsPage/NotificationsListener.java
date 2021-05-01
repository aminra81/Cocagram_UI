package ir.sharif.aminra.listeners.personalPage.notificationsPage;

import ir.sharif.aminra.controller.personalPage.notificationsPage.NotificationsPageController;
import ir.sharif.aminra.view.personalPage.notificationsPage.NotificationsFXController;

public class NotificationsListener {

    NotificationsFXController notificationsFXController;

    public NotificationsListener(NotificationsFXController notificationsFXController) {
        this.notificationsFXController = notificationsFXController;
    }

    public void stringEventOccurred(String event) {
        if ("refresh".equals(event)) {
            NotificationsPageController notificationsPageController = new NotificationsPageController();
            notificationsPageController.refresh(notificationsFXController);
        }
    }
}
