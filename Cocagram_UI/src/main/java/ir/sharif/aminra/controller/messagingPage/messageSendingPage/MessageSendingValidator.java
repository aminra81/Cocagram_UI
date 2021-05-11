package ir.sharif.aminra.controller.messagingPage.messageSendingPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageSendingValidator {

    static private final Logger logger = LogManager.getLogger(MessageSendingValidator.class);

    String SendMessageError(User writer, User receiver) {

        Config errorsConfig = Config.getConfig("messageSendingPage");

        if(writer.equals(receiver)) {
            logger.info(String.format("user %s wants to send an invalid message.", writer.getUsername()));
            return errorsConfig.getProperty("messageToSelfError");
        }
        if(!receiver.isActive()) {
            logger.info(String.format("user %s wants to send an invalid message.", writer.getUsername()));
            return errorsConfig.getProperty("messageToDeactivatedUserError");
        }
        if (!writer.getFollowings().contains(receiver.getID()) && !receiver.getFollowings().contains(writer.getID())) {
            logger.info(String.format("user %s wants to send an invalid message.", writer.getUsername()));
            return errorsConfig.getProperty("invalidFollowError");
        }

        if (writer.getBlockList().contains(receiver.getID())) {
            logger.info(String.format("user %s wants to send an invalid message.", writer.getUsername()));
            return errorsConfig.getProperty("youBlockedReceiverError");
        }
        if (receiver.getBlockList().contains(writer.getID())) {
            logger.info(String.format("user %s wants to send an invalid message.", writer.getUsername()));
            return errorsConfig.getProperty("receiverBlockedYouError");
        }
        return "";
    }
}
