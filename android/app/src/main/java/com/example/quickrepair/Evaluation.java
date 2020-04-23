package com.example.quickrepair;

import androidx.annotation.Nullable;

/**
 * Customer's Evaluation about an Repair
 */
public class Evaluation
{
    private String title;
    private String comment;
    private int rate;

    /**
     * Empty Constructor
     */
    public Evaluation()
    {
    }

    /**
     * Evaluation's Constructor
     *
     * @param title   evaluation's title
     * @param comment evaluation's comments and details about repair and technician's job in general
     * @param rate    evaluation's rate, [1, 5]
     */
    public Evaluation(String title, String comment, int rate)
    {
        this.title = title;
        this.comment = comment;
        setRate(rate);
    }

    //Setters and Getters

    /**
     * return evaluation's title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * set evaluation's title
     *
     * @param title evaluation's title
     */
    public void setTitle(String title)
    {
        if (title == null) throw new NullPointerException("null title");

        if (title.length() > 0)
        {
            this.title = title;
        }
        else
        {
            this.title = "No title";
        }
    }

    /**
     * return evaluation's comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * set evaluation's comment
     *
     * @param comment evaluation's comment
     */
    public void setComment(String comment)
    {
        if (comment == null) throw new NullPointerException("null comment");

        if (comment.length() > 0)
        {
            this.comment = comment;
        }
        else
        {
            this.comment = "No comment";
        }
    }

    /**
     * return evaluation's rate
     */
    public int getRate()
    {
        return rate;
    }

    /**
     * set evaluation's rate
     *
     * @param rate evaluation's rate, [1, 5]
     */
    public void setRate(int rate)
    {
        if (rate < 1 || rate > 5) throw new IllegalArgumentException("[1,5] stars");

        this.rate = rate;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (o == this) { return true; }
        if (!(o instanceof Evaluation)) { return false; }
        // typecast
        Evaluation evaluation = (Evaluation) o;
        return evaluation.getTitle().equals(this.getTitle()) && evaluation.getComment().equals(this.getComment()) && evaluation.getRate() == this.getRate();
    }
}
