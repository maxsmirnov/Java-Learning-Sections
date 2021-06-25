package org.learn.forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.learn.datatranslation.DataConverter;
import org.learn.datatranslation.FileManager;
import org.learn.datatranslation.entities.LearningData;
import org.learn.datatranslation.entities.Section;
import org.learn.entities.ButtonLocation;
import org.learn.handlers.QuestionHandler;
import org.learn.tools.Settings;
import org.learn.utils.LocationBox;

import static org.learn.tools.Settings.FORM_STYLE;
import static org.learn.tools.Settings.SECTION_BUTTON_HEIGHT;
import static org.learn.tools.Settings.SECTION_BUTTON_WIDTH;

public class SectionForm {

    public static Scene getScene() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle(FORM_STYLE);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 1; i < 9; i++) {
            ColumnConstraints column = new ColumnConstraints(SECTION_BUTTON_WIDTH);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 1; i < 25; i++) {
            RowConstraints row = new RowConstraints(SECTION_BUTTON_HEIGHT);
            gridPane.getRowConstraints().add(row);
        }
        gridPane.setPadding(new Insets(20));

        System.out.println(FileManager.getState().loadData());
        LearningData data = DataConverter.getState().getLearningData();
        System.out.println("Section name " + data.getSections().get(0).getName());

        LocationBox locationBox = new LocationBox();

        for (Section section : data.getSections()) {
            locationBox.put();
            Button addSectionButton = new Button();
            addSectionButton.setMinWidth(SECTION_BUTTON_WIDTH);
            addSectionButton.setMinHeight(SECTION_BUTTON_HEIGHT);

            addSectionButton.setText(section.getName());

            addSectionButton.setOnAction(new QuestionHandler(section));

            gridPane.add(addSectionButton,
                    locationBox.getCurrentLock().getColumnIndex(),
                    locationBox.getCurrentLock().getRowIndex(),
                    1, 1);
        }

        Scene scene = new Scene(gridPane, Settings.getState().getResolution().getScreenWidth(), Settings.getState().getResolution().getScreenHeight());
        return scene;
    }

//   5 200 10 200 10 200 10 200 10 200 10 200 10 200 10 200 10 200 5  - 9 штук

    private static ButtonLocation detectLocation(int count) {
        //TODO: нужно сделать адаптивным к разрешению
        int maxColumn = 9;
        int maxRow = 24;
        boolean isNotExist = true;
        ButtonLocation result = new ButtonLocation();

        if (count % 4 == 0) {
            result.setColumnIndex(maxColumn);
            result.setRowIndex(maxRow - (int) (count / 4));
            isNotExist = false;
        } else if (isNotExist && (count % 3 == 0)) {
            result.setColumnIndex(0);
            result.setRowIndex(maxRow - (int) (count / 3));
            isNotExist = false;
        } else if (isNotExist && (count % 2 == 0)) {
            result.setColumnIndex(maxColumn);
            result.setRowIndex(maxRow + (int) (count / 2));
            isNotExist = false;
        } else if (isNotExist) {

        }

        return null;
    }

    public static int findGroup (int count) {
        int result = 0;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        while(true) {
            result ++;
            if (p2 < p1) {

            }
        }
    }

}
