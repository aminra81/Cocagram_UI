package ir.sharif.aminra.listeners.personalPage.notificationsPage;

import ir.sharif.aminra.controller.personalPage.notificationsPage.RequestController;
import ir.sharif.aminra.events.personalPage.notificationsPage.RequestHandlingEvent;
import ir.sharif.aminra.view.personalPage.notificationsPage.RequestFXController;

public class RequestHandlingListener {
    RequestFXController requestFXController;

    public RequestHandlingListener(RequestFXController requestFXController) {
        this.requestFXController = requestFXController;
    }

    public void eventOccurred(RequestHandlingEvent requestHandlingEvent) {
        RequestController requestController = new RequestController();
        requestController.handleRequest(requestHandlingEvent);
    }
}
