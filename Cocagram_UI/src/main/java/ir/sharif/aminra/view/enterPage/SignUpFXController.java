package ir.sharif.aminra.view.enterPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.events.enterPage.SignUpFormEvent;
import ir.sharif.aminra.listeners.enterPage.SignUpFormListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpFXController extends FXController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField bio;

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField password;

    @FXML
    private ChoiceBox<String> lastSeenChoice;

    private final String[] lastSeenChoices = Config.getConfig("signUpPage").
            getPropertyArray(String.class, "allOptions");

    @FXML
    private CheckBox publicData;

    @FXML
    private Label errorLabel;

    private SignUpFormListener signUpFormListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastSeenChoice.getItems().addAll(lastSeenChoices);
        lastSeenChoice.setValue(Config.getConfig("signUpPage").getProperty("everyoneOption"));
        signUpFormListener = new SignUpFormListener();
    }

    @FXML
    public void register() {
        signUpFormListener.eventOccurred(new SignUpFormEvent(this, username.getText(), firstname.getText(),
                lastname.getText(), bio.getText(), birthdate.getValue(), email.getText(), phoneNumber.getText(),
                password.getText(), publicData.isSelected(), lastSeenChoice.getValue()));
    }

    @FXML
    public void setError(String error) {
        errorLabel.setText(error);
    }
}
