package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dhila on 26/01/2018.
 */

public class Bird {

    Texture[] sprites;
    public int x;
    public int y;
    public float velocity;

    SpriteBatch batch;

    int flapSpriteState;



    public Bird (SpriteBatch batch_in) {

        sprites = new Texture[2];

        sprites[0] = new Texture("bird.png");
        sprites[1] = new Texture("bird2.png");

        x = FlappyBird.screen_width/2;
        y = FlappyBird.screen_height/2;

        this.batch = batch_in;

        flapSpriteState = 0;

        velocity = 0;
        
    }

    public void start() {

        velocity = -20;
    }


    public void render() {

        if (flapSpriteState == 0) {
            flapSpriteState = 1;
        } else {
            flapSpriteState = 0;
        }

        //put this in a different method later

        if (y>0 || velocity >0) {
            velocity -= FlappyBird.gravity;

            y += velocity;

        }


        batch.draw(sprites[flapSpriteState],x - sprites[0].getWidth()/2,y -
                sprites[0].getHeight()/2);



    }

    public void jump() {

        if (y < FlappyBird.screen_height) {
            velocity = 15;
        }

    }



}
