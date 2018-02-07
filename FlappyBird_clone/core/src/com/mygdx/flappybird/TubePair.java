package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 4/02/2018.
 */

public class TubePair {

    public int x;
    public static Texture topSprite = new Texture("toptube.png");
    public static Texture bottomSprite = new Texture("bottomtube.png");

    public int offset;




    public TubePair(int x_, int offset_) {
        this.x = x_;
        this.offset = offset_;


    }

    public void step() {

        this.x -= 10;

    }

}
