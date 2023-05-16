package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
