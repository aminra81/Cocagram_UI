package ir.sharif.aminra.listeners.tweets;

import ir.sharif.aminra.controller.tweets.TweetManager;
import ir.sharif.aminra.events.tweets.ViewTweetEvent;
import ir.sharif.aminra.view.tweets.TweetPanelFXController;

public class TweetPanelListener {
    TweetPanelFXController tweetPanelFXController;

    public TweetPanelListener(TweetPanelFXController tweetPanelFXController) {
        this.tweetPanelFXController = tweetPanelFXController;
    }

    public void eventOccurred(ViewTweetEvent viewTweetEvent) {
        TweetManager.getInstance().switchPage(viewTweetEvent);
    }
}
