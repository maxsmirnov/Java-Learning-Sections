package org.learn.datatranslation.entities;

public class ImageContent {

    private String data;
    private String contentId;

    public ImageContent() {
    }

    public ImageContent(String data, String contentId) {
        this.data = data;
        this.contentId = contentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
