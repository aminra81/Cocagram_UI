package ir.sharif.aminra.view.tweets;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.events.tweets.TweetPageEvent;
import ir.sharif.aminra.events.tweets.TweetPageEventType;
import ir.sharif.aminra.listeners.tweets.TweetPageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class TweetFXController extends FXController implements Initializable {
    ID tweetID;
    boolean myTweets;
    TweetPageListener tweetPageListener;

    @FXML
    private Rectangle photoBox;

    @FXML
    private TextArea tweetContent;

    @FXML
    private Label retweetLabel;

    @FXML
    private Label tweetDate;

    @FXML
    private Label likesCounter;

    @FXML
    private VBox commentsBox;

    @FXML
    private Label verdictLabel;

    @FXML
    private Button likeButton;

    public void setTweetID(ID tweetID) {
        this.tweetID = tweetID;
    }

    public VBox getCommentsBox() { return commentsBox; }

    public ID getTweetID() { return tweetID; }

    public boolean isMyTweets() { return myTweets; }

    public Rectangle getPhotoBox() { return photoBox; }

    public void setMyTweets(boolean myTweets) {
        this.myTweets = myTweets;
    }

    public void setTweetContent(String content) { tweetContent.setText(content); }

    public void setTweetDate(String date) {
        tweetDate.setText(date);
    }

    public void setRetweetLabel(String content) {
        retweetLabel.setText(content);
    }

    public void setLikesCounter(int likeNumbers) { likesCounter.setText("likes: " + likeNumbers); }

    public void setVerdictLabelText(String content, boolean isError) {
        if(isError)
            verdictLabel.setTextFill(Color.valueOf(Config.getConfig("tweets").getProperty(String.class, "errorColor")));
        else
            verdictLabel.setTextFill(Color.valueOf(Config.getConfig("tweets").getProperty(String.class, "acceptColor")));
        verdictLabel.setText(content);
    }

    public void setLikeButtonText(String text) {
        likeButton.setText(text);
    }

    @FXML
    public void addComment() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.ADD_COMMENT,
                this, userID, tweetID));
    }

    @FXML
    public void checkUserProfile() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.CHECK_USER_PROFiLE,
                this, userID, tweetID));
    }

    @FXML
    public void like() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.LIKE,
                this, userID, tweetID));
    }

    @FXML
    public void reportSpam() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.REPORT_SPAM,
                this, userID, tweetID));
    }

    @FXML
    public void retweet() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.RETWEET,
                this, userID, tweetID));
    }

    @FXML
    public void save() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.SAVE,
                this, userID, tweetID));
    }

    @FXML
    public void forward() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.FORWARD,
                this, userID, tweetID));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tweetPageListener = new TweetPageListener();
        photoBox.setFill(Color.TRANSPARENT);
        tweetContent.setEditable(false);
    }

    @Override
    public void clear() {
        commentsBox.getChildren().clear();
        verdictLabel.setText("");
    }

    @Override
    public void refresh() {
        tweetPageListener.eventOccurred(new TweetPageEvent(TweetPageEventType.REFRESH,
                this, userID, tweetID));
    }
}
