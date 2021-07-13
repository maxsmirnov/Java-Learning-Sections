package org.learn.controller;

import javafx.stage.Stage;
import org.learn.datatranslation.DataConverter;
import org.learn.datatranslation.FileManager;
import org.learn.datatranslation.entities.LearningData;
import org.learn.datatranslation.entities.Question;
import org.learn.datatranslation.entities.Section;
import org.learn.forms.NewSectionForm;
import org.learn.forms.QuestionCreateNewForm;
import org.learn.forms.QuestionEditForm;
import org.learn.forms.QuestionForm;
import org.learn.forms.SectionForm;
import org.learn.tools.Settings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LearningController {
    private static LearningController state;
    private Stage primaryStage;
    private Long topLevelId = 0L;

    public static LearningController getState() {
        if (state == null) {
            state = new LearningController();
        }
        return state;
    }

    public void showSections() {
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
    }

    public void showQuestions(Section parentSection) {
        LearningData data = DataConverter.getState().getLearningData();
        List<Section> updatedSections = new ArrayList<>();
        for (Section section : data.getSections()) {
            if (section.getId().equals(parentSection.getId())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                section.setLearnDate(simpleDateFormat.format(new Date()));
                updatedSections.add(section);
            } else {
                updatedSections.add(section);
            }
        }
        data.setSections(updatedSections);
        FileManager.getState().saveData(DataConverter.getState().parseLearningData(data));
        primaryStage.setScene(QuestionForm.getScene(parentSection));
        primaryStage.show();
    }

    public void showNewSectionForm() {
        primaryStage.setScene(NewSectionForm.getScene());
        double padX = Settings.getState().getResolution().getScreenWidth() / 2 - Settings.getState().getResolution().getScreenWidth() / 4;
        double padY = Settings.getState().getResolution().getScreenHeight() / 2 - Settings.getState().getResolution().getScreenHeight() / 4;
        primaryStage.setX(padX);
        primaryStage.setY(padY);
        primaryStage.show();
    }

    public void createNewSectionForm(String name) {
        LearningData data = DataConverter.getState().getLearningData();
        topLevelId++;
        data.getSections().add(new Section(topLevelId, name, new ArrayList<>(), new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        FileManager.getState().saveData(DataConverter.getState().parseLearningData(data));
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
    }

    public void showQuestionEditForm(String text, Long questionId, Long sectionId) {
        primaryStage.setScene(QuestionEditForm.getScene(text, questionId, sectionId));
        double padX = Settings.getState().getResolution().getScreenWidth() / 2 - Settings.getState().getResolution().getScreenWidth() / 4;
        double padY = Settings.getState().getResolution().getScreenHeight() / 2 - Settings.getState().getResolution().getScreenHeight() / 4;
        primaryStage.setX(padX);
        primaryStage.setY(padY);
        primaryStage.show();
    }

    public void showNewQuestionForm(String text, Long questionId, Long sectionId) {
        primaryStage.setScene(QuestionCreateNewForm.getScene(text, questionId, sectionId));
        double padX = Settings.getState().getResolution().getScreenWidth() / 2 - Settings.getState().getResolution().getScreenWidth() / 4;
//        double padY = Settings.getState().getResolution().getScreenHeight() / 2 - Settings.getState().getResolution().getScreenHeight() / 4;
        primaryStage.setX(padX);
//        primaryStage.setY(padY);
        primaryStage.show();
    }

    public void editQuestionAnswer(String newAnswer, Long id, Long sectionId) {
        LearningData data = DataConverter.getState().getLearningData();
        List<Section> updatedSections = new ArrayList<>();
        Section parentSection = null;
        for (Section section : data.getSections()) {
            if (section.getId().equals(sectionId)) {
                List<Question> updatedQuestions = new ArrayList<>();
                for (Question question : section.getQuestions()) {
                    if (question.getId().equals(id)) {
                        question.setAnswer(newAnswer);
                        updatedQuestions.add(question);
                    } else {
                        updatedQuestions.add(question);
                    }
                }
                section.setQuestions(updatedQuestions);
                parentSection = section;
                updatedSections.add(section);
            } else {
                updatedSections.add(section);
            }
        }
        data.setSections(updatedSections);
        FileManager.getState().saveData(DataConverter.getState().parseLearningData(data));
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.setX(0);
        primaryStage.setY(0);
        showQuestions(parentSection);
    }

    public void createNewQuestion(Question question, Long sectionId) {
        LearningData data = DataConverter.getState().getLearningData();
        List<Section> updatedSections = new ArrayList<>();
        Section parentSection = null;

        for (Section section : data.getSections()) {
            if (section.getId().equals(sectionId)) {
                List<Question> updatedQuestions = section.getQuestions();
                updatedQuestions.add(question);
                section.setQuestions(updatedQuestions);
                updatedSections.add(section);
                parentSection = section;
            } else {
                updatedSections.add(section);
            }
        }

        data.setSections(updatedSections);
        FileManager.getState().saveData(DataConverter.getState().parseLearningData(data));
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.setX(0);
        primaryStage.setY(0);
        showQuestions(parentSection);
    }

    public void markAsRead(Long sectionId, Long questionId) {
        LearningData data = DataConverter.getState().getLearningData();
        List<Section> updatedSections = new ArrayList<>();
        Section parentSection = null;

        for (Section section : data.getSections()) {
            if (section.getId().equals(sectionId)) {
                List<Question> updatedQuestions = new ArrayList<>();
                for (Question question : section.getQuestions()) {
                    if (questionId.equals(question.getId())) {
                        question.setCountOfRead(question.getCountOfRead() + 1);
                        updatedQuestions.add(question);
                    } else {
                        updatedQuestions.add(question);
                    }
                }
                section.setQuestions(updatedQuestions);
                updatedSections.add(section);
                parentSection = section;
            } else {
                updatedSections.add(section);
            }
        }

        data.setSections(updatedSections);
        FileManager.getState().saveData(DataConverter.getState().parseLearningData(data));
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.setX(0);
        primaryStage.setY(0);
        showQuestions(parentSection);
    }

    public void deleteQuestion(Long sectionId, Long questionId) {
        LearningData data = DataConverter.getState().getLearningData();
        List<Section> updatedSections = new ArrayList<>();
        Section parentSection = null;

        for (Section section : data.getSections()) {
            if (section.getId().equals(sectionId)) {
                List<Question> updatedQuestions = new ArrayList<>();
                for (Question question : section.getQuestions()) {
                    if (!questionId.equals(question.getId())) {
                        updatedQuestions.add(question);
                    }
                }
                section.setQuestions(updatedQuestions);
                updatedSections.add(section);
                parentSection = section;
            } else {
                updatedSections.add(section);
            }
        }

        data.setSections(updatedSections);
        FileManager.getState().saveData(DataConverter.getState().parseLearningData(data));
        primaryStage.setScene(SectionForm.getScene());
        primaryStage.setX(0);
        primaryStage.setY(0);
        showQuestions(parentSection);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setTopLevelId(Long topLevelId) {
        this.topLevelId = topLevelId;
    }
}
