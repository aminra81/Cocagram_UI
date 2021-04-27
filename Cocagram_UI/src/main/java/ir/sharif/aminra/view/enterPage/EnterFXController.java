package ir.sharif.aminra.view.enterPage;

import ir.sharif.aminra.view.FXController;
import ir.sharif.aminra.view.ViewManager;
import javafx.fxml.FXML;

public class EnterFXController extends FXController {
    @FXML
    void signInAction() { ViewManager.getInstance().setPage(new SignInPage()); }

    @FXML
    void signUpAction() {
        ViewManager.getInstance().setPage(new SignUpPage());
    }
}