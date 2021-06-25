package org.learn.datatranslation.entities;

import java.util.List;

public class LearningData {
    private List<Section> sections;

    public LearningData() {
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
