import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private GameScreen gameScreen;
    private boolean isRunning;

    public Game() {
        player = new Player();
        obstacles = new ArrayList<>();
        gameScreen = new GameScreen();
        isRunning = true;
        generateObstacles();
    }

    public void start() {
        while (isRunning) {
            update();
            gameScreen.render(player, obstacles);
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }
        checkCollisions();
    }

    private void checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (player.collidesWith(obstacle)) {
                isRunning = false; // Termina el juego en caso de colisión
                System.out.println("Game Over!");
            }
        }
    }

    private void generateObstacles() {
        // Generar obstáculos en posiciones aleatorias
        for (int i = 0; i < 5; i++) {
            obstacles.add(new Obstacle((int)(Math.random() * 800), (int)(Math.random() * 600)));
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
