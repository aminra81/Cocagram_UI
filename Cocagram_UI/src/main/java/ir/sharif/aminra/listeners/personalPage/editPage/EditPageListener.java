package ir.sharif.aminra.listeners.personalPage.editPage;

import ir.sharif.aminra.controller.personalPage.editPage.EditPageController;
import ir.sharif.aminra.events.personalPage.editPage.EditPageEvent;

public class EditPageListener {
    EditPageController editPageController;
    public void eventOccurred(EditPageEvent editPageEvent) {
        editPageController = new EditPageController();
        editPageController.editUser(editPageEvent);
    }
}
