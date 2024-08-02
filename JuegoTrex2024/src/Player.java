public class Player {
    private int x;
    private int y;
    private int velocityY;
    private boolean isJumping;

    public Player() {
        x = 100;
        y = 100;
        velocityY = 0;
        isJumping = false;
    }

    public void update() {
        if (isJumping) {
            y += velocityY;
            velocityY += 1; // Gravedad

            if (y >= 100) { // Suelo
                y = 100;
                isJumping = false;
                velocityY = 0;
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = -10; // Velocidad de salto
        }
    }

    public boolean collidesWith(Obstacle obstacle) {
        // Simple colisión basada en posición
        return (this.x < obstacle.getX() + 50 && this.x + 50 > obstacle.getX() &&
                this.y < obstacle.getY() + 50 && this.y + 50 > obstacle.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
