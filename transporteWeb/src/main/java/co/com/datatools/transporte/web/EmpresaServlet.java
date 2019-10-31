package co.com.datatools.transporte.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.datatools.transporte.entidades.TipoIdentificacion;
import co.com.datatools.transporte.entidades.jpersonas.Empresa;
import co.com.datatools.transporte.sesion.MapeadorEmpresa;
import co.com.datatools.transporte.sesion.MapeadorTipoIdentificacion;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.ejb.EJB;

/**
 * Servlet implementation class EmpresaServlet
 */
@WebServlet("/EmpresaServlet")
public class EmpresaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
     
	//EJBs del servlet
    @EJB
    private MapeadorTipoIdentificacion objMapeadorTipoIdentificacion;
    
    @EJB
    private MapeadorEmpresa objMapeadorEmpresa;
	
    public EmpresaServlet() {
        super();
    }
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    																					 IOException {
		//Creación objeto json para cargar la información que entregará el servlet a la vista
		JSONObject jsonSalida = new JSONObject();
		
		try
		{
		//Extraer información del request
		String accion = request.getParameter("accion");
		
			switch (Integer.parseInt(accion))
			{
				case 0:
				    //Opción para cargar los datos iniciales de la pantalla
				    CargarListas(request, response);
				    break;
				case 1:
				    //Opción para consultar una empresa
				    consultarEmpresa(request, response);
				    break;
				case 2:
				    //Opción para guardar(modificar/Crear) una empresa
				    guardarEmpresa(request, response);
				    break;
				case 3:
				    //Opción para eliminar empresa
				    eliminarEmpresa(request, response);
				    break;
			}
		    
		}
		catch (Exception e)
		{
			jsonSalida.put("e", "1");
			jsonSalida.put("msg", e.getMessage());
			EmpresaServlet.responseView(jsonSalida, response);
		}
	}
    
    private void CargarListas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        JSONObject json = new JSONObject();
        JSONArray objJSONArrayTipoId = new JSONArray();
        
    try {
	        //Consultar lista de tipos de identificacion
	        List<TipoIdentificacion> objListTipoIdentificacion = objMapeadorTipoIdentificacion.buscarPorNameQuery("TipoIdentificacion.findAll", null);
	        for (TipoIdentificacion objTipoId : objListTipoIdentificacion){
	            JSONObject objJSONTipoId = new JSONObject();
	            objJSONTipoId.put("Id", objTipoId.getId());
	            objJSONTipoId.put("abreviatura", objTipoId.getAbreviatura());
	            objJSONTipoId.put("descripcion", objTipoId.getDescripcion());
	            objJSONArrayTipoId.add(objJSONTipoId);
	        }
	
	        json.put("tipoIds", objJSONArrayTipoId);
	        json.put("e", "0");
        }
        catch (Exception e) 
        {
            json.put("e", "1");
            json.put("msg", e.getMessage());
        }
        EmpresaServlet.responseView(json, response);
    }
    
    private void consultarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        try
        {
            Map parametrosEmpresa = new HashMap<String, String>();
            parametrosEmpresa.put("tipoId", request.getParameter("tipoId"));
            parametrosEmpresa.put("numeroId", request.getParameter("numeroId"));
        	Empresa objEmpresa = (Empresa) objMapeadorEmpresa.buscarObjetoPorNameQuery("Persona.findByTipoIdNumeroId", parametrosEmpresa);
        	
        	if(objEmpresa != null)
        	{
        		json.put("e","0");
        		json.put("empresa",objEmpresa);
        		json.put("msg","exito");
        	}
        	else
        	{
        		json.put("e","1");
        		json.put("msg","Esta empresa aún no existe");
        	}
        }
        catch (Exception e) 
        {
            json.put("e", "1");
            json.put("msg", e.getMessage());
        }
        EmpresaServlet.responseView(json, response);
    }  
    
    private void guardarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        try
        {
        	//Buscar objeto tipo identificacion
        	Map parametrosEmpresa = new HashMap<String, String>();
            parametrosEmpresa.put("tipoId", request.getParameter("tipoId"));
            TipoIdentificacion objTipoIdentificacion = (TipoIdentificacion) objMapeadorTipoIdentificacion.buscarObjetoPorNameQuery("TipoIdentificacion.findByTipoId",parametrosEmpresa);
            
        	//Buscar datos de empresa
            parametrosEmpresa.put("numeroId", request.getParameter("numeroId"));
        	Empresa objEmpresa = (Empresa) objMapeadorEmpresa.buscarObjetoPorNameQuery("Persona.findEmpresaByTipoIdNumeroId", parametrosEmpresa);
        	
        	if(objEmpresa == null)
        	{
        		objEmpresa = new Empresa();
        	}
        	
        	objEmpresa.setPais(request.getParameter("pais"));
        	objEmpresa.setCiudad(request.getParameter("ciudad"));
        	objEmpresa.setDepartamento(request.getParameter("departamento"));
        	objEmpresa.setDireccion(request.getParameter("direccion"));
        	objEmpresa.setNombre(request.getParameter("nombre"));
        	objEmpresa.setNumero_Identificacion(request.getParameter("numero_Identificacion"));
        	objEmpresa.setTelefono(request.getParameter("telefono"));
        	objEmpresa.setTipoIdentificacion(objTipoIdentificacion);
        	
            json.put("e","0");
            json.put("msg","exito");
        }
        catch (Exception e) 
        {
            json.put("e", "1");
            json.put("msg", e.getMessage());
        }
        EmpresaServlet.responseView(json, response);
    }  
    
    private void eliminarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        try
        {
        	
        	Map parametrosEmpresa = new HashMap<String, String>();
            parametrosEmpresa.put("tipoId", request.getParameter("tipoId"));
            parametrosEmpresa.put("numeroId", request.getParameter("numeroId"));
        	Empresa objEmpresa = (Empresa) objMapeadorEmpresa.buscarObjetoPorNameQuery("Persona.findEmpresaByTipoIdNumeroId", parametrosEmpresa);
        	objMapeadorEmpresa.eliminar(objEmpresa);  
        	
            json.put("e","0");
            json.put("msg","exito");
        }
        catch (Exception e) 
        {
            json.put("e", "1");
            json.put("msg", e.getMessage());
        }
        EmpresaServlet.responseView(json, response);
    }  
    
    
    //Método para cargar la información en el response
    private static void responseView(JSONObject json, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.close();
    }
}
