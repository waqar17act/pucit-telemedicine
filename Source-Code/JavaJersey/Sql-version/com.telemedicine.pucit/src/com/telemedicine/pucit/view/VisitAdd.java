package com.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.telemedicine.pucit.methods.Visit;

@Path("/addvisit")
public class VisitAdd {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertVisit(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		Visit visit = new Visit();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			visitFields visitFields = mapper.readValue(values,visitFields.class);
			System.out.println("Visit Values are: "+ values);
			int httpCode = visit.insertVisit(visitFields.visitid, visitFields.visitime, visitFields.visitype,visitFields.visitloc, visitFields.patid);
			
			if(httpCode == 200)
			{
				retString = "This Visit is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this Visit").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this Visit").build();
		}

		return Response.ok(retString).build();
	}
	
}

class visitFields
{
	public String visitid;
	public String visitime;
	public String visitype;
	public String visitloc;
	public String patid;
}