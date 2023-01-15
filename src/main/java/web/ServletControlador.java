package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    
                    this.deleteClient(request,response);
                    
                    break;
                default:
                    this.actionDefault(request, response);
            }
        } else {
            this.actionDefault(request, response);
        }

    }

    private void actionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Cliente> cliente = new ClienteDaoJDBC().listar();

        System.out.println(cliente);
        //aqui estamos usando el nivel de session por que al nivel de request, 
        // cuando enviamos la nueva peticion es decir el response.sendRedirect("clientes.jsp");
        // los datos se pierden ya que request no tiene ese nivel de persistencia 
        // pero session si
        HttpSession session = request.getSession();
        session.setAttribute("clientes", cliente);
        session.setAttribute("totalClientes", cliente.size());
        session.setAttribute("saldoTotal", this.calcularSaldoTotal(cliente));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request,response);
                    break;
                default:
                    this.actionDefault(request, response);
            }
        } else {
            this.actionDefault(request, response);
        }

    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldos = 0;

        String saldo = request.getParameter("saldo");
        if (saldo != null && !"".equals(saldo)) {
            saldos = Double.parseDouble(saldo);
        }
        //Creamos el objecto
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldos);

        //insertamos el nuevo objecto 
        int registrosInsertados = new ClienteDaoJDBC().insertar(cliente);

        System.out.println(registrosInsertados);

        this.actionDefault(request, response);
    }
     private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldos = 0;

        String saldo = request.getParameter("saldo");
        if (saldo != null && !"".equals(saldo)) {
            saldos = Double.parseDouble(saldo);
        }
        //Creamos el objecto
        Cliente cliente = new Cliente(idCliente,nombre, apellido, email, telefono, saldos);

        //modificar un objecto en la base de datos 
        int registrosInsertados = new ClienteDaoJDBC().actualizar(cliente);

        System.out.println(registrosInsertados);

        this.actionDefault(request, response);
    }
     private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Cliente cliente = new Cliente(idCliente);

        //eliminamos un objecto en la base de datos 
        int registrosInsertados = new ClienteDaoJDBC().delete(cliente);

        System.out.println(registrosInsertados);

        this.actionDefault(request, response);
    }
    
    private void editarCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int idCliente= Integer.parseInt(request.getParameter("idCliente"));
        
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente",cliente);
        
        String jspEditar ="/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
        
    }
    
    private double calcularSaldoTotal(List<Cliente> cliente) {
        double saldoTotal = 0;

        for (Cliente cl : cliente) {

            saldoTotal += cl.getSaldo();

        }
        return saldoTotal;

    }

}
