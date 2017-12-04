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


        boolean isSquareNumberValue = false;
        boolean isTriangularNumberValue = false;

        //Holds the final output to the user
        String outputMessage = "";

        EditText myNumberInput = (EditText) findViewById(R.id.numberInput);

        //Checking whether an empty input (including spaces) has been input
        if (myNumberInput.getText().toString().trim().length() > 0) {

            //If the input isn't empty, check if it's a square or triangular number
            isSquareNumberValue = isSquareNumber(Integer.parseInt(myNumberInput.getText()
                    .toString()));

            isTriangularNumberValue = isTriangularNumber(Integer.parseInt(myNumberInput.getText()
            .toString()));
        }
        else {
            outputMessage = outputMessage + "Please enter a number";
        }

        if (isSquareNumberValue) {
            outputMessage = outputMessage + "The number is a square number!\n";
        }
        if (isTriangularNumberValue) {
            outputMessage = outputMessage + "The number is a triangular number!\n";
        }

        //Final output
        if (!outputMessage.equals("")) {
            Toast.makeText(MainActivity.this, outputMessage, Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(MainActivity.this, "The number is neither a square number" +
                    " nor triangular number", Toast.LENGTH_LONG).show();
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
