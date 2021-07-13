package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.learn.controller.LearningController;

public class DeleteQuestionHandler implements EventHandler<ActionEvent> {

    private Long sectionId;
    private Long questionId;

    public DeleteQuestionHandler(Long sectionId, Long questionId) {
        this.sectionId = sectionId;
        this.questionId = questionId;
    }

    @Override
    public void handle(ActionEvent event) {
        LearningController.getState().deleteQuestion(sectionId, questionId);
    }
}
