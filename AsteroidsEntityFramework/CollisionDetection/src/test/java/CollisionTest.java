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
    
    private CollisionDetectionSystem collisionDetection;
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    //Player entity
    Player mockPlayer = new Player();
        
    //Asteroid entity
    Asteroid mockAsteroid = new Asteroid();
        

    
    
    
    @Test
    public void testPlayerCollision() {
        
        World world = mock(World.class);
        GameData gameData = mock(GameData.class);
        
        mockAsteroid.add(new PositionPart(100, 100, 3.14f));
        mockAsteroid.add(new CollisionPart(25, 25));
        world.addEntity(mockAsteroid);  
        
        mockPlayer.add(new PositionPart(101, 101, 3.14f));
        mockPlayer.add(new CollisionPart(8, 8));
        world.addEntity(mockPlayer);
        
        collisionDetection = new CollisionDetectionSystem();
        
        collisionDetection.process(gameData, world);
        
        assertTrue(collisionDetection.checkCollision(mockPlayer, mockAsteroid, true));
        
    }
}
