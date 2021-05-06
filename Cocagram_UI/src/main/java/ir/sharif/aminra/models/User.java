package ir.sharif.aminra.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ir.sharif.aminra.db.Context;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class User {
    private ID id;
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean publicData;
    private boolean isActive;
    private LocalDateTime lastSeen;
    private String lastSeenType;
    private List<ID> followings;
    private List<ID> followers;
    private List<ID> blockList;
    private List<ID> tweets;
    private List<String> requestNotifications;
    private List<ID> likedTweets;
    private List<ID> messages;
    private List<ID> unreadMessages;
    private List<ID> requests;
    private List<String> notifications;
    private boolean isPrivate;
    private List<Group> groups;
    private List<ID> savedMessages;
    private List<ID> mutedUsers;
    private List<ID> messageGroups;
    private ID avatar;
    private List<ID> reportedSpamTweets;

    static private final Logger logger = LogManager.getLogger(User.class);

    public User(String username, String firstname, String lastname, String bio, LocalDate birthDate, String email, String phoneNumber, String password, boolean publicData, String lastSeenType) {
        //get from user.
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.publicData = publicData;
        this.lastSeenType = lastSeenType;

        //fill them by default.
        this.id = new ID(true);
        this.isActive = true;
        this.isPrivate = false;
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.lastSeen = LocalDateTime.now();
        this.groups = new ArrayList<>();

        this.tweets = new ArrayList<>();
        this.likedTweets = new ArrayList<>();
        this.requestNotifications = new ArrayList<>();

        this.messages = new ArrayList<>();
        this.unreadMessages = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.savedMessages = new ArrayList<>();
        this.mutedUsers = new ArrayList<>();
        this.messageGroups = new ArrayList<>();
        this.avatar = Context.getInstance().getImageDB().DEFAULT_AVATAR_ID;
        this.reportedSpamTweets = new ArrayList<>();

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToLikedTweets(ID tweet) {
        this.likedTweets.add(tweet);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromTweets(ID tweet) {
        this.tweets.remove(tweet);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromLikedTweets(ID tweet) {
        this.likedTweets.remove(tweet);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToTweets(ID tweet) {
        this.tweets.add(tweet);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToMessages(ID message) {
        this.messages.add(message);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToUnreadMessages(ID message) {
        this.unreadMessages.add(message);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public ID getID() {
        return this.id;
    }

    public String getPassword() { return password; }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<ID> getTweets() {
        return tweets;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBio() {
        return bio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isPublicData() {
        return publicData;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getLastSeenType() {
        return lastSeenType;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setBio(String bio) {
        this.bio = bio;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setEmail(String email) {
        this.email = email;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public List<String> getNotifications() { return notifications; }

    public List<String> getRequestNotifications() { return requestNotifications; }

    public List<ID> getRequests() { return requests; }

    public void addToFollowings(ID user) {
        followings.add(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromFollowings(ID user) {
        followings.remove(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToFollowers(ID user) {
        followers.add(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromFollowers(ID user) {
        followers.remove(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToBlocklist(ID user) {
        blockList.add(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromBlocklist(ID user) {
        blockList.remove(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToRequestNotifications(String content) {
        requestNotifications.add(content);
        if(requestNotifications.size() > 10)
            requestNotifications.remove(0);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToNotifications(String content) {
        notifications.add(content);
        if(notifications.size() > 10)
            notifications.remove(0);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToRequests(ID requester) {
        requests.add(requester);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromRequests(ID requester) {
        requests.remove(requester);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public List<Group> getGroups() { return groups; }

    public List<ID> getFollowings() {
        return followings;
    }

    public List<ID> getFollowers() {
        return followers;
    }

    public List<ID> getBlockList() {
        return blockList;
    }

    public void removeGroup(Group group) {
        groups.remove(group);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addGroup(Group group) {
        groups.add(group);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public List<ID> getLikedTweets() { return likedTweets; }

    public List<ID> getMutedUsers() { return mutedUsers; }

    public void addToMutedUsers(ID user) {
        mutedUsers.add(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromMutedUsers(ID user) {
        mutedUsers.remove(user);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public LocalDateTime getLastSeen() { return lastSeen; }

    public void addToSavedMessages(ID message) {
        savedMessages.add(message);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public List<ID> getSavedMessages() { return savedMessages; }

    public List<ID> getUnreadMessages() { return unreadMessages; }

    public List<ID> getMessages() { return messages; }

    public void removeFromMessages(ID message) {
        messages.remove(message);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromUnreadMessages(ID message) {
        unreadMessages.remove(message);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromSavedMessages(ID message) {
        savedMessages.remove(message);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setPassword(String password) {
        this.password = password;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setLastSeenType(String lastSeenType) {
        this.lastSeenType = lastSeenType;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromRequestNotifications(String requestNotification) {
        requestNotifications.remove(requestNotification);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void removeFromNotifications(String notification) {
        notifications.remove(notification);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public ID getAvatar() { return avatar; }

    public void setAvatar(ID avatar) {
        this.avatar = avatar;

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public void addToReportedSpamTweets(ID tweet) {
        reportedSpamTweets.add(tweet);

        Context.getInstance().getUserDB().saveIntoDB(this);
    }

    public List<ID> getReportedSpamTweets() { return reportedSpamTweets; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user.getID().equals(getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
