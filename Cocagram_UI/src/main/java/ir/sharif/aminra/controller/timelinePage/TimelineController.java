package ir.sharif.aminra.controller.timelinePage;

import ir.sharif.aminra.controller.tweets.TweetManager;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.mainPage.MainFXController;
import ir.sharif.aminra.view.timelinePage.TimelineFXController;

import java.util.ArrayList;
import java.util.List;

public class TimelineController {
    public void switchPage(MainFXController mainFXController) {
        Page timelinePage = new Page("timelinePage");
        TimelineFXController timelineFXController = (TimelineFXController) timelinePage.getFxController();
        timelineFXController.setUserID(mainFXController.getUserID());
        refresh(timelineFXController);
        ViewManager.getInstance().setPage(timelinePage);
    }

    public void refresh(TimelineFXController timelineFXController) {
        timelineFXController.clear();
        User user = Context.getInstance().getUserDB().getByID(timelineFXController.getUserID());

        //getting valid tweets
        List<Tweet> tweets = new ArrayList<>();
        for (ID followingID : user.getFollowings()) {
            for (ID tweetID : Context.getInstance().getUserDB().getByID(followingID).getTweets())
                    tweets.add(Context.getInstance().getTweetDB().getByID(tweetID));
            for (ID tweetID : Context.getInstance().getUserDB().getByID(followingID).getLikedTweets())
                    tweets.add(Context.getInstance().getTweetDB().getByID(tweetID));
        }
        List<Tweet> validatedTweets = TweetManager.getInstance().validation(user, tweets, true);
        Tweet.sortByLikeNumbers(validatedTweets);

        //adding them to timeline page.
        for (Tweet tweet : validatedTweets)
            timelineFXController.getTweetBox().getChildren().add(TweetManager.getInstance().
                    makeTweetPanel(user, tweet, false));
    }
}
