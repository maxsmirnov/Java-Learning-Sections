package org.learn.datatranslation.entities;

import java.util.List;

public class Section {
    private Long id;
    private String name;
    private String learnDate;
    private List<Question> questions;

    public Section() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLearnDate() {
        return learnDate;
    }

    public void setLearnDate(String learnDate) {
        this.learnDate = learnDate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
