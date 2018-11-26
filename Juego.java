
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Juego que contiene todo un juego de Blackjack que ejecutar, va
 * avanzando por cada jugador simulando el juego real (si un jugador pierde ya
 * no puede jugar, si uno tiene 21 gana, si todos pasan se elige un ganador con
 * los scores, etc)
 *
 * @author Felipe Pacheco
 * @author Kendall Tenorio
 */
public class Juego {

  ArrayList<Carta> baraja;
  ArrayList<Jugador> jugadores;
  String ganador;
  int indiceJugadorActual;
  int cantidadPerdidos;
  int cantidadPeticiones;
  boolean acabado;
  boolean iniciado;
  Random random;

  /**
   * Constructor de un juego, inicializa todos los atributos en 0, false, un
   * nuevo Random o un nuevo ArrayList (según corresponda), luego crea la baraja
   * con la que se jugará mediantes dos arrays de strings con las figuras y sus
   * valores tal que los mezcla entre sí para así formar todas las combinaciones
   * de cartas
   */
  public Juego() {
    random = new Random();
    indiceJugadorActual = 0;
    cantidadPerdidos = 0;
    cantidadPeticiones = 0;
    iniciado = false;
    acabado = false;
    ganador = "";
    jugadores = new ArrayList<>();
    baraja = new ArrayList<>();

    String figuras[] = {"Trébol", "Corazón", "Espada", "Diamante"};
    String valores[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    for (String figura : figuras) {
      for (String valor : valores) {
        baraja.add(new Carta(figura, valor));
      }
    }
  }

  /**
   * Añade un jugador al juego, si y hay tres jugadores indica que no se puede
   * agregar
   *
   * @param jugador
   * @return string que indica si se pudo agregar o no
   */
  public String annadirJugador(Jugador jugador) {
    if (jugadores.size() == 3) {
      return "Ya no se pueden agregar más jugadores";
    } else {
      jugadores.add(jugador);
      return "Jugador agregado";
    }
  }

  /**
   * Inicia el juego, si ya está iniciado retorna, sino indica que se ha
   * iniciado y le reparte a cada jugador dos cartas
   */
  public void iniciarJuego() {
    if (iniciado) {
      return;
    }
    iniciado = true;
    for (int i = 0; i < 2; i++) {
      for (Jugador jugador : jugadores) {
        jugador.agregarCarta(escogerCarta());
      }
    }
  }

  /**
   * Indica si el juego se puede empezar (hay tres jugadores) o no
   *
   * @return true si se puede empezar o false sino
   */
  public boolean isEmpezable() {
    return (jugadores.size() == 3);
  }

  /**
   * Escoge un número al azar entre 0 y la cantidad de cartas de la baraja,
   * remueve esa carta de la baraja y la retorna
   *
   * @return
   */
  private Carta escogerCarta() {
    int indice = random.nextInt(baraja.size());
    return baraja.remove(indice);
  }

  public int getIndiceJugadorActual() {
    return indiceJugadorActual;
  }

  /**
   * Reinicia el juego para ser jugado de nuevo por otros jugadores nuevos, para
   * ellos reinicia todos los atributo a sus valores predeterminados, limpiando
   * los ArrayLists de jugadores y la bajara, y creando de nuevo la baraja
   */
  public void restart() {
    cantidadPeticiones++;
    if (cantidadPeticiones == 3) {
      indiceJugadorActual = 0;
      cantidadPerdidos = 0;
      cantidadPeticiones = 0;
      iniciado = false;
      acabado = false;
      ganador = "";
      jugadores.clear();
      baraja.clear();

      String figuras[] = {"Trébol", "Corazón", "Espada", "Diamante"};
      String valores[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
      for (String figura : figuras) {
        for (String valor : valores) {
          baraja.add(new Carta(figura, valor));
        }
      }
    }
  }

  /**
   * Recibe una respuesta que indica si el jugador que tiene el turno a jugar
   * quiere tomar una carta o pasar, se detallará el código abajo
   *
   * @param respuesta
   * @return string que indica lo que ha sucedido en el turno, si alguien ganó o
   * perdió, o si el turno solo fue jugado normalmente
   */
  public String ejecutarTurno(String respuesta) {
    // Se obtiene el jugador que va a jugar este turno con el indiceJugadorActual
    Jugador jugador = jugadores.get(indiceJugadorActual);
    if (!jugador.estaEnJuego()) {
      return "Este jugador no puede jugar";
    }
    siguienteJugador();
    if (respuesta.equals("Carta")) {
      jugador.resetearPasadas();
      Carta carta = escogerCarta();
      jugador.agregarCarta(carta);
      if (jugador.sumarCartas() == 21) {
        acabado = true;
        return "Ha ganado " + jugador.getNombre();
      } else if (jugador.sumarCartas() > 21) {
        cantidadPerdidos++;
        jugador.perder();
        if (cantidadPerdidos == 2) {
          acabado = true;
          return "Ha perdido " + jugador.getNombre() + " y por ello ha ganado " + elegirGanadorFinal().getNombre();
        }
        return "Ha perdido " + jugador.getNombre();
      }
    } else {
      jugador.pasar();
      if (todosPasan()) {
        acabado = true;
        return "Todos han pasado un turno\n El juego ha acabado\nEl ganador del juego es " + elegirGanador().getNombre();
      }
    }
    return "Turno de " + jugador.getNombre() + " jugado";
  }

  public Jugador getJugador(String nombreJugador) {
    for (Jugador jugador : jugadores) {
      if (jugador.getNombre().equals(nombreJugador)) {
        return jugador;
      }
    }
    return null;
  }

  public void siguienteJugador() {
    indiceJugadorActual = (indiceJugadorActual + 1) % 3;
  }

  public boolean getAcabado() {
    return acabado;
  }

  private Jugador elegirGanadorFinal() {
    for (Jugador jugador : jugadores) {
      if (jugador.estaEnJuego()) {
        ganador = jugador.getNombre();
        return jugador;
      }
    }
    return null;
  }

  public String getGanador() {
    return ganador;
  }

  private Jugador elegirGanador() {
    int mayorCalificacion = 0;
    Jugador mejorJugador = jugadores.get(0);
    for (Jugador jugador : jugadores) {
      int suma = jugador.sumarCartas();
      if (suma <= 21 && suma > mayorCalificacion) {
        mayorCalificacion = suma;
        mejorJugador = jugador;
      }
    }
    ganador = mejorJugador.getNombre();
    return mejorJugador;
  }

  private boolean todosPasan() {
    boolean si = true;
    for (Jugador jugador : jugadores) {
      if (!jugador.haPasado()) {
        return false;
      }
    }
    return si;
  }

  public ArrayList<Carta> getCartas(String nombreJugador) {
    for (Jugador jugador : jugadores) {
      if (jugador.getNombre().equals(nombreJugador)) {
        return jugador.getCartas();
      }
    }
    return null;
  }

  public int getSumaJugador(String nombreJugador) {
    for (Jugador jugador : jugadores) {
      if (jugador.getNombre().equals(nombreJugador)) {
        return jugador.sumarCartas();
      }
    }
    return 0;
  }

  public ArrayList<Jugador> getJugadores() {
    return jugadores;
  }

  public ArrayList<Carta> getBaraja() {
    return baraja;
  }

  public static void main(String[] args) {
    Juego juego = new Juego();
  }
}
