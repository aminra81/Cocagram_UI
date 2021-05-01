package ir.sharif.aminra.view;

import ir.sharif.aminra.config.Config;
import javafx.stage.Stage;

import java.util.Stack;

public class ViewManager {

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

    public Stage getStage() {
        return stage;
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
}
