package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Date;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] birds;

	int flapState = 0;
	long flapStartTime;
	float birdY = 0;
	int screen_width = 0;
	int screen_height = 0;


	float velocity = 0;

	int gameState = 0;

	float gravity = 0.4f;


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");

		screen_width = Gdx.graphics.getWidth();

		screen_height = Gdx.graphics.getHeight();

		birdY = screen_height/2 - birds[0].getHeight()/2;


		flapStartTime = new Date().getTime();


	}

	@Override
	public void render () {




		if (gameState != 0) {

			if (Gdx.input.justTouched()) {

				if (birdY < screen_height) {
					velocity = -10;
					
				}
			}

			if (birdY > 0 || velocity < 0) {
				velocity += gravity;

				birdY -= velocity;

			}





		} else {
			if (Gdx.input.justTouched()) {


				Gdx.app.log("Touched", "yep");
				gameState = 1;
			}
		}

		long flapEndTime = new Date().getTime();

		long flapTimeGap = flapEndTime - flapStartTime;

		if (flapTimeGap > 100) {

			if (flapState == 0) {
				flapState = 1;
			} else {
				flapState = 0;
			}

			flapStartTime = new Date().getTime();

			//THIS MAY CAUSE LAG LATER ON BECAUSE OF A LOT OF OBJECTS BEING CREATED, CHECK LATER!
		}
		batch.begin();

		batch.draw(background, 0, 0, screen_width, screen_height);
		//batch.draw(bird, 0, 0, screen_width, screen_height);

		batch.draw(birds[flapState], screen_width / 2 - birds[flapState].getWidth() / 2, birdY);

		batch.end();




	}
	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
