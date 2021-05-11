package ir.sharif.aminra.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.ChatState;
import ir.sharif.aminra.models.Group;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDB implements DBSet<User> {

    private final File dbDirectory = Config.getConfig("db").getProperty(File.class, "usersDB");
    static private final Logger logger = LogManager.getLogger(UserDB.class);

    @Override
    public User getByID(ID id) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Data));
            logger.info(String.format("file %s opened.", Data.getName()));
            User user = gson.fromJson(bufferedReader, User.class);
            bufferedReader.close();
            logger.info(String.format("file %s closed.", Data.getName()));
            return user;
        } catch (IOException e) {
            logger.error(String.format("Exception occurred while trying to get user %s", id));
        }
        return null;
    }

    @Override
    public void saveIntoDB(User user) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, user.getID() + ".json");
            if (!Data.exists())
                Data.createNewFile();
            logger.info(String.format("file %s opened.", Data.getName()));
            FileWriter writer = new FileWriter(Data);
            writer.write(gson.toJson(user));
            writer.flush();
            writer.close();
            logger.info(String.format("file %s closed.", Data.getName()));
        } catch (Exception e) {
            logger.error(String.format("Exception occurred while trying to save user %s", user.getID()));
        }
    }

    public User getUser(String username) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (File userFile : Objects.requireNonNull(dbDirectory.listFiles())) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile));
                logger.info(String.format("file %s opened!", userFile.getName()));
                User currentUser = gson.fromJson(bufferedReader, User.class);
                bufferedReader.close();
                logger.info(String.format("file %s closed!", userFile.getName()));
                if (currentUser.getUsername().equals(username))
                    return currentUser;
            }
        } catch (IOException e) {
            logger.error("an exception occurred while trying to get user with given username file.");
        }
        return null;
    }

    public User getPhoneNumber(String phoneNumber) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (File userFile : Objects.requireNonNull(dbDirectory.listFiles())) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile));
                logger.info(String.format("file %s opened!", userFile.getName()));
                User currentUser = gson.fromJson(bufferedReader, User.class);
                logger.info(String.format("file %s closed!", userFile.getName()));
                bufferedReader.close();
                if (currentUser.getPhoneNumber().equals(phoneNumber))
                    return currentUser;
            }
        } catch (IOException e) {
            logger.error("an exception occurred while trying to get user with given phone number file.");
        }
        return null;
    }

    public User getEmail(String email) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (File userFile : Objects.requireNonNull(dbDirectory.listFiles())) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile));
                logger.info(String.format("file %s opened!", userFile.getName()));
                User currentUser = gson.fromJson(bufferedReader, User.class);
                logger.info(String.format("file %s closed!", userFile.getName()));
                bufferedReader.close();
                if (currentUser.getEmail().equals(email))
                    return currentUser;
            }
        } catch (IOException e) {
            logger.error("an exception occurred while trying to get user with given email file.");
        }
        return null;
    }

    public void deleteUser(ID userID) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //modify other users files.
            for (File file : dbDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                logger.info(String.format("file %s opened.", file.getName()));
                User currentUser = gson.fromJson(bufferedReader, User.class);
                bufferedReader.close();
                logger.info(String.format("file %s closed.", file.getName()));
                if (currentUser.getID().equals(userID))
                    continue;
                removeUserDetails(currentUser, userID);
            }
            //delete the user file.
            for (File file : dbDirectory.listFiles())
                if (file.getName().equals(userID + ".json")) {
                    logger.info(String.format("file %s deleted.", file.getName()));
                    file.delete();
                }

        } catch (IOException e) {
            logger.error("an exception occurred while deleting user.");
        }
    }

    private void removeUserDetails(User user, ID toBeDeletedUser) {
        //followings
        if (user.getFollowings().contains(toBeDeletedUser))
            user.removeFromFollowings(toBeDeletedUser);
        //followers
        if (user.getFollowers().contains(toBeDeletedUser))
            user.removeFromFollowers(toBeDeletedUser);
        //blocklist
        if (user.getBlockList().contains(toBeDeletedUser))
            user.removeFromBlocklist(toBeDeletedUser);
        //tweets
        List<ID> toBeDeletedTweets = new ArrayList<>();
        for (ID tweetID : user.getTweets())
            if (Context.getInstance().getTweetDB().getByID(tweetID) == null)
                toBeDeletedTweets.add(tweetID);
        for (ID tweetID : toBeDeletedTweets)
            user.removeFromTweets(tweetID);
        toBeDeletedTweets.clear();
        //request notifications
        List<String> toBeDeletedNotifications = new ArrayList<>();
        for (String requestNotification : user.getRequestNotifications()) {
            String acceptedName = String.format("user %s accepted your follow request!",
                    Context.getInstance().getUserDB().getByID(toBeDeletedUser).getUsername());
            String rejectedName = String.format("user %s rejected your follow request!",
                    Context.getInstance().getUserDB().getByID(toBeDeletedUser).getUsername());
            if (requestNotification.equals(acceptedName) || requestNotification.equals(rejectedName))
                toBeDeletedNotifications.add(requestNotification);
        }
        for (String requestNotification : toBeDeletedNotifications)
            user.removeFromRequestNotifications(requestNotification);
        toBeDeletedNotifications.clear();
        //liked tweets
        for (ID tweetID : user.getLikedTweets())
            if (Context.getInstance().getTweetDB().getByID(tweetID) == null)
                toBeDeletedTweets.add(tweetID);
        for (ID tweetID : toBeDeletedTweets)
            user.removeFromLikedTweets(tweetID);
        toBeDeletedTweets.clear();
        //requests
        if (user.getRequests().contains(toBeDeletedUser))
            user.removeFromRequests(toBeDeletedUser);
        //notifications
        for (String notification : user.getNotifications()) {
            String followName = String.format("user %s followed you!",
                    Context.getInstance().getUserDB().getByID(toBeDeletedUser).getUsername());
            String unfollowName = String.format("user %s unfollowed you!",
                    Context.getInstance().getUserDB().getByID(toBeDeletedUser).getUsername());
            if (notification.equals(followName) || notification.equals(unfollowName))
                toBeDeletedNotifications.add(notification);
        }
        for (String notification : toBeDeletedNotifications)
            user.removeFromNotifications(notification);
        toBeDeletedNotifications.clear();
        //groups
        List<Group> modifiedGroups = new ArrayList<>();
        for (Group group : user.getGroups())
            if (group.getUsers().contains(toBeDeletedUser))
                modifiedGroups.add(group);
        for (Group group : modifiedGroups) {
            group.removeUser(toBeDeletedUser);
            user.removeGroup(group);
            user.addGroup(group);
        }
        //chats
        List<ChatState> chatStatesToBeRemoved = new ArrayList<>();
        for (ChatState chatState : user.getChatStates())
            if (Context.getInstance().getChatDB().getByID(chatState.getChat()) == null)
                chatStatesToBeRemoved.add(chatState);
        for (ChatState chatState : chatStatesToBeRemoved)
            user.removeFromChatStates(chatState);
    }
}
