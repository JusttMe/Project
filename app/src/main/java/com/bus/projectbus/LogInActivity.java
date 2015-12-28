package com.bus.projectbus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bus.projectbus.api.Api;
import com.bus.projectbus.api.OnAuthListener;
import com.bus.projectbus.entity.UserEntity;

/**
 * Created by End on 19-Dec-15.
 */
public class LogInActivity extends Activity implements View.OnClickListener, OnAuthListener{
    Api mApi;
    EditText loginEditText,
            passEditText;
    private String mLogin, mPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        mApi = new Api(this);
        mApi.setOnAuthListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logInBtn:
                getData();
                if (checkFields()){
                    break;
                }

                mApi.auth(mLogin, mPass);
                break;

        }
    }

    public void getData() {
        mLogin = String.valueOf(loginEditText.getText());
        mPass = String.valueOf(passEditText.getText());
    }
    private boolean checkFields() {
        if(TextUtils.isEmpty(mLogin)) {
            loginEditText.setError(getString(R.string.error_empty_field));
            return true;
        }
        if(TextUtils.isEmpty(mPass)) {
            passEditText.setError(getString(R.string.error_empty_field));
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onAuth(UserEntity user) {
        SharedPreferences settings = getSharedPreferences("token", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", user.getToken()).commit();
        Toast.makeText(LogInActivity.this, ""+user.getToken(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
