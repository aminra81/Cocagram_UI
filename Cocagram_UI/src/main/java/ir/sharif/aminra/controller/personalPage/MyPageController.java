package ir.sharif.aminra.controller.personalPage;

import ir.sharif.aminra.db.Context;
import ir.sharif.aminra.models.User;
import ir.sharif.aminra.view.Page;
import ir.sharif.aminra.view.ViewManager;
import ir.sharif.aminra.view.mainPage.MainFXController;
import ir.sharif.aminra.view.personalPage.MyFXController;
import javafx.scene.paint.ImagePattern;

public class MyPageController {
    public void switchPage(MainFXController mainFXController) {
        Page myPage = new Page("myPage");
        ViewManager.getInstance().setPage(myPage);
        MyFXController myFXController = (MyFXController) myPage.getFxController();
        myFXController.setUserID(mainFXController.getUserID());
        refresh(myFXController);
    }

    public void refresh(MyFXController myFXController) {
        User user = Context.getInstance().getUserDB().getByID(myFXController.getUserID());
        myFXController.getAvatar().setFill(new ImagePattern(Context.getInstance().getImageDB().getByID(user.getAvatar())));
    }
}
