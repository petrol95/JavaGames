package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class rpgGame extends ApplicationAdapter {
    SpriteBatch batch;
    private final int AST_COUNT = 100;
    Asteroid[] ast = new Asteroid[AST_COUNT];
    Random rand = new Random();

    @Override
    public void create() {
        batch = new SpriteBatch();
        Asteroid.setMyTexture(new Texture("asteroid.tga"));
        for (int i = 0; i < AST_COUNT; i++) {
            ast[i] = new Asteroid(rand.nextInt(800), rand.nextInt(600), 3.0f * rand.nextFloat() - 0.5f, 3.0f * rand.nextFloat() - 0.5f);

        }
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
		for (int i = 0; i < AST_COUNT; i++) {
			ast[i].render(batch);
		}
        batch.end();
    }

    public void update() {
		for (int i = 0; i < AST_COUNT; i++) {
			ast[i].update();
		}
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
