package rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.empleado.EmpleadoService;

import clase.DTO.Empleado;

import com.google.gson.Gson;

@Path("/empleado")
public class EmpleadoRest {
	
	 @DELETE
	 @Path("/borrar/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String borrarEmpleado(@PathParam("id") int id){
		 
		 EmpleadoService emps = new EmpleadoService();
		 String result = emps.borrarEmpleado(id);
		 
		 
			Gson gson = new Gson();
			String resultado = gson.toJson(result);
			//String result = "El Empleado Con El " + id + " Ha Sido Eliminado";
			
			return resultado;
		 
	 }
	 
	 @GET
	 @Path("/consultar/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String consultarEmpleado(@PathParam("id") int id){
		
		 EmpleadoService emps = new EmpleadoService();
		 Empleado empleado = emps.recuperarEmpleado(id);
		 System.out.println("llamado al get");
		 //System.out.println(empleado.getNombre());
		 
			Gson gson = new Gson();
			String resultado = gson.toJson(empleado);
			//String result = "El Empleado Con El " + id + " Ha Sido Eliminado";
			return resultado;
		 
		 
	 }

}
