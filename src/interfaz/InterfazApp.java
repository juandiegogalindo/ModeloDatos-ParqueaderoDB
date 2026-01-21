package interfaz;

import controlador.Controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Giovanni Fajardo Utria
 */
public class InterfazApp extends JFrame {// Atributos  

    private Controlador ctrl;
    private PanelConsole pnlConsole;
    private PanelCrud pnlCrud;
    private PanelVehicule pnlVehicule;

    public InterfazApp(Controlador ctrl) throws SQLException {
        this.ctrl = ctrl;
        getContentPane().setLayout(null);

        pnlCrud = new PanelCrud(ctrl);
        pnlCrud.setBounds(30, 18, 620, 60); 
        getContentPane().add(pnlCrud);
        
        pnlConsole = new PanelConsole(ctrl, null, pnlCrud);
        pnlConsole.setBounds(30, 525, 620, 100);
        getContentPane().add(pnlConsole);

        pnlVehicule = new PanelVehicule(ctrl, pnlCrud, pnlConsole);
        pnlVehicule.setBounds(30, 100, 620, 400);
        getContentPane().add(pnlVehicule);

        pnlConsole.pnlVehicule = pnlVehicule;

        if (ctrl.connect() == null) {
            JOptionPane.showMessageDialog(null, "Mysql: La conexion no fue establecida...");
        } else {
            JOptionPane.showMessageDialog(null, "Mysql: La conexion fue establecida...");
//            insert();
//            select("Select * from Personas");
//            System.out.println("--------------------");
//            update();
//            select("Select * from Personas");
//            System.out.println("--------------------");
//            delete();
//            select("Select * from Personas");
        }

        this.setTitle("Parking.");
        this.setSize(700, 700);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//create
    /*
    "INSERT INTO <Nombre de la tabla> ( <campo1, campoi2...> ) VALUES ( " + null + ", 'string')" );    
     (Create)RUD
     */
    public void insert() throws SQLException {
        ctrl.update("INSERT INTO TipoVehiculo ( codTipoVehiculo, nombreVehiculo ) VALUES ( " + null + ", 'Moto')");
        ctrl.update("INSERT INTO TipoVehiculo ( codTipoVehiculo, nombreVehiculo ) VALUES ( " + null + ", 'Carro')");
        ctrl.update("INSERT INTO TipoVehiculo ( codTipoVehiculo, nombreVehiculo ) VALUES ( " + null + ", 'Camion')");

//        
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Mouse')");
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Impresoras')");
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'PC')");
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Celulares')");
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Audifonos')");
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'iPad')");
//
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Cambiame')");
//        ctrl.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Borrame')");
    }

    /*
    "UPDATE <Nombre de la tabla> SET <campo> = " + "'GoPro'" + " WHERE <llave> = " + valor );
     CR(Update)D
     */
    public void update() throws SQLException {
        ctrl.update("UPDATE TipoProducto SET nombreTipo = " + "'GoPro'" + " WHERE ID_CodTipo = " + 7);
    }

    /* 
    "DELETE FROM <Nombre de la tabla> WHERE <llave> = " + valor); 
     CRU(Delete)
     */
    public void delete() throws SQLException {
        ctrl.update("DELETE FROM TipoProducto WHERE ID_CodTipo = " + 8);
    }

    /* 
    "Select * from <Nombre tabla>"
     C(Read)UD
     */
    public void select(String sql) throws SQLException {
        ResultSet rst = ctrl.select(sql);
        while (rst.next()) {
            System.out.println(rst.getString("codTipoVehiculo") + " :: " + rst.getString("nombreVehiculo"));
        }
    }

    public static void main(String[] args) throws SQLException {
        InterfazApp interfazApp = new InterfazApp(new Controlador());
        interfazApp.setVisible(true);
    }

}
