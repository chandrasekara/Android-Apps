package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 20/02/2018.
 */

public class ScoreManager {

    public Texture[] numbers;

    public static final int drawScale = 3;
    public static final int xDrawPosition = 50;
    public static final int yDrawPosition = 50;


    public ScoreManager() {
        numbers = new Texture[10];

        for (int i=0;i<numbers.length;i++) {
            numbers[i] = new Texture("scoreNumbers/" + i + ".png");
        }
    }
}
