package org.learn.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.learn.controller.LearningController;

public class JavaLearningApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LearningController.getState().setPrimaryStage(primaryStage);
        LearningController.getState().showSections();
    }
}
