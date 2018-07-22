package com.mygdx.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 4/02/2018.
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class TubeGenerator {

    public int timeInterval;

    public FlappyBird game;

    public boolean createTube = true;

    public final int minFPS = 80;

    public final int FPSminThreshold = 60;

    Timer timer;

    public int counter;



    public TubeGenerator(int timeInterval_, FlappyBird game_ ) {
        this.game = game_;
        this.timeInterval = timeInterval_;
        this.game.addTubePair( generateTube()  );

        counter = 0;

    }

    public void step() {
        // This method is aimed to be called at every frame

        counter += 1;

        int FPS = Gdx.graphics.getFramesPerSecond();

        if (FPS < FPSminThreshold) {
            FPS = minFPS;
        }

        if (counter > (int)(FPS * 1.9)) {

            this.game.addTubePair( generateTube()  );

            counter = 0;

        }

    }

    public TubePair generateTube() {

        Random randomNum = new Random();

        int maxOffset = (TubePair.topSprite.getHeight() - (FlappyBird.screen_height/2));

        maxOffset = (int)(maxOffset * 0.75);

        return new TubePair(FlappyBird.screen_width + TubePair.topSprite.getWidth(),
                randomNum.nextInt(maxOffset*2)-maxOffset);

    }

}
