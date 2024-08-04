public class Jugador {
    private int x;
    private int y;
    private int velocidadY;
    private boolean estaSaltando;

    public Jugador() {
        x = 100;
        y = 100;
        velocidadY = 0;
        estaSaltando = false;
    }

    public void actualizar() {
        if (estaSaltando) {
            y += velocidadY;
            velocidadY += 1;

            if (y >= 100) {
                y = 100;
                estaSaltando = false;
                velocidadY = 0;
            }
        }
    }

    public void saltar() {
        if (!estaSaltando) {
            estaSaltando = true;
            velocidadY = -10;
        }
    }

    public boolean colisionaCon(Obstaculo obstaculo) {

        return (this.x < obstaculo.getX() + 50 && this.x + 50 > obstaculo.getX() &&
                this.y < obstaculo.getY() + 50 && this.y + 50 > obstaculo.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}