package ir.sharif.aminra.events.enterPage;

import ir.sharif.aminra.view.enterPage.SignUpFXController;

import java.time.LocalDate;

public class SignUpFormEvent {
    SignUpFXController signUpFXController;
    String username;
    String firstname;
    String lastname;
    String bio;
    LocalDate birthDate;
    String email;
    String phoneNumber;
    String password;
    boolean publicData;
    String lastSeenType;

    public SignUpFormEvent(SignUpFXController signUpFXController, String username, String firstname,
                           String lastname, String bio, LocalDate birthDate, String email, String phoneNumber,
                           String password, boolean publicData, String lastSeenType) {
        this.signUpFXController = signUpFXController;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.publicData = publicData;
        this.lastSeenType = lastSeenType;
    }

    public SignUpFXController getSignUpFXController() {
        return signUpFXController;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBio() {
        return bio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPublicData() {
        return publicData;
    }

    public String getLastSeenType() {
        return lastSeenType;
    }
}
