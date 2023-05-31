/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

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
import java.util.ArrayList;


/**
 *
 * @author Kasper
 */
public class CollisionDetectionSystem implements IPostEntityProcessingService {

    ArrayList<Ellipse2D> bulletList = new ArrayList<Ellipse2D>();
    ArrayList<Ellipse2D> asteroidList = new ArrayList<Ellipse2D>();
    
    boolean playerCollisionDetected;
    
    public boolean getPlayerCollisionDetected() {
        return playerCollisionDetected;
    }
    
    @Override
    public void process(GameData gameData, World world) {
        checkForCollision(world);
    }

    public void checkForCollision(World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            CollisionPart collisionPart = bullet.getPart(CollisionPart.class);
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            
            Ellipse2D area = new Ellipse2D.Float(
                    positionPart.getX(),
                    positionPart.getY(),
                    collisionPart.getWidth(),
                    collisionPart.getHeight()
            );
            for (Entity asteroid : world.getEntities(Asteroid.class)) {
                CollisionPart asteroidcollisionPart = asteroid.getPart(CollisionPart.class);
                PositionPart asteroidpositionPart = asteroid.getPart(PositionPart.class);
                LifePart asteroidlifePart = asteroid.getPart(LifePart.class);
                
                Ellipse2D area2 = new Ellipse2D.Float(
                    asteroidpositionPart.getX(),
                    asteroidpositionPart.getY(),
                    asteroidcollisionPart.getWidth(),
                    asteroidcollisionPart.getHeight()
                );
                if(area.intersects(area2.getX(), area2.getY(), area2.getWidth(), area2.getHeight()) && bullet.getFriendly()) {
                    if(asteroidlifePart.getIsHit()) world.removeEntity(asteroid);
                    else asteroidlifePart.setIsHit(true);
                    world.removeEntity(bullet);
                }
                
                for(Entity player : world.getEntities(Player.class)) {
                    CollisionPart playercollisionPart = player.getPart(CollisionPart.class);
                    PositionPart playerpositionPart = player.getPart(PositionPart.class);
                    LifePart playerlifePart = player.getPart(LifePart.class);
                    
                    Ellipse2D playerArea = new Ellipse2D.Float (
                        playerpositionPart.getX(),
                        playerpositionPart.getY(),
                        playercollisionPart.getWidth(),
                        playercollisionPart.getHeight()
                    );
                    
                    if(playerArea.intersects(area.getX(), area.getY(), area.getWidth(), area.getHeight()) && !bullet.getFriendly()) {
                        playerlifePart.setIsHit(true);
                        world.removeEntity(player);
                        playerCollisionDetected = true;
                    }
                    if(playerArea.intersects(area2.getX(), area2.getY(), area2.getWidth(), area2.getHeight())) {
                        playerlifePart.setIsHit(true);
                        world.removeEntity(player);
                        playerCollisionDetected = true;
                    }
                }
                    
            }
            
            for(Entity enemy : world.getEntities(Enemy.class)) {
                CollisionPart enemyCollisionPart = enemy.getPart(CollisionPart.class);
                PositionPart enemyPositionPart = enemy.getPart(PositionPart.class);
                
                Ellipse2D enemyArea = new Ellipse2D.Float (
                        enemyPositionPart.getX(),
                        enemyPositionPart.getY(),
                        enemyCollisionPart.getWidth(),
                        enemyCollisionPart.getHeight()
                );
                
                if(enemyArea.intersects(area.getX(), area.getY(), area.getWidth(), area.getHeight()) && bullet.getFriendly()) {
                    world.removeEntity(enemy);
                }
            }
            
        }
    }

}
