package com.example.quickrepair;

public class Repair
{
    private double quantity;
    private Payment payment;
    private Evaluation evaluation;
    private boolean isPaid;
    private RepairRequest repairRequest;

    //constructors
    /**
     * Empty Constructor
     */
    public  Repair()
    {
        isPaid = false;
    }

    /**
     * Repair's constructor with quantity
     * technician is going to be paid later
     * @param quantity job's quantity, this parameter is going to define the final cost, the type of the job define the meaning of this parameter
     */
    public Repair(double quantity)
    {
        setQuantity(quantity);
    }

    /**
     * Repair's constructor with quantity and payment
     * technician is already paid
     * @param quantity job's quantity, this parameter is going to define the final cost, the type of the job define the meaning of this parameter
     * @param payment customer's payment
     */
    public Repair(double quantity, Payment payment)
    {
        setQuantity(quantity);
        setPayment(payment);
    }

    /**
     * return repair's quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * set repair's quantity
     * only possitive and non zero quantity
     *
     * @param quantity customer's quantity
     */
    public void setQuantity(double quantity){
        if(quantity > 0){
            //TODO: #1 elegxos analoga me to eidos ergasias
            this.quantity = quantity;
        }else{
            throw new IllegalArgumentException("positive quantity");
        }
    }

    /**
     * return repair's payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * set repair's payment
     * @param payment customer's payment
     */
    public void setPayment(Payment payment)
    {
        if (payment == null) throw new NullPointerException("Payment can not be null.");
        if (this.payment != null) throw new IllegalStateException("The repair already has a payment.");

        this.payment = payment;
        isPaid = true;
    }

    //return repair's evaluation
    /**
     * return repair's evaluation
     */
    public Evaluation getEvaluation() {
        return evaluation;
    }

    /**
     * set repair's evaluation
     * @param evaluation customer's evaluation
     */
    public void setEvaluation(Evaluation evaluation) {
        if (evaluation != null)
        {
            //max 1 evaluation, can't reset it
            if (getEvaluation() == null)
            {
                this.evaluation = evaluation;
            }
            else
            {
                throw new IllegalArgumentException("only one evaluation, can't reset it.");
            }
        }
        else
        {
            throw new NullPointerException("null evaluation");
        }
    }

    /**
     * Checks if the repair has been marked as completed
     * @return true if it is completed
     */
    public boolean isPaid()
    {
        return isPaid;
    }

    /**
     * Returns the repair request that was the cause of the repair
     */
    public RepairRequest getRepairRequest()
    {
        return repairRequest;
    }

    /**
     * Sets the repair request of this object
     */
    public void setRepairRequest(RepairRequest repairRequest)
    {
        if(repairRequest == null) throw new NullPointerException("null repairRequest");

        this.repairRequest = repairRequest;
    }

    public Evaluation evaluate(String title, String comment, int rate)
    {
        Evaluation evaluation = new Evaluation(title, comment, rate);

        setEvaluation(evaluation);

        return evaluation;
    }

    public Payment pay(PaymentType paymentType)
    {
        Payment payment = new Payment();

        payment.setPaymentType(paymentType);
        setPayment(payment);

        return payment;
    }
}
