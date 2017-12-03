package com.example.dhila.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void conversion(View view) {

        EditText myTextView = (EditText) findViewById(R.id.editText);

        Log.i("Test", "Button clicked");

        double USD_AUD = 1.314;

        Toast.makeText(MainActivity.this, Double.toString(Integer.parseInt(myTextView.getText().toString()) * USD_AUD), Toast.LENGTH_LONG
        ).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
