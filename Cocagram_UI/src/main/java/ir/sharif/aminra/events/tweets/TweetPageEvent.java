package ir.sharif.aminra.events.tweets;

import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.tweets.TweetFXController;

public class TweetPageEvent {
    TweetPageEventType tweetPageEventType;
    TweetFXController tweetFXController;
    ID userID;
    ID tweetID;

    public TweetPageEvent(TweetPageEventType tweetPageEventType, TweetFXController tweetFXController, ID userID, ID tweetID) {
        this.tweetPageEventType = tweetPageEventType;
        this.tweetFXController = tweetFXController;
        this.userID = userID;
        this.tweetID = tweetID;
    }

    public TweetPageEventType getTweetPageEventType() { return tweetPageEventType; }

    public TweetFXController getTweetFXController() { return tweetFXController; }

    public ID getUserID() { return userID; }

    public ID getTweetID() { return tweetID; }
}
