package ir.sharif.aminra.controller.tweets;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.tweets.NewTweetEvent;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.tweets.NewTweetFXController;

public class NewTweetController {
    public void switchPage(ID userID, ID upPost) {
        Page newTweetPage = new Page("newTweetPage");
        ViewManager.getInstance().setPage(newTweetPage);
        NewTweetFXController newTweetFXController = (NewTweetFXController) newTweetPage.getFxController();
        newTweetFXController.setUserID(userID);
        newTweetFXController.setUpPost(upPost);
        if(upPost != null)
            newTweetFXController.setTitleLabel(Config.getConfig("tweets").
                    getProperty(String.class, "commentTitle"));
    }

    public void addTweet(NewTweetEvent newTweetEvent) {
        ID imageID = null;
        if(newTweetEvent.getImage() != null)
            imageID = Context.getInstance().getImageDB().saveIntoDB(newTweetEvent.getImage());
        ID userID = newTweetEvent.getNewTweetFXController().getUserID();
        String content = newTweetEvent.getContent();
        Tweet curTweet = new Tweet(content, userID, newTweetEvent.getUpPost(), imageID);
        User user = Context.getInstance().getUserDB().getByID(userID);
        user.addToTweets(curTweet.getID());
        if(newTweetEvent.getUpPost() != null) {
            Tweet upTweet = Context.getInstance().getTweetDB().getByID(newTweetEvent.getUpPost());
            upTweet.addComment(curTweet.getID());
        }
        ViewManager.getInstance().back();
    }
}
