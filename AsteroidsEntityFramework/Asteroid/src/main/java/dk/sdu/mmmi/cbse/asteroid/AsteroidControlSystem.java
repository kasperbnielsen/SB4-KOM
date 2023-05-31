/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.CollisionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;

/**
 *
 * @author Kasper
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    int counter = 0;
    private Entity asteroid;
    
    float[] random = new float[40];
    boolean empty = true;
    
    @Override
    public void process(GameData gameData, World world) {
        
        for(Entity asteroid : world.getEntities(Asteroid.class)) {
            LifePart lp = asteroid.getPart(LifePart.class);
            
            if(!lp.getIsHit()) counter++;
        }
        
        
        if(empty) {
            for(int i = 0; i < random.length; i++ ) {
                random[i] = (float) Math.random();
            }
            empty = false;
        }
        
        
        if(counter < 3) {
            //create new asteroid
            float x = gameData.getDisplayWidth() * (float) Math.random();
            float y = gameData.getDisplayHeight() * (float) Math.random();
            float radians = 3.1415f * (float) Math.random();
            
            asteroid = new Asteroid();
            asteroid.add(new PositionPart(x, y, radians));
            asteroid.add(new MovingPart(0, 500, 100, 0));
            asteroid.add(new CollisionPart(25, 25));
            asteroid.add(new LifePart(2));
            world.addEntity(asteroid);
        }
        
        counter = 0;
        
        for(Entity asteroid : world.getEntities(Asteroid.class)) {
            float[] shapeX = new float[1];
            float[] shapeY = new float[1];
            LifePart lifePart = asteroid.getPart(LifePart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            
            
            movingPart.setUp(true);
            movingPart.setRight(false);
            movingPart.setLeft(false);
            movingPart.setWrap(true);
            movingPart.process(gameData, asteroid);
            
            
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            positionPart.process(gameData, asteroid);
            
            asteroid.setColor(new int[] {2, 1, 0, 0});
            asteroid.setShape(2);
            if(!lifePart.getIsHit()) asteroid.setRadius(25);
            else if(lifePart.getIsHit()) asteroid.setRadius(10);
            
            float x = positionPart.getX();
            float y = positionPart.getY();
            
            shapeX[0] = x;
            shapeY[0] = y;

            asteroid.setShapeX(shapeX);
            asteroid.setShapeY(shapeY);
        }
    }
    
    
}
