package ir.sharif.aminra.listeners.enterPage;

import ir.sharif.aminra.controller.enterPage.SignInController;
import ir.sharif.aminra.events.enterPage.SignInFormEvent;

public class SignInFormListener {
    private final SignInController signInController = new SignInController();

    public void eventOccurred(SignInFormEvent signInFormEvent) {
        signInController.login(signInFormEvent);
    }
}
