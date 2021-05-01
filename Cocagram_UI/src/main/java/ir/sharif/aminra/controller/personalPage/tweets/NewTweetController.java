package ir.sharif.aminra.controller.personalPage.tweets;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.personalPage.tweets.NewTweetEvent;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.personalPage.MyFXController;
import ir.sharif.aminra.view.personalPage.tweets.NewTweetFXController;

public class NewTweetController {
    public void switchPage(MyFXController myFXController) {
        Page newTweetPage = new Page("newTweetPage");
        ViewManager.getInstance().setPage(newTweetPage);
        NewTweetFXController newTweetFXController = (NewTweetFXController) newTweetPage.getFxController();
        newTweetFXController.setUserID(myFXController.getUserID());
    }

    public void addTweet(NewTweetEvent newTweetEvent) {
        ID imageID = null;
        if(newTweetEvent.getImage() != null)
            imageID = Context.getInstance().getImageDB().saveIntoDB(newTweetEvent.getImage());
        ID userID = newTweetEvent.getNewTweetFXController().getUserID();
        String content = newTweetEvent.getContent();
        new Tweet(content, userID, null, imageID);
        ViewManager.getInstance().back();
    }
}
