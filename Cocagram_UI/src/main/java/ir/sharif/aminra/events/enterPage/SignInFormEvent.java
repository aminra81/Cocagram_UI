package ir.sharif.aminra.events.enterPage;

import ir.sharif.aminra.view.enterPage.SignInFXController;

public class SignInFormEvent {
    SignInFXController signInFXController;
    String username;
    String password;

    public SignInFormEvent(SignInFXController signUpFXController, String username, String password) {
        this.signInFXController = signUpFXController;
        this.username = username;
        this.password = password;
    }

    public SignInFXController getSignInFXController() {
        return signInFXController;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
