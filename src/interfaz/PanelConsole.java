/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jdgal
 */
public class PanelConsole extends JPanel {

    private Controlador ctrl;
    public PanelVehicule pnlVehicule;
    private PanelCrud pnlCrud;
    private JTextArea textArea;

    public PanelConsole(Controlador ctrl, PanelVehicule pnlVehicule, PanelCrud pnlCrud) {
        this.ctrl = ctrl;
        this.pnlVehicule = pnlVehicule;
        this.pnlCrud = pnlCrud;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black),
                BorderFactory.createTitledBorder("CONSOLE")));

        textArea = new JTextArea(5, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll, BorderLayout.CENTER);
    }

    public void escribirConsola(String mensaje) {
        textArea.append(mensaje + "\n");
    }
}