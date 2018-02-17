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

    public final int jumpVelocity = 25;
    public final int faceUpVelocity = 17;
    public final int faceUpRotation = 20;
    public final int rateOfDownwardRotation = 5;
    public final int maxDownwardRotation = 90;
    public final int deadFallDownVelocity = 10;
    public final int fallRotation = -90;


    SpriteBatch batch;

    int flapSpriteState;

    int frameCount;

    Circle boundingCircle;

    public Texture gameOver;

    public boolean restart = false;


    public Bird (SpriteBatch batchIn) {

        sprites = new Sprite[2];

        sprites[0] = new Sprite(new Texture("bird.png"));
        sprites[1] = new Sprite(new Texture("bird2.png"));

        x = FlappyBird.screen_width/2;
        y = FlappyBird.screen_height/2;

        this.batch = batchIn;

        flapSpriteState = 0;

        velocity = 0;

        frameCount = 0;

        this.boundingCircle = new Circle(this.x,this.y,sprites[0].getWidth()/2);

        sprites[0].setX(x - sprites[0].getWidth() / 2);
        sprites[0].setY(y - sprites[0].getWidth() / 2);

        gameOver = new Texture("gameover.png");


    }

    public void start() {

        jump();
    }


    public void render() {

        if (FlappyBird.hitTube == false) {

            frameCount += 1;

            if (frameCount > 5) {
                frameCount = 0;
                if (flapSpriteState == 0) {
                    flapSpriteState = 1;
                } else {
                    flapSpriteState = 0;
                }
            }

            int xCentre = (int) (x - sprites[0].getWidth() / 2);
            int yCentre = (int) (y - sprites[0].getHeight() / 2);

            sprites[flapSpriteState].setX(xCentre);
            sprites[flapSpriteState].setY(yCentre);

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
        } else {



            sprites[flapSpriteState].setRotation(fallRotation);
            if (sprites[flapSpriteState].getY() > 0) {
                sprites[flapSpriteState].setY(sprites[flapSpriteState].getY()-deadFallDownVelocity);
            }

            sprites[flapSpriteState].draw(batch);

            batch.draw(gameOver,FlappyBird.screen_width / 2 - gameOver.getWidth() * 3 / 2,
                    FlappyBird.screen_height / 2 - gameOver.getHeight() * 3 / 2,
                    gameOver.getWidth() *  3, gameOver.getHeight() * 3);

            if (sprites[flapSpriteState].getY() <= 0) {
                if (Gdx.input.justTouched()) {
                    restart = true;
                    //velocity = 0;
                }
            }
        }

    }

    public void jump() {

        if (y < FlappyBird.screen_height) {
            velocity = jumpVelocity;
        }

    }

    public void step() {

        if (y>0 || velocity >0) {
            velocity -= FlappyBird.gravity;

            y += velocity;

        }

        this.boundingCircle.setY(this.y);

    }

}
