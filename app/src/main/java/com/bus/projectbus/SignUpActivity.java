package com.bus.projectbus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bus.projectbus.api.Api;
import com.bus.projectbus.api.OnRegisterListener;
import com.bus.projectbus.entity.UserEntity;

import java.util.concurrent.TimeUnit;

/**
 * Created by End on 19-Dec-15.
 */
public class SignUpActivity extends Activity implements View.OnClickListener, OnRegisterListener{
    EditText regFieldLogin,
             regFieldPass,
             regFieldPassRepeat,
             regFieldName,
             regFieldSurname;
    private String mLogin,
                   mPass,
                   mPassRepeat,
                   mName,
                   mSurname;

    UserEntity mUser;
    Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        regFieldLogin = (EditText) findViewById(R.id.regFieldLogin);
        regFieldPass = (EditText) findViewById(R.id.regFieldPass);
        regFieldPassRepeat = (EditText) findViewById(R.id.regFieldPassRepeat);
        regFieldName = (EditText) findViewById(R.id.regFieldName);
        regFieldSurname = (EditText) findViewById(R.id.regFieldSurname);

        mApi = new Api(this);
        mApi.setOnRegistryListener(this);

    }



    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.signUpBtn:
                getData();
                if (checkFields()){
                    break;
                }
                if (checkPass()){
                    break;
                }
                mUser = new UserEntity(mLogin, mPass, mName, mSurname);

                mApi.register(mUser);

                break;
        }
    }

    private boolean checkPass() {
        if(!mPass.equals(mPassRepeat)) {
            regFieldPass.setError(getString(R.string.error_not_equal_pass));
            regFieldPassRepeat.setError(getString(R.string.error_not_equal_pass));
            return true;
        }
        return false;
    }

    private boolean checkFields() {
        if(TextUtils.isEmpty(mLogin)) {
            regFieldLogin.setError(getString(R.string.error_empty_field));
           return true;
        }
        if(TextUtils.isEmpty(mPass)) {
            regFieldPass.setError(getString(R.string.error_empty_field));
            return true;
        }
        if(TextUtils.isEmpty(mPassRepeat)) {
            regFieldPassRepeat.setError(getString(R.string.error_empty_field));
            return true;
        }
        if(TextUtils.isEmpty(mLogin)) {
            regFieldLogin.setError(getString(R.string.error_empty_field));
            return true;
        }
        if(TextUtils.isEmpty(mName)) {
            regFieldName.setError(getString(R.string.error_empty_field));
            return true;
        }
        if(TextUtils.isEmpty(mSurname)) {
            regFieldSurname.setError(getString(R.string.error_empty_field));
            return true;
        }

        return false;
    }




    public void getData() {
        mLogin = String.valueOf(regFieldLogin.getText());
        mPass = String.valueOf(regFieldPass.getText());
        mPassRepeat = String.valueOf(regFieldPassRepeat.getText());
        mName = String.valueOf(regFieldName.getText());
        mSurname = String.valueOf(regFieldSurname.getText());

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onRegister(String token) {
        SharedPreferences settings = getSharedPreferences("token", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token).commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

