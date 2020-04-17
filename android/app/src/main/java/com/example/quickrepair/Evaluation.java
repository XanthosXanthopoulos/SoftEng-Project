package com.example.quickrepair;

public class Evaluation {
    private String title;
    private String details;
    private int rate;

    public Evaluation(String title, String details, int rate) {
        this.title = title;
        this.details = details;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
