package ir.sharif.aminra.listeners.enterPage;
import ir.sharif.aminra.controller.enterPage.SignUpController;
import ir.sharif.aminra.events.enterPage.SignUpFormEvent;

public class SignUpFormListener {

    private final SignUpController signUpController = new SignUpController();

    public void eventOccurred(SignUpFormEvent signUpFormEvent) {
        signUpController.register(signUpFormEvent);
    }
}
