package ir.sharif.aminra.controller.personalPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.controller.tweets.TweetManager;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.mainPage.MainFXController;
import ir.sharif.aminra.view.personalPage.MyFXController;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

public class MyPageController {
    public void switchPage(MainFXController mainFXController) {
        Page myPage = new Page("myPage");
        MyFXController myFXController = (MyFXController) myPage.getFxController();
        myFXController.setUserID(mainFXController.getUserID());
        refresh(myFXController);
        ViewManager.getInstance().setPage(myPage);
    }

    public void refresh(MyFXController myFXController) {
        myFXController.clear();
        User user = Context.getInstance().getUserDB().getByID(myFXController.getUserID());
        myFXController.getAvatar().setFill(new ImagePattern(Context.getInstance().getImageDB().getByID(user.getAvatar())));

        //getting valid tweets.
        List<Tweet> tweets = new ArrayList<>();
        for (ID tweetID : user.getTweets()) {
            Tweet tweet = Context.getInstance().getTweetDB().getByID(tweetID);
            if(tweet.getSpamReports() > Config.getConfig("tweets").getProperty(Integer.class, "maxSpam")) //spam
                continue;
            tweets.add(tweet);
        }
        Tweet.sortByDateTime(tweets);

        //adding them to personal page.
        for (Tweet tweet : tweets)
            myFXController.getTweetBox().getChildren().add(TweetManager.getInstance().
                    makeTweetPanel(user, tweet, true));
    }
}
