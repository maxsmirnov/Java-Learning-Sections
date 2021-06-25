package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.learn.controller.LearningController;
import org.learn.datatranslation.entities.Section;

public class QuestionHandler implements EventHandler<ActionEvent> {

    private Section parentSection;

    public QuestionHandler(Section parentSection) {
        this.parentSection = parentSection;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(parentSection.getId());
        System.out.println(parentSection.getName());
        LearningController.getState().showQuestions();
    }

}
