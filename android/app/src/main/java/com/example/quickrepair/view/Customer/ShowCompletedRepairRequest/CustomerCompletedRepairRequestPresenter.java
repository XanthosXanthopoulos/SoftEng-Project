package com.example.quickrepair.view.Customer.ShowCompletedRepairRequest;

import com.example.quickrepair.domain.Customer;
import com.example.quickrepair.domain.Evaluation;
import com.example.quickrepair.domain.PaymentType;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.util.Utilities;
import com.example.quickrepair.view.Technician.ShowCompletedRepairRequest.TechnicianCompletedRepairRequestView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CustomerCompletedRepairRequestPresenter {
    private RepairRequestDAOMemory repairRequestDAO;
    private CustomerCompletedRepairRequestView view;
    private RepairRequest repairRequest;

    public void searchRepairRequestData(int repairRequestId){
        if(repairRequestId == 0){
            view.showError("Something went wrong");
            return;
        }

        this.repairRequest = repairRequestDAO.find(repairRequestId);

        if(this.repairRequest == null){
            view.showError("Something went wrong");
            return;
        }
        view.setJob(repairRequest.getJob().getJobType().getName());
        view.setTechnicianName("From: " + "\n" + repairRequest.getCustomer().getUsername());
        view.setAddress("Address: " + "\n" + repairRequest.getAddress().toString());
        view.setComments("Comments: " + "\n" + repairRequest.getCommentsFromCustomer());
        view.setConductionDate("Date: " + "\n" + Utilities.getToString(repairRequest.getConductionDate()));
        view.setEstimatedDuration("Estimated Duration: " + "\n" + repairRequest.getEstimatedDuration());

        view.setCost("Cost: " + "\n" + repairRequest.getRepair().calculateCost());

        if(repairRequest.getRepair().getPayment() != null ) {
            Evaluation evaluation= repairRequest.getRepair().getEvaluation();
            if(evaluation!=null) {
                view.setEvaluationData("Evaluation's title:" + "\n" + evaluation.getTitle(),
                        "Evaluation's comments: " + "\n" + evaluation.getComment(),
                        "Evaluation's rate: "+evaluation.getRate());
            }
        }else{
            view.setPayAndEvaluationFields();
            view.setPayListener();
        }
    }

    public void payAndEvaluate(String title, String comments, int rate){
        if(title != null && title.length() > 0 && comments != null && comments.length() > 0 ) {
            // pay
            GregorianCalendar now = (GregorianCalendar) Calendar.getInstance();
            repairRequest.getRepair().pay(now, PaymentType.CARD);
            //evaluate
            repairRequest.getRepair().evaluate(title ,comments, rate);
            //done
            view.donePayAndEvaluate();
        }else{
            view.showError("Please enter an evaluation");
        }

    }
    public void setView(CustomerCompletedRepairRequestView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

    public void setRepairRequestDAO(RepairRequestDAOMemory repairRequestDAO) {
        this.repairRequestDAO = repairRequestDAO;
    }
}
