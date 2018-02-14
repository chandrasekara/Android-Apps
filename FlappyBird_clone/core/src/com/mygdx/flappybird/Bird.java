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

        //batch.draw(sprites[flapSpriteState],xCentre,yCentre);




        sprites[flapSpriteState].setX(xCentre);
        sprites[flapSpriteState].setY(yCentre);


        //sprites[flapSpriteState].draw(batch);

        // abstract these magic numbers later
        if (velocity > -22) {
            sprites[flapSpriteState].setRotation(45);
        } else {
            float theta = 45 + 4 * velocity;
            if (theta < -90) {
                theta = -90;
            }
            sprites[flapSpriteState].setRotation(theta);
        }

        if (FlappyBird.gameState == 0) {
            sprites[flapSpriteState].setRotation(0);
        }


        sprites[flapSpriteState].draw(batch);


        /*
        Sprite sprite = new Sprite(sprites[flapSpriteState]);

        sprite.setX(xCentre);
        sprite.setY(yCentre);

        if (velocity > 0) {
            sprite.setRotation(45);
        }

        sprite.draw(batch);

        */

        /*
        if (this.velocity > 0) {
            Gdx.app.log("jumping","nyess");
            //batch.draw(sprites[flapSpriteState],xCentre+50,yCentre);
            batch.draw(sprites[0], xCentre+50, yCentre, 32, 32, sprites[flapSpriteState].getWidth(),
                    sprites[flapSpriteState].getHeight(),1,1,45,xCentre,yCentre,32,32,false,true);
        }
        */
        /*
        draw(Texture texture, float x, float y, float originX, float originY, float width,
        float height, float scaleX, float scaleY, float rotation, int srcX, int srcY,
        int srcWidth, int srcHeight, boolean flipX, boolean flipY)
        */


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
