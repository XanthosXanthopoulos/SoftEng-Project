package com.example.quickrepair;

/**
 * Customer's Evaluation about an Repair
 */
public class Evaluation {
    private String title;
    private String comment;
    private int rate;

    /**
     * Empty Constructor
     */
    public Evaluation(){}

    /**
     * Evaluation's Constructor
     * @param title evaluation's title
     * @param comment evaluation's comments and details about repair and technician's job in general
     * @param rate evaluation's rate, [1, 5]
     */
    public Evaluation(String title, String comment, int rate) {
        this.title = title;
        this.comment = comment;
        this.rate = rate;
    }

    //Setters and Getters
    /**
     * return evaluation's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * return evaluation's comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * return evaluation's rate
     */
    public int getRate() {
        return rate;
    }

}
