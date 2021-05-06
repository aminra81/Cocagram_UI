package ir.sharif.aminra.events.tweets;

import ir.sharif.aminra.models.ID;

public class ViewTweetEvent {
    ID userID;
    ID tweetID;
    boolean myTweets;

    public ViewTweetEvent(ID userID, ID tweetID, boolean myTweets) {
        this.userID = userID;
        this.tweetID = tweetID;
        this.myTweets = myTweets;
    }

    public ID getUserID() {
        return userID;
    }

    public ID getTweetID() {
        return tweetID;
    }

    public boolean isMyTweets() {
        return myTweets;
    }
}
