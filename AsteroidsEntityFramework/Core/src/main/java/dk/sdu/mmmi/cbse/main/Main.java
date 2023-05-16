package dk.sdu.mmmi.cbse.main;

public class Main {
	
	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		config.setWindowSizeLimits(1000,600,1000,600);

		new LwjglApplication(new Game(), config);
	}
	
}
