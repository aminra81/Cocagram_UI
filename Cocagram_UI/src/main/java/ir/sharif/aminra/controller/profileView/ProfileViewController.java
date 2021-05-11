package ir.sharif.aminra.controller.profileView;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.messagingPage.MessageViewerFXController;
import ir.sharif.aminra.view.profileView.ProfileFXController;
import ir.sharif.aminra.view.tweets.TweetFXController;
import javafx.scene.paint.ImagePattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;

public class ProfileViewController {

    static private final Logger logger = LogManager.getLogger(ProfileViewController.class);

    public void switchPage(User user, User userToBeVisited) {
        if (!userToBeVisited.isActive()) {
            logger.info(String.format("user %s wants to check the profile of a user which doesn't exist.", user.getUsername()));
            Page deactivatedUserPage = new Page("deactivatedUserPage");
            ViewManager.getInstance().setPage(deactivatedUserPage);
            return;
        }
        if (user.equals(userToBeVisited))
            return;
        Page profilePage = new Page("profilePage");
        ProfileFXController profileFXController = (ProfileFXController) profilePage.getFxController();
        profileFXController.setUserID(user.getID());
        profileFXController.setUserToVeVisited(userToBeVisited.getID());
        refresh(profileFXController);
        ViewManager.getInstance().setPage(profilePage);
    }

    public void refresh(ProfileFXController profileFXController) {
        User user = Context.getInstance().getUserDB().getByID(profileFXController.getUserID());
        User userToBeVisited = Context.getInstance().getUserDB().getByID(profileFXController.getUserToVeVisited());

        //build page.
        profileFXController.setUsernameLabel(userToBeVisited.getUsername());
        profileFXController.getAvatar().
                setFill(new ImagePattern(Context.getInstance().getImageDB().getByID(userToBeVisited.getAvatar())));
        profileFXController.setFirstnameLabel(userToBeVisited.getFirstname());
        profileFXController.setLastnameLabel(userToBeVisited.getLastname());
        profileFXController.setLastSeenLabel(getLastSeen(user, userToBeVisited));
        profileFXController.setBioLabel(userToBeVisited.getBio());
        if (userToBeVisited.isPublicData()) {
            if (userToBeVisited.getBirthDate() != null)
                profileFXController.setBirthDateLabel(userToBeVisited.getBirthDate().toString());
            profileFXController.setEmailLabel(userToBeVisited.getEmail());
            profileFXController.setPhoneNumberLabel(userToBeVisited.getPhoneNumber());
        } else {
            profileFXController.setBirthDateLabel(Config.
                    getConfig("profilePage").getProperty(String.class, "private"));
            profileFXController.setEmailLabel(Config.
                    getConfig("profilePage").getProperty(String.class, "private"));
            profileFXController.setPhoneNumberLabel(Config.
                    getConfig("profilePage").getProperty(String.class, "private"));
        }
        if (user.getBlockList().contains(userToBeVisited.getID()))
            profileFXController.setBlockButtonText(Config.getConfig("profilePage").
                    getProperty(String.class, "unblockButtonText"));
        else
            profileFXController.setBlockButtonText(Config.getConfig("profilePage").
                    getProperty(String.class, "blockButtonText"));

        if (user.getMutedUsers().contains(userToBeVisited.getID()))
            profileFXController.setMuteButtonText(Config.getConfig("profilePage").
                    getProperty(String.class, "unmuteButtonText"));
        else
            profileFXController.setMuteButtonText(Config.getConfig("profilePage").
                    getProperty(String.class, "muteButtonText"));

        if (user.getFollowings().contains(userToBeVisited.getID()))
            profileFXController.setFollowButton(Config.getConfig("profilePage").
                    getProperty(String.class, "unfollowButtonText"));
        else if (userToBeVisited.getRequests().contains(user.getID()))
            profileFXController.setFollowButton(Config.getConfig("profilePage").
                    getProperty(String.class, "requestedButtonText"));
        else
            profileFXController.setFollowButton(Config.getConfig("profilePage").
                    getProperty(String.class, "followButtonText"));
    }

    public String getLastSeen(User user, User userToBeVisited) {

        if (userToBeVisited.getLastSeenType().equals(Config.getConfig("profilePage").
                getProperty(String.class, "nobodyLastSeen")))
            return Config.getConfig("profilePage").getProperty(String.class, "recently");

        if (userToBeVisited.getLastSeenType().equals(Config.getConfig("profilePage").
                getProperty(String.class, "followingsLastSeen")) &&
                !userToBeVisited.getFollowings().contains(user.getID()))
            return Config.getConfig("profilePage").getProperty(String.class, "recently");

        if (userToBeVisited.getLastSeen() == null)
            return Config.getConfig("profilePage").getProperty(String.class, "online");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return userToBeVisited.getLastSeen().format(formatter);
    }

    public void muteHandling(ID userID, ID userToBeVisitedID, ProfileFXController profileFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        User userToBeVisited = Context.getInstance().getUserDB().getByID(userToBeVisitedID);

        if (user.getMutedUsers().contains(userToBeVisited.getID())) {
            user.removeFromMutedUsers(userToBeVisited.getID());
            logger.info(String.format("user %s unmuted user %s.", user.getUsername(), userToBeVisited.getUsername()));
        } else {
            user.addToMutedUsers(userToBeVisited.getID());
            logger.info(String.format("user %s muted user %s.", user.getUsername(), userToBeVisited.getUsername()));
        }
        refresh(profileFXController);
    }

    public void blockHandling(ID userID, ID userToBeVisitedID, ProfileFXController profileFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        User userToBeVisited = Context.getInstance().getUserDB().getByID(userToBeVisitedID);

        if (user.getBlockList().contains(userToBeVisited.getID())) {
            user.removeFromBlocklist(userToBeVisited.getID());
            logger.info(String.format("user %s unblocked user %s.", user.getUsername(), userToBeVisited.getUsername()));
        } else {
            user.addToBlocklist(userToBeVisited.getID());
            logger.info(String.format("user %s blocked user %s.", user.getUsername(), userToBeVisited.getUsername()));
        }
        refresh(profileFXController);
    }

    public void followHandling(ID userID, ID userToBeVisitedID, ProfileFXController profileFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        User userToBeVisited = Context.getInstance().getUserDB().getByID(userToBeVisitedID);

        if (user.getFollowings().contains(userToBeVisited.getID())) {
            user.removeFromFollowings(userToBeVisited.getID());
            userToBeVisited.removeFromFollowers(user.getID());
            userToBeVisited.addToNotifications(String.format("user %s unfollowed you!", user.getUsername()));
            logger.info(String.format("user %s unfollowed user %s.", user.getUsername(), userToBeVisited.getUsername()));
        } else if (userToBeVisited.getRequests().contains(user.getID())) {
            logger.info(String.format("user %s removed the request to user %s.", user.getUsername(),
                    userToBeVisited.getUsername()));
            userToBeVisited.removeFromRequests(user.getID());
        } else if (userToBeVisited.isPrivate()) {
            userToBeVisited.addToRequests(user.getID());
            logger.info(String.format("user %s requested user %s.", user.getUsername(), userToBeVisited.getUsername()));
        } else {
            user.addToFollowings(userToBeVisited.getID());
            userToBeVisited.addToFollowers(user.getID());
            userToBeVisited.addToNotifications(String.format("user %s followed you!", user.getUsername()));
            logger.info(String.format("user %s followed user %s.", user.getUsername(), userToBeVisited.getUsername()));
        }
        refresh(profileFXController);
    }

    public void switchPageByTweet(ID userID, ID tweetID, TweetFXController tweetFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        User userToBeVisited = Context.getInstance().getUserDB().
                getByID(Context.getInstance().getTweetDB().getByID(tweetID).getWriter());
        if (user.equals(userToBeVisited))
            tweetFXController.setVerdictLabelText(Config.getConfig("tweets").getProperty(String.class,
                    "viewSelfProfileError"), true);
        else
            switchPage(user, userToBeVisited);
    }

    public void switchPageByMessage(ID userID, ID messageID, MessageViewerFXController messageViewerFXController) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        User userToBeVisited = Context.getInstance().getUserDB().
                getByID(Context.getInstance().getMessageDB().getByID(messageID).getWriter());
        if (user.equals(userToBeVisited))
            messageViewerFXController.setErrorLabel(Config.getConfig("messageViewerPage").getProperty(String.class,
                    "viewSelfProfileError"));
        else
            switchPage(user, userToBeVisited);
    }

    public void switchPageByID(ID userID, ID userToBeVisitedID) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        User userToBeVisited = Context.getInstance().getUserDB().getByID(userToBeVisitedID);
        switchPage(user, userToBeVisited);
    }
}
