package ir.sharif.aminra.controller.enterPage;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.enterPage.SignInFormEvent;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.enterPage.EnterPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignInController {

    static private final Logger logger = LogManager.getLogger(SignUpController.class);

    public void login(SignInFormEvent signInFormEvent) {
        Config errorsConfig = Config.getConfig("signInPage");
        User user = Context.getInstance().getUserDB().getUser(signInFormEvent.getUsername());
        if(user == null) {
            signInFormEvent.getSignInFXController().setError(errorsConfig.getProperty("noUserError"));
            return;
        }
        if(!user.getPassword().equals(signInFormEvent.getPassword())) {
            signInFormEvent.getSignInFXController().setError(errorsConfig.getProperty("passNotMatch"));
            return;
        }
        logger.info(String.format("user %s signed in.", user.getUsername()));
        ViewManager.getInstance().setPage(new EnterPage());
    }
}
