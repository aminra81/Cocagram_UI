package ir.sharif.aminra.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Objects;

public class UserDB implements DBSet<User>{

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
                if(currentUser.getUsername().equals(username))
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
                if(currentUser.getPhoneNumber().equals(phoneNumber))
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
                if(currentUser.getEmail().equals(email))
                    return currentUser;
            }
        } catch (IOException e) {
            logger.error("an exception occurred while trying to get user with given email file.");
        }
        return null;
    }
}
