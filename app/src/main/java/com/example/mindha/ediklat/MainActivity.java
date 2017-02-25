package com.example.mindha.ediklat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_login) {
                    EditText a = (EditText) findViewById(R.id.input_username);
                    String str = a.getText().toString();
                    EditText b = (EditText) findViewById(R.id.input_password);
                    String pass = b.getText().toString();

                    System.out.println("CATCH!!!");
                    String password = helper.searchPass(str);


                    if (pass.equals(password)) {
                        Intent i = new Intent(MainActivity.this, Display.class);
                        i.putExtra("Username", str);
                        startActivity(i);
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });


    }
}

