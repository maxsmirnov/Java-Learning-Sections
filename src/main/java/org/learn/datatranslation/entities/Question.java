package org.learn.datatranslation.entities;

import java.util.List;

public class Question {

    private String question;
    private String answer;
    private Long id;
    private String lastReadDate;
    private int countOfRead;
    private List<ImageContent> imageContents;

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastReadDate() {
        return lastReadDate;
    }

    public void setLastReadDate(String lastReadDate) {
        this.lastReadDate = lastReadDate;
    }

    public int getCountOfRead() {
        return countOfRead;
    }

    public void setCountOfRead(int countOfRead) {
        this.countOfRead = countOfRead;
    }

    public List<ImageContent> getImageContents() {
        return imageContents;
    }

    public void setImageContents(List<ImageContent> imageContents) {
        this.imageContents = imageContents;
    }
}
