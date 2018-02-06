package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 4/02/2018.
 */

public class TubeGenerator {

    public int timeInterval;

    public FlappyBird game;

    public boolean createTube = true;



    public TubeGenerator(int timeInterval_, FlappyBird game_ ) {
        this.game = game_;
        this.timeInterval = timeInterval_;

    }

    public void step() {
        // This method is aimed to be called at every frame




    }

    public TubePair generateTube() {

        // change to proper x value later
        return new TubePair(500, 50); //temp

    }

}
