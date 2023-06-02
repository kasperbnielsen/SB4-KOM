package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.badlogic.gdx.ApplicationListener;

public class Main {
	
	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		config.setWindowSizeLimits(1000,600,1000,600);
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
                
                for (String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName);
                }
		

		new Lwjgl3Application((ApplicationListener) context.getBean(Game.class), config);
	}
	
}
