package org.learn.forms;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import org.learn.controller.LearningController;
import org.learn.datatranslation.DataConverter;
import org.learn.datatranslation.FileManager;
import org.learn.datatranslation.entities.LearningData;
import org.learn.datatranslation.entities.Section;
import org.learn.entities.ButtonLocation;
import org.learn.handlers.NewSectionFormHandler;
import org.learn.handlers.QuestionHandler;
import org.learn.tools.Settings;
import org.learn.utils.LocationBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        Long topId = 0L;
        for (Section section : data.getSections()) {
            if (section.getId() > topId) {
                topId = section.getId();
            }
        }
        LearningController.getState().setTopLevelId(topId);
        System.out.println("Section name " + data.getSections().get(0).getName());

        LocationBox locationBox = new LocationBox();

        for (Section section : data.getSections()) {
            locationBox.put();

            HBox hBox = new HBox();
            hBox.setSpacing(5);
            hBox.setStyle(FORM_STYLE);

            Button addSectionButton = new Button();
            addSectionButton.setMinWidth(SECTION_BUTTON_WIDTH);
            addSectionButton.setMinHeight(SECTION_BUTTON_HEIGHT);
            addSectionButton.setText(section.getName());
            addSectionButton.setOnAction(new QuestionHandler(section));

            ImageView battery = new ImageView(FileManager.getState().loadImage(getImageName(section.getLearnDate())));
            battery.setFitHeight(30);
            battery.setFitWidth(25);

            if (locationBox.getCurrentLock().getColumnIndex() == 0) {

                hBox.getChildren().addAll(addSectionButton, battery);
            } else {
                hBox.getChildren().addAll(battery, addSectionButton);

            }

            gridPane.add(hBox,
                    locationBox.getCurrentLock().getColumnIndex(),
                    locationBox.getCurrentLock().getRowIndex(),
                    1, 1);
        }

        Button newSectionButton = new Button("Add new section");
        newSectionButton.setOnAction(new NewSectionFormHandler());

        //TODO нужно вычислять maxColumn / 2 и т.д. Кнопка в центре экрана
        gridPane.add(newSectionButton, 4, 12);

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

    public static int findGroup(int count) {
        int result = 0;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        while (true) {
            result++;
            if (p2 < p1) {

            }
        }
    }

    public static String getImageName(String date) {
        String imageName = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date learningDate = simpleDateFormat.parse(date);

            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            if (check(calendar, learningDate)) {
                imageName = "battery_very_high.png";
            } else if (check(calendar, learningDate)) {
                imageName = "battery_high.png";
            } else if (check(calendar, learningDate)) {
                imageName = "battery_medium.png";
            } else if (check(calendar, learningDate)) {
                imageName = "battery_low.png";
            } else {
                imageName = "battery_very_low.png";
            }

        } catch (Exception exc) {
            System.out.println("error");
        }
        return imageName;
    }

    public static boolean check(Calendar calendar, Date learningDate) {
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        Date date = calendar.getTime();
        return learningDate.getTime() >= date.getTime();
    }

}
