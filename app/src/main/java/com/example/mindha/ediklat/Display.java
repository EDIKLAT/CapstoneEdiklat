package com.example.mindha.ediklat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String username = getIntent().getStringExtra("Username");

        TextView text = (TextView)findViewById(R.id.name_user);
        text.setText(username);
    }
}
