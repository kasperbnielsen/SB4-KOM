/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;

/**
 *
 * @author tubni
 */
public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            
            movingPart.setUp(true);
            movingPart.setWrap(false);
            
            
            bullet.setTimer(bullet.getTimer() - 1);
            
            if(bullet.getTimer() <= 0) {
                world.removeEntity(bullet);
            }
            
            if(lifePart.getLife() <= 0) {
                world.removeEntity(bullet);
            }
            
            movingPart.process(gameData, bullet);
            positionPart.process(gameData,bullet);

            bullet.setColor(new int[] {4, 1, 1, 1});
            bullet.setShape(2);
            bullet.setRadius(3);
            
            updateShape(bullet);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        shapex[0] = x;
        shapey[0] = y;
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);    
        }   
}
