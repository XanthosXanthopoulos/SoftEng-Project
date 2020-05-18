package com.example.quickrepair.view.Customer.ShowCompletedRepairRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickrepair.R;
import com.example.quickrepair.view.Customer.RepairRequests.CustomerRepairRequestsActivity;
import com.example.quickrepair.view.Technician.RepairRequests.TechnicianRepairRequestsActivity;

public class CustomerCompletedRepairRequestActivity extends AppCompatActivity implements CustomerCompletedRepairRequestView{
    public static final String REPAIR_REQUEST_ID_EXTRA = "repair_request_id";
    public static final String CUSTOMER_ID_EXTRA = "customer_id";

    private static int repairRequestID;
    private static int customerID;

    private CustomerCompletedRepairRequestViewModel technicianCompletedRepairRequestViewModel;
    CustomerCompletedRepairRequestPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_completed_repair_request);

        Intent intent = getIntent();
        repairRequestID = intent.getIntExtra(REPAIR_REQUEST_ID_EXTRA, 0);
        customerID = intent.getIntExtra(CUSTOMER_ID_EXTRA, 0);

        technicianCompletedRepairRequestViewModel = new ViewModelProvider(this).get(CustomerCompletedRepairRequestViewModel.class);

        presenter = technicianCompletedRepairRequestViewModel.getPresenter();
        presenter.setView(this);

        presenter.searchRepairRequestData(repairRequestID);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setJob(String job) {
        TextView jobTextView = findViewById(R.id.job);
        jobTextView.setText(job);
    }

    @Override
    public void setTechnicianName(String technicianName) {
        TextView technicianTextView = findViewById(R.id.technician);
        technicianTextView.setText(technicianName);
    }

    @Override
    public void setAddress(String address) {
        TextView addressTextView = findViewById(R.id.address);
        addressTextView.setText(address);
    }

    @Override
    public void setComments(String comments) {
        TextView commentsTextView = findViewById(R.id.comments);
        commentsTextView.setText(comments);
    }

    @Override
    public void setConductionDate(String conductionDate) {
        TextView conductionDateTextView = findViewById(R.id.conduction_date);
        conductionDateTextView.setText(conductionDate);
    }

    @Override
    public void setEstimatedDuration(String estimatedDuration) {
        TextView estimatedDurationTextView = findViewById(R.id.estimated_duration);
        estimatedDurationTextView.setText(estimatedDuration);
    }

    @Override
    public void setCost(String cost) {
        TextView costTextView = findViewById(R.id.cost);
        costTextView.setText(cost);
    }

    @Override
    public void canNotPay() {
        Button pay = findViewById(R.id.pay_button);
        pay.setVisibility(View.GONE);
    }

    @Override
    public void donePayment() {
        Intent intent = new Intent(this, CustomerRepairRequestsActivity.class);
        intent.putExtra(CUSTOMER_ID_EXTRA, customerID);
        this.startActivity(intent);
        finish();
    }

    @Override
    public void setPayListener() {
        Button buttonConfirm = findViewById(R.id.pay_button);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.pay();
            }
        });
    }

    @Override
    public void setNullCost() {
        TextView cost = findViewById(R.id.cost);
        cost.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CustomerRepairRequestsActivity.class);
        intent.putExtra(CUSTOMER_ID_EXTRA, customerID);
        this.startActivity(intent);
        finish();
    }
}
