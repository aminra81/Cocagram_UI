package ir.sharif.aminra.events.personalPage.editPage;

import ir.sharif.aminra.view.personalPage.editPage.EditFXController;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class EditPageEvent {
    EditFXController editFXController;

    String firstname;
    String lastname;
    String bio;
    LocalDate birthdate;
    String email;
    String phoneNumber;
    Image avatar;

    public EditPageEvent(EditFXController editFXController, String firstname, String lastname, String bio, LocalDate birthdate, String email, String phoneNumber, Image avatar) {
        this.editFXController = editFXController;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public EditFXController getEditFXController() {
        return editFXController;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Image getAvatar() {
        return avatar;
    }
}
