package org.learn.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.learn.datatranslation.entities.ImageContent;
import org.learn.handlers.ApplyNewQuestionHadler;
import org.learn.handlers.InsertImageFromClipBoardHandler;
import org.learn.tools.Settings;

import java.util.ArrayList;
import java.util.List;

import static org.learn.tools.Settings.FORM_STYLE;
import static org.learn.tools.Settings.TEXT_AREA_STYLE;
import static org.learn.tools.Settings.TEXT_FIELD_STYLE;

public class QuestionCreateNewForm {
    public static Scene getScene(String editableText, Long questionId, Long sectionId) {

        StackPane rootPane = new StackPane();
        rootPane.setPrefSize(Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        double paddingLeftRight = Settings.getState().getResolution().getScreenWidth() / 4 - Settings.getState().getResolution().getScreenWidth() / 6;
        double paddingTopBot = 40;
        gridPane.setPadding(new Insets(paddingTopBot, paddingLeftRight, paddingTopBot, paddingLeftRight));

        TextField enterQuestionHint = new TextField();
        enterQuestionHint.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);
        enterQuestionHint.setStyle(TEXT_FIELD_STYLE);
        enterQuestionHint.setText("Enter question:");
        enterQuestionHint.setEditable(false);
        enterQuestionHint.setFocusTraversable(false);

        TextField questionResult = new TextField();
        questionResult.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);
        questionResult.setStyle(TEXT_FIELD_STYLE);
        questionResult.setText("");
        questionResult.setEditable(true);
        questionResult.setFocusTraversable(false);

        TextField enterSectionHint = new TextField();
        enterSectionHint.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);
        enterSectionHint.setStyle(TEXT_FIELD_STYLE);
        enterSectionHint.setText("Enter answer:");
        enterSectionHint.setEditable(false);
        enterSectionHint.setFocusTraversable(false);

        TextArea answerResult = new TextArea();
        answerResult.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);
        answerResult.setStyle(TEXT_AREA_STYLE);
        answerResult.setText(editableText);
        answerResult.setEditable(true);
        answerResult.setFocusTraversable(false);
        answerResult.setPrefRowCount(40);

        List<ImageContent> imageContents = new ArrayList<>();

        Button enterSection = new Button("Create new question");
        enterSection.setPadding(new Insets(0,0,0,40));
        enterSection.setOnAction(new ApplyNewQuestionHadler(questionResult, answerResult, questionId, sectionId, imageContents));
        enterSection.setMinHeight(30);
        enterSection.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);

        Button insertImageFromClipBoard = new Button("Insert image rom clipboard");
        insertImageFromClipBoard.setPadding(new Insets(0,0,0,40));
        insertImageFromClipBoard.setOnAction(new InsertImageFromClipBoardHandler(answerResult,imageContents));
        insertImageFromClipBoard.setMinHeight(30);
        insertImageFromClipBoard.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);

        gridPane.setStyle(FORM_STYLE);

        gridPane.add(enterQuestionHint, 0, 0, 1, 1);
        gridPane.add(questionResult, 0, 1, 1, 1);

        gridPane.add(enterSectionHint, 0, 2, 1, 1);
        gridPane.add(answerResult, 0, 3, 1, 1);
        gridPane.add(enterSection, 0, 4, 1, 1);
        gridPane.add(insertImageFromClipBoard, 0, 5, 1, 1);
        gridPane.setMinHeight(Settings.getState().getResolution().getScreenHeight() / 2);
        gridPane.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 2);

        rootPane.getChildren().add(gridPane);
        StackPane.setAlignment(gridPane, Pos.CENTER);

        Scene scene = new Scene(rootPane, Settings.getState().getResolution().getScreenWidth() / 2, Settings.getState().getResolution().getScreenHeight());
        return scene;
    }
}
