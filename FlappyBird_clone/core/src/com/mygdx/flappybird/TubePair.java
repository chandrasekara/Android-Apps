package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 4/02/2018.
 */

public class TubePair {

    public int x;
    public Texture sprite;

    public TubePair(int x_) {
        this.x = x_;
        this.sprite = new Texture("toptube.png");
    }

}
