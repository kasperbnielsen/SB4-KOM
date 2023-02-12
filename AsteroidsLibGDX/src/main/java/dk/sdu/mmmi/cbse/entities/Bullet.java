/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author Kasper
 */
public class Bullet extends SpaceObject {
    
    int timer = 100;
    
    public boolean isDead() {
        if(timer < 0) return true;
        return false;
    }
    
    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        
        speed = 150;
        
        //shapex = new float[4];
        //shapey = new float[4];
        
        this.radians = radians;
    }
    
    public void update(float dt) {
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        
        x += dx * dt;
        y += dy * dt;
        
        timer -= 1;
    }
    
    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        
        sr.setColor(2, 1, 3, 4);
        
        sr.circle(x, y, 3);
        
        sr.end();
    }
}
