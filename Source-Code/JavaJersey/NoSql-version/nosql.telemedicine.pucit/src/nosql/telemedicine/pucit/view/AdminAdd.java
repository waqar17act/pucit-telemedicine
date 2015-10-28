package nosql.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import nosql.telemedicine.pucit.methods.Admin;

@Path("/addadmin")
public class AdminAdd {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDoctor(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		Admin admin = new Admin();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			adminFields admFields = mapper.readValue(values,adminFields.class);
			System.out.println("Admin Values are: "+ values);
			int httpCode = admin.insertAdmin(admFields.admid, admFields.username, admFields.password,admFields.description, admFields.persid);
			
			if(httpCode == 200)
			{
				retString = "This Admin is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this Admin").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this Admin").build();
		}

		return Response.ok(retString).build();
	}
	
}

class adminFields
{
	public String admid;
	public String username;
	public String password;
	public String description;
	public String persid;
}