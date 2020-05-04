package com.example.quickrepair;

import com.example.quickrepair.domain.Address;
import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Evaluation;
import com.example.quickrepair.domain.Job;
import com.example.quickrepair.domain.JobType;
import com.example.quickrepair.domain.MeasurementUnit;
import com.example.quickrepair.domain.Payment;
import com.example.quickrepair.domain.PaymentType;
import com.example.quickrepair.domain.Repair;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.domain.Specialty;
import com.example.quickrepair.domain.Technician;
import com.example.quickrepair.util.Utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class RepairUnitTest {
    Technician technicianToTest;
    Address exampleAddress;
    Job exampleJob;
    JobType exampleJobType;
    Specialty exampleSpecialty;
    Integer[][] exampleSchedule;
    RepairRequest repairRequest;
    RepairRequest repairRequest2;
    Customer exampleCustomer;
    RepairRequest exampleRepairRequest;


    int monday = 1;//MONDAY
    int april = GregorianCalendar.APRIL;//APRIL
    GregorianCalendar april6 = new GregorianCalendar(2020, april,6);
    GregorianCalendar start = new GregorianCalendar(2020, april, 6, 6, 0);
    GregorianCalendar end = new GregorianCalendar(2020, april, 6, 19, 0);

    GregorianCalendar april1010 = new GregorianCalendar(2020, Calendar.APRIL,10,10,10);
    GregorianCalendar newDateApril1010 = Utilities.getYearMonthDay(april1010);


    @Before
    public void setUpTests() {
        exampleCustomer = new Customer("nikos" , "surname" , "6958674323",
                "nikos.1238@live.com" , "123123" , "username" , "drowssap");
        technicianToTest = new Technician("nikos", "sm" , "6958475635",
                "asdih@ausdh.asdh" , "mybankaccount" , "nikos" ,
                "123" , new Specialty("test"), "128947");
        Address address = new Address("ath" , "15");
        exampleSpecialty = new Specialty("Electrician");
        exampleJobType = new JobType("Allagi plakakia" , exampleSpecialty , MeasurementUnit.METER);
        exampleAddress = address;
        exampleJob = new Job(technicianToTest , exampleJobType , 15);

        //Initializing schedule
        exampleSchedule = new Integer[7][2];
        for (int i = 0; i < 7 ; i ++){
            //example Technician works from 9
            exampleSchedule[i][0] = 9;
            //example Technician works until 5  (17 : 00)
            exampleSchedule[i][1] = 17;
        }
        technicianToTest.setSchedule(exampleSchedule);
        repairRequest = new RepairRequest();
        repairRequest2 = new RepairRequest();
        exampleRepairRequest = new RepairRequest(exampleCustomer , exampleJob ,
                (GregorianCalendar) Calendar.getInstance()  , new GregorianCalendar(2020 , 5  , 3) , exampleAddress,"comments");

    }
    //Constructor Tests
    @Test
    public void constructorWithQuantity(){
        //create repair with job
        RepairRequest repairRequest = exampleRepairRequest;
        repairRequest.setJob(exampleJob);
        repairRequest.setCustomer(exampleCustomer);
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
        RepairRequest repairRequest = new RepairRequest(exampleCustomer
                , exampleJob , (GregorianCalendar) Calendar.getInstance(),
                (GregorianCalendar) Calendar.getInstance() , exampleAddress ,"comment");
        Job job = exampleJob;
        JobType jobType = exampleJobType;
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
        RepairRequest repairRequest = exampleRepairRequest;
        Job job = exampleJob;
        JobType jobType = exampleJobType;
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
        RepairRequest repairRequest = exampleRepairRequest;
        Job job = exampleJob;
        JobType jobType = exampleJobType;
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
        RepairRequest repairRequest = exampleRepairRequest;
        repairRequest.setCustomer(exampleCustomer);
        Job job = exampleJob;
        JobType jobType = exampleJob.getJobType();
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
        RepairRequest repairRequest = exampleRepairRequest;
        Job job = exampleJob;
        JobType jobType = exampleJobType;
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
        Evaluation evaluation= new Evaluation(repair, title, com, rate);
        Assert.assertEquals(evaluation, repair.getEvaluation());
    }


    //isPaid
    @Test
    public void isPaidTestTrue(){
        Repair repair = new Repair();
        Calendar date = new GregorianCalendar(2019,12,12);
        PaymentType paymentType = PaymentType.CARD;
        Payment payment = new Payment(repair, date, paymentType);
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
    public void payWithCARD() {
        exampleRepairRequest.confirm(1000);
        Repair r = exampleRepairRequest.complete(50);
        Payment payment = r.pay(new GregorianCalendar(2018, 2, 1, 1, 1), PaymentType.CARD);
        Assert.assertTrue(payment.getCost() == (r.getQuantity() * r.getRepairRequest().getJob().getPrice()));
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
    @Test
    public void uid(){
        Repair repair = new Repair();
        repair.setUid(10);
        Assert.assertEquals(10, repair.getUid());
    }

    @Test
    public void testHashCode(){
        Repair repair = new Repair(exampleRepairRequest, 10, new Payment());
        Assert.assertEquals(Objects.hash(repair.getQuantity(), repair.getRepairRequest()), repair.hashCode());
    }

}
