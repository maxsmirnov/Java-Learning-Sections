package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.learn.controller.LearningController;

public class MarkAsReadHandler implements EventHandler<ActionEvent> {

    private Long sectionId;
    private Long questionId;

    public MarkAsReadHandler(Long sectionId, Long questionId) {
        this.sectionId = sectionId;
        this.questionId = questionId;
    }

    @Override
    public void handle(ActionEvent event) {
        LearningController.getState().markAsRead(sectionId, questionId);
    }
}
