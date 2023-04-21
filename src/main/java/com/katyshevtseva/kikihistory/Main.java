package com.katyshevtseva.kikihistory;

import com.katyshevtseva.fx.WindowBuilder;
import com.katyshevtseva.kikihistory.view.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.katyshevtseva.kikihistory.view.utils.ViewConstants.HistoryDialogInfo.MAIN;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WindowBuilder.openDialog(MAIN, new MainController());
    }
}