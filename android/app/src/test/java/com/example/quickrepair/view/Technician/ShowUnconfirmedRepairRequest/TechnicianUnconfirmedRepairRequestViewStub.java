package com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest;

import com.example.quickrepair.view.Technician.ShowUnconfirmedRepairRequest.TechnicianUnconfirmedRepairRequestView;

public class TechnicianUnconfirmedRepairRequestViewStub implements TechnicianUnconfirmedRepairRequestView {
    //Buttons
    private static final int REJECTED = 1;
    private static final int CONFIRM = 2;
    private static final int ERROR = 3;
    private static int state;

    @Override
    public void reject() {
        state = REJECTED;
    }

    @Override
    public void confirm() {
        state = CONFIRM;
    }

    @Override
    public void showError(String message) {
        state = ERROR;
    }

    public static int getState() {
        return state;
    }
}