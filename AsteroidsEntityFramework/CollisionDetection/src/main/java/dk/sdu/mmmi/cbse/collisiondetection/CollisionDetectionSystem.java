package dk.sdu.mmmi.cbse.collisiondetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.CollisionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import java.awt.geom.Ellipse2D;


/**
 *
 * @author Kasper
 */
public class CollisionDetectionSystem implements IPostEntityProcessingService {
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            for (Entity asteroid : world.getEntities(Asteroid.class)) {
                LifePart asteroidLifePart = asteroid.getPart(LifePart.class);
                if(checkCollision(asteroid, bullet, bullet.getFriendly())) {
                    if(asteroidLifePart.getIsHit()) world.removeEntity(asteroid);
                    else asteroidLifePart.setIsHit(true);
                    world.removeEntity(bullet);
                }
                for(Entity player : world.getEntities(Player.class)) {
                    if(checkCollision(player, bullet, !bullet.getFriendly())) world.removeEntity(player);
                    if(checkCollision(player, asteroid, true)) world.removeEntity(player);
                }
            }
            for(Entity enemy : world.getEntities(Enemy.class)) {
                if(checkCollision(enemy, bullet, bullet.getFriendly()))
                    world.removeEntity(enemy);
            }
        }
        

    }
    
    public <T extends Entity> boolean checkCollision(T e, T e2, boolean friendly) {
        
            CollisionPart collisionPart = e.getPart(CollisionPart.class);
            PositionPart positionPart = e.getPart(PositionPart.class);
            
            Ellipse2D area = new Ellipse2D.Float(
                    positionPart.getX(),
                    positionPart.getY(),
                    collisionPart.getWidth(),
                    collisionPart.getHeight()
            );
            
            CollisionPart collisionPart2 = e2.getPart(CollisionPart.class);
            PositionPart positionPart2 = e2.getPart(PositionPart.class);
            
            Ellipse2D area2 = new Ellipse2D.Float(
                    positionPart2.getX(),
                    positionPart2.getY(),
                    collisionPart2.getWidth(),
                    collisionPart2.getHeight()
            );
            
            if(area.intersects(area2.getX(), area2.getY(), area2.getWidth(), area2.getHeight()) && friendly) {
                    return true;
            } else return false;
            
    }

}
