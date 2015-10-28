package com.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.telemedicine.pucit.methods.Doctor;

@Path("/adddoctor")
public class DoctorAdd {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDoctor(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		Doctor doctor = new Doctor();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			doctorFields docFields = mapper.readValue(values,doctorFields.class);
			System.out.println("Doctor Values are: "+ values);
			int httpCode = doctor.insertDoctor(docFields.docid, docFields.affiliation, docFields.username, docFields.password,docFields.description, docFields.dob, docFields.persid);
			
			if(httpCode == 200)
			{
				retString = "This Doctor is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this Doctor").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this Doctor").build();
		}

		return Response.ok(retString).build();
	}
	
}

class doctorFields
{
	public String docid;
	public String affiliation;
	public String username;
	public String password;
	public String description;
	public String dob;
	public String persid;
}