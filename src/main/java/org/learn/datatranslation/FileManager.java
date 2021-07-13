package org.learn.datatranslation;


import javafx.scene.image.Image;
import org.learn.tools.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileManager {
    private static FileManager state;
    private String mainDirectory;

    public static FileManager getState() {
        if (state == null) {
            state = new FileManager();
        }
        return state;
    }

    public FileManager() {
        mainDirectory = new File("").getAbsolutePath();
    }

    public StringBuilder loadData() {
        StringBuilder filePath = new StringBuilder();
        filePath.append(mainDirectory)
                .append(Settings.FILE_DELIMITER)
                .append(Settings.DATA_PATH)
                .append(Settings.FILE_DELIMITER)
                .append(Settings.FILE_DATA_NAME);
        System.out.println(filePath.toString());
        StringBuilder data = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath.toString()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> data.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Image loadImage(String name) {
        StringBuilder filePath = new StringBuilder();
        filePath.append(mainDirectory)
                .append(Settings.FILE_DELIMITER)
                .append(Settings.DATA_PATH)
                .append(Settings.FILE_DELIMITER)
                .append(name);
        System.out.println(filePath.toString());

        File file = new File(filePath.toString());
        return new Image(file.toURI().toString());
    }

    public void saveData(String data) {
        StringBuilder filePath = new StringBuilder();
        filePath.append(mainDirectory)
                .append(Settings.FILE_DELIMITER)
                .append(Settings.DATA_PATH)
                .append(Settings.FILE_DELIMITER)
                .append(Settings.FILE_DATA_NAME);
        System.out.println(filePath.toString());
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath.toString());
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception exc) {
            System.out.println("error " + exc);
        }
    }
}
