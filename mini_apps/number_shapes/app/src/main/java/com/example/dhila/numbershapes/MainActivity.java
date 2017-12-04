package com.example.dhila.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {


    public void testNumber(View view) {

        //first test whether it is empty or not

        boolean isSquareNumberValue = false;
        boolean isTriangularNumberValue = false;

        String outputMessage = "";

        EditText myNumberInput = (EditText) findViewById(R.id.numberInput);

        if (myNumberInput.getText().toString().trim().length() > 0) {

            isSquareNumberValue = isSquareNumber(Integer.parseInt(myNumberInput.getText()
                    .toString()));

            isTriangularNumberValue = isTriangularNumber(Integer.parseInt(myNumberInput.getText()
            .toString()));
            Toast.makeText(MainActivity.this, String.valueOf(isTriangularNumberValue),
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "empty", Toast.LENGTH_LONG
            ).show();
        }

    }

    public boolean isSquareNumber(int inputNum) {

        double squareLeftOver = sqrt(inputNum) - (int)sqrt(inputNum);

        if (squareLeftOver > 0.0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isTriangularNumber(int inputNum) {

        int indexNumber = 1;
        int incrementNumber = 1;

        boolean breakOut = false;

        while (!breakOut) {
            if (inputNum == indexNumber) {
                return true;
            } else if (inputNum < indexNumber) {
                return false;
            }

            incrementNumber++;
            indexNumber += incrementNumber;
        }

        return false;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
