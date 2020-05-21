package com.example.quickrepair.view.Customer.RegisterCustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quickrepair.R;
import com.example.quickrepair.view.Customer.RepairRequests.CustomerRepairRequestsActivity;

import static com.example.quickrepair.QuickRepairApplication.CUSTOMER_ID_EXTRA;

public class CustomerRegisterActivity extends AppCompatActivity implements CustomerRegisterView
{
    CustomerRegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register2);

        viewModel = new ViewModelProvider(this).get(CustomerRegisterViewModel.class);
        final CustomerRegisterPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        int customerID = getIntent().getIntExtra(CUSTOMER_ID_EXTRA, 0);
        presenter.setCustomer(customerID);
        presenter.setUpDataSource();

        if (customerID != 0)
        {
            ((TextView) findViewById(R.id.Title)).setText("Profile");
            ((Button) findViewById(R.id.CustomerRegisterButton)).setText("Save");
        }

        findViewById(R.id.CustomerRegisterButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.registerCustomer(getName(), getSurname(), getPhoneNumber(), getEmail(), getBankAccountNumber(), getUsername(), getPassword());
            }
        });
    }

    @Override
    public void setName(String name)
    {
        ((EditText) findViewById(R.id.Name)).setText(name);
    }

    @Override
    public void setSurname(String surname)
    {
        ((EditText) findViewById(R.id.Surname)).setText(surname);
    }

    @Override
    public void setPhoneNumber(String phoneNumber)
    {
        ((EditText) findViewById(R.id.PhoneNumber)).setText(phoneNumber);
    }

    @Override
    public void setBankAccountNumber(String accountNumber)
    {
        ((EditText) findViewById(R.id.BankAccount)).setText(accountNumber);
    }

    @Override
    public void setEmail(String email)
    {
        ((EditText) findViewById(R.id.Email)).setText(email);
    }

    @Override
    public void setUsername(String username)
    {
        ((EditText) findViewById(R.id.Username)).setText(username);
    }

    @Override
    public void setPassword(String password)
    {
        ((EditText) findViewById(R.id.Password)).setText(password);
    }

    @Override
    public String getName()
    {
        return ((EditText) findViewById(R.id.Name)).getText().toString().trim();
    }

    @Override
    public String getSurname()
    {
        return ((EditText) findViewById(R.id.Surname)).getText().toString().trim();
    }

    @Override
    public String getPhoneNumber()
    {
        return ((EditText) findViewById(R.id.PhoneNumber)).getText().toString().trim();
    }

    @Override
    public String getBankAccountNumber()
    {
        return ((EditText) findViewById(R.id.BankAccount)).getText().toString().trim();
    }

    @Override
    public String getEmail()
    {
        return ((EditText) findViewById(R.id.Email)).getText().toString().trim();
    }

    @Override
    public String getUsername()
    {
        return ((EditText) findViewById(R.id.Username)).getText().toString().trim();
    }

    @Override
    public String getPassword()
    {
        return ((EditText) findViewById(R.id.Password)).getText().toString().trim();
    }

    @Override
    public void onSuccessfulRegister(Integer id)
    {
        Intent intent = new Intent(this, CustomerRepairRequestsActivity.class);
        intent.putExtra(CUSTOMER_ID_EXTRA, id);
        startActivity(intent);

        finish();
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }
}