import java.util.List;

public class PantallaDeJuego {
    public void renderizar(Jugador jugador, List<Obstaculo> obstaculos) {

        System.out.println("Jugador en (" + jugador.getX() + ", " + jugador.getY() + ")");
        for (Obstaculo obstaculo : obstaculos) {
            System.out.println("Obstáculo en (" + obstaculo.getX() + ", " + obstaculo.getY() + ")");
        }
    }
}