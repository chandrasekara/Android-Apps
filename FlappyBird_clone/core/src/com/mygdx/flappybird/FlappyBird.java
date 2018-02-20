package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import sun.font.TrueTypeFont;
import sun.rmi.runtime.Log;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture topTube;
	Texture bottomTube;

	static int screen_width = 0;
	static int screen_height = 0;
	static float gravity = 1.3f;

	int tubeXPos;
	public static int gameState = 0;

	Bird flappy;
	ArrayList<TubePair> tubes;

	public int framesPerSecond;

	TubeGenerator tubeGenerator;

	public static boolean hitTube;

	public int score;

	//change attrs that should be private to private later :)


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		screen_width = Gdx.graphics.getWidth();
		screen_height = Gdx.graphics.getHeight();

		flappy = new Bird(batch);
		tubes = new ArrayList<TubePair>();


		tubeGenerator = new TubeGenerator(2, this);

		hitTube = false;

		score = 0;

	}

	@Override
	public void render () {


		Gdx.app.log("Score",Integer.toString(score));

		//Remove later
		framesPerSecond = Gdx.graphics.getFramesPerSecond();
		Gdx.app.log("MyTag", Integer.toString(framesPerSecond));

		batch.begin();
		batch.draw(background, 0, 0, screen_width, screen_height);

		if (gameState != 0) {
			if (Gdx.input.justTouched()) {
				flappy.jump();
			}


			if (hitTube == false) {
				tubeGenerator.step();
			}
			// Render all of the tubes that are present on screen

			// Find a way to have this in the same for loop later

			if (tubes.size() > 0) {
				TubePair leftMostTubePair = tubes.get(0);

				if (leftMostTubePair.x < 0 - TubePair.bottomSprite.getWidth()) {
					tubes.remove(0);
					tubes.trimToSize();
				}
			}

			Rectangle flappyRect = new Rectangle(flappy.x,flappy.y, flappy.sprites[0].getWidth(), flappy.sprites[0].getHeight());

			for (TubePair tube: tubes) {

				if (hitTube == false) {
					tube.step();
					if (tube.shouldScoreBeAwarded(flappy.x)) {
						score++;
					}
				}

				batch.draw(TubePair.bottomSprite,tube.x, tube.bottomTubeY);
				batch.draw(TubePair.topSprite, tube.x, tube.topTubeY);

				// Collision detection here

				Rectangle topRect = new Rectangle(tube.x, tube.topTubeY, tube.topSprite.getWidth(), tube.topSprite.getHeight());
				Rectangle bottomRect = new Rectangle(tube.x, tube.bottomTubeY, tube.bottomSprite.getWidth(), tube.bottomSprite.getHeight());
				Intersector intersector = new Intersector();

				if (intersector.overlaps(flappy.boundingCircle,topRect) || intersector.overlaps(flappy.boundingCircle,bottomRect) ) {
					Gdx.app.log("FLAPPY","hitting the tube");
					hitTube = true;
				}

			}

			flappy.step();
			flappy.render();

		} else {
			if (Gdx.input.justTouched()) {
				Gdx.app.log("Touched", "yep");
				gameState = 1;
				flappy.start();
			}
			flappy.render();
		}

		batch.end();

		if (flappy.restart == true) {
			create();
			gameState = 0;
		}

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
