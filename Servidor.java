
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Servidor {
  
  private static final int PUERTO = 9002;
  private static HashSet<String> nombres = new HashSet<String>();
  private static ArrayList<String> nombresArray = new ArrayList<>();
  private static int cantJugadores = 0;
  private static String ejecucion = "";
  private static Juego juego = new Juego();
  
  public static void main(String[] args) throws Exception {
    System.out.println("Servidor Activo :)");
    ServerSocket servidor = new ServerSocket(PUERTO);
    //Manejador manejador = new Manejador();
    try {
      while (true) {
        new Handler(servidor.accept()).start();
        //manejador.agregarSocket(servidor.accept(),nombres);
      }
    } finally {
      servidor.close();
    }
  }
  
  private static class Handler extends Thread {
    
    private String name;
    private Socket socket;

    /**
     * Constructs a handler thread, squirreling away the socket. All the
     * interesting work is done in the run method.
     */
    public Handler(Socket socket) {
      this.socket = socket;
    }

    /**
     * Services this thread's client by repeatedly requesting a screen name
     * until a unique one has been submitted, then acknowledges the name and
     * registers the output stream for the client in a global set, then
     * repeatedly gets inputs and broadcasts them.
     */
    public void run() {
      try {

        // Create character streams for the socket.
        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        //out = new PrintWriter(socket.getOutputStream(), true);

        // Request a name from this client.  Keep requesting until
        // a name is submitted that is not already used.  Note that
        // checking for the existence of a name and adding the name
        // must be done while locking the set of names.
        while (true) {
          name = (String) in.readObject();
          synchronized (nombres) {
            if (!name.equals("")) {
              System.out.println(name + " ha entrado al juego");
              if (!nombres.contains(name)) {
                System.out.println("Here");
                nombresArray.add(name);
                nombres.add(name);
                juego.annadirJugador(new Jugador(name));
                cantJugadores++;
                out.writeObject(true);
                break;
              }
            }
          }
          out.writeObject(false);
        }
        
        System.out.println(nombres.toString());
        if (cantJugadores == 3) {
          juego.iniciarJuego();
        }
        
        while (true) {
          if (juego.getAcabado()) {
            out.writeObject("Juego Acabado");
            out.writeObject(juego.getGanador());
            out.writeObject(ejecucion);
            juego.restart();
            nombresArray.clear();
            break;
          }
          out.writeObject("");
          if (cantJugadores == 3) {
            out.writeObject("Juego Iniciado");
            for (Jugador jugador : juego.getJugadores()) {
              out.writeObject(jugador.getNombre());
            }
            for (String nombre : nombresArray) {
              ArrayList<Carta> cartas = juego.getCartas(nombre);
              out.writeObject(nombre);
              out.writeObject(cartas.size());
              for (Carta carta : cartas) {
                out.writeObject(carta);
              }
            }
            out.writeObject("" + juego.getSumaJugador(name));
            
            while (true) {
              if (juego.getAcabado()) {
                break;
              }
              System.out.print("");
              //System.out.println(nombresArray.get(juego.getIndiceJugadorActual()));
              //System.out.print(nombresArray.get(juego.getIndiceJugadorActual()) + name);
              if (!ejecucion.equals("")) {
                out.writeObject("Turno");
                out.writeObject(ejecucion);
              }
              
              if (!nombresArray.isEmpty() && nombresArray.get(juego.getIndiceJugadorActual()).equals(name) && !juego.getAcabado()) {
                out.writeObject("Su turno");
                //System.out.println("");
                //out.writeObject("Cartas");
                for (String nombre : nombresArray) {
                  ArrayList<Carta> cartas = juego.getCartas(nombre);
                  out.writeObject(nombre);
                  out.writeObject(cartas.size());
                  for (Carta carta : cartas) {
                    out.writeObject(carta);
                  }
                }
                
                boolean puedeJugar = juego.getJugador(name).estaEnJuego() && !juego.getAcabado();
                out.writeObject(puedeJugar);
                if (puedeJugar) {
                  String condicion = (String) in.readObject();
                  ejecucion = juego.ejecutarTurno(condicion);
                  System.out.println(ejecucion);
                  
                  ArrayList<Carta> cartas = juego.getCartas(name);
                  out.writeObject(cartas.size());
                  for (Carta carta : cartas) {
                    out.writeObject(carta);
                  }
                  out.writeObject("" + juego.getSumaJugador(name));
                } else {
                  juego.siguienteJugador();
                }
              }
            }
          }
        }
      } catch (IOException | ClassNotFoundException e) {
      } finally {
        // This client is going down!  Remove its name and its print
        // writer from the sets, and close its socket.
        if (name != null) {
          nombres.remove(name);
          System.out.println("Ha salido " + name);
          cantJugadores--;
        }
        try {
          socket.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
