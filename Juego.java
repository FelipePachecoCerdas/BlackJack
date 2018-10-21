
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
public class Juego {
  
  ArrayList<Carta> baraja;
  
  public Juego() {
    baraja = new ArrayList<>();
    String figuras[] = {"Trébol","Corazón","Pica","Diamante"};
    String valores[] = {"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};
    for (String figura: figuras) {
      for (String valor: valores) {
        baraja.add(new Carta(figura,valor));
      }
    }
    for (Carta carta: baraja) {
      System.out.println(carta.toString());
    }
  }
  
  public static void main(String[] args) {
    Juego juego = new Juego();
  }
}
