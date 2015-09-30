package service.empleado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clase.DTO.Empleado;
import examples.pool.Pool;

public class EmpleadoService {
	
	
	public Empleado recuperarEmpleado (int id){
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		Empleado emp = null;
		
		
		try 
			{
			conexion = Pool.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select e.first_name, e.last_name, e.phone_number, e.email from employees e where employee_id = " + id);
			while(rs.next()){
			Empleado emp2 = componerObjeto(rs);
			emp = emp2;
			//System.out.println(emp.getNombre());
			//System.out.println("Empleado Consultado");
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		finally {
			Pool.liberarRecursos(conexion, st, rs);
		}
		return emp;
	}
	
	public String borrarEmpleado(int id) {
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		boolean eliminado = false;
		String resultado = null;
		
		
		try 
			{
			conexion = Pool.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = " + id);
			System.out.println("Empleado eliminado");
			eliminado = true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		finally {
			Pool.liberarRecursos(conexion, st, rs);
		}
		
		if(eliminado == true){
			resultado = "Empleado Eliminado";
		}
		else {
			resultado = "Empleado No Ha Sido Eliminado";
		}
		return resultado;
		
	}

	
	public Empleado componerObjeto(ResultSet rs) throws SQLException{
		
		String name = rs.getString(1);
		String apellido = rs.getString(2);
		String telefono = rs.getString(3);
		String correo = rs.getString(4);
		
		Empleado empleado = new Empleado(name, apellido, telefono, correo);
		
		return empleado;
		
	}
}
