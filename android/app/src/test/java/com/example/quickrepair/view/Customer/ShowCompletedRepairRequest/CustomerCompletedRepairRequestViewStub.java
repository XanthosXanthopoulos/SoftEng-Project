package com.example.quickrepair.view.Customer.ShowCompletedRepairRequest;

public class CustomerCompletedRepairRequestViewStub implements CustomerCompletedRepairRequestView {
    private static final int ERROR = 1;
    private static final int CAN_NOT_PAY = 2;
    private static final int PAY = 3;
    private static int state;

    private int sumOfsetter;
    @Override
    public void showError(String message) {
        state = ERROR;
    }

    @Override
    public void setJob(String job) {
        sumOfsetter++;
    }

    @Override
    public void setTechnicianName(String technicianName) {
        sumOfsetter++;
    }

    @Override
    public void setAddress(String Address) {
        sumOfsetter++;
    }

    @Override
    public void setComments(String comments) {
        sumOfsetter++;
    }

    @Override
    public void setConductionDate(String conductionDate) {
        sumOfsetter++;
    }

    @Override
    public void setEstimatedDuration(String estimatedDuration) {
        sumOfsetter++;
    }

    @Override
    public void setCost(String cost) {
        sumOfsetter++;
    }

    @Override
    public void canNotPay() {
        state = CAN_NOT_PAY;
    }

    @Override
    public void setPayListener() {
        state = PAY;
    }

    @Override
    public void setNullCost() {
        sumOfsetter++;
    }

    @Override
    public void donePayment() {
        state = PAY;
    }
    public int getSumOfsetter(){
        return sumOfsetter;
    }

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        CustomerCompletedRepairRequestViewStub.state = state;
    }
}
