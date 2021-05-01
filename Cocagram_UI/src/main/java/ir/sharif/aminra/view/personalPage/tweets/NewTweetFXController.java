package ir.sharif.aminra.view.personalPage.tweets;

import ir.sharif.aminra.events.personalPage.tweets.NewTweetEvent;
import ir.sharif.aminra.listeners.personalPage.tweets.NewTweetListener;
import ir.sharif.aminra.view.FXController;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTweetFXController extends FXController implements Initializable {
    static private final Logger logger = LogManager.getLogger(NewTweetFXController.class);

    Image tweetImage;
    NewTweetListener newTweetListener;

    @FXML
    private TextField tweetContent;

    @FXML
    public void attach() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            tweetImage = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("an error occurred while trying to load image.");
        }
    }
    @FXML
    public void tweet() {
        newTweetListener.eventOccurred(new NewTweetEvent(this, tweetContent.getText(), tweetImage));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newTweetListener = new NewTweetListener();
    }
}
