package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EvaluationUnitTest {
    Evaluation evaluation;
    @Before
    public void setUp(){
        String title = "Title";
        String comment = "Comment";
        int rate = 5;
        evaluation = new Evaluation(title, comment, rate);
        Assert.assertEquals(title, evaluation.getTitle());
        Assert.assertEquals(comment, evaluation.getComment());
        Assert.assertEquals(rate, evaluation.getRate());
    }

    //Title tests
    @Test (expected = NullPointerException.class)
    public void nullTitle(){
        evaluation.setTitle(null);
    }

    @Test
    public void emptyTitle(){
        String title = "";
        evaluation.setTitle(title);
        Assert.assertEquals("No title", evaluation.getTitle());
    }
    @Test
    public void okTitle(){
        String title = "Title";
        evaluation.setTitle(title);
        Assert.assertEquals(title, evaluation.getTitle());
    }

    //Comment tests
    @Test (expected = NullPointerException.class)
    public void nullComment(){
        evaluation.setComment(null);

    }

    @Test
    public void emptyComment(){
        String comment = "";
        evaluation.setComment(comment);
        Assert.assertEquals("No comment", evaluation.getComment());
    }

    @Test
    public void okComment(){
        String comment = "Comment";
        evaluation.setComment(comment);
        Assert.assertEquals(comment, evaluation.getComment());
    }

    //rate tests
    @Test
    public void okRate(){
        int rate = 5;
        evaluation.setRate(rate);
        Assert.assertEquals(rate, evaluation.getRate());
    }
    @Test (expected = IllegalArgumentException.class)
    public void biggerRate(){
        int rate = 6;
        evaluation.setRate(rate);
    }

    @Test (expected = IllegalArgumentException.class)
    public void smallerRate(){
        int rate = 0;
        evaluation.setRate(rate);
    }

    @Test
    public void equals(){
        evaluation.equals(null);
    }

}
