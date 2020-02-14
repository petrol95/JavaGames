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
        private float wind;
        private float color;
        private float time;

        public Heart() {
            this.position = new Vector2(0, 0);
            this.velocity = new Vector2(0,0);
            this.init();
        }

        public void init() {
            this.position.set(MathUtils.random(0, 1280), -100.0f - MathUtils.random(820.0f));
            this.velocity.set(0.0f, 100.0f + MathUtils.random(100.0f));
            this.size = 0.15f + MathUtils.random(0.15f);
            this.wind = MathUtils.random(-30.0f, 30.0f);
            this.color = MathUtils.random(0.7f, 1.0f);
            this.time = MathUtils.random(0.0f, 100.0f);
        }

        public void render() {
            // shadow
            batch.setColor(0.2f, 0.2f, 0.2f, 0.5f);
            batch.draw(heartTexture, position.x - 150 - 5, position.y - 150 - 5, 150.0f, 150.0f, 300, 300, size * (float)Math.sin(time), size, velocity.angle() - 90.0f, 0, 0, 300, 300, false, false);

            batch.setColor(color, color, color, 1.0f);
            batch.draw(heartTexture, position.x - 150, position.y - 150, 150.0f, 150.0f, 300, 300, size * (float)Math.sin(time), size, velocity.angle() - 90.0f, 0, 0, 300, 300, false, false);
            batch.setColor(1, 1, 1, 1);
        }

        public void update(float dt) {
        	position.x += velocity.x * dt;
        	position.y += velocity.y * dt;

        	velocity.x += wind * dt;
        	velocity.y += 20.0f * dt;

        	time += velocity.x * dt / 100.0f;

        	if (position.y > 820.0f) {
                init();
            }
            if (position.x < -100.0f) {
                position.x = 1380.0f;
            }
            if (position.x > 1380.0f) {
                position.x = -100.0f;
            }
        }
    }

    private SpriteBatch batch;
    private Texture heartTexture;
    private Texture logoTexture;
    private Vector2 logoCenter;
    private Vector2 tmp;
    private static final int HEARTS_COUNT = 220;
    private Heart[] hearts;
    private float globalTime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        heartTexture = new Texture("heart.png");
        logoTexture = new Texture("main.png");
        logoCenter = new Vector2(0, 0);
        tmp = new Vector2(0, 0);
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
        logoCenter.set(640.0f, 360.0f + 10.0f * (float)Math.sin(globalTime * 2.0f));

        batch.setColor(0.2f, 0.2f, 0.2f, 0.5f);
        batch.draw(logoTexture, logoCenter.x - 265, logoCenter.y - 245);
        batch.setColor(1, 1, 1, 1);
        batch.draw(logoTexture, logoCenter.x - 260, logoCenter.y - 240);


        for (int i = 0; i < hearts.length; i++) {
            hearts[i].render();
        }
        batch.end();
    }

    public void update(float dt) {
        globalTime += dt;
		for (int i = 0; i < hearts.length; i++) {
			hearts[i].update(dt);
			float rad = 260.0f;
			if (hearts[i].position.dst(logoCenter) < rad) {
                tmp.set(logoCenter).sub(hearts[i].position).nor().scl(-rad).add(logoCenter);
                hearts[i].position.set(tmp);
                hearts[i].velocity.x += Math.signum(hearts[i].position.x - logoCenter.x) * rad * dt;
            }
		}
    }

    @Override
    public void dispose() {
        batch.dispose();
        heartTexture.dispose();
    }
}
