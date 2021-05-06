package ir.sharif.aminra.listeners.tweets;

import ir.sharif.aminra.controller.tweets.NewTweetController;
import ir.sharif.aminra.events.tweets.NewTweetEvent;

public class NewTweetListener {
    NewTweetController newTweetController;
    public void eventOccurred(NewTweetEvent newTweetEvent) {
        newTweetController = new NewTweetController();
        newTweetController.addTweet(newTweetEvent);
    }
}
