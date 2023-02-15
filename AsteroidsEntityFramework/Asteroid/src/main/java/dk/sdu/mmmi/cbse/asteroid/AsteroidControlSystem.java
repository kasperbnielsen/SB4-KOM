/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

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
            counter++;
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
            world.addEntity(asteroid);
        }
        
        counter = 0;
        
        for(Entity asteroid : world.getEntities(Asteroid.class)) {
            
            float[] shapeX = new float[4];
            float[] shapeY = new float[4];
            
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            movingPart.setUp(true);
            movingPart.setRight(false);
            movingPart.setLeft(false);
            movingPart.setWrap(true);
            
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            positionPart.process(gameData, asteroid);
            movingPart.process(gameData, asteroid);
            
            asteroid.setColor(new int[] {2, 1, 0, 0});
            
            float x = positionPart.getX();
            float y = positionPart.getY();
            float radians = positionPart.getRadians();
            
            shapeX[0] = (float) (x + Math.cos(radians + (random[0] * 3.1415f)) * (random[8] * 100));
            shapeY[0] = (float) (y + Math.sin(radians + (random[1] * 3.1415f)) * (random[9] * 10));

            shapeX[1] = (float) (x + Math.cos(radians + (random[2] * 3.1415f)) * (random[10] * 100));
            shapeY[1] = (float) (y + Math.sin(radians + (random[3] * 3.1415f)) * (random[11] * 100));

            shapeX[2] = (float) (x + Math.cos(radians + (random[4] * 3.1415f)) * (random[12] * 100));
            shapeY[2] = (float) (y + Math.sin(radians + (random[5] * 3.1415f)) * (random[13] * 100));

            shapeX[3] = (float) (x + Math.cos(radians + (random[6] * 3.1415f)) * (random[14] * 100));
            shapeY[3] = (float) (y + Math.sin(radians + (random[7] * 3.1415f)) * (random[15] * 100));

            asteroid.setShapeX(shapeX);
            asteroid.setShapeY(shapeY);
        }
    }
    
    
}
