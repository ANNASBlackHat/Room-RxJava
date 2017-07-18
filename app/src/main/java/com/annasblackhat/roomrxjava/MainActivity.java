package com.annasblackhat.roomrxjava;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private TextInputEditText edtName;
    private TextInputEditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.sure_name);
        edtEmail = findViewById(R.id.user_email);
    }

    public void updateUser(View view){

    }
}
