package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;
    private float expiration;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }  
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        System.out.println("outside if");
        if(isHit) {
            life = life-1;
            isHit = false;
            System.out.println("in here");
        }
    }
}
