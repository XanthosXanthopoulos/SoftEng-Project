package com.example.quickrepair;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RepairUnitTest {

    //Constructor Tests
    @Test
    public void constructorWithQuantity(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        JobType jobType = new JobType();
        jobType.setMeasurementUnit(MeasurementUnit.NONE);
        job.setJobType(jobType);
        repairRequest.setJob(job);
        repairRequest.confirm(5);

        double quantity = 1;
        Repair repair = new Repair(repairRequest,quantity);

        Assert.assertEquals(repairRequest,repair.getRepairRequest());
        Assert.assertEquals(quantity, repair.getQuantity(),.0);
        Assert.assertEquals(null, repair.getPayment());
        Assert.assertEquals(null, repair.getEvaluation());
    }
    @Test
    public void constructorWithQuantityAndPayment(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        JobType jobType = new JobType();
        jobType.setMeasurementUnit(MeasurementUnit.NONE);
        job.setJobType(jobType);
        repairRequest.setJob(job);
        repairRequest.confirm(5);

        double quantity = 1;
        Payment payment = new Payment();
        Repair repair = new Repair(repairRequest, quantity, payment);

        Assert.assertEquals(repairRequest,repair.getRepairRequest());
        Assert.assertEquals(quantity, repair.getQuantity(), .0);
        Assert.assertEquals(payment, repair.getPayment());
        Assert.assertEquals(null, repair.getEvaluation());
    }

    //repairRequest Tests
    @Test (expected = NullPointerException.class)
    public void nullRepairRequest(){
        Repair repair = new Repair();
        repair.setRepairRequest(null);
    }
    @Test
    public void okRepairRequest(){
        RepairRequest repairRequest = new RepairRequest();
        Repair repair = new Repair();
        repair.setRepairRequest(repairRequest);
        Assert.assertEquals(repairRequest,repair.getRepairRequest());
    }

    //Quantity Tests
    @Test (expected = IllegalArgumentException.class)
    public void repairWithZeroQuantity(){
        double quantity = 0;
        Repair repair = new Repair();
        repair.setQuantity(quantity);
    }

    @Test (expected = IllegalArgumentException.class)
    public void repairWithNegativeQuantity(){
        double quantity = -1;
        Repair repair = new Repair();
        repair.setQuantity(quantity);
    }

    @Test (expected =  IllegalStateException.class)
    public void repairWithNullRepairRequest(){
        double quantity = 1;
        Repair repair = new Repair();
        repair.setQuantity(quantity);
    }

    @Test (expected =  IllegalStateException.class)
    public void repairWithNullJob(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        double quantity = 1;
        Repair repair = new Repair();
        repair.setRepairRequest(repairRequest);
        repair.setQuantity(quantity);
    }

    @Test (expected =  IllegalStateException.class)
    public void repairWithNullJobType(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        repairRequest.setJob(job);

        double quantity = 1;
        Repair repair = new Repair();
        repair.setQuantity(quantity);
    }

    @Test
    public void repairWithOkQuantityAtFixedPriceJob(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        JobType jobType = new JobType();
        //create jobType of a ConsistentJob
        //a consistent job, have NONE MeasurementUnit
        jobType.setMeasurementUnit(MeasurementUnit.NONE);
        job.setJobType(jobType);
        repairRequest.setJob(job);
        repairRequest.confirm(5);

        double quantity = 1;
        Repair repair = new Repair(repairRequest,quantity);

        repair.setQuantity(quantity);
        Assert.assertEquals(quantity, repair.getQuantity(), .0);
    }

    @Test (expected =  IllegalArgumentException.class)
    public void repairWithNonOkQuantityAtFixedPriceJob(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        JobType jobType = new JobType();
        //create jobType of a ConsistentJob
        //a consistent job, have NONE MeasurementUnit
        jobType.setMeasurementUnit(MeasurementUnit.NONE);
        job.setJobType(jobType);
        repairRequest.setJob(job);
        repairRequest.confirm(5);

        //set wrong quantity
        double quantity = 1.1;
        Repair repair = new Repair(repairRequest,quantity);

        repair.setQuantity(quantity);
    }

    @Test
    public void repairWithDoubleOkQuantityAtNonFixedPriceJob(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        JobType jobType = new JobType();
        //create jobType of a ConsistentJob
        //a consistent job, have NONE MeasurementUnit
        jobType.setMeasurementUnit(MeasurementUnit.METER);
        job.setJobType(jobType);
        repairRequest.setJob(job);
        repairRequest.confirm(5);

        double quantity = 1.1;
        Repair repair = new Repair(repairRequest,quantity);

        repair.setQuantity(quantity);
        Assert.assertEquals(quantity, repair.getQuantity(), .0);

    }
    @Test
    public void repairWithIntegerOkQuantityAtNonFixedPriceJob(){
        //create repair with job
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        JobType jobType = new JobType();
        //create jobType of a ConsistentJob
        //a consistent job, have NONE MeasurementUnit
        jobType.setMeasurementUnit(MeasurementUnit.METER);
        job.setJobType(jobType);
        repairRequest.setJob(job);
        repairRequest.confirm(5);

        double quantity = 1;
        Repair repair = new Repair(repairRequest,quantity);

        repair.setQuantity(quantity);
        Assert.assertEquals(quantity, repair.getQuantity(), .0);

    }

    //Payment Tests
    @Test (expected = NullPointerException.class)
    public void addNullPayment(){
        Repair repair = new Repair();
        repair.setPayment(null);
    }

    @Test
    public void addPaymentFirstTime(){
        Repair repair = new Repair();
        Payment payment = new Payment();
        repair.setPayment(payment);
        Assert.assertEquals(payment, repair.getPayment());
    }

    @Test (expected = IllegalStateException.class)
    public void addPaymentSecondTime(){
        Repair repair = new Repair();
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        repair.setPayment(payment1);
        repair.setPayment(payment2);
    }

    //Evaluation Tests
    @Test (expected = NullPointerException.class)
    public void addNullEvaluation(){
        Repair repair = new Repair();
        repair.setEvaluation(null);
    }
    @Test
    public void addEvaluationFirstTime(){
        Repair repair = new Repair();
        Evaluation evaluation = new Evaluation();
        repair.setEvaluation(evaluation);
        Assert.assertEquals(evaluation, repair.getEvaluation());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addEvaluationSecondTime(){
        Repair repair = new Repair();
        Evaluation evaluation1 = new Evaluation();
        Evaluation evaluation2 = new Evaluation();
        repair.setEvaluation(evaluation1);
        repair.setEvaluation(evaluation2);
    }

    //evaluate test
    @Test
    public void evaluateTest(){
        Repair repair = new Repair();
        String title = "Title";
        String com = "Comment";
        int rate = 1;
        repair.evaluate(title, com, rate);
        Evaluation evaluation= new Evaluation(title, com, rate);
        Assert.assertEquals(evaluation, repair.getEvaluation());
    }


    //isPaid
    @Test
    public void isPaidTestTrue(){
        Repair repair = new Repair();
        Calendar date = new GregorianCalendar(2019,12,12);
        PaymentType paymentType = PaymentType.CARD;
        Payment payment = new Payment(date, paymentType);
        repair.setPayment(payment);
        Assert.assertEquals(true, repair.isPaid());
    }
    //isPaid
    @Test
    public void isPaidTestFalse(){
        Repair repair = new Repair();
        Assert.assertEquals(false, repair.isPaid());
    }

    @Test
    public void payWithCARD(){
        Repair repair = new Repair();
        repair.pay(new GregorianCalendar(2018,2,1,1,1), PaymentType.CASH);
        Assert.assertNotNull(repair.getPayment());
    }

    @Test (expected = IllegalStateException.class)
    public void payWithCashNullJob(){
        Repair repair = new Repair();
        RepairRequest repairRequest = new RepairRequest();
        repair.setRepairRequest(repairRequest);
        repair.pay(new GregorianCalendar(2018,2,1,1,1), PaymentType.CARD);
    }
    @Test (expected = IllegalStateException.class)
    public void payWithCashNullCustomer(){
        Repair repair = new Repair();
        RepairRequest repairRequest = new RepairRequest();
        Job job = new Job();
        job.setPrice(20);
        repairRequest.setJob(job);
        repair.setRepairRequest(repairRequest);
        repair.pay(new GregorianCalendar(2018,2,1,1,1), PaymentType.CARD);

    }
    @Test
    public void payWithCashOK(){
        Repair repair = new Repair();
        RepairRequest repairRequest = new RepairRequest();
        JobType jobType = new JobType();
        Job job = new Job();
        job.setJobType(jobType);
        job.setPrice(20);
        repairRequest.setJob(job);
        repairRequest.setCustomer(new Customer("xrisa","dkn","1111111111","jdjdj@aueb.gr","33","xrisa.d","1234"));
        repair.setRepairRequest(repairRequest);

        repair.setQuantity(1);

        repair.pay(new GregorianCalendar(2018,2,1,1,1), PaymentType.CARD);
        Assert.assertNotNull(repair.getPayment());
    }

}
