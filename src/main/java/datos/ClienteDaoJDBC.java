package datos;

import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC {

    private static final String SQL_SELECT = "SELECT*FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT*FROM cliente WHERE id_cliente=?";

    private static final String SQL_INSERT = "insert into cliente(nombre,apellido,email,telefono,saldo)values(?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE cliente set nombre=?,apellido=?, email=?, telefono=? , saldo=? where id_cliente=?";

    private static final String SQL_DELETE = "DELETE FROM cliente where id_cliente=?";

    public List<Cliente> listar() {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(con);
            Conexion.close(stmt);

        }

        return clientes;

    }

    public Cliente encontrar(Cliente cliente) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());

            rs = stmt.executeQuery();
             int idCliente = 0;
            String nombre ="";
            String apellido ="";
            String email ="";
            String telefono ="";
            double saldo =0;
            //rs.absolute(1);// nos posiciona en el primer registro devuelto
             while (rs.next()) {
             idCliente = rs.getInt("id_cliente");
             nombre = rs.getString("nombre");
             apellido = rs.getString("apellido");
             email = rs.getString("email");
             telefono = rs.getString("telefono");
             saldo = rs.getDouble("saldo");
             }
            cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(con);
            Conexion.close(stmt);

        }

        return cliente;

    }
    
    public int insertar (Cliente cliente){
        
        
        Connection con = null;
        PreparedStatement stmt = null;
       int row=0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            
            row= stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(con);
            Conexion.close(stmt);

        }

        return row;
        
    }
    
    
    public int actualizar (Cliente cliente){
         Connection con = null;
        PreparedStatement stmt = null;
       int row=0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6,cliente.getIdCliente());
            row= stmt.executeUpdate();
  

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(con);
            Conexion.close(stmt);

        }

        return row;
    }
    
    public int delete (Cliente cliente){
          Connection con = null;
        PreparedStatement stmt = null;
       int row=0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1,cliente.getIdCliente());
            row= stmt.executeUpdate();
  

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(con);
            Conexion.close(stmt);

        }

        return row;
    }
    

}
