package org.learn.forms;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import org.learn.datatranslation.entities.Question;
import org.learn.datatranslation.entities.Section;
import org.learn.handlers.BackToSectionsHandler;
import org.learn.tools.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.learn.tools.Settings.BACK_BUTTON;
import static org.learn.tools.Settings.FORM_STYLE;
import static org.learn.tools.Settings.SCROLL_STYLE;
import static org.learn.tools.Settings.TEXT_AREA_STYLE;

public class QuestionForm {

    public static Scene getScene(Section parentSection) {

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle(SCROLL_STYLE);

        GridPane questionPane = new GridPane();
        questionPane.setHgap(10);
        questionPane.setVgap(10);
        int i=1;
        for (Question question : parentSection.getQuestions()) {
            questionPane.add(buildElement(question), 0, i, 1, 1);
            i++;
        }

        scrollPane.setContent(questionPane);
        scrollPane.getContent().setStyle(FORM_STYLE);
        scrollPane.setPadding(new Insets(10));



        Scene scene = new Scene(scrollPane, Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight());

        Platform.runLater(scrollPane::requestFocus);

        return scene;
    }

    private static GridPane buildElement (Question question) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button backToSections = new Button();
        backToSections.setOnAction(new BackToSectionsHandler());
        backToSections.setText(BACK_BUTTON);

        TextField textField = new TextField();
        textField.setMinWidth(Settings.getState().getQuestionWidth());
        textField.setText(question.getQuestion());
        textField.setStyle(FORM_STYLE);
        textField.setEditable(false);

        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(1);
        textArea.setPrefRowCount(1);
        textArea.setMinWidth(Settings.getState().getQuestionWidth());


        textArea.setText(question.getAnswer());
        textArea.setStyle(TEXT_AREA_STYLE);
        textArea.setWrapText(true);
        textArea.setEditable(false);

        gridPane.add(textField, 0, 0, 1, 1);
        gridPane.add(textArea, 0, 1, 1, 1);
        gridPane.add(backToSections, 1, 1, 1, 1);
        gridPane.setStyle(FORM_STYLE);

        return gridPane;
    }
}
