package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class rpgGame extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont bmf;
    Texture img;
    private final int AST_COUNT = 30;
    Asteroid[] ast = new Asteroid[AST_COUNT];
    Random rand = new Random();
    float x = 10.0f;
    float y = 80.0f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Asteroid.setMyTexture(new Texture("asteroid.tga"));
        img = new Texture("bomber.tga");
        bmf = new BitmapFont(Gdx.files.internal("my_font.fnt"), Gdx.files.internal("my_font.png"), false);
        for (int i = 0; i < AST_COUNT; i++) {
            ast[i] = new Asteroid(new Vector2(rand.nextInt(800), rand.nextInt(600)), new Vector2(3.0f * rand.nextFloat() - 0.5f, 3.0f * rand.nextFloat() - 0.5f));

        }
    }
    float time = 0.0f;

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
//        batch.draw(img, InputHandler.getMousePosition().x, InputHandler.getMousePosition().y);
        time += 0.5f;
//        batch.draw(img, InputHandler.getMousePosition().x - 20, InputHandler.getMousePosition().y - 20, 20, 20, 40, 40, 1.0f, 1.0f, time, 0, 0, 40, 40, false, false)
		for (int i = 0; i < AST_COUNT; i++) {
			ast[i].render(batch);
		}
        batch.draw(img, x, y);
        x += 65.0f * Gdx.graphics.getDeltaTime();
		bmf.draw(batch, "Привет МИР", 50, 50);
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
