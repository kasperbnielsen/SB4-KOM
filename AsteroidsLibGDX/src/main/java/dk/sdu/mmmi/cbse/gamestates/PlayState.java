package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
        
        private Enemy enemy;
        
        private List<Bullet> bullet = new ArrayList<Bullet>();
        
        private int delay;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
		
		player = new Player();
		
                enemy = new Enemy();
                
                delay = 0;
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
                
                enemy.update(dt);
                
                if(Math.random() > 0.95) {
                    bullet.add(new Bullet(enemy.getX(), enemy.getY(), enemy.getRadians()));
                }
                
                delay++;
                
                if(GameKeys.isDown(GameKeys.SPACE) && delay > 9) {
                    bullet.add(new Bullet(player.getX(), player.getY(), player.getRadians()));
                    delay = 0;
                }
                
                for(int i = 0; i < bullet.size(); i++) {
                    bullet.get(i).update(dt);
                    if(bullet.get(i).isDead()) {
                        bullet.remove(i);
                    }
                }
	}
	
	public void draw() {
		player.draw(sr);
                
                enemy.draw(sr);
                
                for(Bullet b : bullet) {
                    b.draw(sr);
                }
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {}
	
}









