package ir.sharif.aminra.listeners.personalPage;

import ir.sharif.aminra.controller.personalPage.editPage.EditPageController;
import ir.sharif.aminra.controller.personalPage.MyPageController;
import ir.sharif.aminra.controller.personalPage.tweets.NewTweetController;
import ir.sharif.aminra.controller.personalPage.notificationsPage.NotificationsPageController;
import ir.sharif.aminra.view.personalPage.MyFXController;

public class MyPageListener {
    MyFXController myFXController;

    public MyPageListener(MyFXController myFXController) {
        this.myFXController = myFXController;
    }

    public void stringEventOccurred(String event) {
        switch (event) {
            case "newTweet":
                NewTweetController newTweetController = new NewTweetController();
                newTweetController.switchPage(myFXController);
                break;
            case "edit":
                EditPageController editPageController = new EditPageController();
                editPageController.switchPage(myFXController);
            case "refresh":
                MyPageController myPageController = new MyPageController();
                myPageController.refresh(myFXController);
                break;
            case "notifications":
                NotificationsPageController notificationsPageController = new NotificationsPageController();
                notificationsPageController.switchPage(myFXController);
                break;
            default:
                break;
        }
    }
}
