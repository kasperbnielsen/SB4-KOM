/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.CollisionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
/**
 *
 * @author Kasper
 */
public class AsteroidSplitter implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for(Entity e: world.getEntities(Asteroid.class)) {
            PositionPart pos = e.getPart(PositionPart.class);
            LifePart life = e.getPart(LifePart.class);
            
            if(life.getIsHit()) {
                splitAsteroid(pos, world);
                life.setIsHit(false);
                life.setLife(1);
            }
        }
    }

    private void splitAsteroid(PositionPart pos, World world) {
        float x = pos.getX();
        float y = pos.getY();
            
        Entity asteroid = new Asteroid();
        asteroid.add(new PositionPart(x, y, (float) (3.1415f * Math.random()))); 
        asteroid.add(new MovingPart(0, 200, 300, 0));
        asteroid.add(new CollisionPart(10, 10));
        asteroid.add(new LifePart(1));
        
        world.addEntity(asteroid);
    }
}
