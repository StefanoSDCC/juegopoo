import java.util.List;

public class GameScreen {
    public void render(Player player, List<Obstacle> obstacles) {
        // Renderización básica en consola
        System.out.println("Player at (" + player.getX() + ", " + player.getY() + ")");
        for (Obstacle obstacle : obstacles) {
            System.out.println("Obstacle at (" + obstacle.getX() + ", " + obstacle.getY() + ")");
        }
    }
}
