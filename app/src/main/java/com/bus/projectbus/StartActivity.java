package com.bus.projectbus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by End on 19-Dec-15.
 */
public class StartActivity extends Activity implements View.OnClickListener{
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SharedPreferences settings = getSharedPreferences("token", 0);
        String token = settings.getString("token", null);
        if (token != null){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.logInBtn:
                intent = new Intent(this, LogInActivity.class);
                startActivity(intent);
                finish();
                //SharedPreferences settings = getSharedPreferences("token", 0);
                //String silent = settings.getString("token", null);
                //Toast.makeText(StartActivity.this, silent, Toast.LENGTH_SHORT).show();
                break;
            case R.id.signUpBtn:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
