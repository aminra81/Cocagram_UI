package ir.sharif.aminra.db;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.models.ID;
import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.imageio.ImageIO;
import java.io.*;

public class ImageDB {

    private final File dbDirectory = Config.getConfig("db").getProperty(File.class, "imagesDB");
    static private final Logger logger = LogManager.getLogger(UserDB.class);
    public static final ID DEFAULT_AVATAR_ID = new ID(1);

    public Image getByID(ID id) {
        File data = new File(dbDirectory, id + ".png");
        Image image = new Image(data.toURI().toString());
            logger.info(String.format("read the image from file %s", data.getName()));
        return image;
    }

    public ID saveIntoDB(Image image) {
        File data = null;
        try {
            ID id = new ID(true);
            data = new File(dbDirectory, id + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", data);
            logger.info(String.format("saved image %s", data.getName()));
            return id;
        } catch (IOException e) {
            logger.warn(String.format("exception occurred while saving image %s", data.getName()));
            e.printStackTrace();
        }
        return null;
    }
}
