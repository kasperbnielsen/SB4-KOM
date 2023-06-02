package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;


/**
 *
 * @author tubni
 */
public class BulletPlugin implements IGamePluginService{
    
    public BulletPlugin() {
        
    }

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity bullet : world.getEntities(Bullet.class)) {
            world.removeEntity(bullet);
        }
    }
    
}
