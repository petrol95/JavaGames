package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid {
    private float x;
    private float y;
    private float vx;
    private float vy;
    private static Texture myTexture;

    public Asteroid(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static void setMyTexture(Texture myTexture) {
        Asteroid.myTexture = myTexture;
    }

    public void render(SpriteBatch batch) {
        batch.draw(myTexture, x, y);
    }

    public void update() {
        x += vx;
        y += vy;
        if (x < -myTexture.getWidth()) x = Gdx.graphics.getWidth();
        if (y < -myTexture.getHeight()) y = Gdx.graphics.getHeight();
        if (x > Gdx.graphics.getWidth()) x = -myTexture.getWidth();
        if (y > Gdx.graphics.getHeight()) y = -myTexture.getHeight();
    }
}
