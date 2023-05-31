/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.CollisionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;


/**
 *
 * @author tubni
 */
public class EnemyControlSystem implements IEntityProcessingService {

    boolean isMoving = false;
    private int delay = 20;
    PositionPart positionPart;
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            double random = Math.random();
            
            if(random < 0.2) {
                movingPart.setLeft(true);
            }
            else if (random < 0.4) {
                movingPart.setRight(true);
            }
            else if (random < 0.6) {
                movingPart.setUp(false);
                movingPart.setLeft(false);
                movingPart.setRight(false);
            } else if (random < 0.65) {
                movingPart.setUp(true);
            }
            
            enemy.setColor(new int[] {1, 0, 0, 0 });
            enemy.setShape(1);
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
            
            if(delay <= 0) {
                Bullet bullet = new Bullet();
                bullet.setFriendly(false);
                bullet.add(new PositionPart(positionPart.getX(), positionPart.getY(), positionPart.getRadians()));
                bullet.add(new MovingPart(0, 100, 100, 0));
                bullet.add(new CollisionPart(3, 3));
                bullet.add(new LifePart(1));
                world.addEntity(bullet);
                delay = 20;
            } else {
                delay--;
            }
        }
        
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
}
