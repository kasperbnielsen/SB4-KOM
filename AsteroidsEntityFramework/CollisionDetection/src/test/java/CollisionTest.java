import dk.sdu.mmmi.cbse.collisiondetection.CollisionDetectionSystem;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.CollisionPart;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Kasper
 */
public class CollisionTest {
    
    public CollisionTest() {
    }

    //Player entity
    Player mockPlayer = new Player();
        
    //Asteroid entitiy
    Asteroid mockAsteroid = new Asteroid();
    
    //Bullet entity
    Bullet mockBullet = new Bullet();
    
    //Enemy entity
    Enemy mockEnemy = new Enemy();
        
    @Test
    public void testPlayerCollision() {
        
        World world = mock(World.class);
        
        mockAsteroid.add(new PositionPart(100, 100, 3.14f));
        mockAsteroid.add(new CollisionPart(25, 25));
        world.addEntity(mockAsteroid);  
        
        mockPlayer.add(new PositionPart(101, 101, 3.14f));
        mockPlayer.add(new CollisionPart(8, 8));
        world.addEntity(mockPlayer);
        
        CollisionDetectionSystem collisionDetection = new CollisionDetectionSystem();
        
        assertTrue(collisionDetection.checkCollision(mockPlayer, mockAsteroid, true));       
    }
    
    @Test
    public void testNoCollision() {
        World world = mock(World.class);
        
        mockEnemy.add(new PositionPart(100, 100, 3.14f));
        mockEnemy.add(new CollisionPart(10, 10));
        world.addEntity(mockEnemy);  
        
        mockBullet.add(new PositionPart(125, 125, 3.14f));
        mockBullet.add(new CollisionPart(3, 3));
        world.addEntity(mockBullet);
        
        CollisionDetectionSystem collisionDetection = new CollisionDetectionSystem();
        
        assertFalse(collisionDetection.checkCollision(mockEnemy, mockBullet, true));
    }
}
