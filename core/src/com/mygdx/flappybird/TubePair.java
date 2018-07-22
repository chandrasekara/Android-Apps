package com.mygdx.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 4/02/2018.
 */

public class TubePair {

    public int x;
    public int topTubeY;
    public int bottomTubeY;

    public static Texture topSprite = new Texture("toptube.png");
    public static Texture bottomSprite = new Texture("bottomtube.png");

    public int offset;

    public int gap = 400;

    public int speed = 8;

    public boolean thisTubeHasScored = false;


    public TubePair(int x_, int offset_) {
        this.x = x_;
        this.offset = offset_;

        this.bottomTubeY = Gdx.graphics.getHeight() / 2 - this.gap / 2
                -  this.bottomSprite.getHeight() + this.offset;

        this.topTubeY = Gdx.graphics.getHeight() / 2 + this.gap / 2 + this.offset;

    }

    public void step() {

        this.x -= this.speed;

    }

    public boolean shouldScoreBeAwarded(int flappyX) {
        if (thisTubeHasScored == true) {
            return false;
        }
        if (flappyX > this.x + topSprite.getWidth()) {
            thisTubeHasScored = true;
            return true;
        }
        return false;

    }

}
