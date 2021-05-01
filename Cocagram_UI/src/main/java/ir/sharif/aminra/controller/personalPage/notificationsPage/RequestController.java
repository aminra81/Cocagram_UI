package ir.sharif.aminra.controller.personalPage.notificationsPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.personalPage.notificationsPage.RequestHandlingEvent;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.ViewManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestController {

    static private final Logger logger = LogManager.getLogger(RequestController.class);

    public void handleRequest(RequestHandlingEvent requestHandlingEvent) {
        User user = Context.getInstance().getUserDB().getByID(requestHandlingEvent.getUser());
        User requester = Context.getInstance().getUserDB().getByID(requestHandlingEvent.getRequester());
        switch (requestHandlingEvent.getAnswerType()) {
            case ACCEPT:
                requester.addToFollowings(user.getID());
                requester.addToRequestNotifications(
                        String.format("user %s accepted your follow request!", user.getUsername()));
                logger.info(String.format("%s accepted %s's request.", user.getUsername(), requester.getUsername()));
                user.addToFollowers(requester.getID());
                user.removeFromRequests(requester.getID());
                break;
            case REJECT:
                requester.addToRequestNotifications(
                        String.format("user %s rejected your follow request!", user.getUsername()));
                logger.info(String.format("%s rejected %s's request.", user.getUsername(), requester.getUsername()));
                user.removeFromRequests(requester.getID());
                break;
            case MUTE_REJECT:
                user.removeFromRequests(requester.getID());
                logger.info(String.format("%s rejected %s's request.", user.getUsername(), requester.getUsername()));
                break;
        }
        ViewManager.getInstance().back();
    }
}
