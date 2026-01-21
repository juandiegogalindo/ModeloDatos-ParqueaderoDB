/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import controlador.Controlador;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author jdgal
 */
public class PanelCrud extends JPanel {

    private Controlador ctrl;
    private int seleccion;
    private JRadioButton opcion1, opcion2, opcion3, opcion4;
    
    public PanelCrud(Controlador ctrl) {
        this.ctrl = ctrl;
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createTitledBorder("CRUD")));
        opcion1 = new JRadioButton("Create.");
        opcion2 = new JRadioButton("Read.");
        opcion3 = new JRadioButton("Update.");
        opcion4 = new JRadioButton("Delete.");

        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(opcion1);
        grupoOpciones.add(opcion2);
        grupoOpciones.add(opcion3);
        grupoOpciones.add(opcion4);

        add(opcion1);
        add(opcion2);
        add(opcion3);
        add(opcion4);

        opcion1.addActionListener(e -> seleccion = 1);
        
        opcion2.addActionListener(e -> seleccion = 2);
        opcion3.addActionListener(e -> seleccion = 3);
        opcion4.addActionListener(e -> seleccion = 4);
    }

    public int getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(int seleccion) {
        this.seleccion = seleccion;
    }

}
