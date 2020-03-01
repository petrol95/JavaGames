package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {

    private Vector2 position;
    private Vector2 velocity;
    private static Texture myTexture;

    public Asteroid(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public static void setMyTexture(Texture myTexture) {
        Asteroid.myTexture = myTexture;
    }

    public void render(SpriteBatch batch) {
        batch.draw(myTexture, position.x - 20, position.y - 20);
    }

    public void update() {
        position.add(velocity);
        velocity.scl(0.995f);
        if (position.x < -myTexture.getWidth())
            position.x = Gdx.graphics.getWidth();
        if (position.y < -myTexture.getHeight())
            position.y = Gdx.graphics.getHeight();
        if (position.x > Gdx.graphics.getWidth())
            position.x = -myTexture.getWidth();
        if (position.y > Gdx.graphics.getHeight())
            position.y = -myTexture.getHeight();

        if (InputHandler.isPressed()) {
            if (position.cpy().sub(InputHandler.getMousePosition()).len() < 100)
            velocity = position.cpy().sub(InputHandler.getMousePosition()).nor().scl(5.0f);
        }
    }
}
