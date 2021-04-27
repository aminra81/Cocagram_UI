package ir.sharif.aminra.view.enterPage;

import ir.sharif.aminra.events.enterPage.SignInFormEvent;
import ir.sharif.aminra.listeners.enterPage.SignInFormListener;
import ir.sharif.aminra.view.FXController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInFXController extends FXController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    private SignInFormListener signInFormListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signInFormListener = new SignInFormListener();
    }

    @FXML
    public void login() {
        signInFormListener.eventOccurred(new SignInFormEvent(this, username.getText(), password.getText()));
    }

    @FXML
    public void setError(String error) {
        errorLabel.setText(error);
    }
}
