package ir.sharif.aminra.controller.enterPage;
import ir.sharif.aminra.controller.DataValidator;
import ir.sharif.aminra.events.enterPage.SignUpFormEvent;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpController {

    DataValidator signUpValidator;
    static private final Logger logger = LogManager.getLogger(SignUpController.class);

    public void register(SignUpFormEvent signUpFormEvent) {
        String error;
        signUpValidator = new DataValidator();

        error = signUpValidator.validateUsername(signUpFormEvent.getUsername());

        if (error.equals(""))
            error = signUpValidator.validateFirstname(signUpFormEvent.getFirstname());
        if (error.equals(""))
            error = signUpValidator.validateLastname(signUpFormEvent.getLastname());
        if (error.equals(""))
            error = signUpValidator.validatePassword(signUpFormEvent.getPassword());
        if (error.equals(""))
            error = signUpValidator.validateEmail(signUpFormEvent.getEmail());
        if (error.equals(""))
            error = signUpValidator.validatePhoneNumber(signUpFormEvent.getEmail());

        if (!error.equals("")) {
            signUpFormEvent.getSignUpFXController().setError(error);
            logger.info(error);
        }
        else {
            new User(signUpFormEvent.getUsername(), signUpFormEvent.getFirstname(), signUpFormEvent.getLastname(),
                    signUpFormEvent.getBio(), signUpFormEvent.getBirthDate(), signUpFormEvent.getEmail(),
                    signUpFormEvent.getPhoneNumber(), signUpFormEvent.getPassword(), signUpFormEvent.isPublicData(),
                    signUpFormEvent.getLastSeenType());

            logger.info(String.format("user %s registered.", signUpFormEvent.getUsername()));
            ViewManager.getInstance().setPage(new Page("enterPage"));
        }
    }
}
