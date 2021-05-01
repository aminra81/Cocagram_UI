package ir.sharif.aminra.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.MessageGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class MessageGroupDB implements DBSet<MessageGroup> {
    private final File dbDirectory = Config.getConfig("db").getProperty(File.class, "messageGroupsDB");
    static private final Logger logger = LogManager.getLogger(UserDB.class);

    @Override
    public MessageGroup getByID(ID id) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Data));
            logger.info(String.format("file %s opened.", Data.getName()));
            MessageGroup messageGroup = gson.fromJson(bufferedReader, MessageGroup.class);
            bufferedReader.close();
            logger.info(String.format("file %s closed.", Data.getName()));
            return messageGroup;
        } catch (IOException e) {
            logger.error(String.format("Exception occurred while trying to get message group %s", id));
        }
        return null;
    }

    @Override
    public void saveIntoDB(MessageGroup messageGroup) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, messageGroup.getID() + ".json");
            if (!Data.exists())
                Data.createNewFile();
            logger.info(String.format("file %s opened.", Data.getName()));
            FileWriter writer = new FileWriter(Data);
            writer.write(gson.toJson(messageGroup));
            writer.close();
            logger.info(String.format("file %s closed.", Data.getName()));
        } catch (Exception e) {
            logger.error(String.format("Exception occurred while trying to save message group %s", messageGroup.getID()));
        }
    }
}
