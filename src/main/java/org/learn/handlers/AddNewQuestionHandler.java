package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.learn.controller.LearningController;
import org.learn.datatranslation.entities.Section;

public class AddNewQuestionHandler implements EventHandler<ActionEvent> {

    private Section parentSection;
    private String text;
    private Long questionId;

    public AddNewQuestionHandler(Section parentSection, String text, Long questionId) {
        this.parentSection = parentSection;
        this.text = text;
        this.questionId = questionId;
    }

    @Override
    public void handle(ActionEvent event) {
        LearningController.getState().showNewQuestionForm(text, questionId, parentSection.getId());
    }
}
