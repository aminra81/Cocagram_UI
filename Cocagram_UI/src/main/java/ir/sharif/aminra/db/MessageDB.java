package ir.sharif.aminra.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.media.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class MessageDB implements DBSet<Message> {

    private final File dbDirectory = Config.getConfig("db").getProperty(File.class, "messagesDB");
    static private final Logger logger = LogManager.getLogger(MessageDB.class);

    @Override
    public Message getByID(ID id) {
        try {
            File Data = new File(dbDirectory, id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Data));
            logger.info(String.format("file %s opened.", Data.getName()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Message message = gson.fromJson(bufferedReader, Message.class);
            bufferedReader.close();
            logger.info(String.format("file %s closed.", Data.getName()));
            return message;
        } catch (Exception e) {
            logger.warn(String.format("Exception occurred while trying to get message %s", id));
        }
        return null;
    }

    @Override
    public void saveIntoDB(Message message) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File Data = new File(dbDirectory, message.getId() + ".json");
            if (!Data.exists())
                Data.createNewFile();
            logger.info(String.format("file %s opened.", Data.getName()));
            FileWriter writer = new FileWriter(Data);
            writer.write(gson.toJson(message));
            writer.close();
            logger.info(String.format("file %s closed.", Data.getName()));
        } catch (Exception e) {
            logger.error(String.format("Exception occurred while trying to save message %s", message.getId()));
        }
    }
}
