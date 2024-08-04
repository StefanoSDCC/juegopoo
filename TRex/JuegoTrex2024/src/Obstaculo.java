public class Obstaculo {
    private int x;
    private int y;

    public Obstaculo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void actualizar() {

        x -= 2;
        if (x < 0) {
            x = 800;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}