package com.example.dhila.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void comeFromTop(View view) {
        view.setAlpha(1f);

        view.setTranslationY(-800f);

        view.animate().translationYBy(800f).setDuration(500);





    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
