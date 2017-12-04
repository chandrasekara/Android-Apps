package com.example.dhila.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void testNumber(View view) {

        //first test whether it is empty or not

        EditText myNumberInput = (EditText) findViewById(R.id.numberInput);

        if (myNumberInput.getText().toString().trim().length() > 0) {

            Toast.makeText(MainActivity.this, "not empty", Toast.LENGTH_LONG
            ).show();
        }
        else {
            Toast.makeText(MainActivity.this, "empty", Toast.LENGTH_LONG
            ).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
