package com.example.quickrepair.domain;

/**
 * Customer's Evaluation about an Repair
 */
public class Evaluation
{
    private Integer uid;
    private String title;
    private String comment;
    private int rate;

    /**
     * Empty Constructor
     */
    public Evaluation() { }

    /**
     * Evaluation's Constructor
     *
     * @param title   evaluation's title
     * @param comment evaluation's comments and details about repair and technician's job in general
     * @param rate    evaluation's rate, [1, 5]
     */
    public Evaluation(String title, String comment, int rate)
    {
        setTitle(title);
        setComment(comment);
        setRate(rate);
    }

    //Setters and Getters

    /**
     * Get the title of the evaluation.
     *
     * @return The evaluation's title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Set evaluation's title
     *
     * @param title evaluation's title
     */
    private void setTitle(String title)
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
    private void setComment(String comment)
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
     * Get the rating of the evaluation.
     *
     * @return evaluation rate
     */
    public int getRate()
    {
        return rate;
    }

    /**
     * Set evaluation's rate.
     *
     * @param rate evaluation's rate, [1, 5]
     */
    private void setRate(int rate)
    {
        if (rate < 1 || rate > 5) throw new IllegalArgumentException("[1,5] stars");

        this.rate = rate;
    }

    /**
     * Get the evaluation UID.
     *
     * @return The UID.
     */
    public Integer getUid()
    {
        return uid;
    }

    /**
     * Set the evaluation UID.
     *
     * @param uid The evaluation's UID.
     */
    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation that = (Evaluation) o;
        return rate == that.rate && title.equals(that.title) && comment.equals(that.comment);
    }

    @Override
    public int hashCode()
    {
        return (title + comment + rate).hashCode();
    }
}
