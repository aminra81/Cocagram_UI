package ir.sharif.aminra.view;

import ir.sharif.aminra.config.Config;
import ir.sharif.aminra.controller.enterPage.SignUpController;
import ir.sharif.aminra.models.ID;
import ir.sharif.aminra.view.mainPage.MainFXController;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Stack;

public class ViewManager {

    static private final Logger logger = LogManager.getLogger(SignUpController.class);

    private static ViewManager instance;

    public static ViewManager getInstance() {
        if(instance == null)
            instance = new ViewManager();
        return instance;
    }

    Stage stage;
    Stack<Page> stack = new Stack<>();
    Page curPage;

    public void start(Stage stage) {
        this.stage = stage;

        curPage = new Page("enterPage");
        setPage(curPage);

        stage.setTitle(Config.getConfig("main").getProperty("projectName"));
        stage.setResizable(false);
        stage.show();
    }

    public void setPage(Page page) {
        stack.push(page);
        stage.setScene(page.getScene());
    }

    public void back() {
        stack.pop();
        stack.peek().getFxController().refresh();
        stage.setScene(stack.peek().getScene());
    }

    public void goToMainPage(ID userID) {
        Page mainPage = new Page("mainPage");
        MainFXController mainFXController = (MainFXController) mainPage.getFxController();
        mainFXController.setUserID(userID);
        ViewManager.getInstance().setPage(mainPage);
    }
}
