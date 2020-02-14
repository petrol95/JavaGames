package com.geekbrains.valentine.card;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import sun.jvm.hotspot.gc.g1.HeapRegionType;

public class ValentineCardGame extends ApplicationAdapter {
    private class Heart {
        private Vector2 position;
        private Vector2 velocity;
        private float size;

        public Heart() {
            this.position = new Vector2(0, 0);
            this.velocity = new Vector2(0,0);
            this.init();
        }

        public void init() {
            this.position.set(MathUtils.random(0, 1280), 0);
            this.velocity.set(0.0f, 100.0f + MathUtils.random(100.0f));
            this.size = 0.15f + MathUtils.random(0.15f);
        }

        public void render() {
            batch.draw(heartTexture, position.x - 150, position.y - 150, 150.0f, 150.0f, 300, 300, size, size, 0.0f, 0, 0, 300, 300, false, false);
        }

        public void update(float dt) {
        	position.y += velocity.y * dt;

        }
    }

    private SpriteBatch batch;
    private Texture heartTexture;
    private static final int HEARTS_COUNT = 100;
    private Heart[] hearts;

    @Override
    public void create() {
        batch = new SpriteBatch();
        heartTexture = new Texture("heart.png");
        hearts = new Heart[HEARTS_COUNT];
        for (int i = 0; i < hearts.length; i++) {
            hearts[i] = new Heart();
        }
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (int i = 0; i < hearts.length; i++) {
            hearts[i].render();
        }
        batch.end();
    }

    public void update(float dt) {
		for (int i = 0; i < hearts.length; i++) {
			hearts[i].update(dt);
		}
    }

    @Override
    public void dispose() {
        batch.dispose();
        heartTexture.dispose();
    }
}
