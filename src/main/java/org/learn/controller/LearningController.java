package org.learn.controller;

import javafx.stage.Stage;
import org.learn.forms.QuestionForm;
import org.learn.forms.SectionForm;

public class LearningController {
    private static LearningController state;
    private Stage primaryStage;

    public static LearningController getState() {
        if (state == null) {
            state = new LearningController();
        }
        return state;
    }

    public void showSections() {
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.show();
    }

    public void showQuestions() {
        primaryStage.setScene(QuestionForm.getScene());
        primaryStage.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
