package com.example.quickrepair;

import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Evaluation;
import com.example.quickrepair.domain.Repair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EvaluationUnitTest
{
    Evaluation evaluation;

    @Before
    public void setUp()
    {
        String title = "Title";
        String comment = "Comment";
        int rate = 5;
        evaluation = new Evaluation(new Repair(), title, comment, rate);
        Assert.assertEquals(title, evaluation.getTitle());
        Assert.assertEquals(comment, evaluation.getComment());
        Assert.assertEquals(rate, evaluation.getRate());
    }

    @Test(expected = NullPointerException.class)
    public void nullTitle()
    {
        Evaluation eval = new Evaluation(null, "12", 5);
    }

    @Test
    public void emptyTitle()
    {
        Evaluation eval = new Evaluation("", "12", 5);
        Assert.assertEquals("No title", eval.getTitle());
    }

    @Test
    public void okTitle()
    {
        Evaluation eval = new Evaluation("nn", "12", 5);
        Assert.assertEquals("nn", eval.getTitle());
    }

    //Comment tests
    @Test(expected = NullPointerException.class)
    public void nullComment()
    {
        Evaluation eval = new Evaluation("nn", null, 5);

    }

    @Test
    public void emptyComment()
    {
        Evaluation eval = new Evaluation("nn", "", 5);
        Assert.assertEquals("No comment", eval.getComment());
    }

    @Test
    public void okComment()
    {
        Evaluation eval = new Evaluation("nn", "Comment", 5);
        Assert.assertEquals("Comment", evaluation.getComment());
    }

    //rate tests
    @Test
    public void okRate()
    {
        Evaluation eval = new Evaluation("nn", "Comment", 5);
        Assert.assertEquals(5, evaluation.getRate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void biggerRate()
    {
        int rate = 6;
        Evaluation eval = new Evaluation("nn", "Comment", rate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void smallerRate()
    {
        int rate = 0;
        Evaluation eval = new Evaluation("nn", "Comment", rate);
    }

    @Test
    public void equals()
    {
        Evaluation e1 = new Evaluation("1", "1", 5);
        Evaluation e2 = new Evaluation("1", "1", 5);
        Assert.assertEquals(e1, e2);
        Evaluation e3 = new Evaluation("1", "12", 5);
        Assert.assertNotEquals(e1, e3);
        Address a = new Address("ss", "5");
        Assert.assertNotEquals(a, e1);
    }

}
