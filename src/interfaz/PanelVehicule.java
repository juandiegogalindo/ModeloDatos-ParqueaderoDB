/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import controlador.Controlador;
import java.sql.ResultSet;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jdgal
 */
public class PanelVehicule extends JPanel {

    private Controlador ctrl;
    private int nuevoA, nuevoB;
    private JLabel labelVarA, labelVarB, labelVarC, labelVarD, labelVarE, labelVarF;
    private JTextField campo1, campo2, campo3, campo4, campo5, campo6;
    private JPanel panelinterno;
    private JButton boton1, boton2;
    private PanelCrud pnlCrud;
    private PanelConsole pnlConsole;

    public PanelVehicule(Controlador ctrl, PanelCrud pnlCrud, PanelConsole pnlConsole) {
        this.pnlCrud = pnlCrud;
        this.pnlConsole = pnlConsole;
        this.ctrl = ctrl;
        setLayout(null);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createTitledBorder("VEHICLE")));

        labelVarA = new JLabel("Plate: ");
        labelVarA.setBounds(115, 50, 200, 30);
        add(labelVarA);

        campo1 = new JTextField(10);
        campo1.setBounds(40, 75, 200, 30);
        add(campo1);

        labelVarB = new JLabel("Owner/Visitor: ");
        labelVarB.setBounds(345, 50, 200, 30);
        add(labelVarB);

        campo2 = new JTextField(80);
        campo2.setBounds(255, 75, 325, 30);
        add(campo2);

        labelVarC = new JLabel("Phone number: ");
        labelVarC.setBounds(40, 125, 200, 30);
        add(labelVarC);

        campo3 = new JTextField("");
        campo3.setBounds(40, 150, 200, 30);
        add(campo3);

        panelinterno = new JPanel();
        panelinterno.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), null));
        panelinterno.setOpaque(false);
        panelinterno.setBounds(40, 215, 540, 100);
        panelinterno.setLayout(null);
        add(panelinterno);

        labelVarD = new JLabel("Entry: ");
        labelVarD.setBounds(210, 230, 75, 30);
        add(labelVarD);

        campo4 = new JTextField("");
        campo4.setBounds(165, 260, 125, 30);
        add(campo4);

        labelVarE = new JLabel("Departure: ");
        labelVarE.setBounds(345, 230, 150, 30);
        add(labelVarE);

        campo5 = new JTextField("");
        campo5.setBounds(300, 260, 150, 30);
        add(campo5);

        labelVarF = new JLabel("Pay: ");
        labelVarF.setBounds(495, 230, 100, 30);
        add(labelVarF);

        campo6 = new JTextField("");
        campo6.setBounds(460, 260, 100, 30);
        add(campo6);

        boton1 = new JButton("Liquidate.");
        boton1.setBounds(55, 260, 100, 28);
        add(boton1);

        boton2 = new JButton("Send");
        boton2.setBounds(480, 330, 100, 28);
        add(boton2);

        boton2.addActionListener(e -> {
            try {
                if (pnlCrud.getSeleccion() == 1) {
                    String placa = campo1.getText().trim();
                    String propietario = campo2.getText().trim();
                    String telefono = campo3.getText().trim();

                    if (placa.isEmpty() || propietario.isEmpty() || telefono.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos");
                        return;
                    }

                    String query = "SELECT * FROM Vehiculo WHERE placa = '" + placa + "'";
                    ResultSet rst = ctrl.select(query);

                    if (rst.next()) {
                        pnlConsole.escribirConsola("The vehicle already exists");
                    } else {
                        String insert = "INSERT INTO Vehiculo (placa, propietario, telefono) VALUES ('"
                                + placa + "', '" + propietario + "', '" + telefono + "')";
                        ctrl.update(insert);
                        pnlConsole.escribirConsola("Created vehicle");
                        campo1.setText("");
                        campo2.setText("");
                        campo3.setText("");
                    }

                    rst.close();
                } else if (pnlCrud.getSeleccion() == 2) {
                    boton1.setEnabled(false);
                    String placa = campo1.getText().trim();
                    if (placa.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar la placa");
                        return;
                    }

                    try {
                        String sqlVehiculo = "SELECT * FROM Vehiculo WHERE placa = '" + placa + "'";
                        ResultSet rstVehiculo = ctrl.select(sqlVehiculo);

                        if (!rstVehiculo.next()) {
                            pnlConsole.escribirConsola("Nonexistent vehicle");
                        } else {
                            campo2.setText(rstVehiculo.getString("propietario"));
                            campo2.setEditable(false);
                            campo3.setText(rstVehiculo.getString("telefono"));
                            campo3.setEditable(false);

                            String sqlRegistro = "SELECT * FROM Registro WHERE placa = '" + placa + "' AND estado = 'parqueado'";
                            ResultSet rstRegistro = ctrl.select(sqlRegistro);

                            if (!rstRegistro.next()) {
                                pnlConsole.escribirConsola("Vehicle no parked");

                                LocalDate fecha = LocalDate.now();
                                LocalTime horaEntrada = LocalTime.now();
                                DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                                String insertRegistro = "INSERT INTO Registro (placa, fecha, horaEntrada, estado) VALUES ('"
                                        + placa + "', '" + fecha + "', '" + horaEntrada.format(horaFormatter) + "', 'parqueado')";
                                ctrl.update(insertRegistro);

                                pnlConsole.escribirConsola("Registro de entrada creado");
                                campo4.setText(horaEntrada.format(horaFormatter));
                            } else {
                                campo4.setText(rstRegistro.getString("horaEntrada"));
                                campo4.setEditable(false);
                                boton1.setEnabled(true); // Activar botón Liquidate
                                pnlConsole.escribirConsola("Vehicle is parked");
                            }

                            rstRegistro.close();
                        }

                        rstVehiculo.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else if (pnlCrud.getSeleccion() == 3) { // UPDATE
                    String placa = campo1.getText().trim();

                    if (campo2.isEditable()) {
                        // Fase 2: Confirmar actualización
                        try {
                            String nuevoPropietario = campo2.getText().trim();
                            String nuevoTelefono = campo3.getText().trim();

                            if (nuevoPropietario.isEmpty() || nuevoTelefono.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "No puede dejar campos vacíos");
                                return;
                            }

                            String update = "UPDATE Vehiculo SET propietario = '" + nuevoPropietario + "', telefono = '" + nuevoTelefono + "' WHERE placa = '" + placa + "'";
                            ctrl.update(update);

                            pnlConsole.escribirConsola("Vehicle updated: " + placa);
                            campo2.setEditable(false);
                            campo3.setEditable(false);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
                            ex.printStackTrace();
                        }

                    } else {
                        // Fase 1: Buscar vehículo
                        if (placa.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Debe ingresar la placa");
                            return;
                        }

                        try {
                            String sqlVehiculo = "SELECT * FROM Vehiculo WHERE placa = '" + placa + "'";
                            ResultSet rstVehiculo = ctrl.select(sqlVehiculo);

                            if (!rstVehiculo.next()) {
                                pnlConsole.escribirConsola("Nonexistent vehicle");
                            } else {
                                campo2.setText(rstVehiculo.getString("propietario"));
                                campo3.setText(rstVehiculo.getString("telefono"));
                                campo2.setEditable(true);
                                campo3.setEditable(true);

                                pnlConsole.escribirConsola("Edit the fields and press Send again to update");
                            }

                            rstVehiculo.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                } else if (pnlCrud.getSeleccion() == 4) { // DELETE
                    String placa = campo1.getText().trim();
                    if (placa.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar la placa");
                        return;
                    }

                    try {
                        String sqlVehiculo = "SELECT * FROM Vehiculo WHERE placa = '" + placa + "'";
                        ResultSet rstVehiculo = ctrl.select(sqlVehiculo);

                        if (!rstVehiculo.next()) {
                            pnlConsole.escribirConsola("Nonexistent vehicle");
                        } else {
                            String propietario = rstVehiculo.getString("propietario");
                            String telefono = rstVehiculo.getString("telefono");

                            campo2.setText(propietario);
                            campo3.setText(telefono);

                            pnlConsole.escribirConsola("Vehicle found: Plate: " + placa + ", Owner: " + propietario + ", Phone: " + telefono);

                            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar el vehículo y todos sus registros asociados?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {

                                String deleteCobros = "DELETE FROM Cobro WHERE idRegistro IN (SELECT idRegistro FROM Registro WHERE placa = '" + placa + "')";
                                ctrl.update(deleteCobros);

                                String deleteRegistros = "DELETE FROM Registro WHERE placa = '" + placa + "'";
                                ctrl.update(deleteRegistros);

                                String deleteVehiculo = "DELETE FROM Vehiculo WHERE placa = '" + placa + "'";
                                ctrl.update(deleteVehiculo);

                                pnlConsole.escribirConsola("Vehicle deleted: " + placa);

                                campo1.setText("");
                                campo2.setText("");
                                campo3.setText("");
                                campo4.setText("");
                                campo5.setText("");
                                campo6.setText("");
                            } else {
                                pnlConsole.escribirConsola("Eliminación cancelada por el usuario");
                            }
                        }

                        rstVehiculo.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        boton1.addActionListener(e -> {
            try {
                String placa = campo1.getText().trim();
                if (placa.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar la placa");
                    return;
                }

                String sql = "SELECT * FROM Registro WHERE placa = '" + placa + "' AND estado = 'parqueado'";
                ResultSet rst = ctrl.select(sql);

                if (!rst.next()) {
                    pnlConsole.escribirConsola("No hay registro activo para liquidar.");
                    return;
                }

                int idRegistro = rst.getInt("idRegistro");
                String horaEntradaStr = rst.getString("horaEntrada");
                LocalTime horaEntrada = LocalTime.parse(horaEntradaStr);
                LocalTime horaSalida = LocalTime.now();

                Duration duracion = Duration.between(horaEntrada, horaSalida);
                long minutos = duracion.getSeconds() / 60;
                if (duracion.getSeconds() % 60 != 0) {
                    minutos++;
                }

                float tarifaPorMinuto = 100f;
                float valorCalculado = minutos * tarifaPorMinuto;

                LocalDate fechaCobro = LocalDate.now();
                DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");

                String insertCobro = "INSERT INTO Cobro (idRegistro, minutosTotales, valorCalculado, valorFinal, fechaCobro) VALUES ("
                        + idRegistro + ", " + minutos + ", " + valorCalculado + ", " + valorCalculado + ", '" + fechaCobro + "')";
                ctrl.update(insertCobro);

                String updateRegistro = "UPDATE Registro SET horaSalida = '" + horaSalida.format(formatterHora) + "', estado = 'salido' WHERE idRegistro = " + idRegistro;
                ctrl.update(updateRegistro);

                campo5.setText(horaSalida.format(formatterHora));
                campo6.setText(String.valueOf(valorCalculado));

                pnlConsole.escribirConsola("Liquidación realizada correctamente.");

                boton1.setEnabled(false);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al liquidar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
