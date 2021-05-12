package ir.sharif.aminra.controller.explorerPage;

import ir.sharif.aminra.controller.tweets.TweetManager;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.explorerPage.ExplorerFXController;
import ir.sharif.aminra.view.mainPage.MainFXController;

import java.util.List;

public class ExplorerController {

    public void switchPage(MainFXController mainFXController) {
        Page explorerPage = new Page("explorerPage");
        ExplorerFXController explorerFXController = (ExplorerFXController) explorerPage.getFxController();
        explorerFXController.setUserID(mainFXController.getUserID());
        refresh(explorerFXController);
        ViewManager.getInstance().setPage(explorerPage);
    }

    public void refresh(ExplorerFXController explorerFXController) {
        explorerFXController.clear();
        User user = Context.getInstance().getUserDB().getByID(explorerFXController.getUserID());

        //getting valid tweets
        List<Tweet> tweets = Context.getInstance().getTweetDB().getPublicTweets();
        List<Tweet> validatedTweets = TweetManager.getInstance().validation(user, tweets, true);
        Tweet.sortByLikeNumbers(validatedTweets);

        //adding them to explorer page.
        for (Tweet tweet : validatedTweets)
            explorerFXController.getTweetBox().getChildren().add(TweetManager.getInstance().
                    makeTweetPanel(user, tweet, false));
    }
}
