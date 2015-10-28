package nosql.telemedicine.pucit.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import nosql.telemedicine.pucit.methods.ToJson;
import nosql.telemedicine.pucit.methods.DaoClass;
import nosql.telemedicine.pucit.methods.empClass;


@Path("/getFifty")
public class FetchSome {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnOne(
			@QueryParam("name") String name)
			throws Exception {
	
		String returnString = null;
		//JSONArray json = new JSONArray();
		String json = "";
		try 
		{
			if(name == null) {
				return Response.status(400).entity("Error: please specify employee Name for this search").build();
			}
			
			empClass dao = new empClass();
			
			json = dao.queryReturnEmployee(name);
			returnString = json.toString();
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
	
		return Response.ok(returnString).build();
	}

}
