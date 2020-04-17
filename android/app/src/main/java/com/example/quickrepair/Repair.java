package com.example.quickrepair;

public class Repair {
    private double quantity;
    private Payment payment;
    private Evaluation evaluation;

    public Repair(double quantity) {
        this.quantity = quantity;
    }

    public Repair(double quantity, Payment payment) {
        this.quantity = quantity;
        this.payment = payment;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
