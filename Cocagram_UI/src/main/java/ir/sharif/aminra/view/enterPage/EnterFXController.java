package ir.sharif.aminra.view.enterPage;

import ir.sharif.aminra.view.FXController;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import javafx.fxml.FXML;

public class EnterFXController extends FXController {
    @FXML
    void signInAction() { ViewManager.getInstance().setPage(new Page("signInPage")); }

    @FXML
    void signUpAction() {
        ViewManager.getInstance().setPage(new Page("signUpPage"));
    }
}