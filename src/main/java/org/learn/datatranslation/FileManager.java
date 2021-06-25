package org.learn.datatranslation;


import org.learn.tools.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public void saveData() {

    }
}
