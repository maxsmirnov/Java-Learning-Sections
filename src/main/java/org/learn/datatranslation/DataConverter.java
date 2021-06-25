package org.learn.datatranslation;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.learn.datatranslation.entities.LearningData;

public class DataConverter {
    private static DataConverter state;
    private ObjectMapper objectMapper;

    public static DataConverter getState() {
        if (state == null) {
            state = new DataConverter();
        }
        return state;
    }

    public DataConverter() {
        objectMapper = new ObjectMapper();
    }

    public LearningData getLearningData() {
        LearningData data = new LearningData();
        StringBuilder jsonData = FileManager.getState().loadData();
        try {
            data = objectMapper.readValue(jsonData.toString(), LearningData.class);
        } catch (Exception exc) {
            System.out.println("need logger");
        }
        return data;
    }
}
