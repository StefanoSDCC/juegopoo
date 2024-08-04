import java.util.ArrayList;

public class Juego {
    private Jugador jugador;
    private ArrayList<Obstaculo> obstaculos;
    private PantallaDeJuego pantallaDeJuego;
    private boolean enEjecucion;

    public Juego() {
        jugador = new Jugador();
        obstaculos = new ArrayList<>();
        pantallaDeJuego = new PantallaDeJuego();
        enEjecucion = true;
        generarObstaculos();
    }

    public void iniciar() {
        while (enEjecucion) {
            actualizar();
            pantallaDeJuego.renderizar(jugador, obstaculos);
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizar() {
        jugador.actualizar();
        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.actualizar();
        }
        verificarColisiones();
    }

    private void verificarColisiones() {
        for (Obstaculo obstaculo : obstaculos) {
            if (jugador.colisionaCon(obstaculo)) {
                enEjecucion = false;
                System.out.println("¡Juego Terminado!");
            }
        }
    }

    private void generarObstaculos() {

        for (int i = 0; i < 5; i++) {
            obstaculos.add(new Obstaculo((int)(Math.random() * 800), (int)(Math.random() * 600)));
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
}