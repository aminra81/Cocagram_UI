package ir.sharif.aminra.events.personalPage.notificationsPage;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.personalPage.notificationsPage.RequestFXController;

public class RequestHandlingEvent {

    RequestFXController requestFXController;
    RequestAnswerType answerType;
    ID user, requester;

    public RequestHandlingEvent(RequestFXController requestFXController, RequestAnswerType answerType, ID user, ID requester) {
        this.requestFXController = requestFXController;
        this.answerType = answerType;
        this.user = user;
        this.requester = requester;
    }

    public RequestAnswerType getAnswerType() {
        return answerType;
    }

    public ID getUser() {
        return user;
    }

    public ID getRequester() {
        return requester;
    }
}
