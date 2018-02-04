package com.mygdx.flappybird;

/**
 * Created by dhila on 4/02/2018.
 */

public class TubeGenerator {

    public int timeInterval;

    public FlappyBird game;


    public TubeGenerator(int timeInterval_, FlappyBird game_ ) {
        this.game = game_;
        this.timeInterval = timeInterval_;

    }

    public void step() {
        // This method is aimed to be called at every frame




    }

    public TubePair generateTube() {

        return new TubePair(FlappyBird.screen_width - 100); //temp

    }

}
