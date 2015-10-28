package nosql.telemedicine.pucit.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import nosql.telemedicine.pucit.methods.ToJson;
import nosql.telemedicine.pucit.methods.DaoClass;
import nosql.telemedicine.pucit.methods.empClass;

@Path("/getallperson")
public class FetchAll {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String retStatus() throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		String retString = null;
		
		try
		{

			empClass dao = new empClass();
			
			retString = dao.returnAllEmployees();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
		if(con !=null)
		{
			con.close();
		}	
		}
		return retString;
		
	}
	
	
}
