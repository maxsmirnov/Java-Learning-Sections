package org.learn.tools;

import org.learn.entities.Resolution;

import java.awt.*;

public class Settings {
    private static Settings state;
    private Dimension screenSize;
    public static final String DATA_PATH = "data";
    public static final String BACK_BUTTON = "Return to section";
    public static final String FILE_DATA_NAME = "java-learning.json";
    public static final String FILE_DELIMITER = "/";
    public static final String FORM_STYLE = "-fx-background-color: rgba(46, 49, 49, 1); -fx-text-fill: white; -fx-font-size: 16px;";
    public static final String QUESTION_STYLE = "-fx-background-color: rgba(46, 49, 49, 1); -fx-text-fill: yellow; -fx-font-size: 16px;";
    public static final String LABEL_STYLE = "-fx-background-color: rgba(44, 47, 45, 1); -fx-text-fill: green; -fx-font-size: 16px;";
    public static final String TEXT_AREA_STYLE = "fx-background: blue; -fx-focus-color: transparent; -fx-control-inner-background: rgba(46, 49, 49, 1); -fx-text-fill: white; -fx-font-size: 16px;";
    public static final String TEXT_FIELD_STYLE = "fx-background: blue; -fx-focus-color: transparent; -fx-control-inner-background: rgba(46, 49, 49, 1); -fx-text-fill: white; -fx-font-size: 16px;";
    public static final String SCROLL_STYLE = "-fx-background: rgba(46, 49, 49, 1); -fx-text-fill: white; -fx-font-size: 16px;";
    public static final double SECTION_BUTTON_WIDTH = 200d;
    public static final double SECTION_BUTTON_HEIGHT = 30d;

    public static Settings getState() {
        if (state == null) {
            state = new Settings();
        }
        return state;
    }

    public Settings() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public Resolution getResolution() {
        return new Resolution(screenSize.getHeight(), screenSize.getWidth());
    }

    public Integer getQuestionWidth() {
        Integer width = (int) (screenSize.getWidth() * 0.8);
        return width;
    }
}
