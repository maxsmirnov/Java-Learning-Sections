package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import org.learn.controller.LearningController;

public class ApplyEditableAnswerHandler implements EventHandler<ActionEvent> {

    private TextArea answerResult;
    private Long questionId;
    private Long sectionId;

    public ApplyEditableAnswerHandler(TextArea answerResult, Long questionId, Long sectionId) {
        this.answerResult = answerResult;
        this.questionId = questionId;
        this.sectionId = sectionId;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("try to save");
        LearningController.getState().editQuestionAnswer(answerResult.getText() ,questionId, sectionId);
    }
}
