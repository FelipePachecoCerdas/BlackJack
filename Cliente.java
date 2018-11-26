
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase Cliente que representa un Cliente conectado a un servidor, tiene el
 * método run para ser ejecutado en forma de hilo y así correr junto con el
 * servidor
 *
 * @author Felipe Pacheco
 * @author Kendall Tenorio
 */
public class Cliente {

  String nombre;
  String nombreJug2;
  String nombreJug3;

  /**
   * Constructor de cliente
   */
  public Cliente() {
  }

  /**
   * Método run que permite ejecutar todo lo relacionado con el cliente, recibe
   * informacion del servidor y permite mostrarla en la interfaz gráfica, abajo
   * se explicará paso a paso lo que ocurre
   *
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void run() throws IOException, ClassNotFoundException {

    // Crea una ventana de login y la muestra al usuario
    VentanaLogin login = new VentanaLogin();
    login.setVisible(true);

    // Inicializa el socket con la dirección IP y el puerto 
    System.out.println("Empezando la ejecución");
    String direccion = "172.18.34.174";//"192.168.1.5";
    Socket socket = new Socket(direccion, 9002);
    System.out.println("Conectado al servidor");

    // Inicializa las conexiones para enviar y recibir objetos
    // con los ObjectOutputStream y ObjectInputStream
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

    // Vacía los string de los nombres de los jugadores
    nombre = "";
    nombreJug2 = "";
    nombreJug3 = "";

    // Crea un ciclo que recibe el nombre de la interfaz gráfica, lo envía al
    // servidor y luego el servidor envía una respuesta indicando si el nombre
    // se aprueba o no (si está repetido no se aprueba), si no se aprueba se 
    // indica en la interfaz, si se aprueba se sae del ciclo con el nombre
    // en el string nombre
    boolean aceptado = false;
    while (nombre.equals("") && !aceptado && login.isAbierta()) {
      nombre = login.getNombreObtenido();
      out.writeObject(nombre);
      aceptado = (boolean) in.readObject();
      if (!nombre.equals("") && !aceptado) {
        login.notificarNombreRepetido();
        nombre = "";
      }
    }

    // Se indica el nombre, si la ventana se ha cerrado se termina la ejecución,
    // sino se cierra esta ventana de login
    System.out.println("Nombre Enviado, es " + nombre);
    if (!login.isAbierta()) {
      return;
    }
    login.dispose();

    // Se abre y muestra una ventana de juego, para jugar
    VentanaJuego ventanaJuego = new VentanaJuego();
    ventanaJuego.setVisible(true);

    // Ciclo que corre mientras la ventana del juego esté abierta
    while (ventanaJuego.isAbierta()) {
      // Cada iteración se lee lo que manda el servidor en forma de string
      // ya que así se evaluará a continuación 
      String input = (String) in.readObject();
      // Si se recibe nada, no se hace nada
      if (!input.equals("")) {
      }

      // Si se recibe Juego Iniciado, el juego ha iniciado y se 
      // realizan acciones de interfaz
      if (input.equals("Juego Iniciado")) {
        // Recibe los nombres de todos los jugadores para ponerlos
        // en los atributos nombreJug2 y nombreJug3, además de 
        // mostrarlos en la interfaz
        for (int i = 0; i < 3; i++) {
          String pNombre = (String) in.readObject();
          if (!pNombre.equals(nombre)) {
            if (nombreJug2.equals("")) {
              nombreJug2 = pNombre;
              ventanaJuego.setNombreJugador2(nombreJug2);
            } else {
              nombreJug3 = pNombre;
              ventanaJuego.setNombreJugador3(nombreJug3);
            }
          }
        }

        // Se crean tres ArrayLists que guardarán las cartas de todos 
        // los jugadores, estos se rellenan  con respecto al nombre 
        // del jugador que envíe el servidor y luego recopilando todas
        // las cartas, y luego asignando el ArrayList con su respectivo jugador
        ArrayList<Carta> cartasPropias = new ArrayList<>();
        ArrayList<Carta> cartasJug2 = new ArrayList<>();
        ArrayList<Carta> cartasJug3 = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
          String nombreJug = (String) in.readObject();
          System.out.println(nombreJug);
          int cantidadCartas = (int) in.readObject();
          System.out.println(cantidadCartas);
          ArrayList<Carta> cartas = new ArrayList<>();
          for (int i = 0; i < cantidadCartas; i++) {
            cartas.add((Carta) in.readObject());
          }
          System.out.println(cartas.toString());
          if (nombre.equals(nombreJug)) {
            cartasPropias = cartas;
          } else if (nombreJug.equals(nombreJug2)) {
            cartasJug2 = cartas;
          } else {
            cartasJug3 = cartas;
          }
        }
        // Actualiza la interfaz gráfica con las cartas de los jugadores 
        ventanaJuego.actualizarCartas(cartasPropias, cartasJug2, cartasJug3);
        // Recoge la suma de este jugador actual 
        String suma = (String) in.readObject();
        // Muestra la suma en la interfaz
        ventanaJuego.setSuma(suma);
        // Se da la bienvenida al juego
        ventanaJuego.mostrarInformacion("Juego Iniciado. Bienvenido " + nombre);
      }
      // Si se envía Turno, es que un turno de algún jugador ha ocurrido
      // y se muestra el resultado de la ejecución
      if (input.equals("Turno")) {
        String ejecucion = (String) in.readObject();
        ventanaJuego.mostrarInformacion(ejecucion);
      }
      // Si se envía Su turno es que es el turno de este y se empiezan a mostrar
      // según lo que pueda ocurrir en el turno
      if (input.equals("Su turno")) {
        // Se crean tres ArrayLists que guardarán las cartas de todos 
        // los jugadores, estos se rellenan  con respecto al nombre 
        // del jugador que envíe el servidor y luego recopilando todas
        // las cartas, y luego asignando el ArrayList con su respectivo jugador
        ArrayList<Carta> cartasPropias = new ArrayList<>();
        ArrayList<Carta> cartasJug2 = new ArrayList<>();
        ArrayList<Carta> cartasJug3 = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
          String nombreJug = (String) in.readObject();
          System.out.println(nombreJug);
          int cantidadCartas = (int) in.readObject();
          System.out.println(cantidadCartas);
          ArrayList<Carta> cartas = new ArrayList<>();
          for (int i = 0; i < cantidadCartas; i++) {
            cartas.add((Carta) in.readObject());
          }
          System.out.println(cartas.toString());
          if (nombre.equals(nombreJug)) {
            cartasPropias = cartas;
          } else if (nombreJug.equals(nombreJug2)) {
            cartasJug2 = cartas;
          } else {
            cartasJug3 = cartas;
          }
        }
        // Actualiza la interfaz gráfica con las cartas de los jugadores 
        ventanaJuego.actualizarCartas(cartasPropias, cartasJug2, cartasJug3);
        // Se recibe un booleano del servidor que indica si este jugador puede
        // jugar este turno o no
        boolean puedeJugar = (boolean) in.readObject();
        // Si el jugador puede jugar
        if (puedeJugar) {

          System.out.println("Pidiendo Carta");
          // Se tiene un string peticion que va a pedir constantemente
          // a la interfaz la peticion (si quiere carta o pasa el turno)
          // hasta que esta le responda dejando el string no vacío y deteniendo
          // el ciclo
          String peticion = "";
          // Permite al jugador presionar los botones para escoger
          ventanaJuego.encenderBotones();
          while (peticion.equals("")) {
            System.out.println("Pidiendo");
            peticion = ventanaJuego.getPeticion();
          }
          // Apaga los botones para que ya no pueda escoger peticion
          ventanaJuego.apagarBotones();
          // Manda la peticion al servidor
          out.writeObject(peticion);
          // Recoge las cartas de este jugador del servidor en un ArrayList
          int cantidadCartas = (int) in.readObject();
          System.out.println(cantidadCartas);
          ArrayList<Carta> cartas = new ArrayList<>();
          for (int i = 0; i < cantidadCartas; i++) {
            cartas.add((Carta) in.readObject());
          }
          // Muestra esas cartas en la interfaz
          ventanaJuego.actualizarCartas(cartas);
          // Recoge la suma del servidor
          String suma = (String) in.readObject();
          // Muestra esa suma en la interfaz
          ventanaJuego.setSuma(suma);
          System.out.println(suma);
        }
      }
      // Si se envía Juego Acabado es que el juego concluyó y se realizan
      // requests finales
      if (input.equals("Juego Acabado")) {
        // Se pide el ganador de la partida y la ejecución del último turno 
        // al servidor
        String ganador = (String) in.readObject();
        String ejecucion = (String) in.readObject();
        // Muestra la ejecución en la interfaz y además manda a la interfaz el
        // mensaje de la victoria o la derrota
        ventanaJuego.mostrarInformacion(ejecucion);
        if (ganador.equals(nombre)) {
          System.out.println("Ganó");
          ventanaJuego.mostrarMensajeFinal("Ha ganado, Felicidades!!!\nPor favor reinicie la aplicación para jugar de nuevo.");
        } else {
          System.out.println("Perdio");
          ventanaJuego.mostrarMensajeFinal("Ha perdido.\nPor favor reinicie la aplicación para jugar de nuevo.");
        }
        // Termina el ciclo para que este jugador deje de pertenecer al servidor
        break;
      }
    }
  }

  /**
   * Metodo main para ejecutar un cliente, lo instancia y usa el método run
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Cliente cliente = new Cliente();
    cliente.run();
  }
}
