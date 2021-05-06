package ir.sharif.aminra.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Tweet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TweetDB implements DBSet<Tweet> {

    private final File dbDirectory = Config.getConfig("db").getProperty(File.class, "tweetsDB");
    static private final Logger logger = LogManager.getLogger(TweetDB.class);

    @Override
    public Tweet getByID(ID id) {
        try {
            File Data = new File(dbDirectory, id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Data));
            logger.info(String.format("file %s opened.", Data.getName()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Tweet tweet = gson.fromJson(bufferedReader, Tweet.class);
            bufferedReader.close();
            logger.info(String.format("file %s closed.", Data.getName()));
            return tweet;
        } catch (Exception e) {
            logger.warn(String.format("Exception occurred while trying to get tweet %s", id));
        }
        return null;
    }

    @Override
    public void saveIntoDB(Tweet tweet) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, tweet.getId() + ".json");
            if (!Data.exists())
                Data.createNewFile();
            logger.info(String.format("file %s opened.", Data.getName()));
            FileWriter writer = new FileWriter(Data);
            writer.write(gson.toJson(tweet));
            writer.close();
            logger.info(String.format("file %s closed.", Data.getName()));
        } catch (IOException e) {
            logger.error(String.format("Exception occurred while trying to save tweet %s", tweet.getId()));
        }
    }

    public List<Tweet> getPublicTweets() {
        List<Tweet> tweets = new ArrayList<>();
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (File userFile : dbDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile));
                logger.info(String.format("file %s opened.", userFile.getName()));
                Tweet currentTweet = gson.fromJson(bufferedReader, Tweet.class);
                bufferedReader.close();
                logger.info(String.format("file %s closed.", userFile.getName()));
                User writer = Context.getInstance().getUserDB().getByID(currentTweet.getWriter());
                if(!writer.isPrivate())
                    tweets.add(currentTweet);
            }
        } catch (IOException e) {
            logger.error("an Exception occurred while loading public user's tweets.");
        }
        return tweets;
    }
}
