package org.learn.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.learn.handlers.ApplyEditableAnswerHandler;
import org.learn.tools.Settings;

import static org.learn.tools.Settings.FORM_STYLE;
import static org.learn.tools.Settings.TEXT_AREA_STYLE;
import static org.learn.tools.Settings.TEXT_FIELD_STYLE;

public class QuestionEditForm {
    public static Scene getScene(String editableText, Long questionId, Long sectionId) {

        StackPane rootPane = new StackPane();
        rootPane.setPrefSize(Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        double paddingLeftRight = Settings.getState().getResolution().getScreenWidth() / 4 - Settings.getState().getResolution().getScreenWidth() / 6;
        double paddingTopBot = Settings.getState().getResolution().getScreenHeight() / 4 - Settings.getState().getResolution().getScreenHeight() / 13.5;
        gridPane.setPadding(new Insets(paddingTopBot, paddingLeftRight, paddingTopBot, paddingLeftRight));

        TextField enterSectionHint = new TextField();
        enterSectionHint.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);
        enterSectionHint.setStyle(TEXT_FIELD_STYLE);
        enterSectionHint.setText("Enter new answer:");
        enterSectionHint.setEditable(false);
        enterSectionHint.setFocusTraversable(false);

        TextArea inputSectionNameField = new TextArea();
        inputSectionNameField.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);
        inputSectionNameField.setStyle(TEXT_AREA_STYLE);
        inputSectionNameField.setText(editableText);
        inputSectionNameField.setEditable(true);
        inputSectionNameField.setFocusTraversable(false);
        inputSectionNameField.setPrefRowCount(10);

        Button enterSection = new Button("Update question");
        enterSection.setPadding(new Insets(0,0,0,40));
        enterSection.setOnAction(new ApplyEditableAnswerHandler(inputSectionNameField, questionId, sectionId));
        enterSection.setMinHeight(30);
        enterSection.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 3);

        gridPane.setStyle(FORM_STYLE);
        gridPane.add(enterSectionHint, 0, 0, 1, 1);
        gridPane.add(inputSectionNameField, 0, 1, 1, 1);
        gridPane.add(enterSection, 0, 2, 1, 1);
        gridPane.setMinHeight(Settings.getState().getResolution().getScreenHeight() / 2);
        gridPane.setMinWidth(Settings.getState().getResolution().getScreenWidth() / 2);

        rootPane.getChildren().add(gridPane);
        StackPane.setAlignment(gridPane, Pos.CENTER);

        Scene scene = new Scene(rootPane, Settings.getState().getResolution().getScreenWidth() / 2, Settings.getState().getResolution().getScreenHeight() / 2);
        return scene;
    }
}
