package ir.sharif.aminra.db;

public class Context {

    static private Context context;

    MessageDB messageDB;
    TweetDB tweetDB;
    UserDB userDB;

    public static Context getInstance() {
        if(context == null)
            context = new Context();
        return context;
    }

    private Context() {
        messageDB = new MessageDB();
        tweetDB = new TweetDB();
        userDB = new UserDB();
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
}
