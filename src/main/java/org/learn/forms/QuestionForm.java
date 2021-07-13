package org.learn.forms;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.learn.datatranslation.entities.ImageContent;
import org.learn.datatranslation.entities.Question;
import org.learn.datatranslation.entities.Section;
import org.learn.handlers.AddNewQuestionHandler;
import org.learn.handlers.BackToSectionsHandler;
import org.learn.handlers.DeleteQuestionHandler;
import org.learn.handlers.EditQuestionHandler;
import org.learn.handlers.MarkAsReadHandler;
import org.learn.tools.Settings;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;

import static org.learn.tools.Settings.BACK_BUTTON;
import static org.learn.tools.Settings.FORM_STYLE;
import static org.learn.tools.Settings.LABEL_STYLE;
import static org.learn.tools.Settings.QUESTION_STYLE;
import static org.learn.tools.Settings.SCROLL_STYLE;
import static org.learn.tools.Settings.TEXT_AREA_STYLE;
import static org.learn.tools.Settings.TEXT_FIELD_STYLE;

public class QuestionForm {

    public static Scene getScene(Section parentSection) {

        VBox rootPane = new VBox();
        rootPane.setPrefSize(Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight() - 30);

        TextField sectionName = new TextField();
        sectionName.setMinWidth(Settings.getState().getResolution().getScreenWidth());
        sectionName.setStyle(TEXT_FIELD_STYLE);
        sectionName.setText("Questions topic: " + parentSection.getName());
        sectionName.setEditable(false);
        sectionName.setFocusTraversable(false);

        TextField questionCount = new TextField();
        questionCount.setMinWidth(Settings.getState().getResolution().getScreenWidth());
        questionCount.setStyle(TEXT_FIELD_STYLE);
        questionCount.setText("Questions total: " + parentSection.getQuestions().size());
        questionCount.setEditable(false);
        questionCount.setFocusTraversable(false);

        Long topLevelQuestionId = 0L;
        for (Question question : parentSection.getQuestions()) {
            if (question.getId() > topLevelQuestionId) {
                topLevelQuestionId = question.getId();
            }
        }
        topLevelQuestionId++;

        FlowPane horizonButtonsPanel = new FlowPane();
        horizonButtonsPanel.setStyle(FORM_STYLE);
        horizonButtonsPanel.setHgap(5);

        Button newQuestion = new Button();
        newQuestion.setText("Add new question");
        newQuestion.setOnAction(new AddNewQuestionHandler(parentSection, "", topLevelQuestionId));

        Button backToSection = new Button();
        backToSection.setText("Back to sections");
        backToSection.setOnAction(new BackToSectionsHandler());

        horizonButtonsPanel.getChildren().addAll(newQuestion, backToSection);

        GridPane bio = new GridPane();
        bio.setHgap(5);
        bio.setVgap(5);
        bio.setStyle(FORM_STYLE);
        bio.add(sectionName, 0, 0, 1, 1);
        bio.add(questionCount, 0, 1, 1, 1);
        bio.add(horizonButtonsPanel, 0, 2, 1, 1);
        bio.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMinSize(Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight() - 30);
        scrollPane.setStyle(SCROLL_STYLE);

        GridPane questionPane = new GridPane();
        questionPane.setHgap(10);
        questionPane.setVgap(10);

        List<Question> questionSortedByReadMark = parentSection.getQuestions();
        questionSortedByReadMark.sort(Comparator.comparing(Question::getCountOfRead));

        int i = 1;
        for (Question question : questionSortedByReadMark) {
            questionPane.add(buildElement(question, i, parentSection.getId()), 0, i, 1, 1);
            i++;
        }

        GridPane gap = new GridPane();
        gap.setVgap(20);
        Label gapLabel = new Label("");
        gapLabel.setStyle(LABEL_STYLE);
        gap.add(gapLabel, 0, 9, 1, 1);
        questionPane.add(gap, 0, i, 1, 1);

        scrollPane.setContent(questionPane);
        scrollPane.getContent().setStyle(FORM_STYLE);
        scrollPane.setPadding(new Insets(10));

        rootPane.getChildren().addAll(bio, scrollPane);

        Scene scene = new Scene(rootPane, Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight() - 30);

        Platform.runLater(scrollPane::requestFocus);

        return scene;
    }

    private static GridPane buildElement(Question question, int count, Long sectionId) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button backToSections = new Button();
        backToSections.setOnAction(new BackToSectionsHandler());
        backToSections.setText(BACK_BUTTON);
        backToSections.setMinWidth(200);

        TextField textField = new TextField();
        textField.setMinWidth(Settings.getState().getQuestionWidth());
        textField.setText("No" + count + ". " + question.getQuestion());
        textField.setStyle(QUESTION_STYLE);
        textField.setEditable(false);

        List<String> splittedAnswer = Arrays.asList(question.getAnswer().split("\\*"));

        VBox answerContent = new VBox();
        answerContent.setPadding(new Insets(5, 0, 5, 0));
        answerContent.setSpacing(5);

        int i = 1;
        for (String value : splittedAnswer) {
            if (i % 2 != 0) {
                TextArea textArea = new TextArea();
                textArea.setPrefColumnCount(1);
                textArea.setMinWidth(Settings.getState().getQuestionWidth());
                textArea.setText(value);
                textArea.setStyle(TEXT_AREA_STYLE);
                textArea.setWrapText(true);
                textArea.setEditable(false);
                answerContent.getChildren().add(textArea);
            } else {
                String data = "";
                for (ImageContent imageContent : question.getImageContents()) {
                    if (value.split("image")[1].equals(imageContent.getContentId())) {
                        data = imageContent.getData();
                        break;
                    }
                }
                byte[] imageBytes = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
                ByteArrayInputStream bs = new ByteArrayInputStream(imageBytes);
                answerContent.getChildren().add(new ImageView(new Image(bs)));
            }
            i++;
        }

//        TextArea textArea = new TextArea();
//        textArea.setPrefColumnCount(1);
//        textArea.setMinWidth(Settings.getState().getQuestionWidth());
//        textArea.setText(question.getAnswer());
//        textArea.setStyle(TEXT_AREA_STYLE);
//        textArea.setWrapText(true);
//        textArea.setEditable(false);

        Button editQuestion = new Button("Edit");
        editQuestion.setOnAction(new EditQuestionHandler(question.getAnswer(), question.getId(), sectionId));
        editQuestion.setMinWidth(200);

        Button markAsRead = new Button();
        markAsRead.setText("Mark as read");
        markAsRead.setOnAction(new MarkAsReadHandler(sectionId, question.getId()));
        markAsRead.setMinWidth(200);

        Button deleteQuestion = new Button();
        deleteQuestion.setText("Delete question");
        deleteQuestion.setOnAction(new DeleteQuestionHandler(sectionId, question.getId()));
        deleteQuestion.setMinWidth(200);

        Label countOfRead = new Label("Read: " + question.getCountOfRead() + " times.");
        countOfRead.setStyle(LABEL_STYLE);

        VBox buttonPanel = new VBox();
        buttonPanel.setPadding(new Insets(5, 0, 5, 0));
        buttonPanel.setSpacing(5);
        buttonPanel.getChildren().addAll(
                backToSections,
                editQuestion,
                markAsRead,
                deleteQuestion,
                countOfRead
        );

        gridPane.add(textField, 0, 0, 1, 1);
        gridPane.add(answerContent, 0, 1, 1, 1);
        gridPane.add(buttonPanel, 1, 1, 1, 1);
        gridPane.setStyle(FORM_STYLE);

        return gridPane;
    }
}
