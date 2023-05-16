package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float radius;
    private int[] color = new int[4];
    private int shape;
    private Map<Class, EntityPart> parts;
    private int timer = 500;
    private boolean friendly;
    
    public Entity() {
        parts = new ConcurrentHashMap<>();
    }
    
    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }
    
    public void remove(Class partClass) {
        parts.remove(partClass);
    }
    
    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }
    
    public void setRadius(float r){
        this.radius = r;
    }
    
    public float getRadius(){
        return radius;
    }
    
    public void setColor(int[] colors) {
        this.color = colors;
    }
    
    public int[] getColor() {
        return color;
    }

    public String getID() {
        return ID.toString();
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }
    
    public int getTimer() {
        return timer;
    }
    
    public void setTimer(int timer) {
        this.timer = timer;
    }
    
    public void setShape(int shape) {
        this.shape = shape;
    }
    
    public int getShape() {
        return shape;
    }
    
    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }
    
    public boolean getFriendly() {
        return friendly;
    }
}
