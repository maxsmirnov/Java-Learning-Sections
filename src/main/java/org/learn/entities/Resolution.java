package org.learn.entities;

public class Resolution {
    private Double screenHeight;
    private Double screenWidth;

    public Resolution() {
    }

    public Resolution(Double screenHeight, Double screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public Double getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Double screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Double getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Double screenWidth) {
        this.screenWidth = screenWidth;
    }
}
