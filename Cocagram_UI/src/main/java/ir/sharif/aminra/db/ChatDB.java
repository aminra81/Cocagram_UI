package ir.sharif.aminra.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.Chat;
import ir.sharif.aminra.models.ID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDB implements DBSet<Chat> {
    private final File dbDirectory = Config.getConfig("db").getProperty(File.class, "chatDB");
    static private final Logger logger = LogManager.getLogger(ChatDB.class);

    @Override
    public Chat getByID(ID id) {
        try {
            File Data = new File(dbDirectory, id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Data));
            logger.info(String.format("file %s opened.", Data.getName()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Chat chat = gson.fromJson(bufferedReader, Chat.class);
            bufferedReader.close();
            logger.info(String.format("file %s closed.", Data.getName()));
            return chat;
        } catch (Exception e) {
            logger.warn(String.format("Exception occurred while trying to get chat %s", id));
        }
        return null;
    }

    @Override
    public void saveIntoDB(Chat chat) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, chat.getID() + ".json");
            if (!Data.exists())
                Data.createNewFile();
            logger.info(String.format("file %s opened.", Data.getName()));
            FileWriter writer = new FileWriter(Data);
            writer.write(gson.toJson(chat));
            writer.close();
            logger.info(String.format("file %s closed.", Data.getName()));
        } catch (Exception e) {
            logger.error(String.format("Exception occurred while trying to save chat %s", chat.getID()));
        }
    }

    public void deleteChats(ID userID) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (File file : dbDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                logger.info(String.format("file %s opened.", file.getName()));
                Chat currentChat = gson.fromJson(bufferedReader, Chat.class);
                bufferedReader.close();
                logger.info(String.format("file %s closed.", file.getName()));
                if (!currentChat.isGroup() && currentChat.getUsers().contains(userID)) {
                    logger.info(String.format("file %s deleted.", file.getName()));
                    file.delete();
                } else {
                    List<ID> toBeRemovedMessages = new ArrayList<>();
                    for (ID messageID : currentChat.getMessages())
                        if (Context.getInstance().getMessageDB().getByID(messageID) == null)
                            toBeRemovedMessages.add(messageID);
                    for (ID messageID : toBeRemovedMessages)
                        currentChat.removeMessage(messageID);
                    if(currentChat.getUsers().contains(userID))
                        currentChat.removeUser(userID);
                }
            }
        } catch (IOException e) {
            logger.error(String.format("an exception occurred while deleting chats related to user %s", userID));
        }
    }
}
