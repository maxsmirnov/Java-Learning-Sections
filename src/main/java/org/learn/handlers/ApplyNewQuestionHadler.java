package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.learn.controller.LearningController;
import org.learn.datatranslation.entities.ImageContent;
import org.learn.datatranslation.entities.Question;

import java.util.List;

public class ApplyNewQuestionHadler implements EventHandler<ActionEvent> {

    private TextField questionResult;
    private TextArea answerResult;
    private Long questionId;
    private Long sectionId;
    private List<ImageContent> imageContents;

    public ApplyNewQuestionHadler(TextField questionResult, TextArea answerResult, Long questionId, Long sectionId, List<ImageContent> imageContents) {
        this.questionResult = questionResult;
        this.answerResult = answerResult;
        this.questionId = questionId;
        this.sectionId = sectionId;
        this.imageContents = imageContents;
    }

    @Override
    public void handle(ActionEvent event) {
        Question question = new Question();
        question.setId(questionId);
        question.setCountOfRead(0);
        question.setLastReadDate("");
        question.setQuestion(questionResult.getText());
        question.setAnswer(answerResult.getText());
        question.setImageContents(imageContents);
        LearningController.getState().createNewQuestion(question, sectionId);
    }
}
