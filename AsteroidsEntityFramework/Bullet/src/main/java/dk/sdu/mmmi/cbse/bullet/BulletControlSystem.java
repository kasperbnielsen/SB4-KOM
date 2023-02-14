/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
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
            
            movingPart.setUp(true);
            movingPart.setWrap(false);
            
            bullet.setTimer(bullet.getTimer() - 1);
            
            if(bullet.getTimer() <= 0) {
                world.removeEntity(bullet);
            }
            
            movingPart.process(gameData, bullet);
            positionPart.process(gameData,bullet);

            bullet.setColor(new int[] {4, 1, 1, 1});
            
            updateShape(bullet);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        shapex[0] = (float) (x - 2);
        shapey[0] = (float) (y - 2);

        shapex[1] = (float) (x + 2);
        shapey[1] = (float) (y + 2);

        shapex[2] = (float) (x + 2);
        shapey[2] = (float) (y - 2);

        shapex[3] = (float) (x - 2);
        shapey[3] = (float) (y + 2);
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);    
        }   
}
