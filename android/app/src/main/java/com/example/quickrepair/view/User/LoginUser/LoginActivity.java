package com.example.quickrepair.view.User.LoginUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.quickrepair.R;

public class LoginActivity extends AppCompatActivity implements LoginView
{
    private LoginViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        final LoginPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);
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
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void OnLoginCustomerSuccess(Integer id)
    {
        Intent intent = new Intent();
    }

    @Override
    public void OnLoginTechnicianSuccess(Integer id)
    {

    }
}
