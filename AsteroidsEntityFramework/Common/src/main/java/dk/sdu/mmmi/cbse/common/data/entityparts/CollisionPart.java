package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
/**
 *
 * @author Kasper
 */
public class CollisionPart implements EntityPart {
    
    private int height;
    private int width;
    
    public CollisionPart(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {

    }
    
}
