package com.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.telemedicine.pucit.methods.Person;

@Path("/add")
public class PersonAdd {

	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPerson(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		Person person = new Person();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			personFields persFields = mapper.readValue(values,personFields.class);
			System.out.println("Person Values are: "+ values);
			int httpCode = person.insertPerson(persFields.persid, persFields.name, persFields.gender, persFields.role, persFields.contact);
			
			if(httpCode == 200)
			{
				retString = "This person is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this person").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this person").build();
		}

		return Response.ok(retString).build();
	}
	
}

class personFields
{
	public String persid;
	public String name;
	public String gender;
	public String role;
	public String contact;
}