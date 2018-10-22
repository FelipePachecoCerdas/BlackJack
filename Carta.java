/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author felip
 */
public class Carta {
  private String figura;
  private String valor;
  
  public Carta(String pFigura, String pValor) {
    figura = pFigura;
    valor = pValor;
  }
  
  public String getName() {
    return valor + figura;
  }
  
  public String toString() {
    return figura + " " + valor;
  }
}
