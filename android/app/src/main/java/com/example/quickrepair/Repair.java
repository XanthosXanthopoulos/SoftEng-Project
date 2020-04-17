package com.example.quickrepair;

public class Repair {
    private double quantity;
    private Payment payment;
    private Evaluation evaluation;

    //constructors
    /**
     * Empty Constructor
     */
    public  Repair(){}

    /**
     * Repair's constructor with quantity
     * technician is going to be paid later
     * @param quantity job's quantity, this parameter is going to define the final cost, the type of the job define the meaning of this parameter
     */
    public Repair(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Repair's constructor with quantity and payment
     * technician is already paid
     * @param quantity job's quantity, this parameter is going to define the final cost, the type of the job define the meaning of this parameter
     * @param payment customer's payment
     */
    public Repair(double quantity, Payment payment) {
        this.quantity = quantity;
        this.payment = payment;
    }

    //Setters and Getters
    //return repair's quantity
    public double getQuantity() {
        return quantity;
    }

    //change repair's quantity
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    //return repair's payment
    public Payment getPayment() {
        return payment;
    }

    //create repair's payment
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    //return repair's evaluation
    public Evaluation getEvaluation() {
        return evaluation;
    }

    //create repair's evaluation
    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
