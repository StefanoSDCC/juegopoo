public class Obstacle {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        // Movimiento básico hacia la izquierda
        x -= 2;
        if (x < 0) {
            x = 800; // Resetear a la derecha de la pantalla
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
