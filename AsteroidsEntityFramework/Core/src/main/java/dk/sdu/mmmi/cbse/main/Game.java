package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private List<IPostEntityProcessingService> entityPostProcessors = new ArrayList<>();
    private World world = new World();

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        /*IGamePluginService playerPlugin = new PlayerPlugin();

        IEntityProcessingService playerProcess = new PlayerControlSystem();
        entityPlugins.add(playerPlugin);
        entityProcessors.add(playerProcess);
        
        IGamePluginService enemyPlugin = new EnemyPlugin();
        
        IEntityProcessingService enemyProcess = new EnemyControlSystem();
        entityPlugins.add(enemyPlugin);
        entityProcessors.add(enemyProcess);
        
        IGamePluginService bulletPlugin = new BulletPlugin();
        
        IEntityProcessingService bulletProcess = new BulletControlSystem();
        entityPlugins.add(bulletPlugin);
        entityProcessors.add(bulletProcess);
        
        IGamePluginService asteroidPlugin = new AsteroidPlugin();
        
        IEntityProcessingService asteroidProcess = new AsteroidControlSystem();
        entityPlugins.add(asteroidPlugin);
        entityProcessors.add(asteroidProcess);
        
        IPostEntityProcessingService collisionProcess = new CollisionDetectionSystem();
        entityPostProcessors.add(collisionProcess);*/
        
        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getProccesingServices()) {
            entityProcessorService.process(gameData, world);
        }
        
        for(IPostEntityProcessingService postEntityProcessorService : getPostProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            
            entity.getRadius();
            int[] colors = entity.getColor();
            sr.setColor(colors[0], colors[1], colors[2], colors[3]);
            float radius = entity.getRadius();
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();
            
            
            if(entity.getShape() == 1) {
                sr.begin(ShapeRenderer.ShapeType.Line);

                for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                }   

                sr.end();
            } else if(entity.getShape() == 2) {
                sr.begin(ShapeRenderer.ShapeType.Filled);
                
                sr.circle(shapex[0], shapey[0], radius);
                
                sr.end();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
    
    /*private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getProccesingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    
       private Collection<? extends IPostEntityProcessingService> getPostProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }*/
    
    private Collection<? extends IGamePluginService> getPluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }

    private Collection<? extends IEntityProcessingService> getProccesingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }
    
    private Collection<? extends IPostEntityProcessingService> getPostProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
}
