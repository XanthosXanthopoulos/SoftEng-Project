package com.example.quickrepair.view.Customer.RegisterCustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.quickrepair.R;

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
    }

    @Override
    public String getName()
    {
        return ((EditText)findViewById(R.id.Name)).getText().toString().trim();
    }

    @Override
    public String getSurname()
    {
        return ((EditText)findViewById(R.id.Surname)).getText().toString().trim();
    }

    @Override
    public String getPhoneNumber()
    {
        return ((EditText)findViewById(R.id.PhoneNumber)).getText().toString().trim();
    }

    @Override
    public String getBankAccountNumber()
    {
        return ((EditText)findViewById(R.id.BankAccount)).getText().toString().trim();
    }

    @Override
    public String getEmail()
    {
        return ((EditText)findViewById(R.id.Email)).getText().toString().trim();
    }

    @Override
    public String getUsername()
    {
        return ((EditText)findViewById(R.id.Username)).getText().toString().trim();
    }

    @Override
    public String getPassword()
    {
        return ((EditText)findViewById(R.id.Password)).getText().toString().trim();
    }

    @Override
    public void onSuccessfulRegister(Integer id)
    {
        Intent intent = new Intent();
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }
}