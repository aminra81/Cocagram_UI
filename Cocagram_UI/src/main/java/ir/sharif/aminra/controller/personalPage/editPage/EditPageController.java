package ir.sharif.aminra.controller.personalPage.editPage;

import ir.sharif.aminra.controller.DataValidator;
import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.events.personalPage.editPage.EditPageEvent;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.personalPage.editPage.EditFXController;
import ir.sharif.aminra.view.personalPage.MyFXController;

public class EditPageController {
    public void switchPage(MyFXController myFXController) {
        Page editPage = new Page("editPage");
        ViewManager.getInstance().setPage(editPage);
        User user = Context.getInstance().getUserDB().getByID(myFXController.getUserID());
        EditFXController editFXController = (EditFXController) editPage.getFxController();

        editFXController.setUsernameField(user.getUsername());
        editFXController.setFirstnameField(user.getFirstname());
        editFXController.setLastnameField(user.getLastname());
        editFXController.setBioField(user.getBio());
        editFXController.setBirthdateField(user.getBirthDate());
        editFXController.setEmailField(user.getEmail());
        editFXController.setPhoneNumberField(user.getPhoneNumber());
        editFXController.setLastSeenField(user.getLastSeenType());
        if(user.isPrivate())
            editFXController.setAccountPrivacyField("private");
        else
            editFXController.setAccountPrivacyField("public");
        if(user.isPublicData())
            editFXController.setDataPrivacyField("private");
        else
            editFXController.setDataPrivacyField("public");

        editFXController.setUserID(myFXController.getUserID());
    }

    public void editUser(EditPageEvent editPageEvent) {
        User user = Context.getInstance().getUserDB().getByID(editPageEvent.getEditFXController().getUserID());

        String prevPhoneNumber = user.getPhoneNumber();
        String prevEmail = user.getEmail();

        user.setPhoneNumber("");
        user.setEmail("");

        DataValidator editValidator = new DataValidator();

        String error = editValidator.validateFirstname(editPageEvent.getFirstname());
        if (error.equals(""))
            error = editValidator.validateLastname(editPageEvent.getLastname());
        if (error.equals(""))
            error = editValidator.validateEmail(editPageEvent.getEmail());
        if (error.equals(""))
            error = editValidator.validatePhoneNumber(editPageEvent.getEmail());

        if(!error.equals("")) {
            user.setEmail(prevEmail);
            user.setPhoneNumber(prevPhoneNumber);
            editPageEvent.getEditFXController().setError(error);
        }
        else {
            user.setFirstname(editPageEvent.getFirstname());
            user.setLastname(editPageEvent.getLastname());
            user.setBio(editPageEvent.getBio());
            user.setBirthDate(editPageEvent.getBirthdate());
            user.setEmail(editPageEvent.getEmail());
            user.setPhoneNumber(editPageEvent.getPhoneNumber());
            ID imageID = null;
            if(editPageEvent.getAvatar() != null)
                imageID = Context.getInstance().getImageDB().saveIntoDB(editPageEvent.getAvatar());
            if(imageID != null)
                user.setAvatar(imageID);
            ViewManager.getInstance().back();
        }
    }
}
