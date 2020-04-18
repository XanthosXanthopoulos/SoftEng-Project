package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Test;

public class EvaluationUnitTest {
    @Test
    public void constructorTest(){
        String title = "Title";
        String comment = "Comment";
        int rate = 5;
        Evaluation evaluation = new Evaluation(title, comment, rate);
        Assert.assertEquals(title, evaluation.getTitle());
        Assert.assertEquals(comment, evaluation.getComment());
        Assert.assertEquals(rate, evaluation.getRate());
    }

    //Title tests
    @Test (expected = NullPointerException.class)
    public void nullTitle(){
        Evaluation evaluation = new Evaluation();
        evaluation.setTitle(null);
    }

    @Test
    public void emptyTitle(){
        String title = "";
        Evaluation evaluation = new Evaluation();
        evaluation.setTitle(title);
        Assert.assertEquals("No title", evaluation.getTitle());
    }
    @Test
    public void okTitle(){
        String title = "Title";
        Evaluation evaluation = new Evaluation();
        evaluation.setTitle(title);
        Assert.assertEquals(title, evaluation.getTitle());
    }

    //Comment tests
    @Test (expected = NullPointerException.class)
    public void nullComment(){
        Evaluation evaluation = new Evaluation();
        evaluation.setComment(null);

    }

    @Test
    public void emptyComment(){
        String comment = "";
        Evaluation evaluation = new Evaluation();
        evaluation.setComment(comment);
        Assert.assertEquals("No comment", evaluation.getComment());
    }

    @Test
    public void okComment(){
        String comment = "Comment";
        Evaluation evaluation = new Evaluation();
        evaluation.setComment(comment);
        Assert.assertEquals(comment, evaluation.getComment());
    }

    //rate tests
    @Test
    public void okRate(){
        int rate = 5;
        Evaluation evaluation = new Evaluation();
        evaluation.setRate(rate);
        Assert.assertEquals(rate, evaluation.getRate());
    }
    @Test (expected = IllegalArgumentException.class)
    public void biggerRate(){
        int rate = 6;
        Evaluation evaluation = new Evaluation();
        evaluation.setRate(rate);
    }

    @Test (expected = IllegalArgumentException.class)
    public void smallerRate(){
        int rate = 0;
        Evaluation evaluation = new Evaluation();
        evaluation.setRate(rate);
    }

}
