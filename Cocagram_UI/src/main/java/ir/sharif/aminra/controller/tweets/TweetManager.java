package ir.sharif.aminra.controller.tweets;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.tweets.ViewTweetEvent;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.tweets.TweetFXController;
import ir.sharif.aminra.view.tweets.TweetPanelFXController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TweetManager {
    private static TweetManager instance;
    static private final Logger logger = LogManager.getLogger(TweetManager.class);

    public static TweetManager getInstance() {
        if(instance == null)
            instance = new TweetManager();
        return instance;
    }

    public AnchorPane makeTweetPanel(User user, Tweet tweet, boolean myTweets) {
        Page tweetPanel = new Page("tweetPanel");
        TweetPanelFXController tweetPanelFXController = (TweetPanelFXController) tweetPanel.getFxController();

        tweetPanelFXController.setTweetContent(tweet.getContent());
        tweetPanelFXController.setTweetDate(tweet.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        tweetPanelFXController.setMyTweets(myTweets);

        if(myTweets && tweet.getUpPost() == null && !user.getID().equals(tweet.getWriter()))
            tweetPanelFXController.setRetweetLabel
                    ("you retweeted from " + Context.getInstance().getUserDB().getByID(tweet.getWriter()).getUsername());
        else
            tweetPanelFXController.setRetweetLabel
                    (Context.getInstance().getUserDB().getByID(tweet.getWriter()).getUsername());


        tweetPanelFXController.setTweetID(tweet.getID());
        tweetPanelFXController.setUserID(user.getID());

        return tweetPanelFXController.getTweetPanel();
    }

    public List<Tweet> validation(User user, List<Tweet> tweets, boolean firstLayer) {
        List<Tweet> validatedTweets = new ArrayList<>();
        for (Tweet tweet : tweets) {
            User writer = Context.getInstance().getUserDB().getByID(tweet.getWriter());
            if (!writer.isActive())
                continue;
            if (writer.equals(user)) {
                if (!firstLayer)
                    validatedTweets.add(tweet);
                continue;
            }
            if (user.getMutedUsers().contains(writer.getID()))
                continue;
            if (writer.isPrivate() && !user.getFollowings().contains(writer.getID()))
                continue;
            if (tweet.getSpamReports() > Config.getConfig("tweets").getProperty(Integer.class, "maxSpam"))
                continue;
            if (!validatedTweets.contains(tweet))
                validatedTweets.add(tweet);
        }
        return validatedTweets;
    }

    public void switchPage(ViewTweetEvent viewTweetEvent) {
        Page tweetPage = new Page("tweetPage");
        TweetFXController tweetFXController = (TweetFXController) tweetPage.getFxController();

        tweetFXController.setUserID(viewTweetEvent.getUserID());
        tweetFXController.setTweetID(viewTweetEvent.getTweetID());
        tweetFXController.setMyTweets(viewTweetEvent.isMyTweets());

        refresh(tweetFXController);
        ViewManager.getInstance().setPage(tweetPage);
    }

    public void refresh(TweetFXController tweetFXController) {
        tweetFXController.clear();
        Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetFXController.getTweetID());
        User user = Context.getInstance().getUserDB().getByID(tweetFXController.getUserID());
        boolean myTweets = tweetFXController.isMyTweets();

        //build page
        tweetFXController.setTweetContent(tweet.getContent());

        tweetFXController.setTweetDate(tweet.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if(myTweets && tweet.getUpPost() == null && !user.getID().equals(tweet.getWriter()))
            tweetFXController.setRetweetLabel
                    ("you retweeted from " + Context.getInstance().getUserDB().getByID(tweet.getWriter()).getUsername());
        else
            tweetFXController.setRetweetLabel
                    (Context.getInstance().getUserDB().getByID(tweet.getWriter()).getUsername());

        if(tweet.getImage() != null)
            tweetFXController.getPhotoBox().setFill
                    (new ImagePattern(Context.getInstance().getImageDB().getByID(tweet.getImage())));

        tweetFXController.setLikesCounter(tweet.getLikeNumbers());
        List<Tweet> comments = validation(user, tweet.getComments(), false);
        Tweet.sortByDateTime(comments);
        for (Tweet comment : comments)
            tweetFXController.getCommentsBox().getChildren().add(TweetManager.getInstance().
                    makeTweetPanel(user, comment, myTweets));

        if(user.getLikedTweets().contains(tweet.getID()))
            tweetFXController.setLikeButtonText("dislike");
        else
            tweetFXController.setLikeButtonText("like");
    }

    public void like(ID userID, ID tweetID, TweetFXController tweetFXController) {

        User user = Context.getInstance().getUserDB().getByID(userID);
        Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetID);

        if (user.getLikedTweets().contains(tweet.getID())) {
            dislike(user, tweet);
            tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                    getProperty(String.class, "successfulDislike"), false);
            logger.info(String.format("user %s disliked tweet %s.", user.getUsername(), tweet.getID()));
        }
        else {
            if (tweet.getWriter().equals(user.getID()))
                tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                        getProperty(String.class, "selfLikeError"), true);
            else {
                like(user, tweet);
                tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                        getProperty(String.class, "successfulLike"), false);
                logger.info(String.format("user %s liked tweet %s.", user.getUsername(), tweet.getID()));
            }
        }
        if(user.getLikedTweets().contains(tweet.getID()))
            tweetFXController.setLikeButtonText("dislike");
        else
            tweetFXController.setLikeButtonText("like");
        tweetFXController.setLikesCounter(tweet.getLikeNumbers());
    }

    private void like(User user, Tweet tweet) {
        tweet.addLike(user.getID());
        user.addToLikedTweets(tweet.getID());
    }

    private void dislike(User user, Tweet tweet) {
        tweet.removeLike(user.getID());
        user.removeFromLikedTweets(tweet.getID());
    }

    public void retweet(ID userID, ID tweetID, TweetFXController tweetFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetID);

        if (tweet.getUpPost() != null) {
            tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                    getProperty(String.class, "retweetCommentError"), true);
            logger.info(String.format("user %s wants to retweet a comment", user.getUsername()));
        }
        else if (user.getTweets().contains(tweet.getID())) {
            tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                    getProperty(String.class, "alreadyRetweetedError"), true);
            logger.info(String.format("user %s wants to retweet a tweet which has already retweeted.",
                    user.getUsername()));
        }
        else {
            user.addToTweets(tweet.getID());
            tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                    getProperty(String.class, "successfulRetweet"), false);
            logger.info(String.format("user %s retweeted tweet %s.", user.getUsername(), tweet.getID()));
        }
    }

    public void reportSpam(ID userID, ID tweetID, TweetFXController tweetFXController) {

        User user = Context.getInstance().getUserDB().getByID(userID);
        Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetID);

        if (user.getReportedSpamTweets().contains(tweetID))
            tweetFXController.setVerdictLabelText(Config.getConfig("tweets").
                    getProperty(String.class, "alreadyReportedError"), true);
        else {
            reportSpam(user, tweet);
            ViewManager.getInstance().back();
        }
    }

    private void reportSpam(User user, Tweet tweet) {
        user.addToReportedSpamTweets(tweet.getID());
        tweet.reportSpam();
    }
}
