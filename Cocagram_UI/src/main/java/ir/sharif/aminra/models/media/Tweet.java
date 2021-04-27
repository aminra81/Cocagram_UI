package ir.sharif.aminra.models.media;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Tweet extends Media {

    static private final Logger logger = LogManager.getLogger(Tweet.class);
    private static final File dbDirectory = new File("./src/main/resources/DB/Tweets");

    private ID upPost;
    private List<ID> likes, comments;

    public Tweet(String content, ID writer, ID upPost) {
        super(content, writer);
        this.upPost = upPost;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();

        Context.getInstance().getTweetDB().saveIntoDB(this);
    }

    public void addLike(ID user) {
        this.likes.add(user);

        Context.getInstance().getTweetDB().saveIntoDB(this);
    }

    public void removeLike(ID user) {
        this.likes.remove(user);

        Context.getInstance().getTweetDB().saveIntoDB(this);
    }

    public ID getUpPost() {
        return upPost;
    }

    public int getLikeNumbers() {
        return likes.size();
    }

    public static void sortByDateTime(List<Tweet> tweets) {
        Comparator<Tweet> byDateTime = Comparator.comparing(Tweet::getDateTime).reversed();
        tweets.sort(byDateTime);
    }

    public static void sortByLikeNumbers(List<Tweet> tweets) {
        Comparator<Tweet> byDateTime = Comparator.comparing(Tweet::getLikeNumbers).reversed();
        tweets.sort(byDateTime);
    }

    public void addComment(ID comment) {
        this.comments.add(comment);

        Context.getInstance().getTweetDB().saveIntoDB(this);
    }

    public List<Tweet> getComments() {
        List<Tweet> curComments = new ArrayList<>();
        for (ID comment : comments)
            curComments.add(Context.getInstance().getTweetDB().getByID(comment));
        return curComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return tweet.getId().equals(getId());
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
