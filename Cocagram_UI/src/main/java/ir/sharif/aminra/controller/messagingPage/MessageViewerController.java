package ir.sharif.aminra.controller.messagingPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.controller.messagingPage.messageSendingPage.ChatSelectingController;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.models.media.Message;
import ir.sharif.aminra.models.media.Tweet;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.messagingPage.MessageViewerFXController;
import javafx.scene.paint.ImagePattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;

public class MessageViewerController {
    static private final Logger logger = LogManager.getLogger(MessageViewerController.class);

    public void switchPage(ID userID, ID messageID) {
        Page messageViewerPage = new Page("messageViewerPage");
        MessageViewerFXController messageViewerFXController = (MessageViewerFXController) messageViewerPage.getFxController();
        messageViewerFXController.setUserID(userID);
        messageViewerFXController.setMessageID(messageID);
        refresh(messageViewerFXController);
        ViewManager.getInstance().setPage(messageViewerPage);
    }

    public void refresh(MessageViewerFXController messageViewerFXController) {
        User user = Context.getInstance().getUserDB().getByID(messageViewerFXController.getUserID());
        Message message = Context.getInstance().getMessageDB().getByID(messageViewerFXController.getMessageID());

        if (!message.getWriter().equals(user.getID()))
            messageViewerFXController.deactivateButtons();
        if (message.getImage() != null)
            messageViewerFXController.getPhotoBox().setFill
                    (new ImagePattern(Context.getInstance().getImageDB().getByID(message.getImage())));
        messageViewerFXController.setMessageContentField(message.getContent());
        messageViewerFXController.setMessageDateLabel(message.getDateTime().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String writerUsername = Context.getInstance().getUserDB().getByID(message.getWriter()).getUsername();
        if (message.getMainMedia() == null)
            messageViewerFXController.setSenderLabel(writerUsername);
        else if (message.isForwardedTweet()) {
            Tweet mainTweet = Context.getInstance().getTweetDB().getByID(message.getMainMedia());
            String tweetWriter = Context.getInstance().getUserDB().getByID(mainTweet.getWriter()).getUsername();
            messageViewerFXController.setSenderLabel(String.format("%s forwarded a tweet from %s",
                    writerUsername, tweetWriter));
        } else {
            Message mainMessage = Context.getInstance().getMessageDB().getByID(message.getMainMedia());
            String messageWriter = Context.getInstance().getUserDB().getByID(mainMessage.getWriter()).getUsername();
            messageViewerFXController.setSenderLabel(String.format("%s forwarded a message from %s",
                    writerUsername, messageWriter));
        }
    }

    public void editMessage(ID messageID, String messageNewContent, MessageViewerFXController messageViewerFXController) {
        Message message = Context.getInstance().getMessageDB().getByID(messageID);
        if (message.getMainMedia() == null) {
            message.setContent(messageNewContent);
            logger.info(String.format("message %s is edited.", message.getID()));
            ViewManager.getInstance().back();
        } else {
            messageViewerFXController.setErrorLabel(
                    Config.getConfig("messageViewerPage").getProperty("editForwardedMessageError"));
            logger.info(String.format("user wants to edit message %s which is forwarded", message.getID()));
        }
    }

    public void deleteMessage(ID messageID) {
        Message message = Context.getInstance().getMessageDB().getByID(messageID);
        message.setDeleted(true);
        ViewManager.getInstance().back();
    }

    public void forwardMessage(ID userID, ID messageID) {
        User user = Context.getInstance().getUserDB().getByID(userID);
        Message message = Context.getInstance().getMessageDB().getByID(messageID);

        ID mainMedia = message.getMainMedia();
        if(mainMedia == null)
            mainMedia = message.getID();
        Message newMessage = new Message(message.getContent(), user.getID(), mainMedia,
                message.getImage(), message.isForwardedTweet());
        ChatSelectingController chatSelectingController = new ChatSelectingController();
        chatSelectingController.switchPage(userID, newMessage.getID());
    }
}
