package ir.sharif.aminra.listeners.mainPage;

import ir.sharif.aminra.controller.explorerPage.ExplorerController;
import ir.sharif.aminra.controller.personalPage.MyPageController;
import ir.sharif.aminra.controller.settingsPage.SettingsController;
import ir.sharif.aminra.controller.timelinePage.TimelineController;
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
            case "settings":
                SettingsController settingsController = new SettingsController();
                settingsController.switchPage(mainFXController);
                break;
            case "timeline":
                TimelineController timelineController = new TimelineController();
                timelineController.switchPage(mainFXController);
                break;
            case "explorer":
                ExplorerController explorerController = new ExplorerController();
                explorerController.switchPage(mainFXController);
                break;
            default:
                break;
        }
    }
}
