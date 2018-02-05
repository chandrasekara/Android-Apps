package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Date;

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


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		screen_width = Gdx.graphics.getWidth();
		screen_height = Gdx.graphics.getHeight();

		flappy = new Bird(batch);
		tubes = new ArrayList<TubePair>();


		TubeGenerator tubeGenerator = new TubeGenerator(2, this);
		addTubePair( tubeGenerator.generateTube()  );


	}

	@Override
	public void render () {

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


		for (TubePair tube: tubes) {
			batch.draw(TubePair.bottomSprite,0, -1*tube.offset*5 );
			batch.draw(TubePair.topSprite, 0, screen_height/2 + tube.offset*5);
		}


		batch.end();

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
