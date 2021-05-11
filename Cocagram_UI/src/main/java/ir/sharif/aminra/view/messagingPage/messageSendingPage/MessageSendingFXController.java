package ir.sharif.aminra.view.messagingPage.messageSendingPage;

import ir.sharif.aminra.events.messagingPage.messageSendingPage.NewMessageEvent;
import ir.sharif.aminra.listeners.messagingPage.messageSendigPage.NewMessageListener;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.FXController;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

public class MessageSendingFXController extends FXController implements Initializable {
    static private final Logger logger = LogManager.getLogger(MessageSendingFXController.class);
    NewMessageListener newMessageListener;

    Image messageImage;
    ID receiverID;

    public void setReceiverID(ID receiverID) {
        this.receiverID = receiverID;
    }

    @FXML
    private TextField messageContent;

    @FXML
    private Label errorLabel;

    @FXML
    public void attach() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            messageImage = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
            logger.warn("an error occurred while trying to load image.");
        }
    }

    @FXML
    public void send() {
        newMessageListener.eventOccurred(new NewMessageEvent(this, messageImage,
                messageContent.getText(), receiverID, userID));
    }

    public void setErrorLabel(String error) {
        errorLabel.setText(error);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newMessageListener = new NewMessageListener();
    }
}
