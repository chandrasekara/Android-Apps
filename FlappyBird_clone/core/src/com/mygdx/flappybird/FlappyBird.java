package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Date;


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

		//Remove later
		framesPerSecond = Gdx.graphics.getFramesPerSecond();
		Gdx.app.log("MyTag", Integer.toString(framesPerSecond));

		batch.begin();
		batch.draw(background, 0, 0, screen_width, screen_height);
		flappy.render();

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

		for (TubePair tube: tubes) {
			tube.step();
			batch.draw(TubePair.bottomSprite,tube.x, -1*tube.offset*5 );
			batch.draw(TubePair.topSprite, tube.x, screen_height/2 + tube.offset*5);
		}

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
