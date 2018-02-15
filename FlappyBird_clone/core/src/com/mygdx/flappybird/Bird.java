package com.mygdx.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by dhila on 26/01/2018.
 *
 */

public class Bird {

    Sprite[] sprites;
    public int x;
    public int y;
    public float velocity;

    public final int faceUpVelocity = 17;
    public final int faceUpRotation = 20;
    public final int rateOfDownwardRotation = 5;
    public final int maxDownwardRotation = 90;


    SpriteBatch batch;

    int flapSpriteState;

    int frameCount;

    Circle boundingCircle;



    public Bird (SpriteBatch batch_in) {

        sprites = new Sprite[2];

        sprites[0] = new Sprite(new Texture("bird.png"));
        sprites[1] = new Sprite(new Texture("bird2.png"));

        x = FlappyBird.screen_width/2;
        y = FlappyBird.screen_height/2;

        this.batch = batch_in;

        flapSpriteState = 0;

        velocity = 0;

        frameCount = 0;

        this.boundingCircle = new Circle(this.x,this.y,sprites[0].getWidth()/2);

        sprites[0].setX(x - sprites[0].getWidth() / 2);
        sprites[0].setY(y - sprites[0].getWidth() / 2); //CHANGE THIS TO CENTRE COORDINATES LATER



    }

    public void start() {

        jump();
    }


    public void render() {

        frameCount += 1;


        if (frameCount > 5) {
            frameCount = 0;
            if (flapSpriteState == 0) {
                flapSpriteState = 1;
            } else {
                flapSpriteState = 0;
            }
        }

        int xCentre = (int)(x - sprites[0].getWidth()/2);
        int yCentre = (int)(y - sprites[0].getHeight()/2);

        sprites[flapSpriteState].setX(xCentre);
        sprites[flapSpriteState].setY(yCentre);

        // abstract these magic numbers later
        if (velocity > -faceUpVelocity) {
            sprites[flapSpriteState].setRotation(faceUpRotation);
        } else {
            float theta = faceUpRotation + rateOfDownwardRotation * (velocity + faceUpVelocity);
            if (theta < -maxDownwardRotation) {
                theta = -maxDownwardRotation;
            }
            sprites[flapSpriteState].setRotation(theta);
        }

        if (FlappyBird.gameState == 0) {
            sprites[flapSpriteState].setRotation(0);
        }


        sprites[flapSpriteState].draw(batch);


    }

    public void jump() {

        if (y < FlappyBird.screen_height) {
            velocity = 25;
        }

    }

    public void step() {

        if (y>0 || velocity >0) {
            velocity -= FlappyBird.gravity;

            y += velocity;

        }



        //this.boundingCircle.setX(this.x - sprites[0].getWidth() / 2);
        this.boundingCircle.setY(this.y);

    }



}
