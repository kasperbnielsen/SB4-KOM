/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import dk.sdu.mmmi.cbse.collisiondetection.CollisionDetectionSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.CollisionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
    
    
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
        
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    
    @Test
    public void testPlayerCollision() {
        
        World world = mock(World.class);
        GameData gameData = mock(GameData.class);
        
        Player mockPlayer = new Player();
        mockPlayer.add(new PositionPart(100, 100, 3.14f));
        mockPlayer.add(new CollisionPart(8, 8));
        LifePart lp = new LifePart(1);
        mockPlayer.add(lp);
        world.addEntity(mockPlayer);
        
        Asteroid mockAsteroid = new Asteroid();
        mockAsteroid.add(new PositionPart(100, 100, 3.14f));
        mockAsteroid.add(new CollisionPart(25, 25));
        world.addEntity(mockAsteroid);
        
        
        CollisionDetectionSystem mockSystem = mock(CollisionDetectionSystem.class);
        mockSystem.process(gameData, world);
        mockSystem.checkForCollision(world);
        
        //assertEquals( true, lp.getIsHit());
        
    }
}
