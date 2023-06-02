import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module CollisionDetection {
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    requires CommonPlayer;
    requires CommonAsteroid;
    requires java.desktop;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisiondetection.CollisionDetectionSystem;
    
    exports dk.sdu.mmmi.cbse.collisiondetection;
}