package com.example.dhila.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void conversion(View view) {

        //Convert the input to an EditText
        EditText myEditText = (EditText) findViewById(R.id.usd_input);

        //Incrementally calculate the AUD amount from the USD amount, using appropriate data type
        //conversions
        double USD_AUD = 1.314;
        String editTextToString = myEditText.getText().toString();
        int editTextToInt = Integer.parseInt(editTextToString);
        double convertedAmount = USD_AUD * editTextToInt;


        //Output of the converter
        Toast.makeText(MainActivity.this, Double.toString(convertedAmount), Toast.LENGTH_LONG
        ).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
