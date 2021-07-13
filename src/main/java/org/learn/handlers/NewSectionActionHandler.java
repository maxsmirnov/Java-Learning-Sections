package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import org.learn.controller.LearningController;

public class NewSectionActionHandler implements EventHandler<ActionEvent> {

    private TextField sectionNameResult;

    public NewSectionActionHandler(TextField sectionNameResult) {
        this.sectionNameResult = sectionNameResult;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Section name = " + sectionNameResult.getText());
        LearningController.getState().createNewSectionForm(sectionNameResult.getText());
    }
}