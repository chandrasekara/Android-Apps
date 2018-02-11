package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import sun.rmi.runtime.Log;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture topTube;
	Texture bottomTube;

	static int screen_width = 0;
	static int screen_height = 0;
	static float gravity = 0.8f;

	int tubeXPos;
	int gameState = 0;

	Bird flappy;
	ArrayList<TubePair> tubes;

	public int framesPerSecond;

	TubeGenerator tubeGenerator;



	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		screen_width = Gdx.graphics.getWidth();
		screen_height = Gdx.graphics.getHeight();

		flappy = new Bird(batch);
		tubes = new ArrayList<TubePair>();


		tubeGenerator = new TubeGenerator(2, this);



	}

	@Override
	public void render () {

		// Remove later
		Texture black = new Texture("black.png");

		//Remove later
		framesPerSecond = Gdx.graphics.getFramesPerSecond();
		Gdx.app.log("MyTag", Integer.toString(framesPerSecond));

		batch.begin();
		batch.draw(background, 0, 0, screen_width, screen_height);



		if (gameState != 0) {
			if (Gdx.input.justTouched()) {
				flappy.jump();
			}
		} else {
			if (Gdx.input.justTouched()) {
				Gdx.app.log("Touched", "yep");
				gameState = 1;
				flappy.start();
			}
		}

		// Render all of the tubes that are present on screen

		// Find a way to have this in the same for loop later

		TubePair leftMostTubePair = tubes.get(0);

		if (leftMostTubePair.x < 0 - TubePair.bottomSprite.getWidth()) {
			tubes.remove(0);
			tubes.trimToSize();
		}

		Rectangle flappyRect = new Rectangle(flappy.x,flappy.y, flappy.sprites[0].getWidth(), flappy.sprites[0].getHeight());

		for (TubePair tube: tubes) {
			tube.step();

			// Remove later
			batch.draw(black,tube.x,tube.topTubeY, tube.topSprite.getWidth(), tube.topSprite.getHeight());
			batch.draw(black,tube.x, tube.bottomTubeY, tube.bottomSprite.getWidth(), tube.bottomSprite.getHeight());

			batch.draw(TubePair.bottomSprite,tube.x, tube.bottomTubeY);
			batch.draw(TubePair.topSprite, tube.x, tube.topTubeY);

			// Collision detection here

			Rectangle topRect = new Rectangle(tube.x, tube.topTubeY, tube.topSprite.getWidth(), tube.topSprite.getHeight());

			Rectangle bottomRect = new Rectangle(tube.x, tube.bottomTubeY, tube.bottomSprite.getWidth(), tube.bottomSprite.getHeight());

			if (flappyRect.overlaps(topRect) || flappyRect.overlaps(bottomRect)) {
				Gdx.app.log("FLAPPY","hitting the tube");
			}




		}



		flappy.step();

		batch.draw(black,flappy.x - flappy.sprites[0].getWidth() / 2,flappy.y
				- flappy.sprites[0].getHeight() / 2,flappy.sprites[0].getWidth(),flappy.sprites[0].getHeight());

		flappy.render();
		batch.end();
		tubeGenerator.step();

	}

	public void addTubePair(TubePair tubePairIn) {

		tubes.add(tubePairIn);

	}
	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
