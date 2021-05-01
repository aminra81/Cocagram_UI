package ir.sharif.aminra.listeners.personalPage.tweets;

import ir.sharif.aminra.controller.personalPage.tweets.NewTweetController;
import ir.sharif.aminra.events.personalPage.tweets.NewTweetEvent;

public class NewTweetListener {
    NewTweetController newTweetController;
    public void eventOccurred(NewTweetEvent newTweetEvent) {
        newTweetController = new NewTweetController();
        newTweetController.addTweet(newTweetEvent);
    }
}
