
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import static javax.swing.JOptionPane.showMessageDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kendall Tenorio Chevez
 * @author Felipe Pacheco Cerdas
 */
public class VentanaJuego extends javax.swing.JFrame {

  private int i = 0;
  private boolean abierta;
  private Juego juego;
  private JLabel cartas[];
  private JLabel cartasJugador2[];
  private JLabel cartasJugador3[];
  private String peticion;

  /**
   * Creates new form VentanaJuego
   */
  public VentanaJuego() {

    peticion = "";
    abierta = true;

    setLayout(new BorderLayout());
    setContentPane(new JLabel(new ImageIcon(new ImageIcon("background.jpg").getImage().getScaledInstance(1030, 600, Image.SCALE_DEFAULT))));
    setLayout(new FlowLayout());
    initComponents();
    JLabel etiquetas[] = {carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8, carta9, carta10, carta11};
    cartas = etiquetas;
    JLabel cartasJug2[] = {jug2carta1, jug2carta2, jug2carta3, jug2carta4, jug2carta5, jug2carta6, jug2carta7, jug2carta8, jug2carta9, jug2carta10, jug2carta11};
    cartasJugador2 = cartasJug2;
    JLabel cartasJug3[] = {jug3carta1, jug3carta2, jug3carta3, jug3carta4, jug3carta5, jug3carta6, jug3carta7, jug3carta8, jug3carta9, jug3carta10, jug3carta11};
    cartasJugador3 = cartasJug3;
    carta1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    carta1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 0) {
          JLabel etiqueta = (JLabel) e.getComponent();
          System.out.println(etiqueta.getName());
        }
      }
    });
    for (JLabel carta : cartas) {
      if (carta != carta1) {
        carta.setVisible(false);
        carta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        carta.addMouseListener(cartas[0].getMouseListeners()[0]);
      }
    }
    ImageIcon imagen = new ImageIcon(new ImageIcon("cards.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    this.otraCarta.setIcon(imagen);
    imagen = new ImageIcon(new ImageIcon("right-arrow.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    this.pasar.setIcon(imagen);
    imagen = new ImageIcon(new ImageIcon("cartas.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    this.info.setIcon(imagen);
    this.otraCarta.setContentAreaFilled(false);
    this.pasar.setContentAreaFilled(false);
    juego = new Juego();

    this.pasar.setEnabled(false);
    this.otraCarta.setEnabled(false);
  }

  public boolean isAbierta() {
    return abierta;
  }

  public void apagarBotones() {
    this.pasar.setEnabled(false);
    this.otraCarta.setEnabled(false);
    peticion = "";
  }

  public void encenderBotones() {
    this.pasar.setEnabled(true);
    this.otraCarta.setEnabled(true);
  }

  public String getPeticion() {
    return peticion;
  }

  public void setNombreJugador2(String pNombre) {
    this.jugador2.setText(pNombre);
  }

  public void setNombreJugador3(String pNombre) {
    this.jugador3.setText(pNombre);
  }

  public void setSuma(String pSuma) {
    this.sumaJug.setText(pSuma);
  }

  public void mostrarInformacion(String informacion) {
    this.info.setText(informacion);
  }

  public void mostrarMensajeFinal(String mensaje) {
    showMessageDialog(null, mensaje);
  }

  public void actualizarMisCartas(ArrayList<String> nombres) {
    for (String nombre : nombres) {
      ImageIcon imagen = new ImageIcon(new ImageIcon(nombre + ".png").getImage().getScaledInstance(70, 98, Image.SCALE_DEFAULT));
      for (JLabel carta : cartas) {
        if (carta.getIcon() == null) {
          carta.setVisible(true);
          carta.setName(nombre);
          carta.setIcon(imagen);
          break;
        }
      }
    }
  }

  public void actualizarCartasJugador2(ArrayList<String> nombres) {
    for (String nombre : nombres) {
      ImageIcon imagen = new ImageIcon(new ImageIcon(nombre + ".png").getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
      for (JLabel carta : cartasJugador2) {
        if (carta.getIcon() == null) {
          carta.setVisible(true);
          carta.setName(nombre);
          carta.setIcon(imagen);
          break;
        }
      }
    }
  }

  public void actualizarCartasJugador3(ArrayList<String> nombres) {
    for (String nombre : nombres) {
      ImageIcon imagen = new ImageIcon(new ImageIcon(nombre + ".png").getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
      for (JLabel carta : cartasJugador3) {
        if (carta.getIcon() == null) {
          carta.setVisible(true);
          carta.setName(nombre);
          carta.setIcon(imagen);
          break;
        }
      }
    }
  }

  public void actualizarCartas(ArrayList<Carta> misCartas) {
    ArrayList<String> nombres = new ArrayList<>();
    for (Carta carta : misCartas) {
      boolean esta = false;
      for (JLabel etiqueta : cartas) {
        if (carta.getName().equals(etiqueta.getName())) {
          esta = true;
          break;
        }
      }
      if (!esta) {
        nombres.add(carta.getName());
      }
    }
    actualizarMisCartas(nombres);
  }

  public void actualizarCartas(ArrayList<Carta> misCartas, ArrayList<Carta> cartasJug2, ArrayList<Carta> cartasJug3) {
    ArrayList<String> nombres = new ArrayList<>();
    for (Carta carta : misCartas) {
      boolean esta = false;
      for (JLabel etiqueta : cartas) {
        if (carta.getName().equals(etiqueta.getName())) {
          esta = true;
          break;
        }
      }
      if (!esta) {
        nombres.add(carta.getName());
      }
    }
    actualizarMisCartas(nombres);

    nombres.clear();
    for (Carta carta : cartasJug2) {
      boolean esta = false;
      for (JLabel etiqueta : cartasJugador2) {
        if (carta.getName().equals(etiqueta.getName())) {
          esta = true;
          break;
        }
      }
      if (!esta) {
        nombres.add(carta.getName());
      }
    }
    actualizarCartasJugador2(nombres);

    nombres.clear();
    for (Carta carta : cartasJug3) {
      boolean esta = false;
      for (JLabel etiqueta : cartasJugador3) {
        if (carta.getName().equals(etiqueta.getName())) {
          esta = true;
          break;
        }
      }
      if (!esta) {
        nombres.add(carta.getName());
      }
    }
    actualizarCartasJugador3(nombres);

    /*
    System.out.println(this.juego.getBaraja().get(i).getName() + ".png");
    String nombre = this.juego.getBaraja().get(i).getName();
    ImageIcon imagen = new ImageIcon(new ImageIcon(nombre + ".png").getImage().getScaledInstance(70, 98, Image.SCALE_DEFAULT));
    i++;
    for (JLabel carta : cartas) {
      if (carta.getIcon() == null) {
        carta.setVisible(true);
        carta.setName(nombre);
        carta.setIcon(imagen);
        break;
      }
    }*/
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    carta10 = new javax.swing.JLabel();
    carta1 = new javax.swing.JLabel();
    carta9 = new javax.swing.JLabel();
    carta8 = new javax.swing.JLabel();
    carta7 = new javax.swing.JLabel();
    carta6 = new javax.swing.JLabel();
    carta5 = new javax.swing.JLabel();
    carta4 = new javax.swing.JLabel();
    carta3 = new javax.swing.JLabel();
    carta2 = new javax.swing.JLabel();
    carta11 = new javax.swing.JLabel();
    otraCarta = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    pasar = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    cerrar = new javax.swing.JButton();
    jugador2 = new javax.swing.JLabel();
    jugador3 = new javax.swing.JLabel();
    jug2carta4 = new javax.swing.JLabel();
    jug2carta3 = new javax.swing.JLabel();
    jug2carta2 = new javax.swing.JLabel();
    jug2carta11 = new javax.swing.JLabel();
    jug2carta10 = new javax.swing.JLabel();
    jug2carta1 = new javax.swing.JLabel();
    jug2carta9 = new javax.swing.JLabel();
    jug2carta8 = new javax.swing.JLabel();
    jug2carta7 = new javax.swing.JLabel();
    jug2carta6 = new javax.swing.JLabel();
    jug2carta5 = new javax.swing.JLabel();
    jug3carta2 = new javax.swing.JLabel();
    jug3carta3 = new javax.swing.JLabel();
    jug3carta4 = new javax.swing.JLabel();
    jug3carta6 = new javax.swing.JLabel();
    jug3carta7 = new javax.swing.JLabel();
    jug3carta5 = new javax.swing.JLabel();
    jug3carta8 = new javax.swing.JLabel();
    jug3carta9 = new javax.swing.JLabel();
    jug3carta10 = new javax.swing.JLabel();
    jug3carta11 = new javax.swing.JLabel();
    jug3carta1 = new javax.swing.JLabel();
    jugador4 = new javax.swing.JLabel();
    suma = new javax.swing.JLabel();
    info = new javax.swing.JLabel();
    sumaJug = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    carta10.setBackground(new java.awt.Color(255, 102, 102));

    carta1.setBackground(new java.awt.Color(255, 102, 102));

    carta9.setBackground(new java.awt.Color(255, 102, 102));

    carta8.setBackground(new java.awt.Color(255, 102, 102));

    carta7.setBackground(new java.awt.Color(255, 102, 102));

    carta6.setBackground(new java.awt.Color(255, 102, 102));

    carta5.setBackground(new java.awt.Color(255, 102, 102));

    carta4.setBackground(new java.awt.Color(255, 102, 102));

    carta3.setBackground(new java.awt.Color(255, 102, 102));

    carta2.setBackground(new java.awt.Color(255, 102, 102));

    carta11.setBackground(new java.awt.Color(255, 102, 102));

    otraCarta.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        otraCartaActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Otra Carta");

    pasar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pasarActionPerformed(evt);
      }
    });

    jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Pasar");

    cerrar.setBackground(new java.awt.Color(204, 0, 0));
    cerrar.setText("X");
    cerrar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cerrarActionPerformed(evt);
      }
    });

    jugador2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

    jugador3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

    jug2carta4.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta3.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta2.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta11.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta10.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta1.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta9.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta8.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta7.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta6.setBackground(new java.awt.Color(255, 102, 102));

    jug2carta5.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta2.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta3.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta4.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta6.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta7.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta5.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta8.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta9.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta10.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta11.setBackground(new java.awt.Color(255, 102, 102));

    jug3carta1.setBackground(new java.awt.Color(255, 102, 102));

    jugador4.setBackground(new java.awt.Color(255, 255, 255));
    jugador4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
    jugador4.setForeground(new java.awt.Color(255, 255, 255));
    jugador4.setText("Suma: ");

    suma.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

    info.setBackground(new java.awt.Color(255, 255, 255));
    info.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
    info.setForeground(new java.awt.Color(255, 255, 255));

    sumaJug.setBackground(new java.awt.Color(255, 255, 255));
    sumaJug.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(79, 79, 79)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jug2carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(jug2carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jug3carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(jug3carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jug2carta1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(jug2carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug2carta5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jug3carta5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(jug3carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jug3carta1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGap(82, 82, 82)
            .addComponent(jugador2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jugador3)
            .addGap(85, 85, 85)))
        .addGap(81, 81, 81))
      .addGroup(layout.createSequentialGroup()
        .addGap(98, 98, 98)
        .addComponent(carta1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cerrar)
            .addContainerGap())
          .addGroup(layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(101, Short.MAX_VALUE))))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(55, 55, 55)
        .addComponent(jugador4)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addComponent(suma))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(sumaJug, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(53, 53, 53)
            .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(otraCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jLabel2))
          .addComponent(pasar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(118, 118, 118))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(cerrar)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jugador2)
          .addComponent(jugador3))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(suma)
            .addGap(88, 88, 88)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel2)
              .addComponent(jLabel1))
            .addGap(39, 39, 39))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jug3carta5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jug3carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jug3carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jug3carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jug3carta1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jug3carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug3carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug3carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug3carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug3carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug3carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jug2carta1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jug2carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jug2carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jug2carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jug2carta5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jug2carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug2carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug2carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug2carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug2carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jug2carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(carta1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(carta3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(carta2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(carta4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(carta11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(carta6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(carta5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(carta7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(carta8, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(carta9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(carta10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(otraCarta, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                  .addComponent(pasar, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
              .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(sumaJug, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jugador4)))
              .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(50, 50, 50))))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void otraCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otraCartaActionPerformed
    peticion = "Carta";
  }//GEN-LAST:event_otraCartaActionPerformed

  private void pasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarActionPerformed
    peticion = "No Carta";
  }//GEN-LAST:event_pasarActionPerformed

  private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
    this.dispose();
    abierta = false;
  }//GEN-LAST:event_cerrarActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;

        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class
        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class
        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class
        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VentanaJuego.class
        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new VentanaJuego().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel carta1;
  private javax.swing.JLabel carta10;
  private javax.swing.JLabel carta11;
  private javax.swing.JLabel carta2;
  private javax.swing.JLabel carta3;
  private javax.swing.JLabel carta4;
  private javax.swing.JLabel carta5;
  private javax.swing.JLabel carta6;
  private javax.swing.JLabel carta7;
  private javax.swing.JLabel carta8;
  private javax.swing.JLabel carta9;
  private javax.swing.JButton cerrar;
  private javax.swing.JLabel info;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jug2carta1;
  private javax.swing.JLabel jug2carta10;
  private javax.swing.JLabel jug2carta11;
  private javax.swing.JLabel jug2carta2;
  private javax.swing.JLabel jug2carta3;
  private javax.swing.JLabel jug2carta4;
  private javax.swing.JLabel jug2carta5;
  private javax.swing.JLabel jug2carta6;
  private javax.swing.JLabel jug2carta7;
  private javax.swing.JLabel jug2carta8;
  private javax.swing.JLabel jug2carta9;
  private javax.swing.JLabel jug3carta1;
  private javax.swing.JLabel jug3carta10;
  private javax.swing.JLabel jug3carta11;
  private javax.swing.JLabel jug3carta2;
  private javax.swing.JLabel jug3carta3;
  private javax.swing.JLabel jug3carta4;
  private javax.swing.JLabel jug3carta5;
  private javax.swing.JLabel jug3carta6;
  private javax.swing.JLabel jug3carta7;
  private javax.swing.JLabel jug3carta8;
  private javax.swing.JLabel jug3carta9;
  private javax.swing.JLabel jugador2;
  private javax.swing.JLabel jugador3;
  private javax.swing.JLabel jugador4;
  private javax.swing.JButton otraCarta;
  private javax.swing.JButton pasar;
  private javax.swing.JLabel suma;
  private javax.swing.JLabel sumaJug;
  // End of variables declaration//GEN-END:variables
}
