package ir.sharif.aminra.listeners.tweets;

import ir.sharif.aminra.controller.profileView.ProfileViewController;
import ir.sharif.aminra.controller.tweets.NewTweetController;
import ir.sharif.aminra.controller.tweets.TweetManager;
import ir.sharif.aminra.events.tweets.TweetPageEvent;

public class TweetPageListener {
    public void eventOccurred(TweetPageEvent tweetPageEvent) {
        switch (tweetPageEvent.getTweetPageEventType()) {
            case LIKE:
                TweetManager.getInstance().like(tweetPageEvent.getUserID(), tweetPageEvent.getTweetID(),
                        tweetPageEvent.getTweetFXController());
                break;
            case REFRESH:
                TweetManager.getInstance().refresh(tweetPageEvent.getTweetFXController());
                break;
            case RETWEET:
                TweetManager.getInstance().retweet(tweetPageEvent.getUserID(), tweetPageEvent.getTweetID(),
                        tweetPageEvent.getTweetFXController());
                break;
            case REPORT_SPAM:
                TweetManager.getInstance().reportSpam(tweetPageEvent.getUserID(), tweetPageEvent.getTweetID(),
                        tweetPageEvent.getTweetFXController());
                break;
            case ADD_COMMENT:
                NewTweetController newTweetController = new NewTweetController();
                newTweetController.switchPage(tweetPageEvent.getUserID(), tweetPageEvent.getTweetID());
                break;
            case CHECK_USER_PROFiLE:
                ProfileViewController profileViewController = new ProfileViewController();
                profileViewController.switchPageByTweet(tweetPageEvent.getUserID(), tweetPageEvent.getTweetID(),
                        tweetPageEvent.getTweetFXController());
                break;
        }
    }
}
