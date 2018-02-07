package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dhila on 4/02/2018.
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TubeGenerator {

    public int timeInterval;

    public FlappyBird game;

    public boolean createTube = true;

    Timer timer;

    public int counter;



    public TubeGenerator(int timeInterval_, FlappyBird game_ ) {
        this.game = game_;
        this.timeInterval = timeInterval_;
        this.game.addTubePair( generateTube()  );
        //timer = new Timer();

        counter = 0;

    }

    public void step() {
        // This method is aimed to be called at every frame

        /*


        import java.util.Timer

        Timer
        timer.schedule(new TimerTask() {
  @Override
  public void run() {
    // Your database code here
  }
}, 2*60*1000);
         */

        /*
        if (createTube == true) {

            createTube = false;

            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    game.addTubePair( generateTube()  );
                    createTube = true;

                }
            }, 2 * 1000, 2000);
        }

        */

        counter += 1;

        if (counter > 120) {

            this.game.addTubePair( generateTube()  );

            counter = 0;

        }

    }

    public TubePair generateTube() {

        // change to proper x value later
        return new TubePair(FlappyBird.screen_width + TubePair.topSprite.getWidth(), 50); //temp

    }

}
