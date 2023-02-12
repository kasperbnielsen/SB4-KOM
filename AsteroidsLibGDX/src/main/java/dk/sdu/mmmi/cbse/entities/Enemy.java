/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

/**
 *
 * @author Kasper
 */

public class Enemy extends SpaceObject {
    
    String turning = "";
    public Enemy() {
        x = (float) Math.random() * Game.WIDTH;
        y = (float) Math.random() * Game.HEIGHT;
       
        
        speed = 50;
        
        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;
    }
    
    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getRadians() {
        return radians;
    }
    
    public void update(float dt) {
        
        double random = Math.random();
        
        if (random > 0.8) {
            turning = "";
        } else if(random < 0.2 || turning == "left") {
            radians += rotationSpeed * dt;
            turning = "left";
        } else if (random < 0.4 || turning == "right") {
            radians -= rotationSpeed *dt;
            turning = "right";
        }
        
        
            
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        
        x += dx * dt;
        y += dy * dt;
        
        setShape();
        
        wrap();
    }
    
    public void draw(ShapeRenderer sr) {
        sr.setColor(4, 0, 0, 0);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }
        
        sr.end();
    }
}
