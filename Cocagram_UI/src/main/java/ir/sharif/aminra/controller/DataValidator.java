package ir.sharif.aminra.controller;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.db.Context;

public class DataValidator {

    private final Config errorsConfig = Config.getConfig("signUpPage");

    public String validateUsername(String username) {
        if(username.equals(""))
            return errorsConfig.getProperty("emptyUsernameError");
        if(Context.getInstance().getUserDB().getUser(username) != null)
            return errorsConfig.getProperty("duplicateUsernameError");
        return "";
    }

    public String validateFirstname(String firstname) {
        if(firstname.equals(""))
            return errorsConfig.getProperty("emptyFirstnameError");
        return "";
    }

    public String validateLastname(String lastname) {
        if(lastname.equals(""))
            return errorsConfig.getProperty("emptyLastnameError");
        return "";
    }

    public String validatePassword(String password) {
        if(password.equals(""))
            return errorsConfig.getProperty("emptyPasswordError");
        return "";
    }

    public String validateEmail(String email) {
        if(email.equals(""))
            return errorsConfig.getProperty("emptyEmailError");
        if(Context.getInstance().getUserDB().getEmail(email) != null)
            return errorsConfig.getProperty("duplicateEmailError");
        return "";
    }

    public String validatePhoneNumber(String phoneNumber) {
        if(!phoneNumber.equals("") && Context.getInstance().getUserDB().getPhoneNumber(phoneNumber) != null)
            return errorsConfig.getProperty("duplicatePhoneNumberError");
        return "";
    }
}
