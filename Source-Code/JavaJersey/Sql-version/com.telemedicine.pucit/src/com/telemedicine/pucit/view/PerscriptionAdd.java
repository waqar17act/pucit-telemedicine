package com.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.telemedicine.pucit.methods.Perscription;

@Path("/addperscription")
public class PerscriptionAdd {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPerscription(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		Perscription perscription = new Perscription();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			perscriptionFields perscptFields = mapper.readValue(values,perscriptionFields.class);
			System.out.println("Perscription Values are: "+ values);
			int httpCode = perscription.insertPerscription(perscptFields.perscriptionid, perscptFields.visitid, perscptFields.docid,perscptFields.feedback);
			
			if(httpCode == 200)
			{
				retString = "This Perscription is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this Perscription").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this Perscription").build();
		}

		return Response.ok(retString).build();
	}
	
}

class perscriptionFields
{
	public String visitid;
	public String perscriptionid;
	public String docid;
	public String feedback;
	
}