package servlet.obtenerdetalles;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clase.DTO.Empleado;

import com.google.gson.Gson;

import examples.pool.Pool;

/**
 * Servlet implementation class ServletObtenerDetalles
 */

public class ObtenerDetalles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerDetalles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		//Conexión , ResultSet y Statemen
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		//Terminan la comunicación con la base de datos
		
		//Inicio Las variables
		String nombre = null;
		String apellido = null;
		String telefono = null;
		String correo = null;
		String mensajeJson = null;
		//Termino las variables
		
		
		
		try 
			{
			conexion = Pool.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select first_name, last_name, phone_number, email from employees where employee_id = " + id);
			System.out.println("recuperado de la base de datos");
			 
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();
			
			

			while (rs.next())
			{
				nombre = rs.getString(1);
				apellido = rs.getString(2);
				telefono = rs.getString(3);
				correo = rs.getString(4);
				Empleado empleado = new Empleado(nombre, apellido, telefono, correo, id);
				mensajeJson = gson.toJson(empleado);
				System.out.println(empleado.getNombre());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		finally {
			Pool.liberarRecursos(conexion, st, rs);
		}
		
		response.getWriter().write(mensajeJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
