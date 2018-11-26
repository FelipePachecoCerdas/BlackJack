
import java.io.Serializable;

/**
 * Clase Carta que representa una carta del juego Blackjack, contiene su
 * respectivo valor y figura para identificar a la carta de la baraja. De estas
 * se hara un mazo con el que se pueda jugar
 *
 * @author Felipe Pacheco
 * @author Kendall Tenorio
 */
public class Carta implements Serializable {

  private String figura;
  private String valor;

  /**
   * Constructor de la Carta, recibe la figura y el valor de la misma para
   * ponerlos en los atributos
   *
   * @param pFigura
   * @param pValor
   */
  public Carta(String pFigura, String pValor) {
    figura = pFigura;
    valor = pValor;
  }

  /**
   * Retorna el nombre de la carta de la forma valor + figura, es usado para
   * obtener la imagen que sigue este patron de nombre
   *
   * @return string del valor + figura
   */
  public String getName() {
    return valor + figura;
  }

  /**
   * Retorna el valor de la carta, si tiene alguna letra J,Q o K retorna un 10,
   * una A retorna un 1 y el resto simplemente retorna el valor de valor
   * convertido a entero
   *
   * @return entero del valor de la carta
   */
  public int getValorReal() {
    switch (valor) {
      case "A":
        return 1;
      case "J":
        return 10;
      case "Q":
        return 10;
      case "K":
        return 10;
      case "10":
        return 10;
      default:
        return valor.charAt(0) - '0';
    }
  }

  /**
   * Muestra la informacion de la carta
   *
   * @return la figura y el valor de la respectiva carta
   */
  public String toString() {
    return figura + " " + valor;
  }
}
