package com.example.undangan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.undangan.presenter.login;
import com.github.squti.guru.Guru;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class menu_login extends AppCompatActivity implements Validator.ValidationListener, View.OnClickListener{



    @BindView(R.id.btn_login)
    Button btnLogin;

    @NotEmpty
    @BindView(R.id.edit_user)
    EditText editUser;

    @NotEmpty
    @BindView(R.id.edit_pass)
    EditText editPass;
    Validator validator;
    ProgressDialog pDialog;
    String status_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        pDialog = new ProgressDialog(this);

        status_login = Guru.getString("status_loign", "false");

        if (status_login.equals("true")){
            Intent intent  = new Intent(menu_login.this, MainActivity.class);
            intent.putExtra("Fragmentone", 0); //pass zero for Fragmentone.
            startActivity(intent);
        }else {

        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onValidationSucceeded() {
        login login = new login(null,menu_login.this);
        login.login(editUser.getText().toString().trim(),editPass.getText().toString().trim(),pDialog);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onBackPressed() {
        finishAffinity();
    }
}