package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import mundo.MySql;

public class Controlador {

    private MySql mySql;

    public Controlador() {
        mySql = new MySql();
    }

    /* Metodos de comportamiento */
    public Connection connect() {
        return mySql.connect();
    }

    public void update(String sql) throws SQLException {
        mySql.update(sql);
    }

    public ResultSet select(String sql) throws SQLException {
        return mySql.select(sql);
    }

    public void closeConnection() throws SQLException {
        mySql.closeConnection();
    }
}
