module Core {
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;
    requires CommonAsteroid;
    requires java.desktop;
    requires CommonPlayer;
    requires CollisionDetection;
    requires com.badlogic.gdx;
    requires spring.context;
    requires java.sql;
    
    exports dk.sdu.mmmi.cbse.main;
    opens dk.sdu.mmmi.cbse.main to spring.core;
    
    
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}