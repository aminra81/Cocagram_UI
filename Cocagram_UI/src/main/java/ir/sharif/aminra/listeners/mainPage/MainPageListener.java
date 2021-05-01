package ir.sharif.aminra.listeners.mainPage;

import ir.sharif.aminra.controller.personalPage.MyPageController;
import ir.sharif.aminra.view.mainPage.MainFXController;

public class MainPageListener {
    MainFXController mainFXController;
    public MainPageListener(MainFXController mainFXController) {
        this.mainFXController = mainFXController;
    }
    public void stringEventOccurred(String event) {
        switch (event) {
            case "myPage":
                MyPageController myPageController = new MyPageController();
                myPageController.switchPage(mainFXController);
                break;
            default:
                break;
        }
    }
}
