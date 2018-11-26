
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author felip
 */
public class Jugador {

  private String nombre;
  private ArrayList<Carta> cartas;
  private boolean enJuego;
  private int cantidadPasadas;

  public Jugador(String pNombre) {
    cartas = new ArrayList<>();
    nombre = pNombre;
    enJuego = true;
  }

  public void perder() {
    enJuego = false;
    cantidadPasadas = 1;
  }

  public boolean estaEnJuego() {
    return enJuego;
  }

  public void pasar() {
    cantidadPasadas++;
  }

  public boolean haPasado() {
    return cantidadPasadas > 0;
  }

  public void resetearPasadas() {
    cantidadPasadas = 0;
  }

  public ArrayList<Carta> getCartas() {
    return cartas;
  }

  public Carta getUltimaCarta() {
    return cartas.get(cartas.size() - 1);
  }

  public void agregarCarta(Carta carta) {
    cartas.add(carta);
  }

  public String getNombre() {
    return nombre;
  }

  public int sumarCartas() {
    int suma = 0;
    for (Carta carta : cartas) {
      suma += carta.getValorReal();
    }
    return suma;
  }

}
