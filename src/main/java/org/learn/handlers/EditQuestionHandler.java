package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import org.learn.controller.LearningController;

public class EditQuestionHandler implements EventHandler<ActionEvent> {

    private String questionAnswer;
    private Long questionId;
    private Long sectionId;

    public EditQuestionHandler(String questionAnswer, Long questionId, Long sectionId) {
        this.questionAnswer = questionAnswer;
        this.questionId = questionId;
        this.sectionId = sectionId;
    }

    @Override
    public void handle(ActionEvent event) {
        LearningController.getState().showQuestionEditForm(questionAnswer, questionId, sectionId);
    }
}
