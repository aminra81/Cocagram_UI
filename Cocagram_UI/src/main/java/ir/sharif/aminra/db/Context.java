package ir.sharif.aminra.db;

public class Context {

    static private Context context;

    MessageDB messageDB;
    TweetDB tweetDB;
    UserDB userDB;
    MessageGroupDB messageGroupDB;
    ImageDB imageDB;

    public static Context getInstance() {
        if(context == null)
            context = new Context();
        return context;
    }

    private Context() {
        messageDB = new MessageDB();
        tweetDB = new TweetDB();
        userDB = new UserDB();
        messageGroupDB = new MessageGroupDB();
        imageDB = new ImageDB();
    }

    public MessageDB getMessageDB() {
        return messageDB;
    }

    public TweetDB getTweetDB() {
        return tweetDB;
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public MessageGroupDB getMessageGroupDB() { return messageGroupDB; }

    public ImageDB getImageDB() { return imageDB; }
}
